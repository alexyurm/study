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

@SuppressWarnings("unchecked") // Generic array allocation
static <E extends Comparable<? super E>> void Sort(E[] A) {
  E[] temp = (E[])new Comparable[A.length];
  mergesort(A, temp, 0, A.length-1);
}

/** Insertion Sort for subarrays: sort len elements from index start */
static <E extends Comparable<? super E>>
void inssort(E[] A, int start, int len) {
  for (int i=start+1; i<start+len; i++)        // Insert i'th record
    for (int j=i; (j>start) &&
                  (A[j].compareTo(A[j-1])<0); j--)
      DSutil.swap(A, j, j-1);
}
static <E extends Comparable<? super E>>
void mergesort(E[] A, E[] temp, int l, int r) {
  int i, j, k, mid = (l+r)/2;  // Select the midpoint
  if (l == r) return;          // List has one element
  if ((mid-l) >= THRESHOLD) mergesort(A, temp, l, mid);
  else inssort(A, l, mid-l+1);
  if ((r-mid) > THRESHOLD) mergesort(A, temp, mid+1, r);
  else inssort(A, mid+1, r-mid);
  // Do the merge operation.  First, copy 2 halves to temp.
  for (i=l; i<=mid; i++) temp[i] = A[i];
  for (j=1; j<=r-mid; j++) temp[r-j+1] = A[j+mid];
  // Merge sublists back to array
  for (i=l,j=r,k=l; k<=r; k++)
    if (temp[i].compareTo(temp[j])<0) A[k] = temp[i++];
    else A[k] = temp[j--];
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
