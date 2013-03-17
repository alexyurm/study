/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the Min Heap. */

import java.io.*;

public class MinHeapTest
    extends junit.framework.TestCase
{

  private StringBuffer out;

  public void setUp()
  {
    out = new StringBuffer(100);
  }

@SuppressWarnings("unchecked")  // Allow the generic array allocation
  public void testMH1() {
    int i;
    Integer[] A = new Integer[20];
    for (i=0; i<20; i++)
      A[i] = new Integer(i);
    DSutil.permute(A);
    MinHeap<Integer> AH = new MinHeap<Integer>(A, 20, 20);
    for (i=0; i<20; i++)
	out.append(AH.removemin() + " ");
    assertEquals(out.toString(), "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ");
  }

@SuppressWarnings("unchecked")  // Allow the generic array allocation
  public void testMH2() {
    int i;
    Integer B[] = new Integer[10];
    B[0] = new Integer(73);
    B[1] = new Integer(6);
    B[2] = new Integer(57);
    B[3] = new Integer(88);
    B[4] = new Integer(60);
    B[5] = new Integer(34);
    B[6] = new Integer(83);
    B[7] = new Integer(72);
    B[8] = new Integer(48);
    B[9] = new Integer(85);
    MinHeap<Integer> BH = new MinHeap<Integer>(B, 10, 10);

    for (i=0; i<10; i++)
        out.append(B[i] + " ");
    assertEquals(out.toString(), "6 48 34 72 60 57 83 73 88 85 ");
    out = new StringBuffer(100);
    for (i=0; i<10; i++)
	out.append(BH.removemin() + " ");
    assertEquals(out.toString(), "6 34 48 57 60 72 73 83 85 88 ");
  }  

// Test the remove function.
@SuppressWarnings("unchecked")  // Allow the generic array allocation
  public void testMH3() {
    int i;
    Integer[] A = new Integer[20];
    for (int test=0; test<1000; test++) {
      for (i=0; i<20; i++)
        A[i] = new Integer(i);
      DSutil.permute(A);
      MinHeap<Integer> AH = new MinHeap<Integer>(A, 20, 20);
      for (i=20; i>=1; i--) {
        int x = DSutil.random(i);
        assert (x < i) : "Huh??";    
        AH.remove(x);
      }
    }
  }
}

