/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Dictionary implemented using hashing. */
class HashDictionary<Key extends Comparable<? super Key>, E>
                    implements Dictionary<Key, E> {
  private static final int defaultSize = 10;
  private HashTable<Key,E> T; // The hash table
  private int count;          // # of records now in table
  private int maxsize;        // Maximum size of dictionary

  HashDictionary() { this(defaultSize); }
  HashDictionary(int sz) {
    T = new HashTable<Key,E>(sz);
    count = 0;
    maxsize = sz;
  }

  public void clear() {   /** Reinitialize */
    T = new HashTable<Key,E>(maxsize);
    count = 0;
  }

  public void insert(Key k, E e) {  /** Insert an element */
    assert count < maxsize : "Hash table is full";
    T.hashInsert(k, e);
    count++;
  }

  public E remove(Key k) {  /** Remove an element */
    E temp = T.hashRemove(k);
    if (temp != null) count--;
    return temp;
  }

  public E removeAny() {  /** Remove some element. */
    if (count != 0) {
      count--;
      return T.hashRemoveAny();
    }
    else return null;
  }

  /** Find a record with key value "k" */
  public E find(Key k) { return T.hashSearch(k); }

  /** Return number of values in the hash table */
  public int size() { return count; }
}
