/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class Genfile {

static final int NumRec = 1024;

public static void main(String args[]) throws IOException {

    assert (args.length == 2) || (args.length == 3) :
    "Usage: Genfile <name> <size> [+/-]\nSize is measured in blocks of 4096 bytes"; 
  int filesize = Integer.parseInt(args[1]);
  DataOutputStream file = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream(args[0])));

  int recs = 1024 * filesize - 1;

  if (args.length == 2) // Write out random numbers
    for (int i=0; i<filesize; i++)
      for (int j=0; j<NumRec; j++) {
        int val = DSutil.random(32003);
        file.writeInt(val);
     }
  else if (args[2].charAt(0) == '+') // Write out numbers ascending
    for (int i=0; i<filesize; i++)
      for (int j=0; j<NumRec; j++)
        file.writeInt(i*NumRec + j);
  else if (args[2].charAt(0) == '-') // Write out numbers descending
    for (int i=0; i<filesize; i++)
      for (int j=0; j<NumRec; j++)
        file.writeInt(recs - (i*NumRec + j));
  else assert false : "Bad parameters";

  file.flush();
  file.close();
}

}
