/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class MinMaxTest
    extends junit.framework.TestCase
{

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

  public void testGood() {
    DSutil.permute(A);
    int[] Out = new int[2];
    DSutil.MinMax(A, 0, 99, Out);
    assert Out[0] == 0 : "Not min";
    assert Out[1] == 99 : "Not max";
  }
}
