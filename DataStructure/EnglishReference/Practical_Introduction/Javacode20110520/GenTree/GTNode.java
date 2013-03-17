/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** General tree node ADT */
interface GTNode<E> {
  public E value();
  public boolean isLeaf();
  public GTNode<E> parent();
  public GTNode<E> leftmostChild();
  public GTNode<E> rightSibling();
  public void setValue(E value);
  public void setParent(GTNode<E> par);
  public void insertFirst(GTNode<E> n);
  public void insertNext(GTNode<E> n);
  public void removeFirst();
  public void removeNext();
}
