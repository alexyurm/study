/*
 *      Buttons Example for utulinux 2440
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/select.h>
#include <sys/time.h>
#include <errno.h>

int main(void)
{
	int buttons_fd;
	int key_value;

	buttons_fd = open("/dev/buttons", 0);
	if (buttons_fd < 0) {
		perror("cann't open device /dev/buttons");
		exit(1);
	}

	for (;;) {
		fd_set rds;
		int ret;

		FD_ZERO(&rds);
		FD_SET(buttons_fd, &rds);

		ret = select(buttons_fd + 1, &rds, NULL, NULL, NULL);
		if (ret < 0) {
			perror("select");
			exit(1);
		}
		if (ret == 0) {
			printf("Timeout.\n");
		} else if (FD_ISSET(buttons_fd, &rds)) {
			int ret = read(buttons_fd, &key_value, sizeof key_value);
			if (ret != sizeof key_value) {
				if (errno != EAGAIN)
					perror("read buttons\n");
				continue;
			} else {
				printf("You pressed buttons %d\n", key_value);
			}
				
		}
	}

	close(buttons_fd);
	return 0;
}

