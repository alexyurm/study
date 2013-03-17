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
/** Compute the moves to solve a Tower of Hanoi puzzle.
    Function move does (or prints) the actual move of a disk
    from one pole to another.
    @param n The number of disks
    @param start The start pole
    @param goal The goal pole
    @param temp The other pole */
static void TOH(int n, Pole start, Pole goal, Pole temp) {
  if (n == 0) return;          // Base case
  TOH(n-1, start, temp, goal); // Recursive call: n-1 rings
  move(start, goal);           // Move bottom disk to goal
  TOH(n-1, temp, goal, start); // Recursive call: n-1 rings
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
