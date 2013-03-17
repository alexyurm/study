/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** KD tree node implementation */
class KDNode<E> implements BinNode<E> {
  private int[] key;       // 2D key for this node
  private E element;        // Element for this node
  private KDNode<E> left;   // Pointer to left child
  private KDNode<E> right;  // Pointer to right child

  /** Constructors */
  public KDNode() {left = right = null; }
  public KDNode(int[] k, E val)
  { left = right = null; key = k; element = val; }
  public KDNode(int[] k, E val, KDNode<E> l, KDNode<E> r)
  { left = l; right = r; key = k; element = val; }

  /** Return and set the key value */
  public int[] key() { return key; }
  public void setKey(int[] k) { key = k; }

  /** Return and set the element value */
  public E element() { return element; }
  public void setElement(E v) { element = v; }

  /** Return and set the left child */
  public KDNode<E> left() { return left; }
  public void setLeft(KDNode<E> p) { left = p; }

  /** Return and set the right child */
  public KDNode<E> right() { return right; }
  public void setRight(KDNode<E> p) { right = p; }

  /** Return true if this is a leaf node */
  public boolean isLeaf()
  { return (left == null) && (right == null); }
}
