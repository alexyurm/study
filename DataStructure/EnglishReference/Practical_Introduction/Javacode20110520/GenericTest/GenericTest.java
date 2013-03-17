/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

// Test showing pitfalls of generic array allocation
class GenericTest {

  public static <Key,E> void foo(Key a, E b) {
    System.out.println("First we allocate an array of Keys where Key is Integer.");
    // This gives a compiler warning. But it will work correctly.
    Key[] A1 = (Key[])new Object[10];

    System.out.println("Now we allocate an array of E where E is KVpair<Integer,Integer>");
    // This also gives a compiler warning and will work correctly.
    E[] A2 = (E[])new Object[10];
  }

public static void main(String args[]) throws IOException {
  Integer a=0;
  KVpair<Integer,Integer>b = new KVpair<Integer,Integer>(1, 1);

  System.out.println("Let's start by showing what we can do.");
  foo(a, b);

  System.out.println("Now, let's try the impossible: Allocate an array of KVpair<Integer, Integer>.");
  KVpair<Integer,Integer>[] A2 = (KVpair<Integer,Integer>[])new KVpair[10];

}
}
