/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

// This program tests various code fragements from
// Chapter 14, mainly for syntactic correctness.

public class Ch14
    extends junit.framework.TestCase {

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
  }

  public void testCh14() {
    int[] A = new int[2];

    int sum, i, inc, j;
    int n=0;
    for (i=0; i<A.length; i++) A[i] = 1;
for (i=0; ((i<A.length) && (A[i] == 1)); i++)
  A[i] = 0;
if (i < A.length)
  A[i] = 1;
sum = 0; inc = 0;
for (i=1; i<=n; i++)
  for (j=1; j<=i; j++) {
    sum = sum + inc;
    inc++;
  }
  }
}
