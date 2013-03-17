/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the UAL dictionary. */

import java.io.*;

public class DictTest
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
    D1 = new UALdictionary<Integer, Integer>();
  }

  public void testDict() {
    Integer ten = new Integer(10);
    Integer fifteen = new Integer(15);

    D1.insert(10, 10);
    assertEquals(D1.find(10), ten);
    D1.insert(15, 15);
    assertEquals(D1.find(15), fifteen);
    D1.insert(10, 10);
    assertEquals(D1.size(), 3);
    assertEquals(D1.find(10), ten);
    assertEquals(D1.remove(10), ten);
    assertEquals(D1.find(10), ten);
    assertEquals(D1.remove(10), ten);
    assertEquals(D1.remove(10), null);
    assertEquals(D1.size(), 1);
    D1.clear();
    assertEquals(D1.size(), 0);
    assertEquals(D1.find(10), null);
    D1.insert(25, 25);
    D1.insert(30, 30);
    D1.insert(21, 21);
    Integer e;
    int cnt = 0;
    while (D1.size() > 0) {
      cnt++;
      e = D1.removeAny();
    }
    assertEquals(cnt, 3);
  }
}
