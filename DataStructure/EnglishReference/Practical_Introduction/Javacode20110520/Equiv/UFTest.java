/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for UNION/FIND. */

import java.io.*;

public class UFTest
    extends junit.framework.TestCase
{


  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
  }

  public void testUF() {
    ParPtrTree it = new ParPtrTree(10);

    if (it.differ(1, 2)) it.UNION(1, 2);
    if (it.differ(3, 4)) it.UNION(3, 4);
    if (it.differ(6, 5)) it.UNION(6, 5);
    if (it.differ(4, 1)) it.UNION(4, 1);
    if (it.differ(4, 1)) it.UNION(4, 1);
    it.UNION(4, 1);
    assertEquals(it.print(), "-1 1 2 -1 1 5 -1 -1 -1 -1 ");
  }
}
