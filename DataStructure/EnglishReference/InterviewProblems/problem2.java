import java.util.*;

/*
*  Given a sorted linkedlist, create a binary tree with the array with minimum height. Noted that the question specified only binary tree and not binary search tree.
*
*  The following algorithm takes O(n);
*
*  Step1: Calculate the index of a node x's parent, leftchild and right child in the array.
*  Step2: Traverse through the array and setup each parent, left child and right child.
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

   public BT(ArrayList<Integer> array) {
      int size = array.size();

      ArrayList<Node<E>> nodes = new ArrayList<Node<E>>();
      for (int i = 0; i < size; i++) {
         nodes.add(new Node<E>(array.get(i)));
      }

      root = nodes.get(0); //setup the root

      for (int i = 0; i < size; i++) {
         int parentIndex = parentIndex(i);
         int leftIndex = leftIndex(i);
         int rightIndex = rightIndex(i);

         //Set up the parent
         if (parentIndex >= 0) {
            Node<E> x = nodes.get(i);
            x.parent = nodes.get(parentIndex);
         }

         //Set up the left child    
         if (leftIndex < size) {
            Node<E> x = nodes.get(i);
            x.left = nodes.get(leftIndex);
         }

         //Set up the right child    
         if (rightIndex < size) {
            Node<E> x = nodes.get(i);
            x.right = nodes.get(rightIndex);
         }
      }
   }

   //find the index of the parent of an array element indexed by i
   public int parentIndex(int i) {
      return (i-1)/2;
   }

   //find the index of the left child of an array element indexed by i
   public int leftIndex(int i) {
      return (i*2+1);
   }

   //find the index of the right child of an array element indexed by i
   public int rightIndex(int i) {
      return (i*2+2);
   }

   public void preorder(Node<E> rt, ArrayList<Node<E>> array) {
      if (rt == null) return;

      System.out.println(rt.key);
      array.add(rt);
      
      if (rt.left != null) preorder(rt.left, array);
      if (rt.right != null) preorder(rt.right, array);
   }

   public void inorder(Node<E> rt, ArrayList<Node<E>> array) {
      if (rt == null) return;

      if (rt.left != null) inorder(rt.left, array);
      System.out.println(rt.key);
      array.add(rt);
      if (rt.right != null) inorder(rt.right, array);
   }

   public void postorder(Node<E> rt, ArrayList<Node<E>> array) {
      if (rt == null) return;

      if (rt.left != null) postorder(rt.left, array);
      if (rt.right != null) postorder(rt.right, array);
      System.out.println(rt.key);
      array.add(rt);
   }

   public int max(int x, int y) {
      return (x>y)? x : y;
   }
   
   public int height(Node<E> rt) {
      if (rt == null ) return -1;
      else {
         return max(height(rt.left), height(rt.right)) + 1;
      }
   }
}


class problem2 {
   //find the index of the parent of an array element indexed by i
   public int parentIndex(int i) {
      return (i-1)/2;
   }

   //find the index of the left child of an array element indexed by i
   public int leftIndex(int i) {
      return (i*2+1);
   }

   //find the index of the right child of an array element indexed by i
   public int rightIndex(int i) {
      return (i*2+2);
   }

   public static void main(String args[]) {
      //Create an sorted array
      ArrayList<Integer> array = new ArrayList<Integer>();
      for (int i = 0; i < 20; i++) {
         array.add(i);
      }

      //Create a Binary Tree with the sorted array
      BT<Integer> bt = new BT<Integer>(array);
      
      //System.out.println(bt.height(bt.root));

      //predorder
      ArrayList<Node<Integer>> array2 = new ArrayList<Node<Integer>>();
      
      bt.preorder(bt.root, array2);
      
   }
}

