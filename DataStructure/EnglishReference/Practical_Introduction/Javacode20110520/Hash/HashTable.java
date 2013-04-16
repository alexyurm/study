/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class HashTable<Key extends Comparable<? super Key>, E> {
  private int M;
  private KVpair<Key,E>[] HT;

  private int h(Key key) {
    return M - 1;
  }

  private int p(Key key, int slot) {
    return slot;
  } 

  @SuppressWarnings("unchecked") // Generic array allocation
  HashTable(int m) {
    M = m;
    HT = (KVpair<Key,E>[])new KVpair[M];
  }
/** Insert record r with key k into HT */
void hashInsert(Key k, E r) {
  int home;                         // Home position for r
  int pos = home = h(k);            // Initial position
  for (int i=1; HT[pos] != null; i++) {
    pos = (home + p(k, i)) % M;     // Next pobe slot
    assert HT[pos].key().compareTo(k) != 0 :
           "Duplicates not allowed";
  }
  HT[pos] = new KVpair<Key,E>(k, r); // Insert R
}
/** Search in hash table HT for the record with key k */
E hashSearch(Key k) {
  int home;              // Home position for k
  int pos = home = h(k); // Initial position
  for (int i = 1; (HT[pos] != null) &&
                  (HT[pos].key().compareTo(k) != 0); i++)
    pos = (home + p(k, i)) % M;     // Next probe position
  if (HT[pos] == null) return null; // Key not in hash table
  else return HT[pos].value();      // Found it
}

}
