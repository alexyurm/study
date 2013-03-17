/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for sort algorithms */

import java.io.*;

public class SortTest
    extends junit.framework.TestCase
{

  final static int testsize = 1000;

  static Integer A[];

  static int THRESHOLD = 8;


static <E extends Comparable<? super E>> void Sort(E[] A) {
  qsort(A, 0, A.length-1);
}

static int MAXSTACKSIZE = 1000;
static <E extends Comparable<? super E>>
void qsort(E[] A, int i, int j) {      // Quicksort
  int pivotindex = findpivot(A, i, j); // Pick a pivot
  DSutil.swap(A, pivotindex, j);       // Stick pivot at end
  // k will be the first position in the right subarray
  int k = partition(A, i-1, j, A[j]);
  DSutil.swap(A, k, j);              // Put pivot in place
  if ((k-i) > 1) qsort(A, i, k-1);   // Sort left partition
  if ((j-k) > 1) qsort(A, k+1, j);   // Sort right partition
}
static <E extends Comparable<? super E>>
int partition(E[] A, int l, int r, E pivot) {
  do {                 // Move bounds inward until they meet
    while (A[++l].compareTo(pivot)<0);
    while ((r!=0) && (A[--r].compareTo(pivot)>0));
    DSutil.swap(A, l, r);       // Swap out-of-place values
  } while (l < r);              // Stop when they cross
  DSutil.swap(A, l, r);         // Reverse last, wasted swap
  return l;      // Return first position in right partition
}
static <E extends Comparable<? super E>>
int findpivot(E[] A, int i, int j)
  { return (i+j)/2; }

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    A = new Integer[testsize];
    for (int i=0; i<testsize; i++)
      A[i] = new Integer(DSutil.random(32003));
  }

  /** Successful calls to fact */
  public void testIt() {
    Sort(A);
    for (int i=1; i<testsize; i++)
      assert A[i-1].compareTo(A[i]) <= 0 : "Array not sorted";
  }
}
