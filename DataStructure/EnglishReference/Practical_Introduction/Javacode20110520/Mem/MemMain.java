/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;
import java.nio.*;

public class MemMain {

public static void main(String args[]) throws IOException {

  MemManager mymem = new MemManager(15);
  byte[] stuff = new byte[3];
  for (int i=0; i<3; i++)
    stuff[i] = (byte)(i+10);

System.out.println("Do first request");
  MemHandle h = mymem.insert(stuff);

  byte[] dum = mymem.get(h);

System.out.println("Do second request");
  MemHandle h2 = mymem.insert(stuff);

  mymem.release(h);

  System.in.read();
} // main

} // class MemMain
