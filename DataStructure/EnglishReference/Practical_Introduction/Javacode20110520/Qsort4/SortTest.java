/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for sort algorithms */

import java.io.*;

public class SortTest
    extends junit.framework.TestCase
{

  final static int testsize = 1000;

  static Integer A[];

  static int THRESHOLD = 8;


static <E extends Comparable<? super E>> void Sort(E[] A) {
  qsort(A, 0, A.length-1);
}

static int MAXSTACKSIZE = 1000;
static <E extends Comparable<? super E>> void inssort(E[] A) {
  for (int i=1; i<A.length; i++) // Insert i'th record
    for (int j=i; (j>0) && (A[j].compareTo(A[j-1]) < 0); j--)
      DSutil.swap(A, j, j-1);
}
/** Non-recursive Quicksort */
static <E extends Comparable<? super E>>
void qsort(E[] A, int oi, int oj) {
  int[] Stack = new int[MAXSTACKSIZE]; // Stack for array bounds
  int listsize = oj-oi+1;
  int top = -1;
  E pivot;
  int pivotindex, l, r;

  Stack[++top] = oi;  // Initialize stack
  Stack[++top] = oj;

  while (top > 0) {   // While there are unprocessed subarrays
    // Pop Stack
    int j = Stack[top--];
    int i = Stack[top--];

    // Findpivot
    pivotindex = (i+j)/2;
    pivot = A[pivotindex];
    DSutil.swap(A, pivotindex, j); // Stick pivot at end

    // Partition
    l = i-1;
    r = j;
    do {
      while (A[++l].compareTo(pivot)<0);
      while ((r!=0) && (A[--r].compareTo(pivot)>0));
      DSutil.swap(A, l, r);
    } while (l < r);
    DSutil.swap(A, l, r);  // Undo final swap
    DSutil.swap(A, l, j);  // Put pivot value in place

    // Put new subarrays onto Stack if they are small
    if ((l-i) > THRESHOLD) {   // Left partition
      Stack[++top] = i;
      Stack[++top] = l-1;
    }
    if ((j-l) > THRESHOLD) {   // Right partition
      Stack[++top] = l+1;
      Stack[++top] = j;
    }
  }
  inssort(A);             // Final Insertion Sort
}

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    A = new Integer[testsize];
    for (int i=0; i<testsize; i++)
      A[i] = new Integer(DSutil.random(32003));
  }

  /** Successful calls to fact */
  public void testIt() {
    Sort(A);
    for (int i=1; i<testsize; i++)
      assert A[i-1].compareTo(A[i]) <= 0 : "Array not sorted";
  }
}
