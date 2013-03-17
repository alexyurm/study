/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Test program for various preorder traversals of a binary tree.
    This includes two implementations for preorder traversal,
    a traversal that counts the number of nodes in the tree,
    and a traversal that verifies that a given tree is a BST. */

import java.io.*;

public class PreorderTest
    extends junit.framework.TestCase
{

static private StringBuffer out;

// Stick this in to test the preorder routine
static void visit (BinNode X) {
  out.append(X.element());
  out.append(" ");
}

// The first version of preorder
// is the preferred structure for preorder traversal.
// It tests the pointer to the current node before visiting it,
// but does not test that children are NULL before calling a
// visit on them. This version does not rely on the caller
// to test the root for safety.

// The second version of preorder does "look ahead" to test if
// a child should be visited before doing the visit.
// This requires an external test of the root to make it safe
// from calls on an empty tree.
/** @param rt is the root of the subtree */
void preorder(BinNode rt)
{
  if (rt == null) return; // Empty subtree - do nothing
  visit(rt);              // Process root node
  preorder(rt.left());    // Process all nodes in left
  preorder(rt.right());   // Process all nodes in right
}
void preorder2(BinNode rt)
{
  visit(rt);
  if (rt.left() != null) preorder(rt.left());
  if (rt.right() != null) preorder(rt.right());
}
int count(BinNode rt) {
  if (rt == null) return 0;  // Nothing to count
  return 1 + count(rt.left()) + count(rt.right());
}
boolean checkBST(BinNode<Integer> rt,
                 int low, int high) {
  if (rt == null) return true; // Empty subtree
  int rootkey = rt.element();
  if ((rootkey < low) || (rootkey > high))
    return false; // Out of range
  if (!checkBST(rt.left(), low, rootkey))
    return false; // Left side failed
  return checkBST(rt.right(), rootkey, high);
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


  public void testPreorder() {
    BSTNode<Integer,Integer> root = new BSTNode<Integer,Integer>(10,10);
    root.setLeft(new BSTNode<Integer,Integer>(15,15));
    root.setRight(new BSTNode<Integer,Integer>(20,20));
    preorder(root);
    assertEquals(out.toString(), "10 15 20 ");
  }

  public void testPreorder2() {
    BSTNode<Integer,Integer> root = new BSTNode<Integer,Integer>(10,10);
    root.setLeft(new BSTNode<Integer,Integer>(15,15));
    root.setRight(new BSTNode<Integer,Integer>(20,20));
    preorder2(root);
    assertEquals(out.toString(), "10 15 20 ");
  }

  public void testCount() {
    BSTNode<Integer,Integer> root = new BSTNode<Integer,Integer>(10,10);
    root.setLeft(new BSTNode<Integer,Integer>(15,15));
    root.setRight(new BSTNode<Integer,Integer>(20,20));
    assertEquals(3, count(root));
  }

  // The following tests for checkBST are a little dicey. The book
  // code uses a simple BinNode abstract class. Syntactically, this
  // works fine since the function uses only base class
  // functionality. But logically, this works only so long as the key
  // and element of the BSTNode are the same (since BinNode.element()
  // returns the element, not the key). 

  public void testBSTa() {
    BSTNode<Integer,Integer> root = new BSTNode<Integer,Integer>(10,10);
    root.setLeft(new BSTNode<Integer,Integer>(15,15));
    root.setRight(new BSTNode<Integer,Integer>(20,20));
    assertEquals(false, checkBST(root, -1, 1000));
  }

  public void testBSTb() {
    BSTNode<Integer,Integer> root = new BSTNode<Integer,Integer>(10,10);
    root.setLeft(new BSTNode<Integer,Integer>(5,5));
    root.setRight(new BSTNode<Integer,Integer>(20,20));
    assertEquals(true, checkBST(root, -1, 1000));
  }

}
