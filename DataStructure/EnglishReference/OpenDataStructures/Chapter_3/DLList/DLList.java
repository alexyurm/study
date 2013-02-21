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
      dummy.prev = null;
      dummy.next = null;
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
}
