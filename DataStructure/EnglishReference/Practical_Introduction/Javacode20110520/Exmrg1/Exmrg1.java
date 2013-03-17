/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class Exmrg1 {

static final int NumRec = 1024;

public static String[] FName = new String[4];

public static int IN1 = 0;
public static int IN2 = 1;
public static int OUT1 = 2;
public static int OUT2 = 3;

public static int reads = 0; // A count of the number of times that we do a read
public static int writes = 0; // A count of the number of times that we do a write

// Divide the records in the input file between the two
// output files, leaving numrecs records in each.
static void firstpass(DataInputStream in, DataOutputStream out1,
                  DataOutputStream out2, int numrecs)
              throws IOException {
  int val;
  for (int i=0; i<numrecs; i++) {
    val = in.readInt(); reads++;
    out1.writeInt(val); writes++;
    val = in.readInt(); reads++;
    out2.writeInt(val); writes++;
  }
}


static void dopass(DataInputStream in1, DataInputStream in2,
  DataOutputStream out1, DataOutputStream out2, int numruns,
  int runlen) throws IOException {

  DataOutputStream temp;
  int val1, val2;
  int in1cnt, in2cnt, outcnt;
  for (int i=0; i<numruns; i++) {
    val1 = in1.readInt(); reads++;
    val2 = in2.readInt(); reads++;
    in1cnt = 1; in2cnt = 1;
    outcnt = 0;
    while (outcnt < 2*runlen) {
      if (val1 <= val2) {
        out1.writeInt(val1); outcnt++; writes++;
        if (in1cnt < runlen)
	  { val1 = in1.readInt(); in1cnt++; reads++; }
        else { // Flush second run
	  out1.writeInt(val2); outcnt++; writes++;
          while (in2cnt < runlen) {
	    val2 = in2.readInt(); in2cnt++; reads++;
            out1.writeInt(val2); outcnt++; writes++;
          }
        }
      }
      else {
	out1.writeInt(val2); outcnt++; writes++;
        if (in2cnt < runlen)
	  { val2 = in2.readInt(); in2cnt++; reads++;}
        else { // Flush first run
	  out1.writeInt(val1); outcnt++; writes++;
          while (in1cnt < runlen) {
	    val1 = in1.readInt(); in1cnt++; reads++;
            out1.writeInt(val1); outcnt++; writes++;
          }
        }
      }
    }
    temp = out1; out1 = out2; out2 = temp;
  }
}


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
System.out.println("Do firstpass");
  firstpass(in, out1, out2, recs/2);
  in.close();
  out1.close();
  out2.close();
  
  // Now, do a series of 2-way merge passes
  for (int i=recs/2; i>1; i/=2) {
    DataInputStream in1 = new DataInputStream(
      new BufferedInputStream(new FileInputStream(FName[IN1])));
    DataInputStream in2 = new DataInputStream(
      new BufferedInputStream(new FileInputStream(FName[IN2])));
    out1 = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(FName[OUT1])));
    out2 = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(FName[OUT2])));
System.out.println("Do a pass");
    dopass(in1, in2, out1, out2, i, recs/(2*i));
    in1.close(); in2.close();
    out1.close(); out2.close();
    temp = IN1;  IN1 = OUT1;  OUT1 = temp;
    temp = IN2;  IN2 = OUT2;  OUT2 = temp;
  }

  // Finally, merge the last two runs into the output file
  DataInputStream in1 = new DataInputStream(
    new BufferedInputStream(new FileInputStream(FName[IN1])));
  DataInputStream in2 = new DataInputStream(
    new BufferedInputStream(new FileInputStream(FName[IN2])));
  out1 = new DataOutputStream(
    new BufferedOutputStream(new FileOutputStream(outfile)));
  dopass(in1, in2, out1, null, 1, recs/2);
  in1.close();  in2.close();
  out1.close();
}


public static void main(String args[]) throws IOException {

  byte[] buf = new byte[4*NumRec];
  int[] intbuf = new int[NumRec];

  assert args.length == 2 : "Usage: Exmrg1 <infile> <outfile>";

  File f = new File(args[0]);
  int filelength = (int)f.length();
  if (((filelength/(4*NumRec))*(4*NumRec)) != filelength)
    assert false : "The file size must be a multiple of " + 4*NumRec;
  int blocks = filelength/(4 * NumRec);
  int b = 1;
  while (b < blocks) b = b*2;
  assert b == blocks : "The file size must be a power of 2 blocks";

  FName[IN1] = args[0] + ".1";
  FName[IN2] = args[0] + ".2";
  FName[OUT1] = args[1] + ".1";
  FName[OUT2] = args[1] + ".2";

  f = new File(FName[IN1]);
  if (f.exists()) f.delete();
  f = new File(FName[IN2]);
  if (f.exists()) f.delete();
  f = new File(FName[OUT1]);
  if (f.exists()) f.delete();
  f = new File(FName[OUT2]);
  if (f.exists()) f.delete();

  long time1 = System.currentTimeMillis();
  exmergesort(args[0], args[1], filelength/4);
  long time2 = System.currentTimeMillis();
  System.out.println("Time is " + (time2-time1));

  // Now test that this worked correctly
  DataInputStream outputfile = new DataInputStream(
          new BufferedInputStream(new FileInputStream(args[1])));
  int Val1, Val2, i;
  Val2 = outputfile.readInt(); // Priming read
  for (i=1; i<filelength/4; i++) {
    Val1 = Val2;
    Val2 = outputfile.readInt();
    assert Val1 <= Val2 : "Record " + i + ": Unsorted output file";
  }
  outputfile.close();
  System.out.println(i + " records processed");
  System.out.println(reads + " records read; " + writes + " records written");
}

}
