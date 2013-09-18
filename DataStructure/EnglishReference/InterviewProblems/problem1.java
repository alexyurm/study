import java.util.*;

/*
*
*  Find the Lowest Common Ancestor LCA(x,y) in a binary tree.
*
*  Theorem 1: Elements between two given elements in inorder traversal
*  contains some ancestors of each of the two elements but will only have
*  one common ancestor of the two elements. That common ancestor is the 
*  lowest common ancestor of the two nodes, you can verify/prove it.
*
*  Prove: elements in between does not have any ancestor higher than lowest
*  common ancestor becasue inorder covers left subtree then root then right
*  subtree then only it goes to higher ancestors
*
*  Solution1: inorder + preorder
*
*  Step 1: Extract and save the array [x...L...y] from the inorder array;
*  Step 2: Find the first appearance of [x...L...y] in the preorder array.
*  
*  or Step 2: Find the last appearance of [x...L...y] in the postorder array.
*  
*  The complexity of this algorithm is O(n x n), which is not good...(!!)
*  
*  It is better to find a more efficient algorithm.  
*  
*  Solution2:  
*  
*  Step1: Get all x's ancestors and hash their key values into a hash table. (O(log(n)))
*  
*  Step2: go through y's all ancestors one by one and see if any appears in the hash table(O(log(n))).
*  
*  The total complexity is 2(O(log(n))) in this case.
*  
*  
*  
*  
*  
*  
*  
*  
*/


class Node<E> {
   public Node<E> left;
   public Node<E> right;
   public Node<E> parent;
   public E value;
   public int key;

   public Node() {
      left = null;
      right = null;
      parent = null;
   }

   public Node(int key) {
      this.key = key;
   }
}

class BT<E> {
   public Node<E> root;
   
   public BT() {
      root = null;
   }

   public BT(Node<E> root) {
      this.root = root;
   }

   public void preorder(Node<E> rt, ArrayList<Node<E>> array) {
      if (rt == null) return;

      //System.out.println(rt.key);
      array.add(rt);
      
      if (rt.left != null) preorder(rt.left, array);
      if (rt.right != null) preorder(rt.right, array);
   }

   public void inorder(Node<E> rt, ArrayList<Node<E>> array) {
      if (rt == null) return;

      if (rt.left != null) inorder(rt.left, array);
      //System.out.println(rt.key);
      array.add(rt);
      if (rt.right != null) inorder(rt.right, array);
   }

   public void postorder(Node<E> rt, ArrayList<Node<E>> array) {
      if (rt == null) return;

      if (rt.left != null) postorder(rt.left, array);
      if (rt.right != null) postorder(rt.right, array);
      //System.out.println(rt.key);
      array.add(rt);
   }

   public Node<E> LCA(Node<E> x, Node<E> y) {
      ArrayList<Node<E>> inarray = new ArrayList<Node<E>>();
      ArrayList<Node<E>> prearray = new ArrayList<Node<E>>();

      inorder(root, inarray);
      preorder(root, prearray);

      ArrayList<Node<E>> candidates = new ArrayList<Node<E>>();
      
      //Step1: Find out the candidates array from the inorder array;
      int indexofx_inarray = inarray.indexOf(x); //n
      int indexofy_inarray = inarray.indexOf(y); //n
      if (indexofx_inarray == -1 || indexofy_inarray == -1) {
         System.out.println("The node(s) are not contained in the tree");
         return null;
      } else {
         if (indexofx_inarray < indexofy_inarray) {
            for (int i = indexofx_inarray; i <= indexofy_inarray; i++ ) {
               candidates.add(inarray.get(i));
            }//n
         } else if (indexofx_inarray > indexofy_inarray) {
            for (int i = indexofy_inarray; i <= indexofx_inarray; i++ ) {
               candidates.add(inarray.get(i));
            }
         } else {
            return x;
         }
      }

      //Step 2: Find the first appearence of any candidate in the preorder array
      int indexofx_prearray = prearray.indexOf(x); //n
      int indexofy_prearray = prearray.indexOf(y); //n

      int smaller = (indexofx_prearray < indexofy_prearray)? indexofx_prearray : indexofy_prearray;

      for (int i = 0; i <= smaller; i++) {
         if (candidates.contains(prearray.get(i)) == true) {
            return prearray.get(i);
         }
      }

      return null;
   }

   public Node<E> LCA2(Node<E> x, Node<E> y) {
      //Deal with some special cases
      if ( x == null || y == null) {
         return null;
      } else if (x == y) {
         return x;
      }

      //Create a hash map
      Map m = new HashMap<Integer, Node<E>>();
      
      //Go through all the ancestors of x(include x itself) and hash them into the hash map
      while(x != null) {
         m.put(x.key, x);
         x = x.parent;
      }

      //go through y's all ancestors(include y itself) one by one and see if any appears in the hash table(O(log(n))).
      while(y != null) {
         if (m.containsValue(y) == true) {
            return y;
         }
         y = y.parent;
      }

      return null;
      
   }
}

class problem1 {
   public static void main(String args[]) {

      //Create some nodes
      Node<Integer> node40 = new Node<Integer>(40);
      Node<Integer> node20 = new Node<Integer>(20);
      Node<Integer> node80 = new Node<Integer>(80);
      Node<Integer> node10 = new Node<Integer>(10);
      Node<Integer> node30 = new Node<Integer>(30);
      Node<Integer> node60 = new Node<Integer>(60);
      Node<Integer> node100 = new Node<Integer>(100);
      Node<Integer> node5 = new Node<Integer>(5);
      Node<Integer> node15 = new Node<Integer>(15);
      Node<Integer> node25 = new Node<Integer>(25);
      Node<Integer> node35 = new Node<Integer>(35);
      Node<Integer> node1 = new Node<Integer>(1);

      //Create a BT
      BT<Integer> bt = new BT<Integer>(node40);
      
      //setup the connections
      node40.left = node20;
      node40.right = node80;
      node20.parent = node40;
      node80.parent = node40;

      node20.left = node10;
      node20.right = node30;
      node10.parent = node20;
      node30.parent = node20;
      
      node80.left = node60;
      node80.right = node100;
      node60.parent = node80;
      node100.parent = node80;

      node10.left = node5;
      node10.right = node15;
      node5.parent = node10;
      node15.parent = node10;

      node30.left = node25;
      node30.right = node35;
      node25.parent = node30;
      node35.parent = node30;

      node5.left = node1;
      node1.parent = node5;

      //Call the LCA(x,y)
      Node<Integer> result = bt.LCA2(node25, node35);

      if (result != null) {
        System.out.println(result.key);
      }
   }
}
