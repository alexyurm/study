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


@SuppressWarnings("unchecked") // Generic array allocation
static <E extends Comparable<? super E>> void Sort(E[] A) {
  E[] temp = (E[])new Comparable[A.length];
  mergesort(A, temp, 0, A.length-1);
}

static <E extends Comparable<? super E>>
void mergesort(E[] A, E[] temp, int l, int r) {
  int mid = (l+r)/2;                // Select midpoint
  if (l == r) return;               // List has one element
  mergesort(A, temp, l, mid);   // Mergesort first half
  mergesort(A, temp, mid+1, r); // Mergesort second half
  for (int i=l; i<=r; i++)          // Copy subarray to temp
    temp[i] = A[i];
  // Do the merge operation back to A
  int i1 = l; int i2 = mid + 1;
  for (int curr=l; curr<=r; curr++) {
    if (i1 == mid+1)              // Left sublist exhausted
      A[curr] = temp[i2++];
    else if (i2 > r)              // Right sublist exhausted
      A[curr] = temp[i1++];
    else if (temp[i1].compareTo(temp[i2])<0) // Get smaller
      A[curr] = temp[i1++];
    else A[curr] = temp[i2++];
  }
}

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
