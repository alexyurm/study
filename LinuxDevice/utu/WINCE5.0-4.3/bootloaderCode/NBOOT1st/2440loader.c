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
/************************************************
 * NAME    : 2440loader.C			*
 * DESC    : 					*
 * History : 2002.02.25 ver 0.0			*
 *         : Modified for SMDK2440 PPC2003 BSP ( November 29, 2003 ) -> HMSEO

************************************************/

#include <stdlib.h>
#include <string.h>
#include "option.h"
#include "def.h"
#include "2440addr.h"
#include "2440lib.h"
#include "2440slib.h"
#include "2440addr.h"
#include "nand.h"
#define SIGN_ON "\nWinCE NAND Boot v1.00\n" __DATE__ " " __TIME__ "\n"

// HMSEO : Please check UUID memory location from inc\drv_glob.h file.
unsigned char * pbUUID = ((unsigned char *) (0x30030000 + 0x4608));

//
// Globals
//
DWORD JumpAddr;

DWORD ReadImageFromNand(DWORD dwEntry, DWORD dwSig);

/// Eboot
void loadboot(void)
{

	DWORD dwSector, dwLength;         // Start Sector & Length
    DWORD dwRAM, i;
    
	dwSector=32*2;//��block:2 page:0��ʼ���boot2
	dwLength=32*10;//һ��2��block;block2-7;96K����;���boot2�ļ�����96K����Ҫ�޸ĸô���
	dwRAM=0x30000000;//boot2 load Start address;��NAND FLASH���ݿ�����0X30000000
		
		while (dwLength)		
		{

            FMD_ReadSector(dwSector,(LPBYTE)dwRAM,NULL, 1);
    		dwSector++;
    		dwLength--;
            dwRAM += SECTOR_SIZE;//�ó���Ŀǰ����У��
           
        }
        
	Launch(0x30000000);//��ת��boot2������boot2�����򲻻᷵��
}

void Main(void)
{
    DWORD 	err; //, t0 = 0;
	unsigned char i,j;
    
    //	By default, we launch image CE image. If you want to launch
    //	Eboot, you need to send char"e"or"E" to UART1 when it boots.

    DWORD	dwEntry = 1; 
    MMU_EnableICache();
    Uart_Init();
    Uart_SendString(SIGN_ON);
    NF_Init();
    Delay(1000);
	loadboot();   //will not return
	
	//	Check to see if the apps buttons are pressed and take
	//	the corresponding actions if they do.
    //  Change this for the final shipping device, so that user
    //  doesn't have to press button to start!
    //
    /*
    Uart_SendString("\n'E' for Eboot\t'R' for Pic Boot\n");
	for(j=0;j<10;j++)
	{
		 Uart_SendString("*");
		 Delay100us(9000);
		 if(rUTRSTAT1 & 0x1) //UART ����������
		    
		 {	
			 i=RdURXH1();
			 if((i='E')||(i='e'))
			 {
				 Uart_SendString("\nEboot\n");
				 loadboot();	
			 }
			 if((i='R')||(i='r'))
			 {
				 Uart_SendString("\nPicture Boot\n");
				 loadboot2();	
			 }
		 }
	}
    
    Uart_SendString("\ndwEntry is ");
    Uart_SendDWORD(dwEntry, TRUE);

    // Hardcoded to fetch TOC descriptor dwEntry
    err = ReadImageFromNand(dwEntry,0);


    if (ERR_SUCCESS == err) {
        Launch(JumpAddr);
        err = ERR_JUMP_FAILED;
    }

    Uart_SendString("\nBoot ERROR:");
    Uart_SendDWORD(err, TRUE);
    while (1);
    */
}

/*
// -----------------------------------------------------------------------------
//  ReadImageFromNand:
//      Reads nk.nb0 off NAND
//      Returns ERR_Xxx
// -----------------------------------------------------------------------------
TOC toc; // made global because it's too big for our tiny stack

DWORD
ReadImageFromNand(DWORD dwEntry, DWORD dwSig)
{
    DWORD dwSectorsNeeded;
    DWORD dwSector, dwLength;         // Start Sector & Length
    DWORD dwRAM, i;

    if ( !FMD_ReadSector(TOC_SECTOR,
                        (LPBYTE)&toc,
                        NULL, 1) )
    {
        Uart_SendString("ERR_DISK_OP_FAIL1\n");
        return ERR_DISK_OP_FAIL1;
    }

    if ( !VALID_TOC(&toc) ) {
        Uart_SendString("ERR_INVALID_TOC: ");
        Uart_SendDWORD(toc.dwSignature, TRUE);
        return ERR_INVALID_TOC;
    }

    if ( !(toc.id[dwEntry].dwImageType & IMAGE_TYPE_RAMIMAGE) ) {
        Uart_SendString("ERR_INVALID_FILE_TYPE: ");
        Uart_SendDWORD(toc.id[dwEntry].dwImageType, TRUE);
        return ERR_INVALID_FILE_TYPE;
    }

// ??
//    if ( !(toc.id[dwEntry].dwImageType & IMAGE_TYPE_BINFS) ) {
//        dwSectorsNeeded = toc.id[dwEntry].dwTtlSectors;
//    } else {
        dwSectorsNeeded = toc.id[dwEntry].dwTtlSectors;		// xipkernel size = 0x9B4
//    }
	
	Uart_SendString("Sector addr on NAND: ");
	Uart_SendDWORD(toc.id[dwEntry].sgList[0].dwSector, TRUE);
    Uart_SendString("TotalSector: ");
    Uart_SendDWORD(dwSectorsNeeded, TRUE);

    dwRAM    = VIRTUAL_TO_PHYSICAL(toc.id[dwEntry].dwLoadAddress);

    JumpAddr = toc.id[dwEntry].dwJumpAddress ? VIRTUAL_TO_PHYSICAL(toc.id[dwEntry].dwJumpAddress) :
                                               VIRTUAL_TO_PHYSICAL(toc.id[dwEntry].dwLoadAddress);
                                               
    //
    // Load the disk image directly into RAM
    // BUGBUG: recover from read failures
    //
	Uart_SendString("Reading Kernel Image from NAND\r\n");
    i = 0;
	while (dwSectorsNeeded && i < MAX_SG_SECTORS)
	{
        dwSector = toc.id[dwEntry].sgList[i].dwSector;
        dwLength = toc.id[dwEntry].sgList[i].dwLength;

		Uart_SendString("    dwSector: ");
		Uart_SendDWORD(dwSector, TRUE);
		Uart_SendString("    dwLength: ");
		Uart_SendDWORD(dwLength, TRUE);
		Uart_SendString("    dwRAM: ");
		Uart_SendDWORD(dwRAM, TRUE);

        // read each sg segment
        while (dwLength) {
            if ( !FMD_ReadSector(dwSector,
                                (LPBYTE)dwRAM,
                                NULL, 1) )
            {
                Uart_SendString("ERR_DISK_OP_FAIL2: ");
                Uart_SendDWORD(dwSector, TRUE);

	    		dwSector++;
				continue;

//                return ERR_DISK_OP_FAIL2;
            }
            
    		dwSector++;
    		dwLength--;
            dwRAM += SECTOR_SIZE;
        }

        dwSectorsNeeded -= toc.id[dwEntry].sgList[i].dwLength;
        i++;
    }

    //  We only do this if the dwRAM is not zero (The default tocblock1 
    //  set the dwRAM to be 0)
    if (toc.chainInfo.dwLoadAddress == 0) {
        return ERR_SUCCESS;
    }

    // Load the Chain.bin stored on NAND to the SDRAM
//	if ( toc.id[dwEntry].dwImageType == 6 )		// For WinCE 4.2 Image
//	if ( 1 )		// For WinCE 4.2 Image
//	{
//		dwRAM = VIRTUAL_TO_PHYSICAL(toc.id[dwEntry].dwLoadAddress);
//		dwSectorsNeeded = toc.id[dwEntry].sgList->dwLength;
//		dwSector = toc.id[dwEntry].sgList->dwSector;
//	}
//	else
	{
		dwRAM = VIRTUAL_TO_PHYSICAL(toc.chainInfo.dwLoadAddress);		// 0x303c0000
		dwSectorsNeeded = toc.chainInfo.dwLength;						// 0x20
		dwSector = toc.chainInfo.dwFlashAddress;						// 0x103c0

//		dwSectorsNeeded = 0x20;
//		dwSector = 0x104C0;
	}

#if 0
	// Copy UUID to SDRAM drv_glob area from NAND
    Uart_SendString("Reading UUID from NAND : ");

	for ( i = 0; i < 8; i++ )
	{
		*pbUUID = toc.udid[i];
//		Uart_SendByte(*pbUUID);
//		Uart_SendDWORD(*pbUUID, FALSE);
//	    Uart_SendString(": ");
		pbUUID++;
	}
    Uart_SendString("\r\n");
#endif

//	Uart_SendString("Reading Chain from NAND\r\n");
//	Uart_SendString("LoadAddr: ");
//	Uart_SendDWORD(dwRAM, TRUE);
//	Uart_SendString("NAND SectorAddr: ");
//	Uart_SendDWORD(dwSector, TRUE);
//	Uart_SendString("Length: ");
//	Uart_SendDWORD(dwSectorsNeeded, TRUE);

#if 1
    while(dwSectorsNeeded) {
        
        if (!FMD_ReadSector(dwSector, 
                            (LPBYTE) dwRAM,
                            NULL, 1) ) {
            Uart_SendString("Failed reading Chain.bin:");
            Uart_SendDWORD(dwSector, TRUE);

			dwSector++;
			continue;
        }

        dwSector++;
        dwSectorsNeeded--;
        dwRAM += SECTOR_SIZE;
    }
#endif

//    Uart_SendString("RETURN SUCCESS");
//    Uart_SendString("\r\n                     ");

	return ERR_SUCCESS;
}

///  Picture Boot
void loadboot2(void)
{

	DWORD dwSector, dwLength;         // Start Sector & Length
    DWORD dwRAM, i;
    
	dwSector=32*8;//��block:8 page:0��ʼ��� pic boot
	dwLength=32*2;//һ��2��block;block8-9;32K����;
	dwRAM=0x30000000;//pic boot load Start address;��NAND FLASH���ݿ�����0X30038000
		
while (dwLength) 
		
		{

            FMD_ReadSector(dwSector,(LPBYTE)dwRAM,NULL, 1);
    		dwSector++;
    		dwLength--;
            dwRAM += SECTOR_SIZE;//�ó���Ŀǰ����У��
            
        }
        
	Launch(0x30000000);//��ת��pic boot������pic boot�����򲻻᷵��
}


/// Eboot
void loadboot(void)
{

	DWORD dwSector, dwLength;         // Start Sector & Length
    DWORD dwRAM, i;
    
	dwSector=64;//��block:2 page:0��ʼ���eboot
	dwLength=192;//һ��6��block;block2-7;96K����;���eboot�ļ�����96K����Ҫ�޸ĸô���
	dwRAM=0x30038000;//eboot load Start address;��NAND FLASH���ݿ�����0X30038000
		
while (dwLength) 
		
		{

            FMD_ReadSector(dwSector,(LPBYTE)dwRAM,NULL, 1);
    		dwSector++;
    		dwLength--;
            dwRAM += SECTOR_SIZE;//�ó���Ŀǰ����У��
           
        }
        
	Launch(0x30038000);//��ת��eboot������eboot�����򲻻᷵��
}
*/