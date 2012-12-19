#include <stdio.h> 
#include <stdlib.h>

void merge(int A[], int p, int q, int r)
{
   int n1 = q - p + 1; /* n1: the number of the array L */
   int n2 = r - q; /* n2: the number of the array R */

   int L[n1+1];
   int R[n2+1];
   
   int i, j, k;
   for (i = 0; i < n1; i++)
   {
      L[i] = A[p+i]; /* Make sure the index is correct */
   }
   L[n1] = -1; /* Create an extra element at the end and set it to -1 */

   for (j = 0; j < n2; j++)
   {
      R[j] = A[q+j+1]; /* Make sure the index is correct */
   }
   R[n2] = -1; /* Create an extra element at the end and set it to -1 */

   i = j = 0;
   for (k = p; k <= r; k++)
   {
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
               A[k] = L[i];
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
      q = (r + p) / 2;
      mergesort(A, p, q);
      mergesort(A, q+1, r);
      merge(A, p, q, r);
   }
}

int main()
{
   int i = 0;
   int a[16] = {1, 23, 56, 23, 192, 1765, 61, 222, 190, 637, 12, 332, 516, 123, 1177, 367};
   mergesort(a, 0, 15);

   while( i < 16 )
   {
      printf("%d ", a[i]);
      i++;
   }
   printf("\n");
}
