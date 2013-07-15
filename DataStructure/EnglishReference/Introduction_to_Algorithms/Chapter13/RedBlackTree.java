/*
*  -  A Balanced Search Tree can guarantee that the basic operations(search, insert, delete...) have log2(n) in the worst case(really??), 
*     whereas a binary search tree can only do log2(n) in average and n in the worst case(called chaining). Red Black tree is one of the
*     Balanced Search Tree.
*
*     Note: Why a binary search tree can only do log2(n) in average and n in the worst case?
*     
*     -  You can think about the BST with the following insertion order: 8, 7, 6, 5, 4, 3, 2, 1. This is called chaining.
*
*  -  A red-black tree is a binary search tree with one extra bit of storage per node: its color, which
*     can be either RED or BLACK. By constraining the node colors on any simple path from the root to a 
*     leaf, red-black trees ensure that no such path is more than twice as long as any other, so the 
*     tree is approximately balanced.
*
*  -  Each node now contains the attributes color, key, left, right, and p(parent). 
*
*  -  If a child or the parent of a node does not exist, the corresponding pointer attribute of the node
*     contains the value NIL(??). We shall regard these NILs as being pointers to leaves (external nodes) of
*     the binary search tree and the normal, key-bearing nodes as being internal nodes of the tree.
*
*     Note: Is the root an internal node? Yes, the root is considered an internal node.
*
*  -  A red-black tree is a binary tree that satisfies the following red-black properties:
*
*     1. Every node is either red or black.
*     2. The root is black.
*     3. Every leaf(NIL) is black.
*     4. If a node is red, then both its children are black.
*     5. For each node, all simple paths from the node to descendant leaves contain the same number of black
*        nodes(!!).
*
*  -  We use the sentinel so that we can treat a NIL child of a node x as an ordinary node whose parent is x. We only use
*     one sentinel T.nil to represent all the NILs - all leaves and the root's parent. By doing so, space can be saved. The 
*     values of the attributes p, left, right, and key of the sentinel are immaterial.
*
*  -  We call the number of black nodes on any simple path from, but not including, a node x down to a leaf the "black-height"
*     of the node, denoted bh(x). E.g. For a Red-Black tree which only has one node(the root), they "black-height" of the root
*     is bh(root) = 0.
*
*  -  By property 5, the notion of black-height is well defined, since all descending simple paths(??What are simple paths) from 
*     the node have the same number of black nodes(??).
*
*     I think simple paths are the paths that without loops.
*
*  -  A red-black tree with n internal nodes has height at most 2lg(n+1). See the proof on the page 309.
*
*  -  Rotation: 
*
*     The TREE-INSERT and TREE-DELETE operations on a red-black tree with n keys, take O(lg(n)) time but they also modify the tree such that
*     they can violate the red-black properties. Rotation is the technique to preserves the binary-tree property by changing the pointer structure.
*
*     Note: the TREE-INSERT and TREE-DELETE are the BST(Binary Search Tree) methods.
*       
*     When we do a left rotation on a node x, we assume:
*     
*     -  x's right child y is is not T.nil;
*     -  x may be any node in the tree whose right child is not T.nil.
*
*     The left rotation "pivots" around the link from x to y. It makes y the new root of the subtree, with x as y's left child and y's left child as
*     x's right child. (I still don't understand the concept of "pivots" around the link from x to y)
*
*
*
*
*
*/       
import java.util.*;
import java.lang.Integer;

/** Binary tree node implementation: Pointers to children  */
class BinNode<E> {
   private int key;       //Key for this node
   private E element;   //Element for this node
   private BinNode<E> left;    //Pointer to the left child
   private BinNode<E> right;   //Pointer to the right child
   private BinNode<E> parent;  //Pointer to the parent
   private Color color; //The color. It can be either RED or BLACK

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

   public BinNode(int k, E val, BinNode<E> l, BinNode<E> r, BinNode<E> p, Color c) {
      key = k;
      element = val;
      left = l;
      right = r;
      parent = p;
      l.setParent(this);
      r.setParent(this);
      color = c;
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

   /** Return and set the color */
   public void setColor(Color c) {
      color = c;
   }

   public Color color() {
      return color;
   }

   /** Return true if this is a leaf node */
   public boolean isLeaf() {
      return (left == null) && (right == null);
   }
}

public class RedBlackTree<E> {
   private BinNode<E> root;
   private int count;
   private BinNode<E> nil; //T is the special pointer pointing to the NIL node.

   /** Constructors */
   public RedBlackTree() {
      root = new BinNode<E>();
      root.setParent(nil);
      nil.setColor(Color.BLACK);
   }

   public RedBlackTree(BinNode<E> node) {
      root = node;
      root.setParent(nil);
      nil.setColor(Color.BLACK);
   }

   /** Return the root */
   BinNode root() {
      return root;
   }

   /** Set the root */
   BinNode setRoot(BinNode<E> rt) {
      root = rt;
      root.setParent(nil);
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

   public void leftRotate(BinNode<E> x) {
      BinNode<E> y = x.right();  //set y

      /* step 1 */
      x.setRight(y.left());   //turn y's left subtree into x's right subtree
      if (y.left() != nil) {
         y.left().setParent(x); //Set y's left node's parent as X
      }

      /* step 2 */
      y.setParent(x.parent()); //set Y's parent as X's parent
      if (x.parent() == nil) {
         setRoot(y); //if X is the root, set Y as the new root.
      } else if (x == x.parent().left()) {
         x.parent().setLeft(y);
      } else {
         x.parent().setRight(y);
      }

      /* step 3 */
      y.setLeft(x);
      x.setParent(y);
   }

   public void rightRotate(BinNode<E> y) {
      BinNode<E> x = y.left(); //set x

      /* step 1 */
      y.setLeft(x.right()); //turn x's right subtree into y's left subtree.
      if(x.right() != nil) {
         x.right().setParent(y);
      }

      /* step 2 */
      x.setParent(y.parent());
      if (y.parent() == nil) {
         setRoot(x);
      } else if (y.parent().left() == y) {
         y.parent().setLeft(x);
      } else {
         y.parent().setRight(x);
      }

      /* step 3 */
      x.setRight(y);
      y.setParent(x);
   }

   public void insertRB(BinNode<E> z) {
      BinNode<E> y = nil;
      BinNode<E> x = root;

      while (x != nil) {
         y = x;//save x to y
         if (z.key() < x.key()) {
            x = x.left();
         } else {
            x = x.right();
         }
      }
      z.setParent(y);
      
      if (y == nil) {
         setRoot(z);
      } else if (z.key() < y.key()) {
         y.setLeft(z);
      } else {
         y.setRight(z);
      }

      z.setLeft(nil);   //the extra steps to BST insert
      z.setRight(nil);  //the extra steps to BST insert
      z.setColor(Color.RED); //the extra steps to BST insert
      insertFixUpRB(z); //the extra steps to BST insert. Because coloring z red may cause violation of one of the red-black properties. 
                        //An insertFixUpRB call is required.
   }

   /**
   *  -  This function is to fix up the violations of property 4 and property 2.
   *
   *     2. The root is black.
   *     4. If a node is red, then both its children are black.
   *  
   *  -  case 1: z's uncle y is RED.
   *  -  case 2: z's uncle y is BLACK and z is a right child.
   *  -  case 3: z's uncle y is BLACK and z is a left child.
   *  
   */
   public void insertFixUpRB(BinNode<E> z) {
      while(z.parent().color() == RED ) { //z and its parent are both RED. This violates the property 4!!
         if (z.parent() == z.parent().parent().left()) {  //How can I make sure z.p.p exists??
            y = z.parent().parent().right(); //y is z's uncle 
            if (y.color() == Color.RED) {    //y's uncle(z) is RED. Case 1 applies.
               z.parent().setColor(BLACK);            //case 1. Set z's parent to BLACK.
               y.setColor(BLACK);                     //case 1. Set y to BLACK
               z.parent().parent().setColor(RED);     //case 1. Set z' grandparent to RED.
               z = z.parent().parent();               //case 1. z becomes the grandparent.
            } else if (z == z.parent().right()) { //y's uncle(z) is BLACK and z is the right child. Case 2 applies.
                  z = z.parent();   //case 2. Remember to set z as z's parent before the left rotation!!.
                  leftRotate(z);    //case 2. LEFT-ROTATE "pivots" around z.
            } 
               //!!The rotation in case 2 transfers to case 3 immediately as z becomes the left child.
               z.parent().setColor(BLACK);         //case 3. Set z's parent to BLACK.
               z.parent().parent().setColor(RED);  //case 3. Set z's grandparent to RED.
               rightRotate(z.parent().parent());   //case 3. RIGHT-ROTATE "pivots" around z's grandparent.
         } else if (z.parent() == z.parent().parent().right()) {  //How can I make sure z.p.p exists??
            y = z.parent().parent().left(); //y is z's uncle 
            if (y.color() == Color.RED) {    //y's uncle(z) is RED. Case 1 applies.
               z.parent().setColor(BLACK);            //case 1. Set z's parent to BLACK.
               y.setColor(BLACK);                     //case 1. Set y to BLACK
               z.parent().parent().setColor(RED);     //case 1. Set z' grandparent to RED.
               z = z.parent().parent();               //case 1. z becomes the grandparent.
            } else if (z == z.parent().left()) { //y's uncle(z) is BLACK and z is the left child. Case 2 applies.
                  z = z.parent();    //case 2. Remember to set z as z's parent before the right rotation!!.
                  rightRotate(z);    //case 2. RIGHT-ROTATE "pivots" around z.
            } 
               //!!The rotation in case 2 transfers to case 3 immediately as z becomes the right child.
               z.parent().setColor(BLACK);         //case 3. Set z's parent to BLACK.
               z.parent().parent().setColor(RED);  //case 3. Set z's grandparent to RED.
               leftRotate(z.parent().parent());    //case 3. LEFT-ROTATE "pivots" around z's grandparent.
         }
      }
      
      root.setColor(Color.BLACK);
   }
}
