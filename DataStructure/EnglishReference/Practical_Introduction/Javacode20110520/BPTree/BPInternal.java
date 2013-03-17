/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

class BPInternal<Key,E> implements BPNode<Key,E> { // Leaf node
  private int numrecords;
  private Key theKeys[];
  private BPNode<Key,E> thePointers[];

  public boolean isLeaf() { return true; }
  public int numrecs() { return numrecords; }
  public Key[] keys() { return theKeys; }
  public BPInternal<Key,E> add(BPInternal<Key,E> ptr) { return null; }
  public boolean underflow(int which) { return false; }
  public BPNode<Key,E> pointers(int which) { return thePointers[which]; }
};
