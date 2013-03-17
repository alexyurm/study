/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.nio.*;

class MemManager implements MemManADT{
  private static final int STARTTAG = 0;   // Start tag offset
  private static final int FULLSIZE = 1;   // Size field offset
  private static final int USERSIZE = 2;   // User size offset
  private static final int LPTR = 2;       // Left freelist pointer
  private static final int RPTR = 3;       // Right freelist pointer
  private static final int DATAPOS = 3;    // Start of data
  private static final int FREE = -1;      // Tag value
  private static final int RESERVED = -2;  // Tag value
  private static final int ENDSIZE = 4;    // Size field offset
  private static final int FREEENDTAG = 5; // Tag field offset
  private static final int RESENDTAG = 3;  // Tag field offset
  private static final int MINEXTRA = -2;  // Extra space needed
  private static final int FREEOVERHEAD = 6; // Number free fields
  private static final int RESOVERHEAD = 4;  // Number of res fields
  private static final int MINREQUEST = 2; // Smallest data request

  byte[] mempool;             // The data space
  MemHandle freelist;         // Free memory access

  MemManager(int size) {      // Constructor
    mempool = new byte[size];  // Allocate space
    freelist = new MemHandle(0); // Start of freelist
    mempool[STARTTAG] = mempool[size-1] = FREE;
    mempool[FULLSIZE] = mempool[size-2] = (byte)(size - FREEOVERHEAD);
    mempool[LPTR] = mempool[RPTR] = 0; // Circ doubly-linked list
  }

  public byte[] get(MemHandle h) { // Return data for h
    int startpos = h.getPos();
    int length = mempool[startpos + USERSIZE];
    int startdata = startpos + DATAPOS;
    byte[] stuff = new byte[length];
    for (int i=0; i<length; i++) stuff[i] = mempool[startdata + i];
    return stuff;
  }

  // Sample sequential fit implementation: First fit
  private int pickFreeBlock(int length) {
    if (freelist == null) return -1;  // No free block
    int freestart = freelist.getPos();
    for (int curr=freestart;;)
      if (mempool[curr + FULLSIZE] >= (length + MINEXTRA))
	return curr;
      else {
        curr = mempool[curr + RPTR];
	if (curr == freestart) return -1; // No block available
      }
  }

  public MemHandle insert(byte[] info) {
    int datasize;
    if (info.length < MINREQUEST)   // Minimum necessary for
      datasize = MINREQUEST;        //   a sustainable block
    else datasize = info.length;
    int start= pickFreeBlock(info.length);
    if (start == -1) return null; // No block is big enough
    if (mempool[start + FULLSIZE] > (datasize + RESOVERHEAD)) {
      // Fix up the remaining free space
      int oldsize = mempool[start+FULLSIZE]-datasize-RESOVERHEAD;
      mempool[start + oldsize + FREEENDTAG] = FREE;
      mempool[start + oldsize + ENDSIZE] = (byte)oldsize;
      mempool[start + FULLSIZE] = (byte)oldsize;

      // Now, fix up the new block
      int newstart = start + mempool[start+FULLSIZE] + FREEOVERHEAD;
      mempool[newstart+STARTTAG] = RESERVED;
      mempool[newstart+FULLSIZE] = (byte)datasize;
      mempool[newstart+USERSIZE] = (byte)(info.length);
      mempool[newstart+datasize+RESENDTAG] = RESERVED;
      for (int i=0; i<info.length; i++)
        mempool[newstart+DATAPOS+i] = info[i];
      return new MemHandle(newstart);
    }
    else { // Give over the whole block, remove from freelist
      // First, adjust the freelist
      if (mempool[start+RPTR] == start)
	freelist = null;  // This is the last block
      else {
        mempool[mempool[start+RPTR]+LPTR] = mempool[start+LPTR];
        mempool[mempool[start+LPTR]+RPTR] = mempool[start+RPTR];
      }
      // Now, fill in the block
      mempool[start+STARTTAG] = RESERVED;
      mempool[start+FULLSIZE] += FREEOVERHEAD - RESOVERHEAD;
      mempool[start+USERSIZE] = (byte)info.length;
      for (int i=0; i<info.length; i++)
        mempool[start+DATAPOS+i] = info[i];
      mempool[start+mempool[start+FULLSIZE]+RESENDTAG] = RESERVED;
      return new MemHandle(start);
    }
  }

  public void release(MemHandle h) {
    // Implementation is left as an exercise
  }

} // class MemManager
