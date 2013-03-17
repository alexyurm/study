/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Dummy implementation for
    improved ADT for buffer pools using the buffer-passing 
    style. Most user functionality is in the buffer class,
    not the buffer pool itself. */

/** A single buffer in the buffer pool */
class Buffer implements BufferADT {
  /** Read the associated block from disk (if necessary) and
      return a pointer to the data */
  public byte[] readBlock() { return null; }

  /** Return a pointer to the buffer's data array
      (without reading from disk) */
  public byte[] getDataPointer() { return null; }

  /** Flag the buffer's contents as having changed, so that
      flushing the block will write it back to disk */
  public void markDirty() {}

  /** Release the block's access to this buffer. Further
      accesses to this buffer are illegal. */
  public void releaseBuffer() {}
}
