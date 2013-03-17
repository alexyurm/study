/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

class Flag {

  static final int ONES = 0xffffffff;

  public static void flagbit(int size) {
    int dum = 1;
    int A = 0;

    long time1 = System.currentTimeMillis();
    for (int i=0; i<size; i++) {
      dum = (A >> 0) & 01;
      dum = (A >> 1) & 01;
      dum = (A >> 2) & 01;
      dum = (A >> 3) & 01;
      dum = (A >> 4) & 01;
      dum = (A >> 5) & 01;
      dum = (A >> 6) & 01;
      dum = (A >> 7) & 01;
      dum = (A >> 8) & 01;
      dum = (A >> 9) & 01;
      dum = (A >> 10) & 01;
      dum = (A >> 11) & 01;
      dum = (A >> 12) & 01;
      dum = (A >> 13) & 01;
      dum = (A >> 14) & 01;
      dum = (A >> 15) & 01;
      dum = (A >> 16) & 01;
      dum = (A >> 17) & 01;
      dum = (A >> 18) & 01;
      dum = (A >> 19) & 01;
      dum = (A >> 20) & 01;
      dum = (A >> 21) & 01;
      dum = (A >> 22) & 01;
      dum = (A >> 23) & 01;
      dum = (A >> 24) & 01;
      dum = (A >> 25) & 01;
      dum = (A >> 26) & 01;
      dum = (A >> 27) & 01;
      dum = (A >> 28) & 01;
      dum = (A >> 29) & 01;
      dum = (A >> 30) & 01;
      dum = (A >> 31) & 01;
      A = (A & (ONES & (dum << 0)));
      A = (A & (ONES & (dum << 1)));
      A = (A & (ONES & (dum << 2)));
      A = (A & (ONES & (dum << 3)));
      A = (A & (ONES & (dum << 4)));
      A = (A & (ONES & (dum << 5)));
      A = (A & (ONES & (dum << 6)));
      A = (A & (ONES & (dum << 7)));
      A = (A & (ONES & (dum << 8)));
      A = (A & (ONES & (dum << 9)));
      A = (A & (ONES & (dum << 10)));
      A = (A & (ONES & (dum << 11)));
      A = (A & (ONES & (dum << 12)));
      A = (A & (ONES & (dum << 13)));
      A = (A & (ONES & (dum << 14)));
      A = (A & (ONES & (dum << 15)));
      A = (A & (ONES & (dum << 16)));
      A = (A & (ONES & (dum << 17)));
      A = (A & (ONES & (dum << 18)));
      A = (A & (ONES & (dum << 19)));
      A = (A & (ONES & (dum << 20)));
      A = (A & (ONES & (dum << 21)));
      A = (A & (ONES & (dum << 22)));
      A = (A & (ONES & (dum << 23)));
      A = (A & (ONES & (dum << 24)));
      A = (A & (ONES & (dum << 25)));
      A = (A & (ONES & (dum << 26)));
      A = (A & (ONES & (dum << 27)));
      A = (A & (ONES & (dum << 28)));
      A = (A & (ONES & (dum << 29)));
      A = (A & (ONES & (dum << 30)));
      A = (A & (ONES & (dum << 31)));
    }
    long time2 = System.currentTimeMillis();
    System.out.println("Bit time is " + (time2-time1));
  }


  public static void flagbyte(int size) {
    byte dum;
    byte[] A = new byte[32];

    long time1 = System.currentTimeMillis();
    for (int i=0; i<size; i++) {
      dum = A[1];
      dum = A[2];
      dum = A[3];
      dum = A[4];
      dum = A[5];
      dum = A[6];
      dum = A[7];
      dum = A[8];
      dum = A[9];
      dum = A[10];
      dum = A[11];
      dum = A[12];
      dum = A[13];
      dum = A[14];
      dum = A[15];
      dum = A[16];
      dum = A[17];
      dum = A[18];
      dum = A[19];
      dum = A[20];
      dum = A[21];
      dum = A[22];
      dum = A[23];
      dum = A[24];
      dum = A[25];
      dum = A[26];
      dum = A[27];
      dum = A[28];
      dum = A[29];
      dum = A[30];
      dum = A[31];
      A[1] = dum;
      A[2] = dum;
      A[3] = dum;
      A[4] = dum;
      A[5] = dum;
      A[6] = dum;
      A[7] = dum;
      A[8] = dum;
      A[9] = dum;
      A[10] = dum;
      A[11] = dum;
      A[12] = dum;
      A[13] = dum;
      A[14] = dum;
      A[15] = dum;
      A[16] = dum;
      A[17] = dum;
      A[18] = dum;
      A[19] = dum;
      A[20] = dum;
      A[21] = dum;
      A[22] = dum;
      A[23] = dum;
      A[24] = dum;
      A[25] = dum;
      A[26] = dum;
      A[27] = dum;
      A[28] = dum;
      A[29] = dum;
      A[30] = dum;
      A[31] = dum;
    }
    long time2 = System.currentTimeMillis();
    System.out.println("Byte time is " + (time2-time1));
  }


  public static void flagb(int size) {
    boolean dum;
    boolean[] A = new boolean[32];

    long time1 = System.currentTimeMillis();
    for (int i=0; i<size; i++) {
      dum = A[1];
      dum = A[2];
      dum = A[3];
      dum = A[4];
      dum = A[5];
      dum = A[6];
      dum = A[7];
      dum = A[8];
      dum = A[9];
      dum = A[10];
      dum = A[11];
      dum = A[12];
      dum = A[13];
      dum = A[14];
      dum = A[15];
      dum = A[16];
      dum = A[17];
      dum = A[18];
      dum = A[19];
      dum = A[20];
      dum = A[21];
      dum = A[22];
      dum = A[23];
      dum = A[24];
      dum = A[25];
      dum = A[26];
      dum = A[27];
      dum = A[28];
      dum = A[29];
      dum = A[30];
      dum = A[31];
      A[1] = dum;
      A[2] = dum;
      A[3] = dum;
      A[4] = dum;
      A[5] = dum;
      A[6] = dum;
      A[7] = dum;
      A[8] = dum;
      A[9] = dum;
      A[10] = dum;
      A[11] = dum;
      A[12] = dum;
      A[13] = dum;
      A[14] = dum;
      A[15] = dum;
      A[16] = dum;
      A[17] = dum;
      A[18] = dum;
      A[19] = dum;
      A[20] = dum;
      A[21] = dum;
      A[22] = dum;
      A[23] = dum;
      A[24] = dum;
      A[25] = dum;
      A[26] = dum;
      A[27] = dum;
      A[28] = dum;
      A[29] = dum;
      A[30] = dum;
      A[31] = dum;
    }
    long time2 = System.currentTimeMillis();
    System.out.println("Boolean time is " + (time2-time1));
  }


  public static void flagi(int size) {
    int dum;
    int[] A = new int[32];

    long time1 = System.currentTimeMillis();
    for (int i=0; i<size; i++) {
      dum = A[1];
      dum = A[2];
      dum = A[3];
      dum = A[4];
      dum = A[5];
      dum = A[6];
      dum = A[7];
      dum = A[8];
      dum = A[9];
      dum = A[10];
      dum = A[11];
      dum = A[12];
      dum = A[13];
      dum = A[14];
      dum = A[15];
      dum = A[16];
      dum = A[17];
      dum = A[18];
      dum = A[19];
      dum = A[20];
      dum = A[21];
      dum = A[22];
      dum = A[23];
      dum = A[24];
      dum = A[25];
      dum = A[26];
      dum = A[27];
      dum = A[28];
      dum = A[29];
      dum = A[30];
      dum = A[31];
      A[1] = dum;
      A[2] = dum;
      A[3] = dum;
      A[4] = dum;
      A[5] = dum;
      A[6] = dum;
      A[7] = dum;
      A[8] = dum;
      A[9] = dum;
      A[10] = dum;
      A[11] = dum;
      A[12] = dum;
      A[13] = dum;
      A[14] = dum;
      A[15] = dum;
      A[16] = dum;
      A[17] = dum;
      A[18] = dum;
      A[19] = dum;
      A[20] = dum;
      A[21] = dum;
      A[22] = dum;
      A[23] = dum;
      A[24] = dum;
      A[25] = dum;
      A[26] = dum;
      A[27] = dum;
      A[28] = dum;
      A[29] = dum;
      A[30] = dum;
      A[31] = dum;
    }
    long time2 = System.currentTimeMillis();
    System.out.println("Int time is " + (time2-time1));
  }


  public static void main(String args[]) throws IOException {
    assert args.length == 1 : "Usage: Flag <size>";
    int size = Integer.parseInt(args[0]);

    System.out.println("Size: " + size);
    flagbit(size);
    flagbyte(size);
    flagb(size);
    flagi(size);
  }
}
