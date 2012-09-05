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
#ifndef __NAND_H__
#define __NAND_H__

#define NUM_BLOCKS                  0x1000       //  64 MB Smartmedia card.
#define SECTOR_SIZE                 512
#define SPARE_SIZE                  16
#define PAGES_PER_BLOCK             32

//  For flash chip that is bigger than 32 MB, we need to have 4 step address
//  
#define NFCONF_INIT                 0xF830  // 512-byte 4 Step Address
#define NEED_EXT_ADDR               1
//#define NFCONF_INIT                 0xA830  // 256-byte 4 Step Address
//#define NEED_EXT_ADDR               0

//#define NFCONF_INIT                0xF840

//  NAND Flash Command. This appears to be generic across all NAND flash chips
#define CMD_READ                    0x00        //  Read
#define CMD_READ1                   0x01        //  Read1
#define CMD_READ2                   0x50        //  Read2
#define CMD_READID                  0x90        //  ReadID
#define CMD_WRITE                   0x80        //  Write phase 1
#define CMD_WRITE2                  0x10        //  Write phase 2
#define CMD_ERASE                   0x60        //  Erase phase 1
#define CMD_ERASE2                  0xd0        //  Erase phase 2
#define CMD_STATUS                  0x70        //  Status read
#define CMD_RESET                   0xff        //  Reset

//  Status bit pattern
#define STATUS_READY                0x40        //  Ready
#define STATUS_ERROR                0x01        //  Error

typedef DWORD  SECTOR_ADDR;
typedef PDWORD PSECTOR_ADDR;

typedef struct _SectorInfo
{
	DWORD dwReserved1;				// Reserved - used by FAL
	BYTE  bOEMReserved;				// For use by OEM
	BYTE  bBadBlock;				// Indicates if block is BAD
	WORD  wReserved2;				// Reserved - used by FAL
	
}SectorInfo, *PSectorInfo;

    
#define SECTOR_TO_BLOCK(sector) ((sector) >> 5 )
#define BLOCK_TO_SECTOR(block)  ((block)  << 5 )

//
// ERROR_Xxx
//
#define ERR_SUCCESS               0
#define ERR_DISK_OP_FAIL1         1
#define ERR_DISK_OP_FAIL2         2
#define ERR_INVALID_BOOT_SECTOR   3
#define ERR_INVALID_LOAD_ADDR     4
#define ERR_GEN_FAILURE           5
#define ERR_INVALID_PARAMETER     6
#define ERR_JUMP_FAILED           7
#define ERR_INVALID_TOC           8
#define ERR_INVALID_FILE_TYPE     9

BOOL 
FMD_ReadSector(
    SECTOR_ADDR startSectorAddr, 
    LPBYTE pSectorBuff,
    PSectorInfo pSectorInfoBuff, 
    DWORD dwNumSectors
    );

#ifdef READ_SECTOR_INFO
void 
NAND_ReadSectorInfo(
    SECTOR_ADDR sectorAddr, 
    PSectorInfo pInfo
    );
#endif


void NF_Reset(void);
void NF_Init(void);

#define NF_READID   1
    
#ifdef NF_READID
void NF_ReadID(void);
#else
#define NF_ReadID()
#endif

typedef struct ROMHDR {
    ULONG   dllfirst;               // first DLL address
    ULONG   dlllast;                // last DLL address
    ULONG   physfirst;              // first physical address
    ULONG   physlast;               // highest physical address
    ULONG   nummods;                // number of TOCentry's
    ULONG   ulRAMStart;             // start of RAM
    ULONG   ulRAMFree;              // start of RAM free space
    ULONG   ulRAMEnd;               // end of RAM
    ULONG   ulCopyEntries;          // number of copy section entries
    ULONG   ulCopyOffset;           // offset to copy section
    ULONG   ulProfileLen;           // length of PROFentries RAM 
    ULONG   ulProfileOffset;        // offset to PROFentries
    ULONG   numfiles;               // number of FILES
    ULONG   ulKernelFlags;          // optional kernel flags from ROMFLAGS .bib config option
    ULONG   ulFSRamPercent;         // Percentage of RAM used for filesystem 
                                        // from FSRAMPERCENT .bib config option
                                        // byte 0 = #4K chunks/Mbyte of RAM for filesystem 0-2Mbytes 0-255
                                        // byte 1 = #4K chunks/Mbyte of RAM for filesystem 2-4Mbytes 0-255
                                        // byte 2 = #4K chunks/Mbyte of RAM for filesystem 4-6Mbytes 0-255
                                        // byte 3 = #4K chunks/Mbyte of RAM for filesystem > 6Mbytes 0-255

    ULONG   ulDrivglobStart;        // device driver global starting address
    ULONG   ulDrivglobLen;          // device driver global length
    USHORT  usCPUType;              // CPU (machine) Type
    USHORT  usMiscFlags;            // Miscellaneous flags
    PVOID   pExtensions;            // pointer to ROM Header extensions
    ULONG   ulTrackingStart;        // tracking memory starting address
    ULONG   ulTrackingLen;          // tracking memory ending address
} ROMHDR, *PROMHDR;

#endif /*__NAND_H__*/
