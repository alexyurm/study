/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

public class GenTreeImpl<E> implements GenTree<E> {

private GTNodeImpl<E> rt;

public GenTreeImpl() { rt = null; }

public void clear() { rt = null; }

public GTNode<E> root() { return rt; }

public void newroot(E value, GTNode<E> first, GTNode<E> sib) {
  clear();
  rt = new GTNodeImpl<E>(value, null,
                         (GTNodeImpl<E>)first, (GTNodeImpl<E>)sib);
  if (first != null) first.setParent(rt);
  if (sib != null) sib.setParent(rt);
}

public void newleftchild(E value) {
  GTNodeImpl<E> temp = new GTNodeImpl<E>(value, rt, null,
				   rt.leftmostChild());
  rt.insertFirst(temp);
}
}
