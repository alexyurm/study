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
//===================================================================
// File Name : 2440lib.h
// Function  : S3C2440 
// Program   : Shin, On Pil (SOP)
// Date      : February 26, 2002
// Version   : 1.0
// History
//   0.0 : Programming start (February 20,2002) -> SOP
//	 1.0 : Modified for SMDK2440 PPC2003 BSP ( November 29, 2003 ) -> HMSEO
//===================================================================

#ifndef __2440lib_h__
#define __2440lib_h__

#ifdef __cplusplus
extern "C" {
#endif

#ifdef __ARMCC_VERSION     // ARM Developer Suite
//#define DebugOut Uart_Printf
#define RETAILMSG(cond, printf_exp)
#define ASSERT
#define NULL 0
#define min(x1,x2) ((x1<x2)? x1:x2)
#define max(x1,x2) ((x1>x2)? x1:x2)
#endif

#define ONESEC0 (62500)	             //16us resolution, max 1.04 sec
#define ONESEC1 (31250)	             //32us resolution, max 2.09 sec
#define ONESEC2 (15625)	             //64us resolution, max 4.19 sec
#define ONESEC3 (7812)	             //128us resolution, max 8.38 sec
#define ONESEC4 (PCLK/128/(0xff+1))  //@60Mhz, 128*4us resolution, max 32.53 sec


#define EnterPWDN(clkcon) ((void (*)(int))0x20)(clkcon)


// 2440lib.c
void Delay(int time);              //Watchdog Timer is used.

void Port_Init(void);
void Uart_Select(int ch);
void Uart_Init(void);

#if 0
void Uart_TxEmpty(int ch);
char Uart_Getch(void);
char Uart_GetKey(void);
int  Uart_GetIntNum(void);
#endif
void Uart_SendByte(int data);
//void Uart_Printf(char *fmt,...);
void Uart_SendDWORD(DWORD d, BOOL cr);
void Uart_SendString(char *pt);

char *hex2char(unsigned int val);

void Timer_Start(int divider);    //Watchdog Timer is used.
int  Timer_Stop(void);            //Watchdog Timer is used.

#ifdef LED
void Led_Display(int data);
#else
#define Led_Display(d)
#endif

void ChangeMPllValue(int m,int p,int s);
void ChangeClockDivider(int hdivn,int pdivn);
void ChangeUPllValue(int m,int p,int s);

#ifdef __cplusplus
}
#endif

#endif  //__2440lib_h__
