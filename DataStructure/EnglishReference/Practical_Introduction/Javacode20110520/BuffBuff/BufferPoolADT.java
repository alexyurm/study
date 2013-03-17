/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** ADT for buffer pools using the buffer-passing style */
public interface BufferPoolADT {
  /** Return pointer to the requested block */
  public byte[] getblock(int block);

  /** Set the dirty bit for the buffer holding "block" */
  public void dirtyblock(int block);

  // Tell the size of a buffer
  public int blocksize();
};
