/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for Towers of Hanoi */

import java.io.*;

public class ToHTest
    extends junit.framework.TestCase
{

/** Compute the moves for Tower of Hanoi
    @param n The number of disks
    @param start The start pole
    @param goal The goal pole
    @param temp The other pole
*/
static void TOH(int n, Pole start,
                       Pole goal, Pole temp) {
  // Make a stack just big enough
  Stack<TOHobj> S = new AStack<TOHobj>(2*n+1);
  S.push(new TOHobj(operation.TOH, n,
                    start, goal, temp));
  while (S.length() > 0) {
    TOHobj it = S.pop(); // Get next task
    if (it.op == operation.MOVE) // Do a move
      move(it.start, it.goal);
    else if (it.num > 0) { // Imitate TOH recursive
                           // solution (in reverse)
      S.push(new TOHobj(operation.TOH, it.num-1,
                        it.temp, it.goal, it.start));
      S.push(new TOHobj(operation.MOVE, it.start,
                        it.goal));  // A move to do
      S.push(new TOHobj(operation.TOH, it.num-1,
                        it.start, it.temp, it.goal));
    }
  }
}

  static int counter;
  static Pole start;
  static Pole goal;
  static Pole temp;

  /** Implement a move... print out what happens */
  static void move(Pole start, Pole goal) {
    System.out.println(counter + ": Move " + start + " to " + goal);
    counter++;
  }

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    counter = 1;
    start = new Pole(1);
    goal = new Pole(2);
    temp = new Pole(3);
  }

  /** Successful call to TOH */
  public void testGood() {
    TOH(3, start, goal, temp);
  }
}
