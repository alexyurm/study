/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Dictionary implemented using a B+ tree. */
class BPTree<Key extends Comparable<? super Key>, E>
                    implements Dictionary<Key, E> {
  private BPNode<Key,E> root;     // Root of B+ tree
  private int nodecount;        // # of records now in 2-3 tree
  private static final int NUMRECS = 10;

  /** Stub for a binary search on the key array */
  private int binaryle(Key keys[], int cnt, Key k) { return 0; }

  BPTree() { 
    nodecount = 0;
    root = new BPLeaf<Key,E>(NUMRECS);
  }

  public void clear() {   /** Reinitialize */
    nodecount = 0;
    root = null;
  }

  public void insert(Key k, E e) {  /** Insert an element */
    root = inserthelp(root, k, e);
    nodecount++;
  }

  /** Remove an element */
  public E remove(Key k) {
    E temp = findhelp(root, k);
    if (temp != null) {
      if (removehelp(root, k) && (root.numrecs() == 1)) // Collapse root
        if (!root.isLeaf()) root = ((BPInternal<Key,E>)root).pointers(0);
      nodecount--;
    }
    return temp;
  }

  /** Remove some element. */
  public E removeAny() {
    System.out.println("removeany not implemented");
    return null;
  }

  /** Find a record with key value "k" */
  public E find(Key k) {
    return findhelp(root, k);
  }

  /** Return number of values in the tree */
  public int size() { return nodecount; }

private BPNode<Key,E> inserthelp(BPNode<Key,E> rt,
                                 Key k, E e) {
  BPNode<Key,E> retval;
  if (rt.isLeaf()) // At leaf node: insert here
    return ((BPLeaf<Key,E>)rt).add(k, e);
  // Add to internal node
  int currec = binaryle(rt.keys(), rt.numrecs(), k);
  BPNode<Key,E> temp = inserthelp(
         ((BPInternal<Key,E>)root).pointers(currec), k, e);
  if (temp != ((BPInternal<Key,E>)rt).pointers(currec))
    return ((BPInternal<Key,E>)rt).
               add((BPInternal<Key,E>)temp);
  else
    return rt;
}
private E findhelp(BPNode<Key,E> rt, Key k) {
  int currec = binaryle(rt.keys(), rt.numrecs(), k);
  if (rt.isLeaf())
    if ((((BPLeaf<Key,E>)rt).keys())[currec] == k)
      return ((BPLeaf<Key,E>)rt).recs(currec);
    else return null;
  else
    return findhelp(((BPInternal<Key,E>)rt).
                        pointers(currec), k);
}
/** Delete a record with the given key value, and 
    return true if the root underflows */
private boolean removehelp(BPNode<Key,E> rt, Key k) {
  int currec = binaryle(rt.keys(), rt.numrecs(), k);
  if (rt.isLeaf())
    if (((BPLeaf<Key,E>)rt).keys()[currec] == k)
      return ((BPLeaf<Key,E>)rt).delete(currec);
    else return false;
  else // Process internal node
    if (removehelp(((BPInternal<Key,E>)rt).pointers(currec),
        k))
      // Child will merge if necessary
      return ((BPInternal<Key,E>)rt).underflow(currec);
    else return false;
}
}
