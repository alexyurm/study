/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the convergence termination test */

import java.io.*;

public class TerminateTest
    extends junit.framework.TestCase
{

/** See if repeatedly dividing by 2 reaches termination
    @param val The start value
*/

static void foo (double val) {
  if (val != 0.0)
    foo(val/2.0);
}

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
  }

  /** Successful calls to foo */
  public void testGood() {
    foo(8.0);
    foo(5.0);
  }
}
