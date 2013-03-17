/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Implementation of General Tree Nodes: Left Child/
    Right Sibling implementation (with parent pointer) */
public class GTNodeImpl<E> implements GTNode<E> {

private GTNodeImpl<E> rent;
private GTNodeImpl<E> leftchild;
private GTNodeImpl<E> rightsib;
private E element;

public GTNodeImpl(E value) {
  rent = leftchild = rightsib = null;
  element = value;
}

public GTNodeImpl(E value, GTNodeImpl<E> par,
                  GTNodeImpl<E> leftc, GTNodeImpl<E> rights) {
  element = value;
  rent = par; leftchild = leftc; rightsib = rights;
}  

public E value() { return element; }

public boolean isLeaf() { return leftchild == null; }

public GTNodeImpl<E> parent() { return rent; }

public GTNodeImpl<E> leftmostChild() { return leftchild; }

public GTNodeImpl<E> rightSibling() { return rightsib; }

public void setValue(E value) { element = value; }

public void setParent(GTNode<E> par) {rent = (GTNodeImpl<E>)par; }

public void insertFirst(GTNode<E> it) {
  GTNodeImpl<E>n = (GTNodeImpl<E>)it;
  n.rent = this;
  n.rightsib = leftchild;
  leftchild = n;
}

public void insertNext(GTNode<E> it) {
  GTNodeImpl<E>n = (GTNodeImpl<E>)it;
  n.rent = rent;
  n.rightsib = rightsib;
  rightsib = n;
}

public void removeFirst() {
  if (leftchild == null) return;
  leftchild = leftchild.rightsib;
}

public void removeNext() {
  if (rightsib == null) return;
  rightsib = rightsib.rightsib;
}

}
