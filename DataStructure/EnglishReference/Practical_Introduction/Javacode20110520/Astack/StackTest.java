/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for stacks. */

import java.io.*;

public class StackTest
    extends junit.framework.TestCase
{
  private Stack<Integer> S1;
  private Stack<Integer> S2;

  /**
   * This method is automatically called once before each test case method,
   * so that all the variables are cleanly initialized for each test.
   */
  public void setUp()
  {
    S1 = new AStack<Integer>();
    S2 = new AStack<Integer>(15);
  }

  public void testGeneral() {
    S2.push(10);
    S2.push(20);
    S2.push(15);
    assertEquals("< 15 20 10 >", S2.toString());
    while(S2.length() > 0)
      S1.push(S2.pop());
    assertEquals("< 10 20 15 >", S1.toString());
    assertEquals("< >", S2.toString());
  }

}
