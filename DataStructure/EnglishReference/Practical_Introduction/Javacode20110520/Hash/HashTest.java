/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the factorial function. */

import java.io.*;
import java.math.*;

public class HashTest
    extends junit.framework.TestCase
{

long sfold(String s, int M) {

  int intLength = s.length() / 4;
  long sum = 0;
  for (int j = 0; j < intLength; j++) {
    char c[] = s.substring(j*4,(j*4)+4).toCharArray();
    long mult = 1;
    for (int k = 0; k < c.length; k++) {
      sum += c[k] * mult;
      mult *= 256;
    }
  }

  char c[] = s.substring(intLength * 4).toCharArray();
  long mult = 1;
  for (int k = 0; k < c.length; k++) {
    sum += c[k] * mult;
    mult *= 256;
  }

  return(Math.abs(sum) % M);
}
int h(String x, int M) {
  char ch[];
  ch = x.toCharArray();
  int xlength = x.length();

  int i, sum;
  for (sum=0, i=0; i<x.length(); i++)
    sum += ch[i];
  return sum % M;
}
int h(int x) {
  return(x % 16);
}


  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
  }


  public void testHash() {
    HashTable<Integer,String> T = new HashTable<Integer,String>(101);

    System.out.println("Hello is " + h(101));
    System.out.println("Result of searching for 10 is " + T.hashSearch(10));
    System.out.println("Result of searching for 11 is " + T.hashSearch(11));
  }

  public void testStringHash1() {
    assertEquals(65, sfold("A", 256));
    assertEquals(66, sfold("B", 256));
    assertEquals(65, sfold("ABC", 256));
    assertEquals(130, sfold("ABCDA", 256));
    assertEquals(97, sfold("a", 256));
    assertEquals(98, sfold("b", 256));
    assertEquals(162, sfold("ABCDa", 0x100));
    assertEquals(16961, sfold("ABC", 0x10000));
    assertEquals(17058, sfold("ABCDa", 0x10000));
    assertEquals(42211, sfold("ABCDabcdA", 0x10000));
    assertEquals(75, sfold("aaaabbbb", 101));  // Example in book
  }

}
