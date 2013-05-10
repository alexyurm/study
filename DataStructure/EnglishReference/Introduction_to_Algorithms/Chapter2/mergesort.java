import java.lang.*;
import java.util.*;

class MergeSort {
   public static <E extends Comparable<? super E>> void merge(ArrayList<E> A, int p, int q, int r) {
      int n1 = q - p + 1;  /* n1: the number of the array L(left) */
      int n2 = r - q;      /* n2: the number of the array R(right) */

      ArrayList<E> L = new ArrayList<E>();
      ArrayList<E> R = new ArrayList<E>();
      
      int i, j, k;

      for (i = 0; i < n1; i++) {
         L.add(A.get(p+i));
      }
      L.add(null);//Add an extra element at the end and set it to null

      for (j = 0; j < n2; j++) {
         R.add(A.get(q+1+j));
      }
      R.add(null);//Add an extra element at the end and set it to null
      
      i = j = 0;
      for (k = p; k < r; k++) {
         if ( L.get(i).compareTo(R.get(j)) <= 0 ) {
            A.set(k, L.get(i));
            i++;
            //Reach the end of L
            if (L.get(i) == null) {
               k++; //The loop is not finished yet so k is incremented by 1 here.
               //Copy the rest of the elements from R to A.
               while(R.get(j) != null) {
                  A.set(k, R.get(j));
                  j++;
                  k++;
               }
            }
         } else {
            A.set(k, R.get(j));
            j++;
            //Reach the end of R
            if (R.get(j) == null) {
               k++; //The loop is not finished yet so k is incremented by 1 here.
               //Copy the rest of the elements from L to A.
               while(L.get(i) != null) {
                  //A[k] = L[i];
                  A.set(k, L.get(i));
                  i++;
                  k++;
               }
            }
         }
      }
   }

   public static <E extends Comparable<? super E>> void mergesort(ArrayList<E> A, int p, int r) {
      //Make sure p is less than r: 1) if p == r, there is nothing to do because we are merging sort A[p], which is an element only; 2) if p > r, then order is not allowed.
      if ( p < r ) {
         int q = (p + r) / 2;
         mergesort(A, p, q);
         mergesort(A, q+1, r);//be careful, here the starting index is q+1 NOT q. 
                              //If merrgesort starts from q here, Infinite loop can happen. E.g. p = 14, r = 15 (q = (p+r)/2 = 14).
         merge(A, p, q, r);
      }
   }

   public static void main(String[] args) {
      Integer[] arrayInteger = new Integer[]{1, 23, 56, 192, 1765, 61, 222, 190, 23, 637, 12, 332, 516, 123, 1177, 367};
      ArrayList<Integer>A = new ArrayList<Integer>();
      for (int i = 0; i < arrayInteger.length; i++) {
         A.add(arrayInteger[i]);
      }

      mergesort(A, 0, A.size()-1);
      System.out.println(A);
   }
}
