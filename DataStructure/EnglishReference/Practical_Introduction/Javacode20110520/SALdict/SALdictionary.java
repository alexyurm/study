/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Dictionary implemented with the sorted array-based list. */
class SALdictionary<Key extends Comparable<? super Key>, E>
                    implements Dictionary<Key, E> {

  private static final int defaultSize = 10; // Default size
  private SAList<Key, E> list;

  // Consturctors
  SALdictionary() { this(defaultSize); }
  SALdictionary(int sz) { list = new SAList<Key, E>(sz); }

  public void clear() { list.clear(); }     // Reinitialize

  /** Insert an element -- goes in sorted position */
  public void insert(Key k, E e) {
    list.insert(k, e);
  }

  /** Use sequential search to find the element to remove */
  // This really should get replaced with binary search
  public E remove(Key k) {
    E temp = find(k);
    if (temp != null) list.remove();
    return temp;
  }

  /** Remove the last element */
  public E removeAny() {
    if (size() != 0) {
      list.moveToEnd();
      list.prev();
      E e = list.remove();
      return e;
    }
    else return null;
  }

  // Find "k" using sequential search -- Should use binary search
  public E find(Key k) {
    for(list.moveToStart(); list.currPos() < list.length();
        list.next()) {
      Key key = list.getKey();
      if (k == key)
        return list.getValue();
    }
    return null; // "k" does not appear in dictionary
  }

  public int size() // Return list size
    { return list.length(); }
}
