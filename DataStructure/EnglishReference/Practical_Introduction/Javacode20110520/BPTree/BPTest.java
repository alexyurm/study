/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the B+ tree-based dictionary. */

import java.io.*;

public class BPTest
    extends junit.framework.TestCase
{

  private Dictionary<Integer, Integer> D1;
  
  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    D1 = new BPTree<Integer, Integer>();
  }

  public void testDict() {
  }
}
