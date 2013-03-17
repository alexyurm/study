/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the payroll dictionary. */

import java.io.*;

public class SortedTest
    extends junit.framework.TestCase
{

  private SortedList<Integer,Integer> L1;
  private SortedList<String,String> L2;
  private SortedList<String,Payroll> L3;

  /**
   * This method is automatically called once before each test case method,
   * so that all the variables are cleanly initialized for each test.
   */
  public void setUp()
  {
    L1 = new SAList<Integer,Integer>();
    L2 = new SAList<String,String>(15);
    L3 = new SAList<String,Payroll>();
  }

  // Return true if "k" matches the key for an element in list "L",
  // false otherwise
  public static <Key,E> E find(SortedList<Key,E> L, Key k ) {
    Key itkey;
    for (L.moveToStart(); L.currPos()<L.length(); L.next()) {
      itkey = L.getKey();
      if (k == itkey) return L.getValue();
    }
    return null;                // K not found
  }

  public void testSorted() {
    int sumlength, temp1, temp2;
    Integer L1val;

    L1.moveToStart();
    L1.insert(39, 39);
    L1.next();
    L1.insert(9, 9);
    L1.insert(5, 5);
    assertEquals(L1.toString(), "< | 5 9 39 >");
    L1.moveToStart();
    assertEquals(find(L1, 3), null);
    assertEquals(L1.toString(), "< 5 9 39 | >");
    L1.moveToStart();
    L1val = find(L1, 9);
    assertEquals(L1val, new Integer(9));
    L1val = find(L1, 5);
    assertEquals(L1val, new Integer(5));
    assertEquals(L1.length(), 3);

    L2.insert("X", "X");
    assertEquals(L2.toString(), "< | X >");
    L2.insert("A", "A");
    assertEquals(L2.toString(), "< | A X >");
    L2.insert("Y", "Y");
    assertEquals(L2.toString(), "< A X | Y >");
    String temp2I = L2.remove();
    assertEquals(temp2I, "Y");
    assertEquals(L2.toString(), "< A X | >");
  }
}
