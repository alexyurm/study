/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class Test2 {

public static void main(String args[]) throws IOException {

  assert args.length == 1 : "Usage: Test2 <name>";

  File it = new File(args[0]);

  if (it.exists()) {
    System.out.println("File named " + args[0] + " exists -- delete it");
    it.delete();
  }
  else
    System.out.println("File named " + args[0] + " does not exist");
}

}
