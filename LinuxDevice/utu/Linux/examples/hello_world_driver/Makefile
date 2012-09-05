# Makefile  
# Please modify here or set environments.
# KSOURCE should be pointed to the build directory of your kernel.
#
DEBUG ?= n
KSOURCE ?= /home/azure/Utu-linux/s3c2440/Kernel/utu-linux_for_s3c2440_V1.5/

%.x:%.c
	arm-linux-gcc -o $@ $<

KBUILD_VERBOSE:=1

obj-m := hello.o

default:
	make -C $(KSOURCE) LANG=C KBUILD_VERBOSE=${KBUILD_VERBOSE}  SUBDIRS=`pwd` modules

.PHONY: cscope
cscope:
	cscope -b -k -R

.PHONY: clean
clean:
	make -C $(KSOURCE) LANG=C KBUILD_VERBOSE=${KBUILD_VERBOSE}  SUBDIRS=`pwd` clean
	rm -f *.x *~
