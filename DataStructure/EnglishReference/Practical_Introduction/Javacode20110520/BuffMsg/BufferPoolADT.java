/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** ADT for buffer pools using the message-passing style */
public interface BufferPoolADT {
  /** Copy "sz" bytes from "space" to position "pos" in the
      buffered storage */
  public void insert(byte[] space, int sz, int pos);

  /** Copy "sz" bytes from position "pos" of the buffered
      storage to "space". */
  public void getbytes(byte[] space, int sz, int pos);
}
