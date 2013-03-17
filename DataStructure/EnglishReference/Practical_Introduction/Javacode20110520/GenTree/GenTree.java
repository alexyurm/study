/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** General tree ADT */
interface GenTree<E> {
  public void clear();      // Clear the tree
  public GTNode<E> root();  // Return the root
  // Make the tree have a new root, give first child and sib
  public void newroot(E value, GTNode<E> first,
                               GTNode<E> sib);
  public void newleftchild(E value); // Add left child
}
