/*
** Insert Sort Algorithm
*/

#include <stdio.h>
#include <stdlib.h>

void swap(int *s1, int *s2)
{
   int temp = *s1;
   *s1 = *s2;
   *s2 = temp;
}


void insertion_sort (int *s, int size)
{
   int i, j;

   for (i = 0; i < size; i++)
   {
      j = i;
      while ( ( j > 0) && (s[j] < s[j-1]) )
      {
         swap(&s[j], &s[j-1]);
         j = j - 1;
      }
   }
}
      
int main()
{
   int i;
   int s[6] = {1, -23, -89, -102, 3920, 102};
   insertion_sort(s, 6);

   for (i = 0; i < 6; i++)
   {
      if (i == 5)
      {
         printf(" %d\n", s[i]);
      }
      else
      {
         printf("%d, ", s[i]);
      }
   }
   return 0;
}
