/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the 2-3 tree-based dictionary. */

import java.io.*;

public class TTTest
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
    D1 = new TTTree<Integer, Integer>();
  }

  public void testDict() {
    assertEquals(D1.size(), 0);
    D1.insert(new Integer(10), new Integer(10));
    assertEquals(D1.size(), 1);
    assertEquals(D1.toString(), "10");
    D1.clear();
    assertEquals(D1.size(), 0);
    assertEquals(D1.toString(), "");
    D1.insert(new Integer(10), new Integer(10));
    assertEquals(D1.size(), 1);
    assertEquals(D1.toString(), "10");
    D1.insert(new Integer(15), new Integer(15));
    assertEquals(D1.toString(), "10 15");
    assertEquals(D1.find(10), new Integer(10));
    assertEquals(D1.find(20), null);
    D1.insert(new Integer(35), new Integer(35));
    assertEquals(D1.toString(), "35T10T15T");
    D1.insert(new Integer(1), new Integer(1));
    D1.insert(new Integer(30), new Integer(30));
    D1.insert(new Integer(17), new Integer(17));
    D1.insert(new Integer(19), new Integer(19));
    D1.insert(new Integer(100), new Integer(100));
    D1.insert(new Integer(90), new Integer(90));
    D1.insert(new Integer(95), new Integer(95));
    assertEquals(D1.toString(), "30T19TT1TT17TTT35 100TT10TT90 95TT15T");
    assertEquals(D1.find(99), null);
    assertEquals(D1.find(95), new Integer(95));
    assertEquals(D1.find(30), new Integer(30));
  }
}
