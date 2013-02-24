/* Double Linked-list */
/* Theorem: A DLList implements the List interface. The get(i), set(i, x), add(i,x) and remove(i) operations run in O(1+min{i, n-i} time per operation) */

class Node {
   Node prev;
   Node next;
   Object item;

   Node() 
   {
      this(null);
   }

   Node(Object v) 
   {
      item = v;
      prev = null;
      next = null;
   }
}

public class DLList {
   Node dummy;
   int length;

   public DLList() {
      dummy = new Node();
      length = 0;
   }

   public Node getNode(int i) {
      Node p = null;

      if (i < length/2) {
         p = dummy.next;
         for (int j = 0; j < i; j++) {
            p = p.next;
         }
      } else {
         p = dummy.prev;
         for (int j = length-1; j > i; j--) {
            p = p.prev;
         }
      }

      return(p);

      //note: the running time of these operations is dominated by the time it takes to find the ith node, and is therefore
      // O(1 + min(i, n-i))
   }

   public Object get(int i) {
      return getNode(i).item;
   }
   
   public Object set(int i, Object o) {
      Node node = getNode(i);
      if (node != null) {
         Object orig = node.item;
         node.item = o;
         /* return the original value */
         return orig;
      }
      else {
         return null;
      }
   }

   /* Insert a node(inserted) before another node(target) */
   public Node addBefore(Node target, Object o) {
      Node inserted = new Node(o);
         inserted.item = o;
         inserted.prev = target.prev;
         inserted.next = target;

         /* Don't forget to set these up */
         inserted.next.prev = inserted;
         inserted.prev.next = inserted;

         length++;
         return inserted;

         //Note: Thanks to the dummy node, there is no need to worry about target.prev or target.next not existing.
   }

   /* Add a node before the ith node */
   public boolean add(int i, Object o) {
      //addBefore(getNode(i), o);
      Node target = getNode(i);
      if (target != null) {
         addBefore(target, o);
         return true;
      }
      else {
         System.out.println("The ith node doesn't exist in the double linked-list!");
         return false;
      }
   }

   /* remove a node */
   public void remove(Node target) {
      target.next.prev = target.prev;
      target.prev.next = target.next;
      length--;
   }

   /* remove the ith node */
   public Object remove(int i) {
      Node target = getNode(i);
      if (target != null) {
         remove(target);
         return target.item;
      }
      else {
         System.out.println("The ith node doesn't exist in the double linked-list!");
         return null;   
      }
   }

   /*
   public static void main(String[] args) {
      //Create a new DDList
      DLList ddlist = new DLList();

      // Create an int array with 5 elements
      int[] a = new int[5];
      for (int i = 0; i < 5; i++) {
         a[i] = i;
      }

      // Create 5 new nodes
      Node[] nodes = new Node[5];
      for (int i = 0; i < 5; i++)
      {
         nodes[i] = new Node(i);
      }

      //Insert the first three elements
      for (int i = 0; i < 3; i++) {
         ddlist.add(i, i);
      }
   }*/
}
