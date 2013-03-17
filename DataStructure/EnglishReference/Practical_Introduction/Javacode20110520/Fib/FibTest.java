/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the fibonacci implementations. */

import java.io.*;

public class FibTest
    extends junit.framework.TestCase
{

/** Iteratively generate and return the n'th Fibonacci
    number */
static long fibi(int n) {
  // fibr(91) is the largest value that fits in a long
  assert (n > 0) && (n <= 91) : "n out of range";
  long curr, prev, past;
  if ((n == 1) || (n == 2)) return 1;
  curr = prev = 1;     // curr holds current Fib value
  for (int i=3; i<=n; i++) { // Compute next value
    past = prev;             // past holds fibi(i-2)
    prev = curr;             // prev holds fibi(i-1)
    curr = past + prev;      // curr now holds fibi(i)
  }
  return curr;
}
/** Recursively generate and return the n'th Fibonacci
    number */
static long fibr(int n) {
  // fibr(91) is the largest value that fits in a long
  assert (n > 0) && (n <= 91) : "n out of range";
  if ((n == 1) || (n == 2)) return 1;     // Base case
  return fibr(n-1) + fibr(n-2);      // Recursive call
}

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
  }

  /** Successful calls to fibi and fibr */
  public void testGood() {
    assertEquals(1, fibi(1));
    assertEquals(1, fibr(1));
    assertEquals(1, fibi(2));
    assertEquals(1, fibr(2));
    assertEquals(5, fibi(5));
    assertEquals(5, fibr(5));
    assertEquals(4660046610375530309L, fibi(91));
  }

  /** Call to fibi that is too big */
  public void testBadHi() {
    // We really need to figure out a way to do this that doesn't
    // include a control variable
    boolean assertTriggered = true;
    try
    {
      fibi(92);
      assertTriggered = false;
    }
    catch (AssertionError e)
    {
    // do nothing
    }
    assert assertTriggered : "The assert didn't work!";
  }


  /** Call to fibi that is too small */
  public void testBadLow() {
    // We really need to figure out a way to do this that doesn't
    // include a control variable
    boolean assertTriggered = true;
    try
    {
      fibi(0);
      assertTriggered = false;
    }
    catch (AssertionError e)
    {
    // do nothing
    }
    assert assertTriggered : "The assert didn't work!";
  }
}
