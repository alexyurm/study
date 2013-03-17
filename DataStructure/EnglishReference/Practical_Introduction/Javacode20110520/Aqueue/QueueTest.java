/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for queues. */

import java.io.*;

public class QueueTest
    extends junit.framework.TestCase
{
  private Queue<Integer> Q1;
  private Queue<Integer> Q2;

  /**
   * This method is automatically called once before each test case method,
   * so that all the variables are cleanly initialized for each test.
   */
  public void setUp()
  {
    Q1 = new AQueue<Integer>();
    Q2 = new AQueue<Integer>(15);
  }

  public void testGeneral() {
    int temp;
    Q2.enqueue(10);
    Q2.enqueue(20);
    Q2.enqueue(15);
    assertEquals("< 10 20 15 >", Q2.toString());
    while(Q2.length() > 0) {
      temp = Q2.dequeue();
      Q1.enqueue(temp);
    }
    assertEquals("< >", Q2.toString());
    assertEquals("< 10 20 15 >", Q1.toString());

  }
}
