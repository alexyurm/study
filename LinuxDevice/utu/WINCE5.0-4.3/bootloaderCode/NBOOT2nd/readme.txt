//---------------------------------------------------------------------
//  Copyright (c) Microsoft Corporation
//
//  Module: NAND bootloader
//
//---------------------------------------------------------------------

Purpose:
--------

The NAND bootloader is the first piece of code executed by S3C2410 at 
power on. The code was based on NAND bootloader from Samsung with some
modification to work with Catfish device. 

Development Tools Required:
---------------------------

The NAND bootloader has to fit in 4KB for S3C2410 to boot off NAND flash.
There is no simple way to build NAND bootloader using Windows CE tool set.
In stead, we used ARM Development Suite v1.2 to build NAND bootloader. 
Both the retail version and evaluate version of ARM tools can be used. 

How does it work:
-----------------

The boot loader does all the necessary hardware initialization. After that,
it will look at block 1 of the NAND flash to read the table of content for
the image(s) stored on the NAND flash. 

The table of content structure is defined in loader.h under Catfish\Inc 
directory. 

Two possible scenario are supported with the current bootloader implementation:

1) boot off a second stage boot loader, which is Ethernet bootloader. This is 
mainly used for development. 

2) boot off the image stored on the NAND flash, the image parsing capability is
not included with NAND flash to save sapce, so the image on the NAND has to be
in RAW format (.nb0 file using Romimage). 

How to get image and bootloader onto the NAND flash:
----------------------------------------------------

The initial bootloader(s) is downloaded to the NAND flash via JTAG interface 
using SJF.exe tool provided under Catfish\tools\sjf\bin directory. 

NAND bootloader (nboot.bin under Catfish\Nboot\bin directory) is loaded via 
SJF to the block 0 of NAND flash.

tocblock1 file, which is located at Catfish\Tools\SJF\bin directory, is loaded
using SJF to the block 1 of NAND flash.

Eboot.nb0, the Ethernet bootloader, which is located at Catfish\Eboot\bin directory
is loaded to the block 2 of NAND flash.

After you are done with the above work items, you can start development using Eboot.

In order to download the full Pocket PC image to NAND, we need to use imgloader.exe
tools which can be built from Catfish\tools\imgloader directory. 

Here is the steps you need to follow if you are using imgloader to download Pocket 
PC or any other big image to the NAND ( downloading big image via JTAG is EXTREMELY
SLOW )

1)  Set BOOT_LOADER=1 and rebuild Catfish\Drivers\NandFlash\FMD directory
2)  Build ImaLoader.exe from Catfish\tools\imgloader directory
3)  Build your CE image (Pocket PC or any other CE image). You should get a NK.nb0
    file. That is the file you need to store on the NAND.
4)  Turn on the power on the device, and using eshell.exe (Catfish\tools\eshell) or
    PB 3.0 to download small.bin (Catfish\Eboot\bin) 
5)  At the cesh> prompt, run: s imgloader nk.nb0 to download the image to the NAND
6)  For your convience, you may want to modify NAND bootloader so that it will boot
    from your image instead of Eboot.nb0, by modifying the default boot entry to 1.
    
Dependencies:
-------------

None

Issues and Recommendations:
---------------------------

The NAND bootloader here provides a general framework for you to develop NAND boot
loader. The main restriction that we have to deal with is the space restriction (4KB). 

There are several improvements that can be done to the NAND bootloader:

1)  Check ECC when reading from NAND.
2)  Provide different table of content structure to allow more flexible way to boot.

 


