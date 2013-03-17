/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

public class MemHandle { // An access handle for the
	                     //  memory manager
  int pos;               // Position of data in memory

  MemHandle(int inpos) { pos = inpos; }

  int getPos() { return pos; }
}
