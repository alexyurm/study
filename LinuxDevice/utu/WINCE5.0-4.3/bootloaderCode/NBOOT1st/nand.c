//
// Copyright (c) Microsoft Corporation.  All rights reserved.
//
//
// Use of this source code is subject to the terms of the Microsoft end-user
// license agreement (EULA) under which you licensed this SOFTWARE PRODUCT.
// If you did not accept the terms of the EULA, you are not authorized to use
// this source code. For a copy of the EULA, please see the LICENSE.RTF on your
// install media.
//
/*++
THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF
ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
PARTICULAR PURPOSE.
--*/
#include <string.h>

#include "def.h"
#include "option.h"
#include "2440addr.h"
#include "2440lib.h"
#include "nand.h"

void __RdPage512(UCHAR *bufPt); 
void __RdPage256(UCHAR *bufPt); 

//  Status bit pattern
#define STATUS_READY                0x40          
#define STATUS_ERROR                0x01

#define NF_CMD(cmd)	    {rNFCMD  = (cmd); }
#define NF_ADDR(addr)	{rNFADDR = (addr); }	
#define NF_nFCE_L()	    {rNFCONT &= ~(1<<1); }
#define NF_nFCE_H()	    {rNFCONT |= (1<<1); }
#define NF_RSTECC()	    {rNFCONT |= (1<<4); }
#define NF_RDDATA() 	(rNFDATA)
#define NF_WRDATA(data) {rNFDATA = (data); }
#define NF_WAITRB()     {while(!(rNFSTAT&(1<<0)));} 
#define NF_CLEAR_RB()	{rNFSTAT |= (1<<2); }
#define NF_DETECT_RB()	{while(!(rNFSTAT&(1<<2)));}
#define NF_MECC_UnLock()	{rNFCONT &= ~(1<<5);}
#define NF_MECC_Lock()		{rNFCONT |= (1<<5);}

#define     pNFCONF     rNFCONF 
#define     pNFCMD      rNFCMD  
#define     pNFADDR     rNFADDR 
#define     pNFDATA     rNFDATA 
#define     pNFSTAT     rNFSTAT 
#define     pNFECC      rNFECC0  

#define NF_CE_L()     NF_nFCE_L()
#define NF_CE_H()     NF_nFCE_H()
#define NF_DATA_R()   rNFDATA
#define NF_ECC()      rNFECC0

typedef union _ECCRegVal
{
	DWORD	dwECCVal;
	BYTE	bECCBuf[4];
} ECCRegVal;

//
//  Reset the chip
//
void NF_Reset()
{                       
    NF_CE_L();
	NF_CLEAR_RB();
    NF_CMD(CMD_RESET);  
    NF_CE_H();          
}

// HCLK=133Mhz
#define TACLS		0
#define TWRPH0		6
#define TWRPH1		0

void NF_Init(void)
{
    rNFCONF = (TACLS<<12)|(TWRPH0<<8)|(TWRPH1<<4)|(0<<0);
    rNFCONT = (0<<13)|(0<<12)|(0<<10)|(0<<9)|(0<<8)|(0<<6)|(0<<5)|(1<<4)|(1<<1)|(1<<0);
    rNFSTAT = 0;
}

#ifdef NF_READID
void NF_ReadID()
{
    USHORT  wData1, wData2;

    //  First we enable chip select
    NF_CE_L();
	NF_CLEAR_RB();

    //  Issue commands to the controller
    NF_CMD(CMD_READID);
    NF_ADDR(0x00);

    NF_DETECT_RB();

    wData1 = (BYTE)NF_DATA_R();
    wData2 = (BYTE)NF_DATA_R();

    NF_CE_H();

    Uart_SendString("Nand Mfg: ");
    Uart_SendDWORD((DWORD)wData1, TRUE);
    Uart_SendString("Nand Dev: ");
    Uart_SendDWORD((DWORD)wData2, TRUE);
}
#endif

#ifdef READ_SECTOR_INFO
/* 
 *  NAND_ReadSectorInfo
 *
 *  Read SectorInfo out of the spare area. The current implementation only handles
 *  one sector at a time.
 */
void 
NAND_ReadSectorInfo(
    SECTOR_ADDR sectorAddr, 
    PSectorInfo pInfo
    )
{
    //  Chip enable
    NF_CE_L();
	NF_CLEAR_RB();

    //  Write the command
    NF_CMD(CMD_READ2);

    //  Write the address
    NF_ADDR(0x00);
    NF_ADDR(sectorAddr & 0xff);
    NF_ADDR((sectorAddr >> 8) & 0xff);
    
    if (NEED_EXT_ADDR) {
        NF_ADDR((sectorAddr >> 16) & 0xff);
    }

    //  Wait for the Ready bit
    NF_DETECT_RB();

    //  Read the SectorInfo data (we only need to read first 8 bytes) 
    pInfo->dwReserved1  = (DWORD) ((BYTE) NF_DATA_R()) << 24;
    pInfo->dwReserved1 |= (DWORD) ((BYTE) NF_DATA_R()) << 16;
    pInfo->dwReserved1 |= (DWORD) ((BYTE) NF_DATA_R()) << 8;
    pInfo->dwReserved1 |= (DWORD) ((BYTE) NF_DATA_R());

    //  OEM byte
    pInfo->bOEMReserved = (BYTE) NF_DATA_R();

    //  Read the bad block mark
    pInfo->bBadBlock = (BYTE) NF_DATA_R();
    
    //  Second reserved field (WORD)
    pInfo->wReserved2 = ((BYTE) NF_DATA_R() << 8);
    pInfo->wReserved2 |= ((BYTE) NF_DATA_R());

    NF_CE_H();
}
#endif


//  FMD_ReadSector
//
//  Read the content of the sector.
//
//  startSectorAddr: Starting page address
//  pSectorBuff  : Buffer for the data portion
//  pSectorInfoBuff: Buffer for Sector Info structure
//  dwNumSectors : Number of sectors
//
BOOL 
FMD_ReadSector(
    SECTOR_ADDR startSectorAddr, 
    LPBYTE pSectorBuff,
    PSectorInfo pSectorInfoBuff, 
    DWORD dwNumSectors
    )
{
    DWORD   i, r = 0;
    BYTE   ecc0,ecc1,ecc2;
    BOOL   rc = TRUE;
    ECCRegVal eccRegVal;

    //  BUGBUGBUG: I need to come back to support dwNumSectors > 1
    //
    //  Sanity check
    if (!pSectorBuff && !pSectorInfoBuff || dwNumSectors > 1 || !pSectorBuff) {
//        Uart_SendString("ERROR_INVALID_PARAMETER\n");
        return FALSE;
    }

//    Uart_SendString("R: ");
//    Uart_SendDWORD(startSectorAddr, TRUE);

_retry:
    //  Initialize ECC register
    NF_RSTECC();
	NF_MECC_UnLock();

    //  Enable the chip
    NF_nFCE_L();   
	NF_CLEAR_RB();

    // Issue Read command
    NF_CMD(CMD_READ);

    //  Set up address
    NF_ADDR(0x00);
    NF_ADDR((startSectorAddr) & 0xff);
    NF_ADDR((startSectorAddr >> 8) & 0xff);
    if (NEED_EXT_ADDR) {
        NF_ADDR((startSectorAddr >> 16) & 0xff);
    }

    for (i = 0; i < 5; i++);   // wait tWB(100ns)

    NF_DETECT_RB();        // wait tR(max 12us)

    // read the data
    __RdPage512(pSectorBuff);
//    __RdPage256(pSectorBuff);
	NF_MECC_Lock();

//	for ( i = 0; i < 512; i++ )
//		Uart_SendByte(*(pSectorBuff+i));


	//	Read the ECC from ECC Register
	eccRegVal.dwECCVal = NF_ECC();

	//	Skip first 8 bytes
	for(i=0; i<8; i++){
		ecc0 = (BYTE)NF_DATA_R();
	}
	
	ecc0 = (BYTE)NF_DATA_R();
	ecc1 = (BYTE)NF_DATA_R();
	ecc2 = (BYTE)NF_DATA_R();
	
    NF_nFCE_H();

    if ( !rc && r++ < 3 ) {
        Uart_SendString("FMD_ReadSector: ");
        Uart_SendDWORD(startSectorAddr, TRUE);

        NF_Reset();
        
        for (i = 0; i < 5; i++);   // delay

        rc = TRUE;
        
        goto _retry;
    }
    
    if ( startSectorAddr < 0x1c0 ) // NO ECC Check about EBOOT
    {
    	rc = TRUE;
    }
    else
    {
	if(	ecc0 != eccRegVal.bECCBuf[0] ||
		ecc0 != eccRegVal.bECCBuf[0] ||
		ecc0 != eccRegVal.bECCBuf[0] )  {
//		Uart_SendString("ECC mismatch for Sector: ");
//		Uart_SendDWORD(startSectorAddr, TRUE);
		rc = FALSE;
	}
    }

    return rc;
}


