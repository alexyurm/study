/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

public class KDtree<E> implements SpatialDictionary<E> {
  private KDNode<E> root;
  private int D = 2; // Only supporting 2D points
  int nodecount;     // Number of nodes in the KD tree

  public KDtree() { root = null; }

  public void clear() { root = null; }

  public void insert(int[] pt, E val) {
    root = inserthelp(root, pt, val, 0);
    nodecount++;
  }

  public E remove(int[] key) {
    E temp = findhelp(root, key, 0);   // First find it
    if (temp != null) {
      root = removehelp(root, key, 0); // Now remove it
      nodecount--;
    }
    return temp;
  }

  /** Remove and return the root node from the dictionary.
      @return The record removed, null if tree is empty. */
  public E removeAny() {
    if (root != null) {
      E temp = root.element();
      root = removehelp(root, root.key(), 0);
      nodecount--;
      return temp;
    }
    else return null;
  }

  public E find(int key[]) { return findhelp(root, key, 0); }
  
  public void regionSearch(int[] point, int radius)
    { rshelp(root, point, radius, 0); }

  public boolean isEmpty() { return root == null; }

  public void print() {
    if (root == null)
      System.out.println("The k-d tree is empty.");
    else {
      printhelp(root, 0);
      System.out.println();
    }
  }

  public int size() {
    return nodecount;
  }

  private KDNode<E> inserthelp(KDNode<E> rt, int[] key, E val, int level) {
    if (rt == null) return new KDNode<E>(key, val);
    int[] rtkey = rt.key();
    if (rtkey[level] > key[level])
      rt.setLeft(inserthelp(rt.left(), key, val, (level+1)%D));
    else
      rt.setRight(inserthelp(rt.right(), key, val, (level+1)%D));
    return rt;
  }

private E findhelp(KDNode<E> rt, int[] key, int level) {
  if (rt == null) return null;
  E it = rt.element();
  int[] itkey = rt.key();
  if ((itkey[0] == key[0]) && (itkey[1] == key[1]))
    return rt.element();
  if (itkey[level] > key[level])
    return findhelp(rt.left(), key, (level+1)%D);
  else
    return findhelp(rt.right(), key, (level+1)%D);
}
private KDNode<E>
findmin(KDNode<E> rt, int descrim, int level) {
  KDNode<E> temp1, temp2;
  int[] key1 = null;
  int[] key2 = null;
  if (rt == null) return null;
  temp1 = findmin(rt.left(), descrim, (level+1)%D);
  if (temp1 != null) key1 = temp1.key();
  if (descrim != level) {
    temp2 = findmin(rt.right(), descrim, (level+1)%D);
    if (temp2 != null) key2 = temp2.key();
    if ((temp1 == null) || ((temp2 != null) &&
                   (key1[descrim] > key2[descrim])))
    temp1 = temp2;
    key1 = key2;
  } // Now, temp1 has the smaller value
  int[] rtkey = rt.key();
  if ((temp1 == null) || (key1[descrim] > rtkey[descrim]))
    return rt;
  else
    return temp1;
}
private void rshelp(KDNode<E> rt, int[] point,
                    int radius, int lev) {
  if (rt == null) return;
  int[] rtkey = rt.key();
  if (InCircle(point, radius, rtkey))
    System.out.println(rt.element());
  if (rtkey[lev] > (point[lev] - radius))
    rshelp(rt.left(), point, radius, (lev+1)%D);
  if (rtkey[lev] < (point[lev] + radius))
    rshelp(rt.right(), point, radius, (lev+1)%D);
}
  private void printhelp(KDNode<E> rt, int level) {
    for(int i=1; i<=level; i++) System.out.print(" ");
    if (rt == null) {
      System.out.println("null");
      return;
    }
    System.out.println(rt.element());
    printhelp(rt.left(), level+1);
    printhelp(rt.right(), level+1);
  }

  private KDNode<E> removehelp(KDNode<E> rt, int[] key, int level) {
    if (rt == null) return null;
    int[] rtkey = rt.key();
    if (key[level] < rtkey[level])
      rt.setLeft(removehelp(rt.left(), key, (level+1)%D));
    else if (key[level] > rtkey[level])
      rt.setRight(removehelp(rt.right(), key, (level+1)%D));
    else {  // Found it
      if (rt.right() == null)
        if (rt.left() == null) // Just drop element
	  return null;
	else { // Switch subtree to right
	  rt.setRight(rt.left());
	  rt.setLeft(null);
        }
      KDNode<E> temp = findmin(rt.right(), level, (level+1)%D);
      rt.setRight(removehelp(rt.right(), temp.key(), (level+1)%D));
      rt.setElement(temp.element());
    }
    return rt;
  }


  private boolean InCircle(int[] point, int radius, int[] coord) {
    assert point.length == coord.length : "Bad InCircle";
    System.out.println("Test against " + coord[0] + ", " + coord[1]);
    int sum = 0;
    for (int i=0; i<point.length; i++)
      sum += (point[i] - coord[i]) * (point[i] - coord[i]);
    return sum < (radius * radius);
  }

}
