#include "header.h"

void move(char x, int n, char y)
{
   printf("Move the disk %d from column %c to column %c\n", n, x, y);
}

void hanoi(int n, char x, char y, char z)
{
   if (n == 1)
   {
      /* move the last disk from column x to column z */
      move(x, n, z);
   }
   else
   {
      /* move the (n-1) disks from column from x to z, with the assistence of y*/
      hanoi(n-1, x, z, y);
      move(x, n, z);
      hanoi(n-1, y, x, z);
   }
}

int main()
{
   hanoi(10, 'x', 'y', 'z');
   return 0;
}
