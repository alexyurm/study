/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class KDmain {

public static void main(String args[]) throws IOException {
  KDtree<String> dum = new KDtree<String>();
  int[] p1 = {30, 34};
  int[] p2 = {30, 35};
  int[] s1 = {20, 20};

  dum.insert(p1, "Blacksburg");
  dum.insert(p2, "Christiansburg");
  dum.insert(s1, "Radford");
  dum.print();
  dum.regionSearch(s1, 20);
  if(dum.find(p1) != null)
	System.out.println("Found 30, 34");
  if (dum.find(p2) != null)
	System.out.println("Found 30, 35");
}

}
