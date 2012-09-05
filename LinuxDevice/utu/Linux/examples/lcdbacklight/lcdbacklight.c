/*
 *	Copyright (C) 2007 UTULinux
 *
 *  LCD BackLight Control V0.01
 *
 *  This file is subject to the terms and conditions of the GNU General Public
 *  License. See the file COPYING in the main directory of this archive for
 *  more details.
 */
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <sys/ioctl.h>
#include <linux/fb.h>
int main(int argc, char** argv)
{
    if ( argc == 1 ) 
	{
		printf("Useage:fbtools on\n");
		printf("       fbtools off\n");
		return 1;
	}
	int fb = open("/dev/fb0", O_RDWR);
	if( fb < 0 ) 
	{
		printf("Cann't open /dev/fb0\n");
		return 1;
	}
    int on = 0;
    if( strcmp( argv[1], "on" ) == 0 )
        on = 4;
    else if( strcmp( argv[1], "off" ) == 0 )
        on = 2;
	ioctl(fb, FBIOBLANK, on );
	close(fb);
}

