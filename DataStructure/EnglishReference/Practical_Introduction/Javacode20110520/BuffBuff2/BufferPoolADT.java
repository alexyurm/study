/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** The bufferpool */
public interface BufferPoolADT {

  /** Relate a block to a buffer, returning a pointer to
      a buffer object */
  Buffer acquireBuffer(int block);
}
