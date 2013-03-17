/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for binary search */

import java.io.*;

public class BsearchTest
    extends junit.framework.TestCase
{

/** Return the position of an element in a sorted int array.
    @param A The array
    @param K The value being searched for.
    @return The position of the "K" if it is in the array,
            or A.length otherwise. */
/** @return The position of an element in sorted array A
    with value k.  If k is not in A, return A.length. */
static int binary(int[] A, int k) {
  int l = -1;
  int r = A.length;   // l and r are beyond array bounds
  while (l+1 != r) {  // Stop when l and r meet
    int i = (l+r)/2;  // Check middle of remaining subarray
    if (k < A[i]) r = i;     // In left half
    if (k == A[i]) return i; // Found it
    if (k > A[i]) l = i;     // In right half
  }
  return A.length;    // Search value not in A
}

    private int size = 20;
    private int[] A = new int[size];

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    for (int i=0; i<size; i++)
      A[i] = i;
  }

  /** Various successful and unsuccessful searches */
  public void testGood() {
    assertEquals(0, binary(A, 0));
    assertEquals(1, binary(A, 1));
    assertEquals(size-1, binary(A, size-1));
    assertEquals(size, binary(A, size));
    assertEquals(size, binary(A, size+1));
  }
}

