import java.util.*;
import java.lang.Integer;

/** ADT for binary tree nodes */
interface BinNode <E> {
   /** Return and set the element value */
   public E element();
   public void setElement(E v);

   /** Return the left child */
   public BinNode<E> left();

   /** Return the right child */
   public BinNode<E> right();

   /** Return true if this is a leaf node */
   public boolean isLeaf();
}

/** Binary tree node implementation: Pointers to children  */
class BSTNode<E> implements BinNode<E> {
   private int key;       //Key for this node
   private E element;   //Element for this node
   private BSTNode<E> left;    //Pointer to the left child
   private BSTNode<E> right;   //Pointer to the right child
   private BSTNode<E> parent;  //Pointer to the parent

   /** Constructors */
   public BSTNode() {
      parent = left = right = null;
   }

   public BSTNode(int k, E val) {
      parent = left = right = null;
      key = k;
      element = val;
   }

   public BSTNode(int k, E val, BSTNode<E> l, BSTNode<E> r) {
      key = k;
      element = val;
      left = l;
      right = r;
      l.setParent(this);
      r.setParent(this);
   }

   public BSTNode(int k, E val, BSTNode<E> l, BSTNode<E> r, BSTNode<E> p) {
      key = k;
      element = val;
      left = l;
      right = r;
      parent = p;
      l.setParent(this);
      r.setParent(this);
   }

   /** Return and set the key value  */
   public int key() {
      return key;
   }
   public void setKey(int k) {
      key = k;
   }

   /** Return and set the element value */
   public E element() {
      return element;
   }

   public void setElement(E v) {
      element = v;
   }

   /** Return and set the left child */
   public BSTNode<E> left() {
      return left;
   }

   public void setLeft(BSTNode<E> p) {
      if (p != null) {
         left = p;
         p.setParent(this);
      } else {
         left = null;
      }
   }

   /** Return and set the right child */
   public BSTNode<E> right() {
      return right;
   }

   public void setRight(BSTNode<E> p) {
      if (p != null) {
         right = p;
         p.setParent(this);
      } else {
         right = null;
      }
   }

   /** Return and set the parent  */
   public void setParent(BSTNode<E> p) {
      parent = p;
   }

   public BSTNode<E> parent() {
      return parent;
   }

   /** Return true if this is a leaf node */
   public boolean isLeaf() {
      return (left == null) && (right == null);
   }
}

public class BinTree {

   private BSTNode root;
   private int count;
   
   /** Constructors */
   public BinTree() {
      root = new BSTNode();
   }

   public BinTree(BSTNode node) {
      root = node;
   }

   /** Return the root */
   BSTNode root() {
      return root;
   }

   /** Set the root */
   BSTNode setRoot(BSTNode rt) {
      root = rt;
      return root;
   }

   /** Return the count */
   int count() {
      return count;
   }
   
   void preorder(BSTNode rt) {
      if (rt == null) return;
      visit(rt);
      preorder(rt.left());
      preorder(rt.right());
   }
   
   /** The second implementation of preorder. It is more efficient than preorder because it makes only half
    as many as recursive calls */   
   void preorder2(BSTNode rt) {
   if (rt == null) return;
      visit(rt);
      if (rt.left()!= null) preorder2(rt.left());
      if (rt.right()!= null) preorder2(rt.right());
   }

   void postorder(BSTNode rt) {
      if (rt == null) return;
      postorder(rt.left());
      postorder(rt.right());
      visit(rt);
   }

   int count(BSTNode rt) {
      if (rt == null) return 0;
      return 1 + count(rt.left()) + count(rt.right());
   }

   static void visit(BSTNode rt) {
      if (rt != null) {
         System.out.println(rt.key());
      } else {
         System.out.println("The node is null");
      }
   }

   /** Return the maximum element of the tree rooted by rt */
   BSTNode maximum(BSTNode rt) {
      if (rt != null) {
         BSTNode x = rt;
         while (x.right() != null) {
            x = x.right();
         }
         return x;
      } else {
         return null;
      }
   }

   /** Return the Minimum element of the tree rooted by rt*/
   BSTNode minimum(BSTNode rt) {
      if (rt != null) {
         BSTNode x = rt;
         while( x.left() != null) {
            x = x.left();   
         }
         return x;
      } else {
         return null;
      }
   }

   /** Return the largest key smaller than x */
   BSTNode predecessor(BSTNode rt) {
      if (rt.left() != null) {
         return maximum(rt.left());   
      } else {
         BSTNode x = rt;
         BSTNode y = rt.parent();   
         //while (y != null && x.key() == y.left().key )
         while (y != null) {
            if (y.left() != null) {
               //We simply go up the tree x until encounter a node that is the left child of its parent.
               //Remember the binary search tree's property: a given node's right subtrees >= that node.
               if (x.key() != y.left().key()) { 
                  break;
               }
            } else {
               break;
            }
               
            x = y;
            y = x.parent();
         }
         return y;
      }
   }

   /** Return the smallest key greater than x */
   BSTNode successor(BSTNode rt) {
      if (rt.right() != null) {
         //return successor(rt.right());
         return minimum(rt.right());
      } else {
         BSTNode x = rt;
         BSTNode y = rt.parent();
         //while ( y != null && x.key() == y.right().key() ) {
         while (y != null) {
            if (y.right() != null) {
               //We simply go up the tree x until encounter a node that is the left child of its parent.
               //Remember the binary search tree's property: a given node's left subtree <= that node.
               if (x.key() != y.right().key()) { 
                  break;
               }
            } else {
               break;
            }
            
            x = y;
            y = y.parent();
         }
         return y;
      }
   }

   /** Insert a node */
   void insert(BSTNode z) {
      BSTNode y = null;
      BSTNode x = root;
      
      while (x != null) {
         y = x;
         if (z.key() < x.key() ) {
            x = x.left();
         } else {
           x = x.right();
         }
      }
      z.setParent(y);
      if (y == null) {
         setRoot(z);
      } else {
         if (z.key() < y.key()) {
            y.setLeft(z);
         } else {
            y.setRight(z);
         }
      }
   }

   /* Transplant */
   // In order to move subtrees around within the binary search tree, we define a subroutine TRANSPLANT, which replaces one subtree as a child
   // of its parent with another subtree. When TRANSPLANT replaces the subtree rooted at node u with the subtree rooted at node v, node u's parent
   // becomes node v's parent, and u's parent ends up having v as its appropriate child.
   void transplant(BSTNode u, BSTNode v) {
      if (u.parent() == null) {
         root = v;
      } else if(u == u.parent().left())  {
         u.parent().setLeft(v);
      } else {
         u.parent().setRight(v);
      }
      
      if ( v != null ) {
         v.setParent(u.parent());
      }
   }

   /* Delete a node z */
   // The procedure for deleting a given node z from a binary search tree T takes as arguments pointers to T and z:
   //
   // 1. If z has no left child, then we replace z by its right child, which may or may not be NIL. When z's right child
   // is NIL, this case deals with the situation in which z has no children. When z's right child is on-NIL, this case
   // handles the situation in which z has just one child, which is its right child.
   //
   // 2. If z has just one child, which is just its left child, then we replace z by its left child.
   //
   // 3. Otherwise, z has both a left and a right child. We find z's sucessor y, which lies in z's right subtree and has no left child
   // We want to splice y out of its current location and have it replace z in the tree:
   // 3.1 If y is z's right child, then we replace z by y, leaving y's right child alone.
   // 3.2 Otherwise, y lies within z's right subtree but is not z's right child. In this case, we first replace y by its own right child,
   // And then we replace z by y.
    
   BSTNode delete(BSTNode rt, BSTNode z) {
      if (z.left() == null ) {
         rt.transplant(z, z.right());
      } else if ( z.right() == null ) {
         rt.transplant(z, z.left());
      } else {
         BSTNode y = rt.minimum(z.right()); //find y, which is the successor of z
         if ( y.parent() != z) { // if z is not y's parent 
            rt.transplant(y, y.right()); //replace y by y's right child and y's parent ends up having y's right child as its appropriate child
            y.setRight(z.right());
            y.right().setParent(y);
         }
         rt.transplant(z, y); //replace 
         y.setLeft(z.left());
         y.left().setParent(y);
      }

   }

   public static void main(String[] args) {

      /* Create a whole bunch new nodes */
      BSTNode<Integer> node_0 = new BSTNode<Integer>(15, 0);
      BSTNode<Integer> node_1 = new BSTNode<Integer>(6, 0);
      BSTNode<Integer> node_2 = new BSTNode<Integer>(18, 0);
      BSTNode<Integer> node_3 = new BSTNode<Integer>(3, 0);
      BSTNode<Integer> node_4 = new BSTNode<Integer>(7, 0);
      BSTNode<Integer> node_5 = new BSTNode<Integer>(17, 0);
      BSTNode<Integer> node_6 = new BSTNode<Integer>(20, 0);
      BSTNode<Integer> node_7 = new BSTNode<Integer>(2, 0);
      BSTNode<Integer> node_8 = new BSTNode<Integer>(4, 0);
      BSTNode<Integer> node_9 = new BSTNode<Integer>(13, 0);
      BSTNode<Integer> node_10 = new BSTNode<Integer>(9, 0);

      /* Create a new BinTree */
      BinTree bTree = new BinTree(node_0);
      bTree.insert(node_1);
      bTree.insert(node_2);
      bTree.insert(node_3);
      bTree.insert(node_4);
      bTree.insert(node_5);
      bTree.insert(node_6);
      bTree.insert(node_7);
      bTree.insert(node_8);
      bTree.insert(node_9);
      bTree.insert(node_10);

      /* Do stuff */
      //BSTNode x = bTree.successor(node_6);
      //BinTree.visit(x);

      BSTNode y = bTree.predecessor(node_7);
      BinTree.visit(y);

      return;
   }
}
