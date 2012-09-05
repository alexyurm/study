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
;************************************************ 
; NAME    : MEMCFG.A
; DESC	  : Memory bank configuration file
; Revision: 02.28.2002 ver 0.0
;************************************************

SDRAM_BASE      EQU     0x30000000

SDRAM_SIZE      EQU     0x04000000


;BWSCON
DW8		    EQU	(0x0)
DW16		EQU	(0x1)
DW32		EQU	(0x2)
WAIT		EQU	(0x1<<2)
UBLB		EQU	(0x1<<3)

	ASSERT :DEF:BUSWIDTH
    [ BUSWIDTH=16
B1_BWSCON	EQU (DW16)
B2_BWSCON	EQU (DW16)
;B3_BWSCON	EQU (DW16)
B3_BWSCON	EQU	(DW16 + WAIT + UBLB)
B4_BWSCON	EQU (DW16)
B5_BWSCON	EQU (DW16)
;B6_BWSCON	EQU (DW16 + UBLB)
B6_BWSCON	EQU (DW16)
B7_BWSCON	EQU (DW16 + UBLB)
    | ;BUSWIDTH=32
B1_BWSCON	EQU (DW32)
B2_BWSCON	EQU (DW16)
B3_BWSCON	EQU (DW16)
B4_BWSCON	EQU (DW16)
B5_BWSCON	EQU (DW16)
B6_BWSCON	EQU (DW32)
B7_BWSCON	EQU (DW32)
    ]

;BANK0CON 
B0_Tacs		EQU	0x0	;0clk
B0_Tcos		EQU	0x0	;0clk
B0_Tacc		EQU	0x7	;14clk
B0_Tcoh		EQU	0x0	;0clk
B0_Tah		EQU	0x0	;0clk
B0_Tacp		EQU	0x0	
B0_PMC		EQU	0x0	;normal

;BANK1CON
B1_Tacs		EQU	0x0	;0clk
B1_Tcos		EQU	0x0	;0clk
B1_Tacc		EQU	0x7	;14clk
B1_Tcoh		EQU	0x0	;0clk
B1_Tah		EQU	0x0	;0clk
B1_Tacp		EQU	0x0	
B1_PMC		EQU	0x0	;normal

;Bank 2 parameter
B2_Tacs		EQU	0x0	;0clk
B2_Tcos		EQU	0x0	;0clk
B2_Tacc		EQU	0x7	;14clk
B2_Tcoh		EQU	0x0	;0clk
B2_Tah		EQU	0x0	;0clk
B2_Tacp		EQU	0x0	
B2_PMC		EQU	0x0	;normal

;Bank 3 parameter
B3_Tacs		EQU	0x0	;0clk
B3_Tcos		EQU	0x0	;0clk
B3_Tacc		EQU	0x7	;14clk
B3_Tcoh		EQU	0x0	;0clk
B3_Tah		EQU	0x0	;0clk
B3_Tacp		EQU	0x0	
B3_PMC		EQU	0x0	;normal

;Bank 4 parameter
B4_Tacs		EQU	0x0	;0clk
B4_Tcos		EQU	0x0	;0clk
B4_Tacc		EQU	0x7	;14clk
B4_Tcoh		EQU	0x0	;0clk
B4_Tah		EQU	0x0	;0clk
B4_Tacp		EQU	0x0	
B4_PMC		EQU	0x0	;normal

;Bank 5 parameter
B5_Tacs		EQU	0x0	;0clk
B5_Tcos		EQU	0x0	;0clk
B5_Tacc		EQU	0x7	;14clk
B5_Tcoh		EQU	0x0	;0clk
B5_Tah		EQU	0x0	;0clk
B5_Tacp		EQU	0x0	
B5_PMC		EQU	0x0	;normal

;Bank 6 parameter
B6_MT		EQU	0x3	;SDRAM
B6_Trcd		EQU	0x1	;3clk
B6_SCAN		EQU	0x1	;9bit

;Bank 7 parameter
B7_MT		EQU	0x0	; ROM or SRAM
B7_Trcd		EQU	0x0	;2clk
;B7_Trcd		EQU	0x1	;3clk
B7_SCAN		EQU	0x1	;9bit

;REFRESH parameter
REFEN		EQU	0x1	;Refresh enable
TREFMD		EQU	0x0	;CBR(CAS before RAS)/Auto refresh

Trp		    EQU	0x0	;2clk

Trc		    EQU	0x3	;7clk

Tchr		EQU	0x2	;3clk
;REFCNT		EQU	1012	;period=7.8us, HCLK=133Mhz, (2048+1-7.8*133)
REFCNT		EQU	1659	;period=7.8us, HCLK=50Mhz, (2048+1-7.8*50)

        END
