##### File Definition ####
PRJ = 2440loader
INIT= 2440init
AM1 = 2440slib
AM2 = nand_s
CM1 = nand
CM2 = 2440lib


#### Destination path Definition ####
OBJ=.\obj
ERR=.\err

#### ARM tool Definition ####
ARMLINK= armlink
ARMASM = armasm
ARMCC  = armcc

#### Option Definition ####
LFLAGS = -ro-base 0x0 -rw-base 0x33ff0000 -elf -map -xref -list list.txt -first $(INIT).o(Init)
AFLAGS = -li -apcs /noswst -cpu ARM920T
CFLAGS = -c -g- -Otime -li -apcs /noswst -cpu ARM920T
#AFLAGS = -bi -apcs /noswst -cpu ARM920T
#CFLAGS = -c -g+ -bi -apcs /noswst -cpu ARM920T

#If you doesn't debug,use following CFLAGS for more faster operation.
#CFLAGS = -c -g- -li -apcs /noswst -cpu ARM920T


#### Object combine Definition ####
OBJS =	$(OBJ)\$(INIT).o $(OBJ)\$(AM1).o $(OBJ)\$(PRJ).o $(OBJ)\$(CM1).o $(OBJ)\$(CM2).o $(OBJ)\$(AM2).o 
all: $(PRJ).elf

clean:
	del $(OBJ)\*.o
	del $(ERR)\*.err
	del $(PRJ).elf
	del $(PRJ).bin
	
$(PRJ).elf: $(OBJS) 
	$(ARMLINK) $(LFLAGS) -o $(PRJ).elf $(OBJS)
#	fromelf -nodebug -nozeropad $(PRJ).elf -bin $(PRJ).bin 
#	fromelf -nodebug -bin -output $(PRJ).bin $(PRJ).axf
	fromelf -nodebug -bin -output $(PRJ).bin $(PRJ).elf
    
#	fromelf  $(PRJ).axf -text/s  syms.sym
#	fromelf  $(PRJ).axf -text/c  symc.sym

#For SDT2.5 fromelf -nodebug -nozeropad $(PRJ).elf -bin $(PRJ).bin 
#For ADS1.0 fromelf -nodebug -bin -output $(PRJ).bin $(PRJ).elf


$(OBJ)\$(PRJ).o :  $(PRJ).c  2440addr.h  makefile
	$(ARMCC) $(CFLAGS)  $(PRJ).c -o $(OBJ)\$(PRJ).o -Errors $(ERR)\$(PRJ).err
 
$(OBJ)\$(INIT).o:  $(INIT).s  option.inc  memcfg.inc  makefile
	$(ARMASM) $(AFLAGS)  $(INIT).s -o $(OBJ)\$(INIT).o -Errors $(ERR)\$(INIT).err

$(OBJ)\$(AM1).o:  $(AM1).s  makefile
	$(ARMASM) $(AFLAGS)  $(AM1).s -o $(OBJ)\$(AM1).o -Errors $(ERR)\$(AM1).err

$(OBJ)\$(AM2).o:  $(AM2).s  makefile
	$(ARMASM) $(AFLAGS)  $(AM2).s -o $(OBJ)\$(AM2).o -Errors $(ERR)\$(AM2).err

$(OBJ)\$(CM1).o :  $(CM1).c  2440addr.h  makefile
	$(ARMCC) $(CFLAGS)  $(CM1).c -o $(OBJ)\$(CM1).o -Errors $(ERR)\$(CM1).err

$(OBJ)\$(CM2).o :  $(CM2).c  2440addr.h  makefile
	$(ARMCC) $(CFLAGS)  $(CM2).c -o $(OBJ)\$(CM2).o -Errors $(ERR)\$(CM2).err
