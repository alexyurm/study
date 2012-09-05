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
// File Name : 2440lib.c
// Function  : S3C2440 PLL,Uart, LED, Port Init
// Program   : Shin, On Pil (SOP)
// Date      : March 20, 2002
// Version   : 1.0
// History
//   0.0 : Programming start (February 20,2002) -> SOP
//	 1.0 : Modified for SMDK2440 PPC2003 BSP ( November 29, 2003 ) -> HMSEO

//===================================================================

#include "def.h"
#include "option.h"
#include "2440addr.h"
#include "2440lib.h"
#include "2440slib.h" 

#include <stdarg.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

void MMU_SetAsyncBusMode(void);

#define BAUD_RATE       115200

#define WRITE_UART      WrUTXH0
#define UART_STATUS     rUTRSTAT0

//***************************[ PORTS ]****************************************************
void Port_Init(void)
{
    //CAUTION:Follow the configuration order for setting the ports. 
    // 1) setting value(GPnDAT) 
    // 2) setting control register  (GPnCON)
    // 3) configure pull-up resistor(GPnUP)  

    //32bit data bus configuration  
    //*** PORT A GROUP
    //Ports  : GPA22 GPA21  GPA20 GPA19 GPA18 GPA17 GPA16 GPA15 GPA14 GPA13 GPA12  
    //Signal : nFCE nRSTOUT nFRE   nFWE  ALE   CLE  nGCS5 nGCS4 nGCS3 nGCS2 nGCS1 
    //Binary :  1     1      1  , 1   1   1    1   ,  1     1     1     1
    //Ports  : GPA11   GPA10  GPA9   GPA8   GPA7   GPA6   GPA5   GPA4   GPA3   GPA2   GPA1  GPA0
    //Signal : ADDR26 ADDR25 ADDR24 ADDR23 ADDR22 ADDR21 ADDR20 ADDR19 ADDR18 ADDR17 ADDR16 ADDR0 
    //Binary :  1       1      1      1   , 1       1      1      1   ,  1       1     1      1         
    rGPACON = 0x7fffff; 

    //**** PORT B GROUP
    //Ports  : GPB10    GPB9    GPB8    GPB7    GPB6     GPB5    GPB4   GPB3   GPB2     GPB1      GPB0
    //Signal : nXDREQ0 nXDACK0 nXDREQ1 nXDACK1 nSS_KBD nDIS_OFF L3CLOCK L3DATA L3MODE nIrDATXDEN Keyboard
    //Setting: INPUT  OUTPUT   INPUT  OUTPUT   INPUT   OUTPUT   OUTPUT OUTPUT OUTPUT   OUTPUT    OUTPUT 
    //Binary :   00  ,  01       00  ,   01      00   ,  01       01  ,   01     01   ,  01        01  
    rGPBCON = 0x044555;
    rGPBUP  = 0x7ff;     // The pull up function is disabled GPB[10:0]

    //*** PORT C GROUP
    //Ports  : GPC15 GPC14 GPC13 GPC12 GPC11 GPC10 GPC9 GPC8  GPC7   GPC6   GPC5 GPC4 GPC3  GPC2  GPC1 GPC0
    //Signal : VD7   VD6   VD5   VD4   VD3   VD2   VD1  VD0 LCDVF2 LCDVF1 LCDVF0 VM VFRAME VLINE VCLK LEND  
    //Binary :  10   10  , 10    10  , 10    10  , 10   10  , 10     10  ,  10   10 , 10     10 , 10   10
    rGPCCON = 0xaaaaaaa9;       
    rGPCDAT |=0x1;//GPC0=1;
    rGPCUP  = 0xffff;     // The pull up function is disabled GPC[15:0] 

    //*** PORT D GROUP
    //Ports  : GPD15 GPD14 GPD13 GPD12 GPD11 GPD10 GPD9 GPD8 GPD7 GPD6 GPD5 GPD4 GPD3 GPD2 GPD1 GPD0
    //Signal : VD23  VD22  VD21  VD20  VD19  VD18  VD17 VD16 VD15 VD14 VD13 VD12 VD11 VD10 VD9  VD8
    //Binary : 10    10  , 10    10  , 10    10  , 10   10 , 10   10 , 10   10 , 10   10 ,10   10
    //rGPDCON  = 0xaaa9aaaa;   // GPD8: USB_CHG_DIS: Output
    //rGPDUP   = 0xffff;       // The pull up function is disabled GPD[15:0]
    //rGPDDAT &= ~(0x1 << 8);  // Set USB_CHG_DIS Low

    //Ports  : GPD15        GPD14   GPD13   GPD12   GPD11   GPD10   GPD9    GPD8         
    //Signal : USB_PULLUP   NC      NC      NC      NC      nFWP    SELI    USB_CHG_DIS  
    //CON    : 00           00      00      00      00      01      01      01
    //DAT    : 0            0       0       0       0       1       0(100ma)0 (low)
    //UP     : 1            0       0       0       0       1       1       1

    //Ports  : GPD7         GPD6    GPD5    GPD4    GPD3    GPD2    GPD1    GPD0
    //Signal : NC           EL_EN   KEEPACT IR_SD   PWREN2  NC      TXON    NC
    //CON    : 00           01      01      01      01      00      01      00
    //DAT    : 0            0(on)   1(high) 1(on)   0(low)  0       0(low)  0
    //UP     : 0            1       1       1       1       0       1       0
    rGPDCON  = 0x00151544;
    rGPDDAT  = 0x0430;
    rGPDUP   = 0x877A;

    //*** PORT E GROUP
    //Ports  : GPE15  GPE14 GPE13   GPE12   GPE11   GPE10   GPE9    GPE8     GPE7  GPE6  GPE5   GPE4  
    //Signal : IICSDA IICSCL SPICLK SPIMOSI SPIMISO SDDATA3 SDDATA2 SDDATA1 SDDATA0 SDCMD SDCLK I2SSDO 
    //Binary :  10     10  ,  10      10  ,  00      10   ,  10      10   ,   10    10  , 10     10  ,     
    //-------------------------------------------------------------------------------------------------------
    //Ports  :  GPE3   GPE2  GPE1    GPE0    
    //Signal : I2SSDI CDCLK I2SSCLK I2SLRCK     
    //Binary :  10     10  ,  10      10 
    //rGPECON = 0xaaaaaaaa;       
    //rGPEUP  = 0xffff;     // The pull up function is disabled GPE[15:0]
    rGPECON = 0xaa2aaaaa;       
    rGPEUP  = 0xf7ff;       // GPE11 is NC

    //*** PORT F GROUP
    //Ports  : GPF7   GPF6   GPF5   GPF4      GPF3     GPF2  GPF1   GPF0
    //Signal : nLED_8 nLED_4 nLED_2 nLED_1 nIRQ_PCMCIA EINT2 KBDINT EINT0
    //Setting: Output Output Output Output    EINT3    EINT2 EINT1  EINT0
    //Binary :  01      01 ,  01     01  ,     10       10  , 10     10
    rGPFCON = 0x55aa;
    rGPFUP  = 0xff;     // The pull up function is disabled GPF[7:0]

    //*** PORT G GROUP
    //Ports  : GPG15 GPG14 GPG13 GPG12  GPG11    GPG10    GPG9     GPG8     GPG7      GPG6    
    //Signal : nYPON  YMON nXPON XMON   NC       SDWP     WHEEL_SW WHEEL_A  WHEEL_B   ONE_WIRE
    //Setting: nYPON  YMON nXPON XMON   IN       EINT18   EINT17   ENT16    EINT15    OUTPUT
    //Binary :   11    11 , 11    11  , 00       01    ,  10       10   ,   10        01
    //UP     :   1     1    1     1     0        1        1        1        1         1
    //-----------------------------------------------------------------------------------------
    //Ports  : GPG5       GPG4      GPG3    GPG2    GPG1     GPG0    
    //Signal : NC         LCD_PWREN NC      nSS_SPI JACK_CLK JACK_DATA
    //Setting: IN         LCD_PWRDN IN      nSS0    EINT9    EINT8
    //Binary : 00         11   ,    00      11  ,   10       10
    //UP     : 0          1         0       1       1        1
// Behavior changed from board rev A to rev D. Don't power LCD down.
    
    //*** PORT H GROUP
    //Ports  :  GPH10    GPH9  GPH8 GPH7  GPH6  GPH5 GPH4 GPH3 GPH2 GPH1  GPH0 
    //Signal : CLKOUT1 CLKOUT0 UCLK nCTS1 nRTS1 RXD1 TXD1 RXD0 TXD0 nRTS0 nCTS0
    //Binary :   01   ,  01     10 , 11    11  , 10   10 , 10   10 , 10    10    
    rGPHCON = 0x16faaa;
    rGPHUP  = 0x7ff;    // The pull up function is disabled GPH[10:0]

    //External interrupt will be falling edge triggered. 
    rEXTINT0 = 0x22222222;    // EINT[7:0]
    rEXTINT1 = 0x22222222;    // EINT[15:8]
    rEXTINT2 = 0x22222222;    // EINT[23:16]
}

//***************************[ UART ]******************************
void Uart_Init(void)
{
    int i;

    rUFCON0 = 0x0;      // FIFO disable
    rUMCON0 = 0x0;      // AFC disable

    rULCON0 = 0x3;      // Normal,No parity,1 stop,8 bits
    rUCON0  = 0x245;   
    
    rUBRDIV0=( (int)(PCLK/16./BAUD_RATE) -1 );

    for(i=0;i<100;i++);
    
}

//=====================================================================
void Uart_SendByte(int data)
{
        if(data=='\n')
        {
            while(!(UART_STATUS & 0x2));
            Delay(10);                 //because the slow response of hyper_terminal 
            WRITE_UART('\r');
        }

        while(!(UART_STATUS & 0x2));   //Wait until THR is empty.
        Delay(10);
        WRITE_UART(data);
}               


//====================================================================
void Uart_SendString(char *pt)
{
    while(*pt)
        Uart_SendByte(*pt++);
}

//====================================================================
void Uart_SendDWORD(DWORD d, BOOL cr)
{
    Uart_SendString("0x");
    Uart_SendString(hex2char((d & 0xf0000000) >> 28));
    Uart_SendString(hex2char((d & 0x0f000000) >> 24));
    Uart_SendString(hex2char((d & 0x00f00000) >> 20));    
    Uart_SendString(hex2char((d & 0x000f0000) >> 16));
    Uart_SendString(hex2char((d & 0x0000f000) >> 12));
    Uart_SendString(hex2char((d & 0x00000f00) >> 8));
    Uart_SendString(hex2char((d & 0x000000f0) >> 4));
    Uart_SendString(hex2char((d & 0x0000000f) >> 0));
    if (cr)
        Uart_SendString("\n");
}

//====================================================================
char *hex2char(unsigned int val)
{
    static char str[2];

    str[1]='\0';	
    
    if(val<=9)
        str[0]='0'+val;
    else 
        str[0]=('a'+val-10);
        
    return str;
}

void ChangeClockDivider(int hdivn,int pdivn)
{
     // hdivn,pdivn FCLK:HCLK:PCLK
     //     0,0         1:1:1 
     //     0,1         1:1:2 
     //     1,0         1:2:2
     //     1,1         1:2:4
    rCLKDIVN = (hdivn<<1) | pdivn;    
    
    if(hdivn)
        MMU_SetAsyncBusMode();
//    else 
//      MMU_SetFastBusMode();
}

void ChangeMPllValue(int mdiv,int pdiv,int sdiv)
{
    rMPLLCON = (mdiv<<12) | (pdiv<<4) | sdiv;
}

void Delay(int time)
{
      // time=0: adjust the Delay function by WatchDog timer.
      // time>0: the number of loop time
      // resolution of time is 100us.
    int i;
    for(i=0;i<1000;i++);
}
void Delay100us(int time)
{
	while(time)
	{
    int i;
    for(i=0;i<1000;i++);
    time--;
    }
}
