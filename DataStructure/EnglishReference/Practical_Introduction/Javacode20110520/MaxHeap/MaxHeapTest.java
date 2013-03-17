/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the Max Heap. */

import java.io.*;

public class MaxHeapTest
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
    MaxHeap<Integer> AH = new MaxHeap<Integer>(A, 20, 20);
    for (i=0; i<20; i++)
	out.append(AH.removemax() + " ");
    assertEquals(out.toString(), "19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 ");
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
    MaxHeap<Integer> BH = new MaxHeap<Integer>(B, 10, 10);

    for (i=0; i<10; i++)
        out.append(B[i] + " ");
    assertEquals(out.toString(), "88 85 83 72 73 34 57 6 48 60 ");
    out = new StringBuffer(100);
    for (i=0; i<10; i++)
	out.append(BH.removemax() + " ");
    assertEquals(out.toString(), "88 85 83 73 72 60 57 48 34 6 ");
}


    // Remove test
@SuppressWarnings("unchecked")  // Allow the generic array allocation
  public void testMH3() {
    int i;
    Integer B[] = new Integer[10];
    B[0] = new Integer(73);
    B[1] = new Integer(6);
    B[2] = new Integer(57);
    B[3] = new Integer(88);
    B[4] = new Integer(60);
    B[5] = new Integer(34);
    B[6] = new Integer(58);
    B[7] = new Integer(72);
    B[8] = new Integer(48);
    B[9] = new Integer(85);
    MaxHeap<Integer> BH = new MaxHeap<Integer>(B, 10, 10);

    for (i=0; i<10; i++)
        out.append(B[i] + " ");
    assertEquals(out.toString(), "88 85 58 72 73 34 57 6 48 60 ");
    BH.remove(5);
    out = new StringBuffer(100);
    for (i=0; i<10; i++)
        out.append(B[i] + " ");
    assertEquals(out.toString(), "88 85 60 72 73 58 57 6 48 34 ");
    BH.remove(2);
    out = new StringBuffer(100);
    for (i=0; i<10; i++)
        out.append(B[i] + " ");
    assertEquals(out.toString(), "88 85 58 72 73 48 57 6 60 34 ");

  }  
}
