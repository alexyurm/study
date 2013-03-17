/** A JUnit test class for the general tree. */

import java.io.*;

public class GenTreeTest
    extends junit.framework.TestCase
{

  static private StringBuffer out;

  static private <E> void PrintNode(GTNode<E> it) {
    if (it == null) out.append("NULL");
    else out.append(it.value() + " ");
  }
/** Preorder traversal for general trees */
static <E> void preorder(GTNode<E> rt) {
  PrintNode(rt);
  if (!rt.isLeaf()) {
    GTNode<E> temp = rt.leftmostChild();
    while (temp != null) {
      preorder(temp);
      temp = temp.rightSibling();
    }
  }
}

  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    out = new StringBuffer(100);
  }

  public void testGT() {
    GenTree<Integer> tree = new GenTreeImpl<Integer>();
    GTNode<Integer> ptr;
    tree.newroot(new Integer(1), null, null);
    ptr = tree.root();
    preorder(tree.root());
    assertEquals(out.toString(), "1 ");
    ptr.insertFirst(new GTNodeImpl<Integer>(new Integer(2)));
    out = new StringBuffer(100);
    preorder(tree.root());
    assertEquals(out.toString(), "1 2 ");
    ptr = ptr.leftmostChild();
    assertEquals(ptr.value(), new Integer(2));
    ptr.insertNext(new GTNodeImpl<Integer>(new Integer(3)));
    out = new StringBuffer(100);
    preorder(tree.root());
    assertEquals(out.toString(), "1 2 3 ");
    ptr.insertNext(new GTNodeImpl<Integer>(new Integer(4)));
    out = new StringBuffer(100);
    preorder(tree.root());
    assertEquals(out.toString(), "1 2 4 3 ");
  }
}
