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

   public DList() {
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
   }

   public Object get(int i) {
      return getNode(i).item;
   }
   
   public Object set(int i, Object o)
}
