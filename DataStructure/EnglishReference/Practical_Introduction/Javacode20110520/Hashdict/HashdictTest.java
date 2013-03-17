/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the Hash table-based dictionary. */

import java.io.*;

public class HashdictTest
    extends junit.framework.TestCase
{

  private Dictionary<Integer, Integer> D1;
  private Dictionary<String, Integer> D2;
  
  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    D1 = new HashDictionary<Integer, Integer>(101);
    D2 = new HashDictionary<String, Integer>(101);
  }

  public void testDictString() {
    Integer ten = new Integer(10);
    Integer fifteen = new Integer(15);

    D2.insert("ABC", 10);
    assertEquals(D2.find("ABC"), ten);
    D2.insert("DEFG", 15);
    assertEquals(D2.find("DEFG"), fifteen);
  }

  public void testDictDup() {
    boolean assertTriggered = true;
    try
    {
      Integer ten = new Integer(10);
      Integer fifteen = new Integer(15);

      D1.insert(10, 10);
      assertEquals(D1.find(10), ten);
      D1.insert(15, 15);
      assertEquals(D1.find(15), fifteen);
      D1.insert(10, 10);
    }
    catch (AssertionError e)
    {
      // do nothing
    }
    assert assertTriggered : "The assert didn't work!";
  }

  public void testOddKeyType() {
    Dictionary<Double, Integer> D3;
    D3 = new HashDictionary<Double, Integer>(101);
    D3.insert(5.3, 10);
  }

  public void testDictInt() {
    Integer ten = new Integer(10);
    Integer fifteen = new Integer(15);

    D1.insert(10, 10);
    assertEquals(D1.find(10), ten);
    D1.insert(15, 15);
    assertEquals(D1.find(15), fifteen);
    assertEquals(D1.size(), 2);
    assertEquals(D1.find(10), ten);
    assertEquals(D1.remove(10), ten);
    assertEquals(D1.find(10), null);
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
    for (e = D1.removeAny(); e != null; cnt++)
      e = D1.removeAny();
    assertEquals(cnt, 3);
  }
}
