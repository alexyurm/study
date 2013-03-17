/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** 2-3 tree node implementation */
class TTNode<Key extends Comparable<? super Key>,E> {
  private E lval;        // The left record
  private Key lkey;        // The node's left key
  private E rval;        // The right record
  private Key rkey;        // The node's right key
  private TTNode<Key,E> left;   // Pointer to left child
  private TTNode<Key,E> center; // Pointer to middle child
  private TTNode<Key,E> right;  // Pointer to right child

  public TTNode() { center = left = right = null; }
  public TTNode(Key lk, E lv, Key rk, E rv,
                TTNode<Key,E> p1, TTNode<Key,E> p2,
                TTNode<Key,E> p3) {
    lkey = lk; rkey = rk;
    lval = lv; rval = rv;
    left = p1; center = p2; right = p3;
  }

  public boolean isLeaf() { return left == null; }
  public TTNode<Key,E> lchild() { return left; }
  public TTNode<Key,E> rchild() { return right; }
  public TTNode<Key,E> cchild() { return center; }
  public Key lkey() { return lkey; }  // Left key
  public E lval() { return lval; }  // Left value
  public Key rkey() { return rkey; }  // Right key
  public E rval() { return rval; }  // Right value
  public void setLeft(Key k, E e) { lkey = k; lval = e; }
  public void setRight(Key k, E e) { rkey = k; rval = e; }
  public void setLeftChild(TTNode<Key,E> it) { left = it; }
  public void setCenterChild(TTNode<Key,E> it)
    { center = it; }
  public void setRightChild(TTNode<Key,E> it)
    { right = it; }
/** Add a new key/value pair to the node. There might be a
    subtree associated with the record being added. This
    information comes in the form of a 2-3 tree node with
    one key and a (possibly null) subtree through the
    center pointer field. */
  public TTNode<Key,E> add(TTNode<Key,E> it) {
    if (rkey == null) { // Only one key, add here
      if (lkey.compareTo(it.lkey()) < 0) {
	rkey = it.lkey(); rval = it.lval();
        right = center; center = it.cchild();
      }
      else {
	rkey = lkey; rval = lval; right = center;
        lkey = it.lkey(); lval = it.lval();
        center = it.cchild();
      }
      return this;
    }
    else if (lkey.compareTo(it.lkey()) >= 0) { // Add left
      center = new TTNode<Key,E>(rkey, rval, null, null,
                               center, right, null);
      rkey = null; rval = null; right = null;
      it.setLeftChild(left); left = it;
      return this;
    }
    else if (rkey.compareTo(it.lkey()) < 0) { // Add center
      it.setCenterChild(new TTNode<Key,E>(rkey, rval, null,
                          null, it.cchild(), right, null));
      it.setLeftChild(this);
      rkey = null; rval = null; right = null;
      return it;
    }
    else { // Add right
      TTNode<Key,E> N1 = new TTNode<Key,E>(rkey, rval, null,
                                   null, this, it, null);
      it.setLeftChild(right);
      right = null; rkey = null; rval = null;
      return N1;
    }
  }

  public String toString() {
    if (rkey == null) return lkey.toString();
    else return lkey.toString() + " " + rkey.toString();
  }
}
