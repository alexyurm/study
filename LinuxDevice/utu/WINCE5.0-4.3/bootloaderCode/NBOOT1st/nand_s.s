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
;*************************************************************
; NAME : NAND FLASH Subroutine for a410 bootstrap
; DATE : 18.FEB.2002
; DESC :
;  02.18.2002:purnnamu: modified for A410
;  04.24.2002:purnnamu: optimized for NAND flash bootstrap 
;*************************************************************

A410_BASE_ADDR	EQU	0x2000000

	MACRO
	LDR4STR1 $src,$tmp1,$tmp2	
	ldrb	$tmp1,[$src]
	ldrb	$tmp2,[$src]
	orr	$tmp1,$tmp1,$tmp2,LSL #8
	ldrb	$tmp2,[$src]
	orr	$tmp1,$tmp1,$tmp2,LSL #16
	ldrb	$tmp2,[$src]
	orr	$tmp1,$tmp1,$tmp2,LSL #24
	MEND

	AREA |C$$code|, CODE, READONLY
	
	EXPORT	__RdPage512
__RdPage512
	;input:a1(r0)=pPage
	stmfd	sp!,{r1-r11}

	ldr	r1,=0x4e000010  ;NFDATA
	mov	r2,#0x200
0	
	LDR4STR1 r1,r4,r3
	LDR4STR1 r1,r5,r3
	LDR4STR1 r1,r6,r3
	LDR4STR1 r1,r7,r3
	LDR4STR1 r1,r8,r3
	LDR4STR1 r1,r9,r3
	LDR4STR1 r1,r10,r3
	LDR4STR1 r1,r11,r3
	stmia	r0!,{r4-r11}
	subs	r2,r2,#32
	bne	%B0

	ldmfd	sp!,{r1-r11}
	mov	pc,lr


	EXPORT	__RdPage256
__RdPage256
	;input:a1(r0)=pPage
	stmfd	sp!,{r1-r11}

	ldr	r1,=0x4e000010  ;NFDATA
	mov	r2,#0x100
0	
	LDR4STR1 r1,r4,r3
	LDR4STR1 r1,r5,r3
	LDR4STR1 r1,r6,r3
	LDR4STR1 r1,r7,r3
	LDR4STR1 r1,r8,r3
	LDR4STR1 r1,r9,r3
	LDR4STR1 r1,r10,r3
	LDR4STR1 r1,r11,r3
	stmia	r0!,{r4-r11}
	subs	r2,r2,#32
	bne	%B0

	ldmfd	sp!,{r1-r11}
	mov	pc,lr

	END
