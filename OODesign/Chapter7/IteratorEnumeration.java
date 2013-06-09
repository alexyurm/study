//Convert an Iterator to an Enumeration. Use ArrayList to test your code
//Note: ArrayList class supports the Iterator interface but doesn't support Enumeration(well, not yet anyway)

import java.util.*;

class IteratorEnumeration implements Enumeration {
   Iterator it;

   public IteratorEnumeration(Iterator it) {
      this.it = it;
   }

   public boolean hasMoreElements() {
     return it.hasNext();
   }

   public Object nextElement() {
     return it.next();
   }
}

class testIteratorEnumeration {
   public static void main(String[] args) {
      Integer[] intArray = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
      ArrayList<Integer> array = new ArrayList<Integer>();
      for (Integer i : intArray) {
         array.add(i);
      }
      
      IteratorEnumeration ie = new IteratorEnumeration(array.iterator());

      while(ie.hasMoreElements()) {
         System.out.println(ie.nextElement());
      }
   }
}
