/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Dictionary implemented using a 2-3tree. */
class TTTree<Key extends Comparable<? super Key>, E>
                    implements Dictionary<Key, E> {
  private TTNode<Key,E> root;     // Root of 2-3 tree
  private int reccount;        // # of records now in 2-3 tree

  TTTree() { 
    reccount = 0;
    root = null;
  }

  public void clear() {   /** Reinitialize */
    reccount = 0;
    root = null;
  }

  public void insert(Key k, E e) {  /** Insert an element */
    root = inserthelp(root, k, e);
    reccount++;
  }

  /** Remove an element */
  public E remove(Key k) {
    System.out.println("remove not implemented");
    return null;
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
  public int size() { return reccount; }

  private void printhelp(TTNode<Key,E> rt, StringBuffer out, int level) {
    for (int i=1; i<=level; i++) {
      System.out.print("  ");
      out.append("T");
    }
    if (rt == null) {
        System.out.println("null");
        return;
    }
    System.out.println(rt);
    out.append(rt);
    if (rt.isLeaf()) return;
    printhelp(rt.lchild(), out, level+1);
    printhelp(rt.cchild(), out, level+1);
    printhelp(rt.rchild(), out, level+1);
  }

  public String toString() {
    StringBuffer out = new StringBuffer(1000);
    printhelp(root, out, 0);
    return out.toString();
  }
private TTNode<Key,E> inserthelp(TTNode<Key,E> rt,
                                 Key k, E e) {
  TTNode<Key,E> retval;
  if (rt == null) // Empty tree: create a leaf node for root
    return new TTNode<Key,E>(k, e, null, null,
                           null, null, null);
  if (rt.isLeaf()) // At leaf node: insert here
    return rt.add(new TTNode<Key,E>(k, e, null, null,
                                  null, null, null));
  // Add to internal node
  if (k.compareTo(rt.lkey()) < 0) { // Insert left
    retval = inserthelp(rt.lchild(), k, e);
    if (retval == rt.lchild()) return rt;
    else return rt.add(retval);
  }
  else if((rt.rkey() == null) ||
          (k.compareTo(rt.rkey()) < 0)) {
    retval = inserthelp(rt.cchild(), k, e);
    if (retval == rt.cchild()) return rt;
    else return rt.add(retval);
  }
  else { // Insert right
    retval = inserthelp(rt.rchild(), k, e);
    if (retval == rt.rchild()) return rt;
    else return rt.add(retval);
  }
}
private E findhelp(TTNode<Key,E> root, Key k) {
  if (root == null) return null;          // val not found
  if (k.compareTo(root.lkey()) == 0) return root.lval();
  if ((root.rkey() != null) && (k.compareTo(root.rkey())
       == 0))
    return root.rval();
  if (k.compareTo(root.lkey()) < 0)       // Search left
    return findhelp(root.lchild(), k);
  else if (root.rkey() == null)           // Search center
    return findhelp(root.cchild(), k);
  else if (k.compareTo(root.rkey()) < 0)  // Search center
    return findhelp(root.cchild(), k);
  else return findhelp(root.rchild(), k); // Search right
}
}
