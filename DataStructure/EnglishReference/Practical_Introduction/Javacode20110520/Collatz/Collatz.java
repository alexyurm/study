/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

class Collatz {

  static boolean ODD(int val) {
	  return (val & 1) != 0;
  }

  public static void main(String args[]) throws IOException {
    assert args.length == 1 : "Usage: Collatz <value>";
    int n = Integer.parseInt(args[0]);
	int temp = n;
    
    System.out.println("The start value is: " + n);

    // This is not the book's code.  That is repeated below.
	// The book's code does not have a print statement.
	while (n > 1) {
      if (ODD(n))
        n = 3 * n + 1;
      else
        n = n / 2;
      System.out.print(n + "  ");
    }

	n = temp;

	System.out.println();

/// collatz.x
while (n > 1)
  if (ODD(n))
    n = 3 * n + 1;
   else
     n = n / 2;

    System.in.read();

  }
}

