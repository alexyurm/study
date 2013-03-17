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

/** Insertion Sort for subarrays: sort len elements from index start */
static <E extends Comparable<? super E>>
void inssort(E[] A, int start, int len) {
  for (int i=start+1; i<start+len; i++)        // Insert i'th record
    for (int j=i; (j>start) &&
                  (A[j].compareTo(A[j-1])<0); j--)
      DSutil.swap(A, j, j-1);
}
static <E extends Comparable<? super E>>
void mergesort(E[] A, E[] temp, int l, int r) {
  int i, j, k, mid = (l+r)/2;  // Select the midpoint
  if (l == r) return;          // List has one element
  if ((mid-l) >= THRESHOLD) mergesort(A, temp, l, mid);
  else inssort(A, l, mid-l+1);
  if ((r-mid) > THRESHOLD) mergesort(A, temp, mid+1, r);
  else inssort(A, mid+1, r-mid);
  // Do the merge operation.  First, copy 2 halves to temp.
  for (i=l; i<=mid; i++) temp[i] = A[i];
  for (j=1; j<=r-mid; j++) temp[r-j+1] = A[j+mid];
  // Merge sublists back to array
  for (i=l,j=r,k=l; k<=r; k++)
    if (temp[i].compareTo(temp[j])<0) A[k] = temp[i++];
    else A[k] = temp[j--];
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
