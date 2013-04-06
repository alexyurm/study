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
*/

class QuickSort {
   private int []A;
   private int p;
   private int q;
   private int r;
   
   public void partition(int[] A, int p, int r) {
      int x = A[r];
      int i = p - 1;
      for (int j = p; j < r; j++) {
         if (A[j] <= x) {
            i = i + 1;
         }
      }
   }

   public void qSort(A, p, r) {
      if (p < r) {
         q = partition(A, p, r);
         qSort(A, p, q-1);
         qSort(A, q+1, r);
      }
   }
}







