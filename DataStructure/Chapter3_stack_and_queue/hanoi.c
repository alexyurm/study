#include "header.h"

void move(char x, int n, char y)
{
   printf("Move the disk %d from column %c to column %c\n", n, x, y);
}

/* Move n disks from column x to z, with the assistance of y */
void hanoi(int n, char x, char y, char z)
{
   if (n == 1)
   {
      /* move the last disk from column x to column z */
      move(x, n, z);
   }
   else
   {
      hanoi(n-1, x, z, y); //move the (n-1) disks from column from x to y, with the assistance of z
      move(x, n, z); //move the disk n from x to z
      hanoi(n-1, y, x, z); //move the (n-1) disks from column from y to z, with the assitance of x
   }
}

int main()
{
   hanoi(10, 'x', 'y', 'z');
   return 0;
}
