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
//====================================================================
// File Name : option.h
// Function  : S3C2440 
// Program   : Shin, On Pil (SOP)
// Date      : March 4, 2002
// Version   : 1.0
// History
//   0.0 : Programming start (February 20,2002) -> SOP
//	 1.0 : Modified for SMDK2440 PPC2003 BSP ( November 29, 2003 ) -> HMSEO
//====================================================================

#ifndef __OPTION_H__
#define __OPTION_H__

#define FCLK 405000000
#define HCLK FCLK/3
#define PCLK HCLK/2

// BUSWIDTH : 16,32
#define BUSWIDTH    (32)

//64MB
// 0x30000000 ~ 0x30ffffff : Download Area (16MB) Cacheable
// 0x31000000 ~ 0x31feffff : Non-Cacheable Area
// 0x31ff0000 ~ 0x31ff47ff : Heap & RW Area
// 0x31ff4800 ~ 0x31ff7fff : FIQ ~ User Stack Area
// 0x31ff8000 ~ 0x31fffeff : Not Useed Area
// 0x31ffff00 ~ 0x31ffffff : Exception & ISR Vector Table

#define _RAM_STARTADDRESS 	0x30000000
//#define _RAM_STARTADDRESS 	0x30200000
#define _ISR_STARTADDRESS 	0x31ffff00     
#define _MMUTT_STARTADDRESS	0x31ff8000
#define _STACK_BASEADDRESS  0x31ff8000
#define HEAPEND		  		0x31ff0000
#define _RAM_ENDADDRESS 	0x31ffffff

#define VA_BASE             0x8C000000  // defined in OEMAddressTable
#define VIRTUAL_TO_PHYSICAL(va) ((va) - VA_BASE + _RAM_STARTADDRESS)

//If you use ADS1.x, please define ADS10
// #define ADS10 FALSE

#define ADS10 TRUE

// note: makefile,option.a should be changed

#endif /*__OPTION_H__*/
