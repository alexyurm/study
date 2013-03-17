/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

import java.io.*;

// External sort using mergesort.
// First, generate a series of initial runs using Quicksort.
// Then, do a series of two-way merging passes.
// The records are simply an int value interger values.
public class Exmrg2 {

static final int NumRec = 1024; // Number of records in a block
static final int bufblocks = 32; // Size of working memory (in blocks)

public static String[] FName = new String[4];

// Indices for I/O files.
// Use two input files and two output files.
public static int IN1 = 0;
public static int IN2 = 1;
public static int OUT1 = 2;
public static int OUT2 = 3;

////// Code for Quicksort ////////////////////////////////////
static void swap(int[] array, int p1, int p2) {
  int temp = array[p1];
  array[p1] = array[p2];
  array[p2] = temp;
}

static int partition(int[] array, int l, int r, int pivot) {
  do {                // Move the bounds inward until they meet
    while (array[++l] < pivot); // Move left bound right
    while ((r!=0) && (array[--r]>pivot)); // Move right bound
    swap(array, l, r);        // Swap out-of-place values
  } while (l < r);                   // Stop when they cross
  swap(array, l, r);          // Reverse last, wasted swap
  return l;           // Return first position in right partition
}

static void qsort(int[] array, int i, int j) { // Quicksort
  int pivotindex = (i+j)/2;
  swap(array, pivotindex, j);    // Stick pivot at end
  // k will be the first position in the right subarray
  int k = partition(array, i-1, j, array[j]);
  swap(array, k, j);             // Put pivot in place
  if ((k-i) > 1) qsort(array, i, k-1);  // Sort left partition
  if ((j-k) > 1) qsort(array, k+1, j);  // Sort right partition
}
////// End code for Quicksort ////////////////////////////////


// Use quicksort to generate initial runs, placing half of the runs
// into each output file.
static void firstpass(DataInputStream in, DataOutputStream out1,
                  DataOutputStream out2, int numrecs)
              throws IOException {
  int bufrecs = 1024 * bufblocks; // Working memory
  int[] buffer = new int[bufrecs];
  int runs = numrecs/bufrecs;
  DataOutputStream temp;
  System.out.println("Process " + numrecs + " records into " +
	                 runs + " runs of length " + bufrecs);
  for (int i=0; i<runs; i++) {
    for (int j=0; j<bufrecs; j++)
      buffer[j] = in.readInt();
    qsort(buffer, 0, bufrecs-1);
    for (int j=0; j<bufrecs; j++)
      out1.writeInt(buffer[j]);
    temp = out1;  out1 = out2;  out2 = temp;
  }
}


// Do a single 2-way merge pass
static void dopass(DataInputStream in1, DataInputStream in2,
  DataOutputStream out1, DataOutputStream out2, int numruns,
  int runlen) throws IOException {
  // In this case, numruns is the number of runs in each file,
  // while runlen is the number of records in a run.

  DataOutputStream temp;
  int val1, val2;
  int in1cnt, in2cnt;
  int outcnt;

  for (int i=0; i<numruns; i++) {
    val1 = in1.readInt();
    val2 = in2.readInt();
    in1cnt = 1; in2cnt = 1;
    outcnt = 0;
    while (outcnt < 2*runlen) {
      if (val1 <= val2) {
        out1.writeInt(val1); outcnt++;
        if (in1cnt < runlen)
          { val1 = in1.readInt(); in1cnt++; }
        else { // Flush second run
          out1.writeInt(val2); outcnt++;
          while (in2cnt < runlen) {
            val2 = in2.readInt(); in2cnt++;
            out1.writeInt(val2); outcnt++;
          }
        }
      }
      else {
        out1.writeInt(val2); outcnt++;
        if (in2cnt < runlen)
          { val2 = in2.readInt(); in2cnt++; }
        else { // Flush first run
          out1.writeInt(val1); outcnt++;
          while (in1cnt < runlen) {
            val1 = in1.readInt(); in1cnt++;
            out1.writeInt(val1); outcnt++;
          }
        }
      }
    }
    temp = out1; out1 = out2; out2 = temp;
  }
}


// Do an external sort
static void exmergesort(String infile, String outfile, int recs)
       throws IOException {
  int temp;

  // For first pass, need the original input file.  Won't use again
  // Divide the original input into two
  DataInputStream in = new DataInputStream(
          new BufferedInputStream(new FileInputStream(infile)));
  DataOutputStream out1 = new DataOutputStream(
          new BufferedOutputStream(new FileOutputStream(FName[IN1])));
  DataOutputStream out2 = new DataOutputStream(
          new BufferedOutputStream(new FileOutputStream(FName[IN2])));
  System.out.println("We will process " + recs + " records");
  System.out.println("Generate initial runs (using Quicksort)");
  firstpass(in, out1, out2, recs);
  in.close();
  out1.close();
  out2.close();
  
  // Now, do a series of 2-way merge passes
  int numRuns = recs/(1024*bufblocks);
  System.out.println("Now, merge " + numRuns + " runs down to 2");
  for (int i=numRuns; i>2; i/=2) {
    DataInputStream in1 = new DataInputStream(
      new BufferedInputStream(new FileInputStream(FName[IN1])));
    DataInputStream in2 = new DataInputStream(
      new BufferedInputStream(new FileInputStream(FName[IN2])));
    out1 = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(FName[OUT1])));
    out2 = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(FName[OUT2])));
System.out.println("Do a merge pass");
    dopass(in1, in2, out1, out2, i/2, recs/i);
    in1.close(); in2.close();
    out1.close(); out2.close();
    temp = IN1;  IN1 = OUT1;  OUT1 = temp;
    temp = IN2;  IN2 = OUT2;  OUT2 = temp;
  }

  // Finally, merge the last two runs into the output file
  if(numRuns > 1) {
    DataInputStream in1 = new DataInputStream(
      new BufferedInputStream(new FileInputStream(FName[IN1])));
   DataInputStream in2 = new DataInputStream(
      new BufferedInputStream(new FileInputStream(FName[IN2])));
    out1 = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(outfile)));
System.out.println("Do last pass to file " + outfile);
    dopass(in1, in2, out1, null, 1, recs/2);
    in1.close();  in2.close();
    out1.close();
  }
}


// Main routine for external sort
public static void main(String args[]) throws IOException {

  assert args.length == 3 :
    "Usage: Exmrg2 <infile> <outfile> <size-in-blocks>";
  int filesize = Integer.parseInt(args[2]);

  FName[IN1] = args[0] + ".1";
  FName[IN2] = args[0] + ".2";
  FName[OUT1] = args[1] + ".1";
  FName[OUT2] = args[1] + ".2";

  File f = new File(FName[IN1]);
  if (f.exists()) f.delete();
  f = new File(FName[IN2]);
  if (f.exists()) f.delete();
  f = new File(FName[OUT1]);
  if (f.exists()) f.delete();
  f = new File(FName[OUT2]);
  if (f.exists()) f.delete();

  long time1 = System.currentTimeMillis();
  exmergesort(args[0], args[1], filesize*NumRec);
  long time2 = System.currentTimeMillis();
  System.out.println("Time is " + (time2-time1));

  // Now test that this worked correctly
  DataInputStream outputfile = new DataInputStream(
          new BufferedInputStream(new FileInputStream(args[1])));
  int Val1, Val2, i;
  Val2 = outputfile.readInt(); // Priming read
  for (i=1; i<filesize*NumRec; i++) {
    Val1 = Val2;
    Val2 = outputfile.readInt();
    assert Val1 <= Val2 : "Record " + i + ": Unsorted output file";
  }
  outputfile.close();
  System.out.println(i + " records processed");
  System.in.read();
}

}
