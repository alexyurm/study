/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

public class SkipNode<Key,E> {
  private E element;
  private Key key;

  public SkipNode<Key,E>[] forward;

  public E element() { return element; }
  public Key key() { return key; }

  public SkipNode(Key k, E r, int level) {
    key = k;
    element = r;
    forward = (SkipNode<Key,E>[])new SkipNode[level+1];
    for (int i=0; i<level; i++)
      forward[i] = null;
  }
}
