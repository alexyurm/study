/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for sort algorithms */

import java.io.*;

public class SortTest
    extends junit.framework.TestCase
{

  final static int testsize = 1000;

  static Integer A[];

  static int THRESHOLD = 8;

static <E extends Comparable<? super E>>
void Sort(E[] A) {
  for (int i=A.length/2; i>2; i/=2) // For each increment
    for (int j=0; j<i; j++)         // Sort each sublist
      inssort2(A, j, i);
  inssort2(A, 0, 1);     // Could call regular inssort here
}

/** Modified Insertion Sort for varying increments */
static <E extends Comparable<? super E>>
void inssort2(E[] A, int start, int incr) {
  for (int i=start+incr; i<A.length; i+=incr)
    for (int j=i; (j>=incr)&&
                  (A[j].compareTo(A[j-incr])<0); j-=incr)
      DSutil.swap(A, j, j-incr);
}

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    A = new Integer[testsize];
    for (int i=0; i<testsize; i++)
      A[i] = new Integer(DSutil.random(32003));
  }

  /** Successful calls to fact */
  public void testIt() {
    Sort(A);
    for (int i=1; i<testsize; i++)
      assert A[i-1].compareTo(A[i]) <= 0 : "Array not sorted";
  }
}
