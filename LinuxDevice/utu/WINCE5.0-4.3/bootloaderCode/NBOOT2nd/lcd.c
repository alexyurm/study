#include <stdlib.h>
#include <string.h>
#include "option.h"
#include "def.h"
#include "2440addr.h"
#include "2440lib.h"
#include "2440slib.h"
#include "2440addr.h"
#include "nand.h"
#include "loader.h"
////
//#include "demo256.h"
#include "k.h"

// == Params == //
#define CLKVAL_TFT_240320	(5) 	
#define MVAL		(13)
#define MVAL_USED 	(0)
//Timing parameter for LTS350Q1(SAMSUNG) 335,640
/*
#define VBPD_240320                ((33-1)&0xff)
#define VFPD_240320                ((10-1)&0xff)
#define VSPW_240320                ((2-1) &0x3f)

#define HBPD_240320                ((48-1)&0x7f)
#define HFPD_240320                ((16-1)&0xff)
#define HSPW_240320                ((96-1)&0xff)
*/
//Timing parameter for LTS350Q1(SAMSUNG) 360,675

#define VBPD_240320                ((7-1)&0xff)
#define VFPD_240320                ((43-1)&0xff)
#define VSPW_240320                ((4-1) &0x3f)
#define HBPD_240320                ((48)&0x7f)
#define HFPD_240320                ((16)&0xff)
#define HSPW_240320                ((128)&0xff)

#define M5D(n) ((n) & 0x1fffff)	// To get lower 21bits
//TFT240320
#define HOZVAL_TFT_240320	(LCD_XSIZE_TFT_240320-1)
#define LINEVAL_TFT_240320	(LCD_YSIZE_TFT_240320-1)
//TFT 240320
#define LCD_XSIZE_TFT_240320 	(480)	
#define LCD_YSIZE_TFT_240320 	(272)

#define SCR_XSIZE_TFT_240320 	(LCD_XSIZE_TFT_240320*2)
#define SCR_YSIZE_TFT_240320 	(LCD_YSIZE_TFT_240320*2)

// LCD Params
U32 (*frameBuffer8BitTft240320)[SCR_XSIZE_TFT_240320/4];

#define PALETTE     0x4d000400                         //Palette start address
#define LCDFRAMEBUFFER 0x33800000 //_NONCACHE_STARTADDRESS 

// =====   _PutTft8Bit_240320  ===== //
void PutPixel(U32 x,U32 y,U32 c)
{
    if(x<SCR_XSIZE_TFT_240320 && y<SCR_YSIZE_TFT_240320)
        frameBuffer8BitTft240320[(y)][(x)/4]=( frameBuffer8BitTft240320[(y)][x/4]
        & ~(0xff000000>>((x)%4)*8) ) | ( (c&0x000000ff)<<((4-1-((x)%4))*8) );
}

void lcdplay(void)
{
	
	int i,j,k,m,n;
	int x0,y0;
	U32* palette;

	//======PWM timer init ======//
	rTCFG0 =24;
    rTCFG1 &=~(0xf);
    rTCMPB0 =2500;
    rTCNTB0 =5000;
    rTCON &=~(0xf);
    rTCON |=0xa;
    rTCON &=~(0xf);
    rTCON |=0x9;    
    
	//===========Lcd port init==========//
    rGPCUP=0xffffffff; // Disable Pull-up register
    rGPCCON=0xaaaaaaaa; //Initialize VD[7:0],LCDVF[2:0],VM,VFRAME,VLINE,VCLK,LEND 
    rGPDUP=0xffffffff; // Disable Pull-up register
    rGPDCON=0xaaaaaaaa; //Initialize VD[23:8]
    
    
    //==========Lcd_Palette8Bit_Init==========//   
    rLCDCON5|=(1<<11); // 5:6:5 Palette Setting
    palette=(U32 *)(PALETTE);
    for(i=0;i<256;i++)
	//*palette++=Colorsk[i];    
	*palette++=Colorsk[i];    
	
    
    //===========Lcd init==========//
    frameBuffer8BitTft240320=(U32 (*)[SCR_XSIZE_TFT_240320/4])LCDFRAMEBUFFER;
   	rLCDCON1=(CLKVAL_TFT_240320<<8)|(MVAL_USED<<7)|(3<<5)|(11<<1)|0;
        // TFT LCD panel,8bpp TFT,ENVID=off
	rLCDCON2=(VBPD_240320<<24)|(LINEVAL_TFT_240320<<14)|(VFPD_240320<<6)|(VSPW_240320);
	rLCDCON3=(HBPD_240320<<19)|(HOZVAL_TFT_240320<<8)|(HFPD_240320);
	rLCDCON4=(MVAL<<8)|(HSPW_240320);
	rLCDCON5=(1<<11)|(1<<9)|(1<<8);	//FRM5:6:5,HSYNC and VSYNC are inverted
	rLCDSADDR1=(((U32)frameBuffer8BitTft240320>>22)<<21)|M5D((U32)frameBuffer8BitTft240320>>1);
	rLCDSADDR2=M5D( ((U32)frameBuffer8BitTft240320+(SCR_XSIZE_TFT_240320*LCD_YSIZE_TFT_240320/1))>>1 );
	rLCDSADDR3=(((SCR_XSIZE_TFT_240320-LCD_XSIZE_TFT_240320)/2)<<11)|(LCD_XSIZE_TFT_240320/2);
	rLCDINTMSK|=(3); // MASK LCD Sub Interrupt
	rLPCSEL&=(~7); // Disable LPC3600
	//rTPAL=0; // Disable Temp Palette
	//rLCDINTPND=3;	
	 
	 
	//============Lcd PowerEnable===========//
		    //GPG4 is setted as LCD_PWREN
	rGPGUP=rGPGUP&(~(1<<4))|(1<<4); // Pull-up disable
	rGPGCON=rGPGCON&(~(3<<8))|(3<<8); //GPG4=LCD_PWREN	    
	    	//Enable LCD POWER ENABLE Function
	rLCDCON5=rLCDCON5&(~(1<<3))|(1<<3);   // PWREN
	rLCDCON5=rLCDCON5&(~(1<<5))|(0<<5);   // INVPWREN   	
   			// ENVID=ON,enable the LCD output    
    rLCDCON1|=1;   	
    
    	
    // ============ Show Map ============= //
    /*  Show Palette  *
    for(j=0;j<256;j++)
    {
    	y0=(j/16)*(320/16);
    	x0=(j%16)*(240/16);
    		
    	for(m=0;m<(240/16);m++)
    	for(n=0;n<(320/16);n++)
    	{
    		PutPixel(x0+m,y0+n,j);
    	}
    }
    //*/	
    
    /* Show Map */     	
    for(k=0,j=0;j<LCD_YSIZE_TFT_240320;j++)
       for(i=0;i<LCD_XSIZE_TFT_240320;i++)
       {       		      
	   	PutPixel(i,j,ackj[k++]);	      
       }
       
   
}

// ======== Draw Process Bar ======== //
int currWidth=0;
int const bar_height=LCD_YSIZE_TFT_240320*0.07;
int bar_base  =LCD_YSIZE_TFT_240320-bar_height;

void drawProcessBar(DWORD total,int current)
{
	int i,j;	
	
	if(total != -1)
	{
		// Bar Dimension
		int bar_width =LCD_XSIZE_TFT_240320*(current*1.0/total);
		
		
		// will be very fast
		if(bar_width>currWidth)
		{
			for(j=0;j<bar_height;j++)
				PutPixel(bar_width,j+bar_base,(int)(255-16+1));			
		
			currWidth=bar_width;
		}		
	}
}

