/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for finding the largest value in an array */

import java.io.*;

public class LargeTest
    extends junit.framework.TestCase
{

/** Find the largest value in an array.
    @param A The array (of type int)
    @return The position of the largest value
*/
/** @return Position of largest value in  array A */
static int largest(int[] A) {
  int currlarge = 0; // Holds largest element position
  for (int i=1; i<A.length; i++)   // For each element
    if (A[currlarge] < A[i])      // if A[i] is larger
       currlarge = i;       //   remember its position
  return currlarge;         // Return largest position
}

  private static final int size = 100;
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

  /** Exercise largest. */
  public void testGood() {
    assertEquals(size-1, largest(A));
  }
}
