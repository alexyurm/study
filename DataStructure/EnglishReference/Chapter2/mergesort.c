#include <stdio.h> 
#include <stdlib.h>

void merge(int A[], int p, int q, int r)
{
   int n1 = q - p + 1;
   int n2 = r - q;

   printf("p = %d\n", r);
   printf("q = %d\n", q);
   printf("r = %d\n", r);
   printf("n1 = %d\n", n1);
   printf("n2 = %d\n", n2);

   int L[n1+1];
   int R[n2+2];
   
   int i, j, k;
   for (i = 0; i < n1; i++)
   {
      L[i] = A[p+i];
   }
   L[n1+1] = -1;

   for (j = 0; j < n2; j++)
   {
      R[j] = A[q+1+j];
   }
   R[j+1] = -1;

   i = j = 0;
   for (k = p; k <= r; k++)
   {
      printf("k = %d\n", k);
      printf("i = %d\n", i);
      printf("j = %d\n", j);

      if (L[i] <= R[j])
      {
         A[k] = L[i];
         i++;
         if (L[i] == -1)
         {
            k++;
            /* Copy the rest of the elements from R to A */
            while (R[j] != -1)
            {
               A[k] = R[j];
               k++;
               j++;
            }
         }
      }
      else
      {
         A[k] = R[j];
         j++;
         if (R[j] == -1)
         {
            k++;
            /* Copy the rest of the elements from L to A */
            while (L[i] != -1)
            {
               A[k] = R[i];
               k++;
               i++;
            }
         }
      }
   }
}

void mergesort(int A[], int p, int r)
{
   int q;
   if (p < r)
   {
      q = (r - p) / 2;
      mergesort(A, p, q);
      mergesort(A, q+1, r);
      merge(A, p, q, r);
   }
}

int main()
{
   int i = 0;
   int a[16] = {1, 23, 56, 23, 45, 12, 67, 22, 90, 67, 2, 32, 56, 23, 77, 67};
   mergesort(a, 0, 15);
   while( i < 16)
   {
      printf("%d ", a[i]);
   }
   printf("\n");
}
