/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Copy a file's contents to "dum.out". */
import java.io.*;

public class Test {

static final int NumRec = 1024;

public static void main(String args[]) throws IOException {

  byte[] buf = new byte[4*NumRec];
  int[] intbuf = new int[NumRec];

  assert args.length == 2 :
    "Usage: Test <name> <size>\nSize is measured in blocks of 4096 bytes";
  int filesize = Integer.parseInt(args[1]);
  RandomAccessFile in = new RandomAccessFile(args[0], "r");
  RandomAccessFile out = new RandomAccessFile("dum.out", "rw");

  for (int blocks=0; blocks<filesize; blocks++) {
    // Suck it in
    ByteArrayInputStream bsbuf = new ByteArrayInputStream(buf);
    in.read(buf, 0, 4*NumRec);
    DataInputStream din = new DataInputStream(bsbuf);
    for (int i=0; i<NumRec; i++)
      intbuf[i] = din.readInt();

//    for (int i=0; i<NumRec; i++)
//      System.out.print(intbuf[i] + " ");
//    System.out.println();

    // Now, write it out
    ByteArrayOutputStream bsoutbuf = new ByteArrayOutputStream();
	DataOutputStream dout = new DataOutputStream(bsoutbuf);
    for (int i=0; i<NumRec; i++)
      dout.writeInt(intbuf[i]);
    out.write(bsoutbuf.toByteArray());
  }
}

}

