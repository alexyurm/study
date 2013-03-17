/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class PermuteTest
    extends junit.framework.TestCase
{

  private static final int size = 100;
  private Integer[] A = new Integer[size];

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

  /** Exercise permute. There isn't really any good way to test this 
      other than to see if something did get moved around. But there is
      a minute possibility that the test will fail because nothing got
      moved. */
  public void testGood() {
    DSutil.permute(A);
    boolean check = false;
    for (int i=0; i<size; i++) // Somebody better change
      if (A[i] != i) check = true;
    assert check : "Nothing was permuted -- Check again to be sure";
  }
}
