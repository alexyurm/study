/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

class BPLeaf<Key,E> implements BPNode<Key,E> { // Leaf node
  private int numrecords;
  private Key theKeys[];
  private E theRecs[];

  BPLeaf(int maxrec) {
  }

  public boolean isLeaf() { return true; }
  public int numrecs() { return numrecords; }
  public Key[] keys() { return theKeys; }
  public E recs(int r) { return theRecs[r]; }
  public BPLeaf<Key,E> add(Key k, E e) { return null; }
  public boolean delete(int which) { return false; }
};
