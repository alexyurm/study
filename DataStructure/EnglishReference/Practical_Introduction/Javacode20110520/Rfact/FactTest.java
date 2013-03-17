/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the factorial function. */

import java.io.*;

public class FactTest
    extends junit.framework.TestCase
{

/** Compute the factorial function.
    @param n The value to compute factorial of
    @return The factorial of {@link n}
*/
/** Recursively compute and return n! */
static long fact(int n) {
  // fact(20) is the largest value that fits in a long
  assert (n >= 0) && (n <= 20) : "n out of range";
  if (n <= 1)  return 1;  // Base case: return base solution
  return n * fact(n-1);   // Recursive call for n > 1
}

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
  }

  /** Successful calls to fact */
  public void testGood() {
    assertEquals(720, fact(6));
    assertEquals(1, fact(0));
    assertEquals(1, fact(1));
    assertEquals(121645100408832000L, fact(19));
    assertEquals(2432902008176640000L, fact(20));
  }

  /** Call to fact that is too big */
  public void testBadHi() {
    // We really need to figure out a way to do this that doesn't
    // include a control variable
    boolean assertTriggered = true;
    try
    {
      fact(21);
      assertTriggered = false;
    }
    catch (AssertionError e)
    {
      // do nothing
    }
    assert assertTriggered : "The assert didn't work!";
  }


  /** Call to fact that is too small */
  public void testBadLow() {
    // We really need to figure out a way to do this that doesn't
    // include a control variable
    boolean assertTriggered = true;
    try
    {
      fact(-1);
      assertTriggered = false;
    }
    catch (AssertionError e)
    {
    // do nothing
    }
    assert assertTriggered : "The assert didn't work!";
  }
}
