/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// Sorting main function for testing correctness of sort algorithm.
// To use: <sortname> [+/-] <size_of_array> <threshold>
//  + means increasing values, - means decreasing value and no
//    parameter means random values;
// where <size_of_array> controls the number of objects to be sorted;
// and <threshold> controls the threshold parameter for certain sorts, e.g.,
//   cutoff point for quicksort sublists.

import java.io.*;

public class Sortmain {

static int THRESHOLD = 0;

static int ARRAYSIZE = 0;
static int[] POW2 = {1, 2, 4, 8, 16, 32, 64, 128, 256};

@SuppressWarnings("unchecked") // Generic array allocation
static void Sort(Integer[] A) {
  assert THRESHOLD > 0 :
         "Usage: Sortmain [+/-] <size> <threshold>, " +
         "MUST SET THRESHOLD AS NUMBER OF BITS IN RADIX";

  Integer[] temp = new Integer[A.length];
  int[] count = new int[POW2[THRESHOLD]];

  radix(A, temp, 16/THRESHOLD, POW2[THRESHOLD], count);
}

static void radix(Integer[] A, Integer[] B,
                  int k, int r, int[] count) {
  // Count[i] stores number of records in bin[i]
  int i, j, rtok;

  for (i=0, rtok=1; i<k; i++, rtok*=r) { // For k digits
    for (j=0; j<r; j++) count[j] = 0;    // Initialize count

    // Count the number of records for each bin on this pass
    for (j=0; j<A.length; j++) count[(A[j]/rtok)%r]++;

    // count[j] will be index in B for last slot of bin j.
    for (j=1; j<r; j++) count[j] = count[j-1] + count[j];

    // Put records into bins, working from bottom of bin
    // Since bins fill from bottom, j counts downwards
    for (j=A.length-1; j>=0; j--)
      B[--count[(A[j]/rtok)%r]] = A[j];

    for (j=0; j<A.length; j++) A[j] = B[j]; // Copy B back
  }
}

public static void main(String args[]) throws IOException {
  assert args.length >= 1 : "Usage: Sortmain [+/-] <size> <threshold>";

System.out.println("Args: " + args.length);
  int i;
  int input = 0;
  int currarg = 0;
  if (args[currarg].charAt(0) == '+') { input = 1; currarg++; }
  else if (args[currarg].charAt(0) == '-') { input = -1; currarg++; }

  ARRAYSIZE = Integer.parseInt(args[currarg++]);
  if (args.length > currarg)
	THRESHOLD = Integer.parseInt(args[currarg]);


  Integer[] A = new Integer[ARRAYSIZE];

  System.out.println("Input: " + input +
          ", size: " + ARRAYSIZE + ", threshold: " + THRESHOLD);

  if (input == -1)
    for (i=0; i<ARRAYSIZE; i++)
      A[i] = new Integer(ARRAYSIZE - i);  // Reverse sorted
  else if (input == 0)
    for (i=0; i<ARRAYSIZE; i++)
      A[i] = new Integer(DSutil.random(32003));  // Random
  else
    for (i=0; i<ARRAYSIZE; i++)
      A[i] = new Integer(i);              // Sorted

  long time1 = System.currentTimeMillis();
    Sort(A);
  long time2 = System.currentTimeMillis();
  System.out.println("Time is " + (time2-time1));

  System.out.println("Done sorting");

  for (i=1; i<ARRAYSIZE; i++)
    if (A[i-1].compareTo(A[i]) > 0) {
      System.out.println("OOPS -- bad sort algorithm!");
        for (int j=0; j<ARRAYSIZE; j++)
          System.out.print(A[j] + " ");
      System.out.println();
      break;
    }
  System.out.println("Sorting checked out OK.");

//  System.in.read();
}

}
