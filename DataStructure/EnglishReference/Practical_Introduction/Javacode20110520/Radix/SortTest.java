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

static int[] POW2 = {1, 2, 4, 8, 16, 32, 64, 128, 256};

@SuppressWarnings("unchecked") // Generic array allocation
static void Sort(Integer[] A) {
  assert THRESHOLD > 0 :
         "Usage: Sortmain [+/-] <size> <threshold>, " +
         "MUST SET THRESHOLD AS NUMBER OF BITS IN RADIX";

  Integer[] temp = new Integer[A.length];
  int[] count = new int[POW2[THRESHOLD]];

  radix(A, temp, 16/THRESHOLD, POW2[THRESHOLD], count);
}

static void radix(Integer[] A, Integer[] B,
                  int k, int r, int[] count) {
  // Count[i] stores number of records in bin[i]
  int i, j, rtok;

  for (i=0, rtok=1; i<k; i++, rtok*=r) { // For k digits
    for (j=0; j<r; j++) count[j] = 0;    // Initialize count

    // Count the number of records for each bin on this pass
    for (j=0; j<A.length; j++) count[(A[j]/rtok)%r]++;

    // count[j] will be index in B for last slot of bin j.
    for (j=1; j<r; j++) count[j] = count[j-1] + count[j];

    // Put records into bins, working from bottom of bin
    // Since bins fill from bottom, j counts downwards
    for (j=A.length-1; j>=0; j--)
      B[--count[(A[j]/rtok)%r]] = A[j];

    for (j=0; j<A.length; j++) A[j] = B[j]; // Copy B back
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
