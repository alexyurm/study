import java.util.*;
import java.lang.Integer;

/** Binary tree node implementation: Pointers to children  */
class BinNode<E> {
   private int key;       //Key for this node
   private E element;   //Element for this node
   private BinNode<E> left;    //Pointer to the left child
   private BinNode<E> right;   //Pointer to the right child
   private BinNode<E> parent;  //Pointer to the parent

   /** Constructors */
   public BinNode() {
      parent = left = right = null;
   }

   public BinNode(int k, E val) {
      parent = left = right = null;
      key = k;
      element = val;
   }

   public BinNode(int k, E val, BinNode<E> l, BinNode<E> r) {
      key = k;
      element = val;
      left = l;
      right = r;
      l.setParent(this);
      r.setParent(this);
   }

   public BinNode(int k, E val, BinNode<E> l, BinNode<E> r, BinNode<E> p) {
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
   public BinNode<E> left() {
      return left;
   }

   public void setLeft(BinNode<E> p) {
      if (p != null) {
         left = p;
         p.setParent(this);
      } else {
         left = null;
      }
   }

   /** Return and set the right child */
   public BinNode<E> right() {
      return right;
   }

   public void setRight(BinNode<E> p) {
      if (p != null) {
         right = p;
         p.setParent(this);
      } else {
         right = null;
      }
   }

   /** Return and set the parent  */
   public void setParent(BinNode<E> p) {
      parent = p;
   }

   public BinNode<E> parent() {
      return parent;
   }

   /** Return true if this is a leaf node */
   public boolean isLeaf() {
      return (left == null) && (right == null);
   }
}

public class BinTree {

   private BinNode root;
   private int count;
   
   /** Constructors */
   public BinTree() {
      root = new BinNode();
   }

   public BinTree(BinNode node) {
      root = node;
   }

   /** Return the root */
   BinNode root() {
      return root;
   }

   /** Set the root */
   BinNode setRoot(BinNode rt) {
      root = rt;
      return root;
   }

   /** Return the count */
   int count() {
      return count;
   }
   
   void preorder(BinNode rt) {
      if (rt == null) return;
      visit(rt);
      preorder(rt.left());
      preorder(rt.right());
   }
   
   /** The second implementation of preorder. It is more efficient than preorder because it makes only half
    as many as recursive calls */   
   void preorder2(BinNode rt) {
   if (rt == null) return;
      visit(rt);
      if (rt.left()!= null) preorder2(rt.left());
      if (rt.right()!= null) preorder2(rt.right());
   }

   void postorder(BinNode rt) {
      if (rt == null) return;
      postorder(rt.left());
      postorder(rt.right());
      visit(rt);
   }

   int count(BinNode rt) {
      if (rt == null) return 0;
      return 1 + count(rt.left()) + count(rt.right());
   }

   static void visit(BinNode rt) {
      if (rt != null) {
         System.out.println(rt.key());
      } else {
         System.out.println("The node is null");
      }
   }

   /** Search for a node with a given key k in a binary search tree rooted at x */
   // The procedure begins its search at the root and traces a simple path downward in the tree. For each node x it encounters, it 
   // compares the key k with x.key. If the two keys are equal or x is null, the search terminates. If key is similar than x.key, the search continues in the left subtree.
   // else the search continues in the right subtree.
   BinNode search(BinNode x, int k) {
      while (x != null && x.key() != k) {
         if (k < x.key()) {
            x = x.left();
         } else {
            x = x.right();
         }
      }
      
      return x;
   }

   /** Return the maximum element of the tree rooted at rt */
   // It is also the the rightmost element of the tree rooted at rt
   BinNode maximum(BinNode rt) {
      if (rt != null) {
         BinNode x = rt;
         while (x.right() != null) {
            x = x.right();
         }
         return x;
      } else {
         return null;
      }
   }

   /** Return the Minimum element of the tree rooted at rt*/
   // It is also the leftmost element of the tree rooted by rt
   BinNode minimum(BinNode rt) {
      if (rt != null) {
         BinNode x = rt;
         while( x.left() != null) {
            x = x.left();   
         }
         return x;
      } else {
         return null;
      }
   }

   /** Return the largest key smaller than x */
   // We break the code into two cases:
   // 1) If the left subtree of node x is nonempty, then the predecessor of x is just the rightmost node of x's right subtree by calling maximum(x.left());
   // 2) If the left subtree of node x empty and x has a predecessor y, then y is the lowest ancestor of x whose right child is also an ancestor of x(remember
   //    that x is also the ancestor if itself). To find y, we simply go up the tree from x until we encounter a node that is the right child of its parent.

   BinNode predecessor(BinNode rt) {
      if (rt.left() != null) {
         return maximum(rt.left());   
      } else {
         BinNode x = rt;
         BinNode y = rt.parent();   

         while (y != null) {
            if (y.left() != null) {
               //!!We simply go up the tree x until encounter a node that is the right child of its parent.
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
   // We break the code into two cases: 
   // 1) If the right subtree of node x is nonempty, then the sucessor of x is just the leftmost node in x's right subtree by calling minimum(x.right());
   // 2) If the right subtree of node x is empty and x has a sucessor y, then y is the lowest ancestor of x whose left child is also an ancestor of x(remember
   //    that x is also the ancestor of itself). To find y, we simply go up the tree from x until we encounter a node that is the left child of its parent.
   BinNode successor(BinNode rt) {
      if (rt.right() != null) {
         //If the right subtree of x is nonempty, then the successor of x is just the leftmost node in x's right subtree.
         return minimum(rt.right());
      } else {
         BinNode x = rt;
         BinNode y = rt.parent(); //y is x's parent.

         while (y != null) {
            if (y.right() != null) {
               //!!We simply go up the tree from x until encounter a node that is the left child of its parent.
               //Remember the binary search tree's property: a given node's left subtree <= that node.
               
               //exercise 12.2-6 asks you to show, if the right subtree of node x is empty and
               //x has a successor y, then y is the lowest ancestor of x whose left child is also an ancestor of x.
               //
               //My thoughts: 
               //1) In this case, x must be in y's left subtree => y is x's ancestor => y's left child is also an ancestor of x.
               //2) Since y is x's sucessor(y.key is the smallest key >= x.key). It is impossible that there is another node z of y's ancestors can be the sucessor of x because:
               // 2-1) If y is in z's left subtree, then y.key <= z.key. Therefore z cannot be the sucessor of x;
               // 2-2) If y is in z's right subtree, then x is also in z' right subtree so that x.key >= z.key. That is also impossible.
               // Therefore, we know that y is the lowest ancestor of x.
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
   // The procedure takes a node z for which z.key = v, z.left = NIL, and z.right = NIL. It modifies T and some of the attributes of z in such a way
   // that it inserts z into an appropriate position in the tree. This method begins at the root of the tree and the pointer x traces a simple path
   // downward looking for a NIL to replace with the input item z. The procedure maintains the trailing pointer y as the parent y as the parent of x.
   // After initialization, the while loop causes these two pointers to move down the tree, going left or right depending on the comparision of z.key
   // with x.key, until x becomes NIL. This NIL occupies the position where we wish to place the input item z. We need the trailing pointer y, because
   // by the time we find the NIL where z belongs, the search has proceeded one step beyond the node that needs to be changed(good point!). 
   void insert(BinNode z) {
      BinNode y = null;
      BinNode x = root;
      
      while (x != null) {
         y = x; //save the x to y
         if (z.key() < x.key() ) {
            x = x.left();
         } else {
           x = x.right();
         }
      }
      z.setParent(y);
      if (y == null) { 
         setRoot(z); //handle the special case when the tree is empty
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
   void transplant(BinNode u, BinNode v) {
      if (u.parent() == null) {
         root = v; //handle the case when u is the root of the tree. That means the whole tree is replaced by another tree rooted by v
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
   // 3. Otherwise, z has both a left and a right child. We find z's sucessor y, which lies in z's right subtree and has no left child(Of course y has no left child)
   // We want to splice y out of its current location and have it replace z in the tree:
   // 3.1 If y is z's right child, then we replace z by y, leaving y's right child alone.
   // 3.2 Otherwise, y lies within z's right subtree but is not z's right child. In this case, we first replace y by its own right child,
   // and then we replace z by y.
    
   BinNode delete(BinNode z) {
      if (z.left() == null ) {
         transplant(z, z.right());
      } else if ( z.right() == null ) {
         transplant(z, z.left());
      } else {
         BinNode y = minimum(z.right()); //find y, which is the successor of z
         if ( y.parent() != z) { // if z is not y's parent 
            transplant(y, y.right()); //replace y by y's right child and y's parent ends up having y's right child as its appropriate child
            y.setRight(z.right()); //set z's right child as y's right child
            y.right().setParent(y);//set z's right child's parent as y.
         }
         
         transplant(z, y);  //replace z by y
         y.setLeft(z.left()); //set z's left child as y's left child.
         y.left().setParent(y); //set y's left child's parent as y.
      }

      return z;
   }

   public static void main(String[] args) {

      /* Create a whole bunch new nodes */
      BinNode<Integer> node_0 = new BinNode<Integer>(15, 0);
      BinNode<Integer> node_1 = new BinNode<Integer>(6, 0);
      BinNode<Integer> node_2 = new BinNode<Integer>(18, 0);
      BinNode<Integer> node_3 = new BinNode<Integer>(3, 0);
      BinNode<Integer> node_11 = new BinNode<Integer>(8, 0);
      BinNode<Integer> node_4 = new BinNode<Integer>(7, 0);
      BinNode<Integer> node_5 = new BinNode<Integer>(17, 0);
      BinNode<Integer> node_6 = new BinNode<Integer>(20, 0);
      BinNode<Integer> node_7 = new BinNode<Integer>(2, 0);
      BinNode<Integer> node_8 = new BinNode<Integer>(4, 0);
      BinNode<Integer> node_9 = new BinNode<Integer>(13, 0);
      BinNode<Integer> node_10 = new BinNode<Integer>(9, 0);

      /* Create a new BinTree and insert all the nodes */
      BinTree bTree = new BinTree(node_0);
      bTree.insert(node_1);
      bTree.insert(node_2);
      bTree.insert(node_3);
      bTree.insert(node_11);
      bTree.insert(node_4);
      bTree.insert(node_5);
      bTree.insert(node_6);
      bTree.insert(node_7);
      bTree.insert(node_8);
      bTree.insert(node_9);
      bTree.insert(node_10);

      BinNode<Integer> n1 = bTree.search(bTree.root(), 8);
      if ( n1 != null) {
         bTree.delete(n1);
         if (node_9.left() != null) {
            System.out.println(node_9.left().key());
         }
            
         if (node_9.right() != null) {
            System.out.println(node_9.right().key());
         }
      }

      return;
   }
}
