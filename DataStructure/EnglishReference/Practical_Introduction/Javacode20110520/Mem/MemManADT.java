/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Memory Manager interface */
interface MemManADT {

  /** Store a record and return a handle to it */
  public MemHandle insert(byte[] info);

  /** Get back a copy of a stored record */
  public byte[] get(MemHandle h);

  /** Release the space associated with a record */
  public void release(MemHandle h);
}
