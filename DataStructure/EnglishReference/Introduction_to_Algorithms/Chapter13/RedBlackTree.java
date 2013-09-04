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
*     contains the value NIL. We shall regard these NILs as being pointers to leaves (external nodes) of
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
*        nodes.
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
*     x's right child. (I still don't understand the concept of "pivots" around the link from x to y ?? Are the Colors changed for those two nodes??)
*
*  -  RB-INSERT(T, Z)
*
*     1  y = T.nil
*     2  x = T.root
*     3  while x != T.nil
*     4     y = x
*     5     if (z.key < x.key)
*     6        x = x.left
*     7     else x = x.right
*     8  z.p = y
*     9  if y == T.nil
*     10    T.root = z
*     11 elseif z.key < y.key
*     12    y.left = z
*     13 else y.right = z   
*     14 z.left = T.nil
*     15 z.right = T.nil
*     16 z.color = RED
*     17 RB-INSERT-FIXUP(T,z)
*
*     The insert process is pretty much the same as the normal BST insert except for the addition of RB-INSERT-FIXUP(T,z)
*
*  -  RB-INSERT-FIXUP(T,z)
*
*     The fact that z and z's parent are both RED violates property 4
*
*     1  while z.p.color == RED
*     2     if z.p == z.p.p.left
*     3        y = z.p.p.right
*     4        if y.color == RED
*     5           z.p.color == BLACK   //case 1
*     6           y.color = BLACK      //case 1
*     7           z.p.p.color = RED    //case 1
*     8           z = z.p.p            //case 1
*     9        else if z == z.p.right
*     10                z = z.p              //case 2
*     11                LEFT-ROTATE(T,z)     //case 2
*     12            z.p.color = BLACK        //case 3
*     13            z.p.p.color = RED        //case 3
*     14            RIGHT-ROTATE(T, z.p.p)//case3
*     15    else(same as then clause with "right" and "left" exchanged)
*     16    T.root.color = BLACK
*
*     case 1: z's uncle y is red
*     case 2: z's uncle y is BLACK and z is a right child of its parent 
*     case 3: z's uncle y is BLACK and z is a left child of its parent
*
*     note: In case 2, we do a left rotate in order to make z as a left child of its parent. That means it transfers to case 3 immediately.
*     
*     -  RB-DELETE
*
*     RB-TRANSPLANT(T,u,v)
*
*     1  if u.p == T.nil
*     2     T.root = v
*     3  elseif u == u.p.left
*     4     u.p.left = v
*     5  else u.p.right = v
*     6  v.p = u.p
*
*     RB-DELETE(T, z)
*
*     1  y = z
*     2  y-original-color = y.color
*     3  if z.left == T.nil
*     4     x = z.right
*     5     RB-TRANSPLANT(T, z, z.right)
*     6  elseif z.right == T.nil
*     7     x = z.left
*     8     RB-TRANSPLANT(T, z, z.left)
*     9  else y = TREE-MINIMUM(z.right)
*     10    y-original-color = y.color
*     11    x = y.right
*     12    if y.p == z
*     13       x.p = y // This step is correct and necessary because simply calling RB-TRANSPLANT(T, y, y.right) will direct x.p to z, which is going to be removed. That is not good.
*     14    else RB-TRANSPLANT(T, y, y.right)
*     15       y.right = z.right
*     16       y.right.p = y
*     17    RB-TRANSPLANT(T,z,y)
*     18    y.left = z.left
*     19    y.left.p = y
*     20    y.color = z.color
*     21 if y-original-color == BLACK   
*     22    RB-DELETE-FIXUP(T,x)
*
*     RB-DELETE contains almost twice as many lines of pseudocode as TREE-DELETE, the procedures have the same basic structure. Here are the differences between the two 
*     procedures.
*
*     z: the node to be removed
*     y: the node pointing to z if z has only one child, or the successor of z if z has two children. Therefore, y will be either removed(same fate as z) or moved.
*     x: the node which is moving to y's original position.
*
*     1) Line 1 sets y to point node z when z has fewer than two children and is therefore removed. When z has two children, line 9 sets y to 
*        point to z's successor, just as in TREE-DELETE, and y will move into z's position in the tree.
*
*        In summary: y can be either 1) z if z has only one child; 2) z's successor if z has two children. Therefore, the fate of node y is either removed or moved to z's original
*        position.
*
*     2) Because node y's color might change, the variable y-original-color stores y's color before any changes occur. Line 2 and 10 set this variable
*        immediately after assignments to y. When z has two children, then y != z and node y moves into node z's original position in the red-black tree. 
*        Line 20 gives y the same color as z. We need to save y's original color in order to test it at the end of RB-DELETE. if it was black, then removing 
*        or moving y could cause violations of the red-black properties.
*
*        In summary: the variable "y-original-color" is used to store the color of the node z which is going to be removed or the color of z's successor y when z has two children. 
*        This variable is used to restore the red-black properties which are probablly destroyed. But what happen if z has two children and z's color is BLACK and y(z's successor)'s 
*        color is RED? Should we still call RB-DELETE-FIXUP? No. Because in line 20, y's color is replaced by z's color therefore no property violations occur.
*
*     3) We keep track of the node x that moves into node y's original position. The assignments in line 4, 7, and 11 set x to point to either y's only
*        child or if y has no children, the sentinel T.nil. Since node x moves into node y's original position, the attribute x.p is always set to point to the original
*        position in the tree of y's parent, evenif x is, in fact, the sentinel T.nil. Unless z is y's original parent(which occurs only when z has two children and its successor y 
*        is z's right child) the assignment to x.p takes place in line 6 of RB-TRANSPLANT (Observe that when RB-TRANSPLANT is called in lines 5, 8 and 14, the second 
*        parameter passed is the same as x.)
*
*        When y's original parent is z, however, we do not want x.p to point to y's original parent, since we are removing that node from the tree. Because
*        node y will move up to take z's position in the tree. setting x.p to y in line 13 causes x.p to point to the original position of y's parent, even 
*        if x = T.nil.
*
*        In summary: x is pointing to y's one child because x is moving to that position. If z has two children and y (z's successor) is its right child, x.p is set to y
*
*     4) Finally, if node y was black, we might have introduced one or more violations of the red-black properties, and so we call RB-DELETE-FIXUP in line 22 to restore the red-black
*        properties. If y is red, the red-black properties still hold when y is removed or moved, for the following reasons:
*
*        1. No black-heights in the tree have changed.
*
*        Why? what if z's color is BLACK? No worries, since line 20 shows that y's color is replaced by z's color.
*        
*        2. No red nodes have been made adjacent. Because y takes z's place in the tree, along with z's color, we cannot have two adjacent red nodes at y's new position in the tree(??).
*           In addition, if y was not z's right child, then y's original right child x replaces y in the tree. If y is red, then x must be black, and so replacing y by x cannot
*           cause two red nodes to become adjacent.
*
*           Questions: 1) if z has two children l and y(z's successor), which are z's left and right child respectively, what happen if z is BLACK, l is RED and y is RED.
*        
*           Still no ways! line 20 shows that y's color is replaced by z's color.   
*
*        3. Since y could not have been the root if it was red, the root remains black. 
*
*     5) If node y was black, three problems may arise, which the call of RB-DELETE-FIXUP will remedy.
*
*        1. First, if y had been the root and a red child of y becomes the new root, we have violated property 2.
*
*           Comment: How can a red child of y become the new root? When z has only one left child.
*
*        2. Second, if both x and x.p are red, then we have violated property 4. 
*
*        3. Third, moving y within the tree causes any simple path that previously contained by y to have one fewer black node. Thus property 5 is violated y any ancestor of
*           y in the tree. We can correct the violation of property 5 by saying that node x, now occupying y's original position, has an "extra" black. That is, if we add 1
*           to the count of black nodes on any simple path that contains x, them under this interpretation, property 5 holds. When we remove or move the black node y, we "push"
*           its blackness onto node x. The problem is that now x is neither red nor black, thereby violating property 1. Instead, node x is either "doubly black" or "red-and-black"
*           , and it contributes either 2 or 1, 
*
*        RB-DELETE-FIXUP(T,x)
*        1  while x != T.root and x.color = BLACK
*        2     if x == x.p.left
*        3        w = x.p.right
*        4        if w.color == RED
*        5           w.color = BLACK      // case 1
*        6           x.p.color = RED      // case 1
*        7           LEFT-ROTATE(T, x.p)  // case 1
*        8           w = x.p.right        // case 1
*        9        if w.left.color == BLACK and w.right.color == BLACK 
*        10          w.color = RED        // case 2
*        11          x = x.p              // case 2
*        12       else if w.right.color == BLACK
*        13          w.left.color = BLACK    // case 3
*        14          w.color = RED           // case 3
*        15          RIGHT-ROTATE(T,w)       // case 3
*        16          w = x.p.right           // case 3
*        17       w.color = x.p.color        // case 4
*        18       x.p.color = BLACK          // case 4
*        19       w.right.color = BLACK      // case 4
*        20       LEFT-ROTATE(T,x.p)         // case 4
*        21       x = T.root                 // case 4
*        22    else (same as then clause with "right" and "left" exchanged)
*        23 x.color = BLACK
*
*        The procedure RB-DELETE-FIXUP restores properties 1, 2, and 4(why 1??). The goal of the while loop in lines 1-22 is to move the extra black up the tree until
*
*        1. x points to a red-black node, in which case we color x (singly) black in line 23.
*        2. x points to the root, in which case we simply "remove" the extra black; or
*        3. having performed suitable rotations and recolorings, we exit the loop.
*
*        Within the while loop, x always points to a nonroot doubly black node. 
*
*
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
      nil = new BinNode<E>();

      root.setParent(nil);
      root.setLeft(nil);
      root.setRight(nil);
      
      root.setColor(Color.BLACK);
      nil.setColor(Color.BLACK);
   }

   public RedBlackTree(BinNode<E> node) {
      root = node;
      nil = new BinNode<E>();

      root.setParent(nil);
      root.setLeft(nil);
      root.setRight(nil);

      root.setColor(Color.BLACK);
      nil.setColor(Color.BLACK);
   }

   /** Return the root */
   BinNode<E> root() {
      return root;
   }

   /** Set the root */
   BinNode<E> setRoot(BinNode<E> rt) {
      root = rt;
      root.setParent(nil);
      return root;
   }

   /** Return the count */
   int count() {
      return count;
   }

   /** preorder tree walk: root, left, right */
   void preorder(BinNode rt) {
      if (rt == nil) return; //We use the "nil" pointer to replace "null" in a normal BST
      visit(rt);
      preorder(rt.left());
      preorder(rt.right());
   }
   
   /** The second implementation of preorder. It is more efficient than preorder because it makes only half
    as many as recursive calls */   
   void preorder2(BinNode rt) {
   if (rt == nil) return;//We use the "nil" pointer to replace "null" in a normal BST
      visit(rt);
      if (rt.left()!= null) preorder2(rt.left());
      if (rt.right()!= null) preorder2(rt.right());
   }

   /** postorder tree walk: left, right, root */
   void postorder(BinNode rt) {
      if (rt == nil) return;//We use the "nil" pointer to replace "null" in a normal BST
      postorder(rt.left());
      postorder(rt.right());
      visit(rt);
   }

   /** The second implementation of postorder. It is more efficient than postorder because it makes only half
    as many as recursive calls */   
   void postorder2(BinNode rt) {
      if (rt == nil) return;//We use the "nil" pointer to replace "null" in a normal BST
      if (rt.left() != null) postorder(rt.left());
      if (rt.right() != null) postorder(rt.right());
      visit(rt);
   }

   /** inorder tree walk: left, root, right */
   void inorder(BinNode rt) {
      if (rt == nil) return;//We use the "nil" pointer to replace "null" in a normal BST
      postorder(rt.left());
      visit(rt);
      postorder(rt.right());
   }

   /** The second implementation of inorder. It is more efficient than inorder because it makes only half
    as many as recursive calls */   
   void inorder2(BinNode rt) {
      if (rt == nil) return;//We use the "nil" pointer to replace "null" in a normal BST
      postorder(rt.left());
      visit(rt);
      postorder(rt.right());
   }

   int count(BinNode rt) {
      if (rt == null) return 0;
      return 1 + count(rt.left()) + count(rt.right());
   }

   static void visit(BinNode rt) {
      if (rt != null) {
         System.out.println(rt.key() + "(" + rt.color() + ")");
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
   BinNode<E> maximum(BinNode<E> rt) {
      if (rt != null) {
         BinNode<E> x = rt;
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
   BinNode<E> minimum(BinNode<E> rt) {
      if (rt != null) {
         BinNode<E> x = rt;
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
   BinNode<E> predecessor(BinNode<E> rt) {
      if (rt.left() != null) {
         return maximum(rt.left());   
      } else {
         BinNode<E> x = rt;
         BinNode<E> y = rt.parent();   

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
   BinNode<E> successor(BinNode<E> rt) {
      if (rt.right() != null) {
         //If the right subtree of x is nonempty, then the successor of x is just the leftmost node in x's right subtree.
         return minimum(rt.right());
      } else {
         BinNode<E> x = rt;
         BinNode<E> y = rt.parent(); //y is x's parent.

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

   /** The operation LEFT-ROTATE(T,x) transforms the configuration of the two ndoes on the right into the configuration on the left
   by changing a constant number of pointers */
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

      //The same as BST Insert
      while (x != nil) {
         y = x;//save x to y

         if (z.key() < x.key()) {
            x = x.left();
         } else {
            x = x.right();
         }
      }
      z.setParent(y);
      
      //The same as BST Insert
      if (y == nil) {
         setRoot(z);
      } else if (z.key() < y.key()) {
         y.setLeft(z);
      } else {
         y.setRight(z);
      }

      //The Extra steps for RedBlack tree
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
      BinNode<E> y = null;
      while(z.parent().color() == Color.RED ) { //z and its parent are both RED. This violates the property 4!!
         if (z.parent() == z.parent().parent().left()) {  //How can I make sure z.p.p exists??
            y = z.parent().parent().right(); //y is z's uncle 
            if (y.color() == Color.RED) {    //y's uncle(z) is RED. Case 1 applies.
               z.parent().setColor(Color.BLACK);            //case 1. Set z's parent to BLACK.
               y.setColor(Color.BLACK);                     //case 1. Set y to BLACK
               z.parent().parent().setColor(Color.RED);     //case 1. Set z' grandparent to RED.
               z = z.parent().parent();               //case 1. z becomes the grandparent.
            } else if (z == z.parent().right()) { //y's uncle(z) is BLACK and z is the right child. Case 2 applies.
                  z = z.parent();   //case 2. Remember to set z as z's parent before the left rotation!!.
                  leftRotate(z);    //case 2. LEFT-ROTATE "pivots" around z.

                  z.parent().setColor(Color.BLACK);         //case 3. Set z's parent to BLACK.
                  z.parent().parent().setColor(Color.RED);  //case 3. Set z's grandparent to RED.
                  rightRotate(z.parent().parent());   //case 3. RIGHT-ROTATE "pivots" around z's grandparent.
            } else {
               //!!The rotation in case 2 transfers to case 3 immediately as z becomes the left child.
               z.parent().setColor(Color.BLACK);         //case 3. Set z's parent to BLACK.
               z.parent().parent().setColor(Color.RED);  //case 3. Set z's grandparent to RED.
               rightRotate(z.parent().parent());   //case 3. RIGHT-ROTATE "pivots" around z's grandparent.
            }
         } else if (z.parent() == z.parent().parent().right()) {  //How can I make sure z.p.p exists??
            y = z.parent().parent().left(); //y is z's uncle 
            if (y.color() == Color.RED) {    //y's uncle(z) is RED. Case 1 applies.
               z.parent().setColor(Color.BLACK);            //case 1. Set z's parent to BLACK.
               y.setColor(Color.BLACK);                     //case 1. Set y to BLACK
               z.parent().parent().setColor(Color.RED);     //case 1. Set z' grandparent to RED.
               z = z.parent().parent();               //case 1. z becomes the grandparent.
            } else if (z == z.parent().left()) { //y's uncle(z) is BLACK and z is the left child. Case 2 applies.
                  z = z.parent();    //case 2. Remember to set z as z's parent before the right rotation!!.
                  rightRotate(z);    //case 2. RIGHT-ROTATE "pivots" around z.

                  z.parent().setColor(Color.BLACK);         //case 3. Set z's parent to BLACK.
                  z.parent().parent().setColor(Color.RED);  //case 3. Set z's grandparent to RED.
                  leftRotate(z.parent().parent());    //case 3. LEFT-ROTATE "pivots" around z's grandparent.
            } else {
               //!!The rotation in case 2 transfers to case 3 immediately as z becomes the right child.
               z.parent().setColor(Color.BLACK);         //case 3. Set z's parent to BLACK.
               z.parent().parent().setColor(Color.RED);  //case 3. Set z's grandparent to RED.
               leftRotate(z.parent().parent());    //case 3. LEFT-ROTATE "pivots" around z's grandparent.
            }
         }
      }
      
      root.setColor(Color.BLACK);
   }

   public void transplantRB(BinNode<E> u, BinNode<E> v) {
      if (u.parent() == nil) {
         root = v;
      } else if (u == u.parent().left()) {
         u.parent().setLeft(v);
      } else {
         u.parent().setRight(v);
      }

      /* the assignment to v.p occurs unconditionally. This is different from the counterpart in a normal BST */
      v.setParent(u.parent());
   }

   /* 
   *  
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   */
   public void deleteRB(BinNode<E> z) {
      BinNode<E> y = z;
      Color y_original_color = y.color();
      if (z.left() == nil) {
         x = z.right();
         transplantRB(z, z.right());
      } else if (z.right() == nil) {
         x = z.left();
         transplantRB(z, z.left());
      } else {
         y = minimum(z.right());
         y_original_color = y.color();
         x = y.right();
         if (y.parent() == z) {
            if x.setParent(y);
         } else {
            transplantRB(y, y.right());
            y.setRight();
            y.right.setParent(y);
            
            if (y.parent() == z) {
               x.setParent(y);
            }
         }
      }
      
      
   }

   public static void main(String[] args) {
      //Create a bunch of nodes
      BinNode<Integer> node15 = new BinNode<Integer>(15, 0);
      BinNode<Integer> node14 = new BinNode<Integer>(14, 0);
      BinNode<Integer> node13 = new BinNode<Integer>(13, 0);
      BinNode<Integer> node12 = new BinNode<Integer>(12, 0);
      BinNode<Integer> node11 = new BinNode<Integer>(11, 0);
      BinNode<Integer> node10 = new BinNode<Integer>(10, 0);
      BinNode<Integer> node9 = new BinNode<Integer>(9, 0);
      BinNode<Integer> node8 = new BinNode<Integer>(8, 0);
      BinNode<Integer> node7 = new BinNode<Integer>(7, 0);
      BinNode<Integer> node6 = new BinNode<Integer>(6, 0);
      BinNode<Integer> node5 = new BinNode<Integer>(5, 0);
      BinNode<Integer> node4 = new BinNode<Integer>(4, 0);
      BinNode<Integer> node3 = new BinNode<Integer>(3, 0);
      BinNode<Integer> node2 = new BinNode<Integer>(2, 0);
      BinNode<Integer> node1 = new BinNode<Integer>(1, 0);

      /* Create a new RedBlack Tree */
      RedBlackTree<Integer> rbt = new RedBlackTree<Integer>(node11);
      
      /* Test the insertRB method */
      rbt.insertRB(node2);
      rbt.insertRB(node14);
      rbt.insertRB(node1);
      rbt.insertRB(node7);
      rbt.insertRB(node15);
      rbt.insertRB(node5);
      rbt.insertRB(node8);
      rbt.insertRB(node4);
      rbt.preorder(rbt.root());

      //13.3-2 Show the red-black trees that result after successfully inserting the keys 41, 38, 31, 12, 19, 8 into an initially rempty red-black tree.
      BinNode<Integer> node_1 = new BinNode<Integer>(41, 0);
      BinNode<Integer> node_2 = new BinNode<Integer>();
      BinNode<Integer> node_3 = new BinNode<Integer>();
      BinNode<Integer> node_4 = new BinNode<Integer>();
      BinNode<Integer> node_5;
      BinNode<Integer> node_6;

      RedBlackTree<Integer> rbt2 = new RedBlackTree<Integer>(node_1);
   }
}
