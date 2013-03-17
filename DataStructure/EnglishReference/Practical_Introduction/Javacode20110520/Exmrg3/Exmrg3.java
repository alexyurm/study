/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class Exmrg3 {

static final int NumRec = 1024;   // Number of records in a block
static final int bufblocks = 32; // Number of blocks in the buffer (run)

public static String[] FName = new String[4];

public static int IN1 = 0;
public static int IN2 = 1;
public static int OUT1 = 2;
public static int OUT2 = 3;

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

// Use quicksort to generate initial runs, placing the runs
// one after another into the temporary file.
static void firstpass(DataInputStream in, DataOutputStream out,
                      int numrecs) throws IOException {
  int bufrecs = NumRec * bufblocks;
  int[] buffer = new int[bufrecs];
  int runs = 2*numrecs/bufrecs;
System.out.println("Number of records: " + numrecs*2);
System.out.println("Number of runs: " + runs);

  for (int i=0; i<runs; i++) {
    for (int j=0; j<bufrecs; j++)
      buffer[j] = in.readInt();
    qsort(buffer, 0, bufrecs-1);
    for (int j=0; j<bufrecs; j++)
      out.writeInt(buffer[j]);
  }
}


static int getnext(int[][] buffer, int[] currpos, int numruns, int runlength) {
  int currrun;

  for (currrun=0; currpos[currrun] == runlength; currrun++);
  int currmin = buffer[currrun][currpos[currrun] % NumRec];
  for (int i=currrun; i<numruns; i++)
    if (currpos[i] < runlength)
      if (buffer[i][currpos[i]%NumRec] < currmin)
        { currrun = i; currmin = buffer[i][currpos[i]%NumRec]; }
  return currrun;
}


static void secondpass(RandomAccessFile in, DataOutputStream out,
                       int numruns) throws IOException {
  int[][] buffer = new int[numruns][NumRec];
  int[] currpos = new int[numruns];
  byte[] bbuf = new byte[4*NumRec];
  int runlength = NumRec*bufblocks;
  int runlengthbytes = 4*runlength;
  int recs = numruns * runlength;

System.out.println("Number of runs: " + numruns);
System.out.println("Runlength: " + runlength);

  for (int i=0; i<numruns; i++) currpos[i] = 0;
  for (int i=0; i<numruns; i++) { // Suck in the original data
    in.seek(i*runlengthbytes);
    ByteArrayInputStream bsbuf = new ByteArrayInputStream(bbuf);
    in.read(bbuf, 0, 4*NumRec);
    DataInputStream din = new DataInputStream(bsbuf);
    for (int j=0; j<NumRec; j++)
      buffer[i][j] = din.readInt();
  }

System.out.print("Data are in: ");
for (int k=0; k<numruns; k++)
  System.out.print(buffer[k][0] + " ");
System.out.println();

  // Now, do the multiway merge
  for (int cnt=0; cnt<recs; cnt++) {
    int whichrun = getnext(buffer, currpos, numruns, runlength);
    out.writeInt(buffer[whichrun][currpos[whichrun]%NumRec]);
    currpos[whichrun]++;
    // Now, read in a new block if necessary
    if ((currpos[whichrun] % NumRec) == 0)
      if (currpos[whichrun] != runlength) {
        in.seek(whichrun*runlengthbytes + 4*currpos[whichrun]);
        ByteArrayInputStream bsbuf = new ByteArrayInputStream(bbuf);
        in.read(bbuf, 0, 4*NumRec);
        DataInputStream din = new DataInputStream(bsbuf);
        for (int j=0; j<NumRec; j++)
          buffer[whichrun][j] = din.readInt();
      }

  }
}

static void exmergesort(String infile, String outfile, int recs)
       throws IOException {
  int temp;

  // For first pass, need the original input file.  Won't use again
  DataInputStream in = new DataInputStream(
          new BufferedInputStream(new FileInputStream(infile)));
  DataOutputStream out = new DataOutputStream(
          new BufferedOutputStream(new FileOutputStream(infile + ".tmp")));
System.out.println("Do firstpass");
  firstpass(in, out, recs/2);
  in.close();
  out.close();
  
  // Now, do a single multi-way merge pass
  RandomAccessFile in2 = new RandomAccessFile(infile + ".tmp", "r");
  out = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(outfile)));
System.out.println("Do secondpass");
  secondpass(in2, out, recs/(NumRec*bufblocks));
  in2.close(); out.close();
}


public static void main(String args[]) throws IOException {

  byte[] buf = new byte[4*NumRec];
  int[] intbuf = new int[NumRec];

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

  System.in.read();
}

}
