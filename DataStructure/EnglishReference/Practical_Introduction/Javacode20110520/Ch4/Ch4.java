/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis, 3rd Edition (Java)"
// by Clifford A. Shaffer, Prentice Hall, 2008.
// Copyright 2008 by Clifford A. Shaffer

import java.io.*;

// This program tests a bunch of various code fragements from
// Chapter 4, mainly for syntactic correctness.
class Ch4 {

  public static void doSomething(Integer it) { }

  public static void main(String args[]) throws IOException {
    List<Integer> L = new AList<Integer>();
    List<Integer> L1 = new AList<Integer>();
    List<Integer> L2 = new AList<Integer>();
    Integer it;
    int a=0, b=0, c=0;
    Dictionary<Integer, Integer> dict = new UALdictionary<Integer, Integer>();
L1.append(10);
L1.append(20);
L1.append(15);
L2.append(10);
L2.append(20);
L2.append(15);
L2.moveToStart();
L2.insert(39);
L2.next();
L2.insert(12);
for (L.moveToStart(); L.currPos()<L.length(); L.next()) {
  it = L.getValue();
  doSomething(it);
}
a = a + b;
b = a - b; // Now b contains original value of a
a = a - b; // Now a contains original value of b
while (dict.size() > 0) {
  it = dict.removeAny();
  doSomething(it);
}
  }
}
