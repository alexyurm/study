#include <stdio.h>
#include <stdlib.h>

void insertsort(int array[], int num)
{
   int i, j, key;
   for (j = 1; j < num; j++)
   {
      key = array[j];
      i = j - 1;
      while ( (i >= 0) && (array[i] < key) )
      {
         array[i+1] = array[i];
         i = i - 1;
      }
      array[i+1] = key;
   }
}

int main()
{
   int i, num;
   printf("enter the length of the array:");
   scanf("%d", &num);
   printf("\n");
   
   int array[num];
   for(i = 0; i < num; i++)
   {
      printf("Enter the element a[%d]:", i);
      scanf("%d", &array[i]);
   }
   printf("\n");

   insertsort(array, num);

   for (i = 0; i < num; i++)
   {
      printf("%d ", array[i] );
      if (i == (num - 1))
      {
         printf("\n");
      }
   }

   return 0;
}
