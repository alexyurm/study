import java.util.*;
import java.lang.Integer;

/* The quicksort algorithm has a worst-case running time of O(n.^2) on an input array of n numbers.
*  Despite this slow worst-case running time, quicksort is often the best practical choice for sorting
*  because it is remarkably efficient on the average(!!). It's expected running time is O(nlgn). And the
*  constant factors hidden in the O(nlgn) notation are quite small. It works quite well even in virtual-memory
*  environments
*
*  QuickSort, likes merge sort, applies the divide-and-conquer paradigm. Here is the three-step divide-and-conquer
*  for sorting a typical subarray A[p..r]:
*  
*  Divide:  Partition(rearrange) the array A[p..r] into two (possibly empty) subarrays A[p..q-1] and A[q+1..r] such that 
*           each element in A[p..q-1] is less than or equal to A[q], which is, in turn, less than or equal to each element
*           of A[q+1..r]. Compute the index q as part of this partitioning procedure.
*
*  Conquer: Sort the two subarrays A[p..q-1] and A[q+1, r] by recursive calls to quicksort.
*
*  Combine: Because the subarrays are already sorted, no work is needed to combine them: the entire array[p,..r] is now sorted.
*
*  -  At the beginning of each iteration of the loop in partition, for any array index k, 
*     
*     1. If p<=k<=i, then A[k]<=x
*     2. If i+1<=k<=j-1, then A[k]>x
*     3. If k=r, then A[k]=x
*
*  -  Worst case partition:   The worst case behavior for quicksort occurs when the partitioning routine produces one subproblem
*                             with n-1 elements and one with 0 elements(why 0 element instead of 1 element??).
*
*
*
* -   Best-case partitioning: In the most even possible split, PARTITION produces two subproblems, each of size no more than n/2. 
*                             each of size no more than n/2, since one is of size floor(n/2) and one of size ceil(n/2)-1.
*
*
*
*/


class QuickSort {
   private int[] A;
   private int p;
   private int r;
   
   QuickSort() {
      p =  r = 0;
   }

   QuickSort(int[] array, int lower, int upper) {
      A = new int[array.length];
      System.arraycopy(array, 0, A, 0, array.length); 

      p = lower;
      r = upper;
   }

   public int partition(int p, int r) {
      int x = A[r];
      int i = p - 1;
      for (int j = p; j < r; j++) {
         if (A[j] <= x) {
            i = i + 1;
            swap(i, j);
         }
      }
      swap(i+1, r);
      return i+1;
   }

   /* sort into nonincreasing order */
   public int partition2(int p, int r) {
      int x = A[r];

      int i = p - 1;
      for (int j = p; j < r; j++) {
         if (A[j] > x) {
            i = i + 1;
            swap(i, j);
         }
      }
      swap(i+1, r);
      return i+1;

   }

   public void swap(int i, int j) {
      if  ((i >= p) && (i <= r) && (j >= p) && (j <= r)) {
         int temp = A[i];
         A[i] = A[j];
         A[j] = temp;
      }
   }

   public void qSort(int lower, int upper) {
      if (lower < upper) {
         int q = partition(lower, upper);

         qSort(lower, q-1);
         qSort(q+1, upper);
      }
   }

   void showArray() {
      for (int i = 0; i < A.length; i++) {
         if (i+1 == A.length) {
            System.out.println(A[i]);
         } else {
            System.out.print( A[i] + ", ");
         }
      }
   }

   public static void main(String[] args) {
      //int[] array = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
      int[] array = new int[]{2, 8, 7, 4, 3, 5, 6, 1};

      QuickSort qs = new QuickSort(array, 0, array.length-1);
      
      qs.qSort(0, array.length-1);

      qs.showArray();

      return;
   }
}







