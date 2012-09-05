;
; Copyright (c) Microsoft Corporation.  All rights reserved.
;
;
; Use of this source code is subject to the terms of the Microsoft end-user
; license agreement (EULA) under which you licensed this SOFTWARE PRODUCT.
; If you did not accept the terms of the EULA, you are not authorized to use
; this source code. For a copy of the EULA, please see the LICENSE.RTF on your
; install media.
;
;=====================================================================
; File Name : 2440slib.s
; Function  : S3C2440  (Assembly)
; Program   : Shin, On Pil (SOP)
; Date      : February 26, 2002
; Version   : 0.0
; History
;   0.0 : Programming start (February 26,2002) -> SOP
;   04.24.2002: purnnamu: optimized for NAND flash bootstrap 
;   11.29.2003: hmseo: modified for S3C2440 PPC2003
;=====================================================================
;Interrupt, FIQ/IRQ disable
NOINT  EQU 0xc0    ; 1100 0000  

;Check if tasm.exe(armasm -16 ...@ADS 1.0) is used.
   GBLL    THUMBCODE
   [ {CONFIG} = 16 
THUMBCODE SETL  {TRUE}
	 CODE32
   |   
THUMBCODE SETL  {FALSE}
   ]

   MACRO
     MOV_PC_LR
     [ THUMBCODE
       bx lr
     |
       mov pc,lr
     ]
   MEND

   AREA |C$$code|, CODE, READONLY


;====================================
; MMU Cache/TLB/etc on/off functions
;====================================
R1_I	EQU	(1<<12)
R1_C	EQU	(1<<2)
R1_A	EQU	(1<<1)
R1_M    EQU	(1)
R1_iA	EQU	(1<<31)
R1_nF   EQU	(1<<30)

;void MMU_EnableICache(void)
   EXPORT MMU_EnableICache
MMU_EnableICache	
   mrc p15,0,r0,c1,c0,0
   orr r0,r0,#R1_I
   mcr p15,0,r0,c1,c0,0
   MOV_PC_LR

;void MMU_SetAsyncBusMode(void) 
; FCLK:HCLK= 1:2
   EXPORT MMU_SetAsyncBusMode
MMU_SetAsyncBusMode
   mrc p15,0,r0,c1,c0,0
   orr r0,r0,#R1_nF:OR:R1_iA
   mcr p15,0,r0,c1,c0,0
   MOV_PC_LR


; void Launch(DWORD PhysicalAddress)
    EXPORT Launch
Launch   
	nop
	nop
    nop
	nop
	
	mov     pc, r0  ; Jump to PhysicalAddress
	nop
	nop
	nop
	nop
    MOV_PC_LR

    
   END
