/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.util.*;
import java.math.*;

/** A bunch of utility functions. */
class DSutil<E> {

  /** Swap two Objects in an array
      @param A The array
      @param p1 Index of one Object in A
      @param p2 Index of another Object A
  */
  public static <E> void swap(E[] A, int p1, int p2) {
    E temp = A[p1];
	A[p1] = A[p2];
	A[p2] = temp;
  }

  /** Randomly permute the Objects in an array.
      @param A The array
  */

// int version
// Randomly permute the values of array "A"
static void permute(int[] A) {
  for (int i = A.length; i > 0; i--) // for each i
    swap(A, i-1, DSutil.random(i));  //   swap A[i-1] with
}                                    //   a random element

  public static void swap(int[] A, int p1, int p2) {
    int temp = A[p1];
	A[p1] = A[p2];
	A[p2] = temp;
  }

/** Randomly permute the values in array A */
static <E> void permute(E[] A) {
  for (int i = A.length; i > 0; i--) // for each i
    swap(A, i-1, DSutil.random(i));  //   swap A[i-1] with
}                                    //   a random element
/** @return The minimum and maximum values in A
    between positions l and r */
static void MinMax(int A[], int l, int r, int Out[]) {
  if (l == r) {        // n=1
    Out[0] = A[r];
    Out[1] = A[r];
  }
  else if (l+1 == r) { // n=2
    Out[0] = Math.min(A[l], A[r]);
    Out[1] = Math.max(A[l], A[r]);
  }
  else {               // n>2
    int[] Out1 = new int[2];
    int[] Out2 = new int[2];
    int mid = (l + r)/2;
    MinMax(A, l, mid, Out1);
    MinMax(A, mid+1, r, Out2);
    Out[0] = Math.min(Out1[0], Out2[0]);
    Out[1] = Math.max(Out1[1], Out2[1]);
  }
}

  /** Initialize the random variable */
  static private Random value = new Random(); // Hold the Random class object

  /** Create a random number function from the standard Java Random
      class. Turn it into a uniformly distributed value within the
      range 0 to n-1 by taking the value mod n.
      @param n The upper bound for the range.
      @return A value in the range 0 to n-1.
  */
  static int random(int n) {
	return Math.abs(value.nextInt()) % n;
  }

}
