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
;                                                                     
; Module Name: 2440init.s                                           
; 
; Start up code for NAND bootloader. 
;
;      
;=========================================
; NAME: 2440INIT.S
; DESC: C start up codes
;       Configure memory, ISR ,stacks
;   Initialize C-variables
; HISTORY:
; 2002.02.25:kwtark: ver 0.0
; 2002.03.20:purnnamu: Add some functions for testing STOP,POWER_OFF mode
;=========================================

    INCLUDE option.inc
    INCLUDE memcfg.inc
    INCLUDE 2440addr.inc

    IMPORT  Port_Init

    GBLL    NOT_MIN_CODE
NOT_MIN_CODE SETL  {FALSE}



BIT_SELFREFRESH EQU (1<<22)

;Pre-defined constants
USERMODE    EQU     0x10
FIQMODE     EQU     0x11
IRQMODE     EQU     0x12
SVCMODE     EQU     0x13
ABORTMODE   EQU     0x17
UNDEFMODE   EQU     0x1b
MODEMASK    EQU     0x1f
NOINT       EQU     0xc0

SDRAM_CLEAR EQU     0x0
SDRAM_TEST  EQU     0x12345678

DELAY       EQU     0x200

;The location of stacks
UserStack   EQU (_STACK_BASEADDRESS-0x3800) ;0x31ff4800 ~ 
SVCStack    EQU (_STACK_BASEADDRESS-0x2800) ;0x31ff5800 ~ // 256 byte stack
UndefStack  EQU (_STACK_BASEADDRESS-0x2400) ;0x31ff5c00 ~
AbortStack  EQU (_STACK_BASEADDRESS-0x2000) ;0x31ff6000 ~
IRQStack    EQU (_STACK_BASEADDRESS-0x1000) ;0x31ff7000 ~
FIQStack    EQU (_STACK_BASEADDRESS-0x0)    ;0x31ff8000 ~ 

_FCLK   EQU (406)
R1_iA   EQU (1<<31)
R1_nF   EQU (1<<30)

; :::::::::::::::::::::::::::::::::::::::::::::
;           BEGIN: Power Management 
; - - - - - - - - - - - - - - - - - - - - - - -
Mode_USR            EQU     0x10
Mode_FIQ            EQU     0x11
Mode_IRQ            EQU     0x12
Mode_SVC            EQU     0x13
Mode_ABT            EQU     0x17
Mode_UND            EQU     0x1B
Mode_SYS            EQU     0x1F

I_Bit               EQU     0x80
F_Bit               EQU     0x40
; - - - - - - - - - - - - - - - - - - - - - - -
;           END: Power Management 
; :::::::::::::::::::::::::::::::::::::::::::::


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

        MACRO
    MOVEQ_PC_LR
        [ THUMBCODE
            bxeq lr
        |
            moveq pc,lr
        ]
    MEND

        MACRO
$HandlerLabel HANDLER $HandleLabel

$HandlerLabel
    sub sp,sp,#4        ;decrement sp(to store jump address)
    stmfd   sp!,{r0}        ;PUSH the work register to stack(lr does't push because it return to original address)
    ldr     r0,=$HandleLabel;load the address of HandleXXX to r0
    ldr     r0,[r0]         ;load the contents(service routine start address) of HandleXXX
    str     r0,[sp,#4]      ;store the contents(ISR) of HandleXXX to stack
    ldmfd   sp!,{r0,pc}     ;POP the work register and pc(jump to ISR)
    MEND
    
;---------------------------------------------------------------------------
;	4 LED light function
;	The LEDs are located below AMD Flash ROM

	MACRO
	LED_ON	$data
	LDR	    r10, =0x56000054        
	LDR	    r11, =$data
	MOV     r11, r11, lsl #4
  	STR	    r11, [r10]
    MEND
;---------------------------------------------------------------------------

    IMPORT  |Image$$RO$$Limit|  ; End of ROM code (=start of ROM data)
    IMPORT  |Image$$RW$$Base|   ; Base of RAM to initialise
    IMPORT  |Image$$ZI$$Base|   ; Base and limit of area
    IMPORT  |Image$$ZI$$Limit|  ; to zero initialise
    
    IMPORT  Main    ; The main entry of mon program


    AREA    Init,CODE,READONLY

    ENTRY 

    ;1)The code, which converts to Big-endian, should be in little endian code.
    ;2)The following little endian code will be compiled in Big-Endian mode. 
    ;  The code byte order should be changed as the memory bus width.
    ;3)The pseudo instruction,DCD can't be used here because the linker generates error.
    ASSERT  :DEF:ENDIAN_CHANGE
    
    b   ResetHandler    ; 0x00 Reset
    b   .               ; 0x04 Undefined
    b   .               ; 0x08 Supervisor
    b   .               ; 0x0c Prefetch Abort
    b   .               ; 0x10 Data Abort
    b   .               ; 0x14 Reserved
    b   .               ; 0x18 IRQ
    b   .               ; 0x1c FIQ
PowerHandler
	str     r1, [r0]		; Enable SDRAM self-refresh
	str		r3, [r2]		; MISCCR Setting
	str     r5, [r4]		; Power Off !!
	b       .
    
    LTORG   
    
;=======
; ENTRY  
;=======
ResetHandler
	ldr     r0, = GPFCON
	ldr     r1, = 0x55aa      
	str     r1, [r0]

    ldr r0,=WTCON       ;watch dog disable 
    ldr r1,=0x0         
    str r1,[r0]

    ldr r0,=INTMSK
    ldr r1,=0xffffffff  ;all interrupt disable
    str r1,[r0]

    ldr r0,=INTSUBMSK
    ldr r1,=0x7ff       ;all sub interrupt disable
    str r1,[r0]

    ldr     r0, = INTMOD
    mov r1, #0x0        ; set all interrupt as IRQ (not FIQ)
    str     r1, [r0]

    ; configure GPIO pins
    bl  Port_Init

    ; CLKDIVN
    ldr r0,=CLKDIVN
    ldr r1,=0x7     ; 0x0 = 1:1:1  ,  0x1 = 1:1:2	, 0x2 = 1:2:2  ,  0x3 = 1:2:4,  0x4 = 1:4:4,  0x5 = 1:4:8, 0x6 = 1:3:3, 0x7 = 1:3:6

    str r1,[r0]
    ; delay
    mov     r0, #DELAY
5   subs    r0, r0, #1
    bne     %B5

    ; MMU_SetAsyncBusMode FCLK:HCLK= 1:2
    ands r1, r1, #0x2
    beq %F1
    mrc p15,0,r0,c1,c0,0
    orr r0,r0,#R1_nF:OR:R1_iA
    mcr p15,0,r0,c1,c0,0
1

    ;To reduce PLL lock time, adjust the LOCKTIME register. 
    ldr r0,=LOCKTIME
    ldr r1,=0xffffff
    str r1,[r0]
    ; delay
    mov     r0, #DELAY
5   subs    r0, r0, #1
    bne     %B5

    ;Configure MPLL
    ldr r0,=MPLLCON          
;    ldr r1,=((110<<12)+(3<<4)+1)  ;Fin=16MHz,Fout=399MHz
    ldr r1,=((0x7f<<12)+(0x2<<4)+0x1)  ;Fin=12MHz,Fout=405MHz
    str r1,[r0]
    ; delay
    mov     r0, #DELAY
5   subs    r0, r0, #1
    bne     %B5

    ;Configure UPLL
    ldr     r0, =UPLLCON          
;   ldr     r1, =((60<<12)+(4<<4)+2)  ;Fin=16MHz, Fout=48MHz
    ldr     r1, =((0x48<<12)+(0x3<<4)+0x2)  ;Fin=12MHz, Fout=48MHz
    str     r1, [r0]
    ; delay
    mov     r0, #0x200
5   subs    r0, r0, #1
    bne     %B5

; :::::::::::::::::::::::::::::::::::::::::::::
;           BEGIN: Power Management 
; - - - - - - - - - - - - - - - - - - - - - - -
	ldr	r1, =GSTATUS2           ; Determine Booting Mode
	ldr	r10, [r1]
	tst	r10, #0x2
	beq	%F2                     ; if not wakeup from PowerOffmode Skip
                                ;    MISCCR setting


	LED_ON 0xc
	str r10, [r1]				; Clear Test

;	B .


	ldr 	r1, =MISCCR         ; MISCCR's Bit 17, 18, 19 -> 0
	ldr	r0, [r1]                ; I don't know why, Just fallow Sample Code.
	bic	r0, r0, #(3 << 17)      ; SCLK0:0->SCLK, SCLK1:0->SCLK, SCKE:L->H
	str	r0, [r1]

	; Set memory control registers
	add		r0, pc, #SMRDATA - (. + 8)
	ldr	r1, =BWSCON	; BWSCON Address
	add	r2, r0, #52	; End address of SMRDATA
loop10
	ldr	r3, [r0], #4
	str r3, [r1], #4
	cmp	r2, r0
	bne loop10

	mov r1, #256
loop11
	subs r1, r1, #1		; wait until the SelfRefresh is released.
	bne loop11


	ldr		r2, =0x201000					; offset into the RAM 
	add		r2, r2, #0x30000000				; add physical base
	mov     pc, r2							;  & jump to StartUp address
	nop
	nop
	nop
	b .

	b	%F3						; if wakeup from PowerOff mode
								;	 goto Power-up code.
; Watchdog reset
2
	tst		r10, #0x4				; In case of the wake-up from Watchdog reset, 
									;	 go to SDRAM start address(0x3000_0000)
	b		%F4						; If not wakeup from Watchdog reset,
;	beq		%F4						; If not wakeup from Watchdog reset,
									;	 goto Normal Mode.

	mov	r0, #4
	str	r0, [r1]					; Clear the GSTATUS2. Because same code is located in memory address.

	; Set memory control registers
	ldr	r0, =SMRDATA
	ldr	r1, =BWSCON	; BWSCON Address
	add	r2, r0, #52	; End address of SMRDATA
loop0
	ldr	r3, [r0], #4
	str r3, [r1], #4
	cmp	r2, r0
	bne loop0

	mov r1, #256
loop1
	subs r1, r1, #1		; wait until the SelfRefresh is released.
	bne loop1

	ldr		r2, =0x201000					; offset into the RAM 
	add		r2, r2, #0x30000000				; add physical base
	mov     pc, r2							;  & jump to StartUp address
	b .

; Case of Power off reset
3
	ldr 	r1, =MISCCR         ; MISCCR's Bit 17, 18, 19 -> 0
	ldr	r0, [r1]                ; I don't know why, Just fallow Sample Code.
	bic	r0, r0, #(3 << 17)      ; SCLK0:0->SCLK, SCLK1:0->SCLK, SCKE:L->H
	str	r0, [r1]
; - - - - - - - - - - - - - - - - - - - - - - -
;           END: Power Management 
; :::::::::::::::::::::::::::::::::::::::::::::
4

    ; Configure memory controller
    ;ldr    r0,=SMRDATA
    add     r0, pc, #SMRDATA - (. + 8)
    ldr r1,=BWSCON  ;BWSCON Address
    add r2, r0, #52 ;End address of SMRDATA
0       
    ldr r3, [r0], #4    
    str r3, [r1], #4    
    cmp r2, r0      
    bne %B0

BringUpWinCE    
    ;Normal Boot: Clear SDRAM
    ldr r1,=SDRAM_CLEAR
    ldr r2,=SDRAM_CLEAR
    ldr r3,=SDRAM_CLEAR
    ldr r4,=SDRAM_CLEAR
    ldr r5,=SDRAM_CLEAR
    ldr r6,=SDRAM_CLEAR
    ldr r7,=SDRAM_CLEAR
    ldr r8,=SDRAM_CLEAR
1   
    ldr r9,=SDRAM_SIZE
    ldr r0,=SDRAM_BASE

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
0   
    stmia   r0!,{r1-r8}
    subs    r9,r9,#32 
    bne %B0
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    ; Initialize stacks
    ldr sp,=SVCStack            ; r13 = 0x33ff5800
    
    ;Copy and paste RW data/zero initialized data
    ldr r0, =|Image$$RO$$Limit| ; r0 = pointer to ROM data (0x000009bc)
    ldr r1, =|Image$$RW$$Base|  ; r1 = RAM copy (0x31ff0000)
    ldr r3, =|Image$$ZI$$Base|  ; r3 = globals (0x31ff0004)
    
    ;Zero init base => top of initialised data
    cmp r0, r1      ; Check that they are different
    beq %F2
1       
    cmp r1, r3      ; Copy init data
    ldrcc   r2, [r0], #4    ;--> LDRCC r2, [r0] + ADD r0, r0, #4         
    strcc   r2, [r1], #4    ;--> STRCC r2, [r1] + ADD r1, r1, #4
    bcc %B1
2       
    ldr r1, =|Image$$ZI$$Limit| ; Top of zero init segment
    mov r2, #0
3       
    cmp r3, r1      ; Zero init
    strcc   r2, [r3], #4
    bcc %B3

    b   Main


    LTORG

SMRDATA DATA
; Memory configuration should be optimized for best performance 
; The following parameter is not optimized.                     
; Memory access cycle parameter strategy
; 1) The memory settings is  safe parameters even at HCLK=75Mhz.
; 2) SDRAM refresh period is for HCLK=75Mhz. 

        DCD (0+(B1_BWSCON<<4)+(B2_BWSCON<<8)+(B3_BWSCON<<12)+(B4_BWSCON<<16)+(B5_BWSCON<<20)+(B6_BWSCON<<24)+(B7_BWSCON<<28))
        DCD ((B0_Tacs<<13)+(B0_Tcos<<11)+(B0_Tacc<<8)+(B0_Tcoh<<6)+(B0_Tah<<4)+(B0_Tacp<<2)+(B0_PMC))   ;GCS0
        DCD ((B1_Tacs<<13)+(B1_Tcos<<11)+(B1_Tacc<<8)+(B1_Tcoh<<6)+(B1_Tah<<4)+(B1_Tacp<<2)+(B1_PMC))   ;GCS1 
        DCD ((B2_Tacs<<13)+(B2_Tcos<<11)+(B2_Tacc<<8)+(B2_Tcoh<<6)+(B2_Tah<<4)+(B2_Tacp<<2)+(B2_PMC))   ;GCS2
        DCD ((B3_Tacs<<13)+(B3_Tcos<<11)+(B3_Tacc<<8)+(B3_Tcoh<<6)+(B3_Tah<<4)+(B3_Tacp<<2)+(B3_PMC))   ;GCS3
        DCD ((B4_Tacs<<13)+(B4_Tcos<<11)+(B4_Tacc<<8)+(B4_Tcoh<<6)+(B4_Tah<<4)+(B4_Tacp<<2)+(B4_PMC))   ;GCS4
        DCD ((B5_Tacs<<13)+(B5_Tcos<<11)+(B5_Tacc<<8)+(B5_Tcoh<<6)+(B5_Tah<<4)+(B5_Tacp<<2)+(B5_PMC))   ;GCS5
        DCD ((B6_MT<<15)+(B6_Trcd<<2)+(B6_SCAN))    ;GCS6
        DCD ((B7_MT<<15)+(B3_Tacs<<13)+(B3_Tcos<<11)+(B3_Tacc<<8)+(B3_Tcoh<<6)+(B3_Tah<<4)+(B3_Tacp<<2)+(B3_PMC))
        DCD ((REFEN<<23)+(TREFMD<<22)+(Trp<<20)+(Trc<<18)+(Tchr<<16)+REFCNT)
        
        DCD 0x32            ;SCLK power saving mode, BANKSIZE 128M/128M
        DCD 0x30            ;MRSR6 CL=3clk
        DCD 0x30            ;MRSR7

        ALIGN


        AREA RamData, DATA, READWRITE

        ^   _ISR_STARTADDRESS
HandleReset     #   4 
HandleUndef     #   4
HandleSWI       #   4
HandlePabort    #   4
HandleDabort    #   4
HandleReserved  #   4
HandleIRQ       #   4
HandleFIQ       #   4

;Don't use the label 'IntVectorTable',
;The value of IntVectorTable is different with the address you think it may be.
;IntVectorTable
HandleEINT0     #   4
HandleEINT1     #   4
HandleEINT2     #   4
HandleEINT3     #   4
HandleEINT4_7   #   4
HandleEINT8_23  #   4
HandleRSV6      #   4
HandleBATFLT    #   4
HandleTICK      #   4
HandleWDT       #   4
HandleTIMER0    #   4
HandleTIMER1    #   4
HandleTIMER2    #   4
HandleTIMER3    #   4
HandleTIMER4    #   4
HandleUART2     #   4
HandleLCD       #   4
HandleDMA0      #   4
HandleDMA1      #   4
HandleDMA2      #   4
HandleDMA3      #   4
HandleMMC       #   4
HandleSPI0      #   4
HandleUART1     #   4
HandleRSV24     #   4
HandleUSBD      #   4
HandleUSBH      #   4
HandleIIC       #   4
HandleUART0     #   4
HandleSPI1      #   4
HandleRTC       #   4
HandleADC       #   4

        END
