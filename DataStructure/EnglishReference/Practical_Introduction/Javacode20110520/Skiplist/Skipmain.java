/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class Skipmain {

public static void main(String args[]) throws IOException {
  SkipList<Integer, Integer> dum = new SkipList<Integer, Integer>();

  dum.insert(10, 10);
  dum.print();
  dum.insert(5, 5);
  dum.print();
  dum.insert(20, 20);
  dum.print();
  System.out.println("Search for 10: " + dum.find(10));
  System.out.println("Search for 15: " + dum.find(15));
  System.out.println("Search for 20: " + dum.find(20));
  System.out.println("Search for 25: " + dum.find(25));
}

}
