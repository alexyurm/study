import java.util.*;

class Interview {
   //Question1: Find the middle element of linked list in one pass
   public static <T> T findMid(LinkedList<T> list) {

      T middle = null;
      int size = list.size();
      if (size <= 0 ) {
         throw new NoSuchElementException();        
      }

      int i = list.size() / 2;   
      middle = list.get(i);
      return middle;
   }

   public static void testFindMid() {
      //Create a new linkedlist with 100 elements
      LinkedList<Integer> list = new LinkedList<Integer>();
      for (int i = 0; i < 100; i++) {
         list.add(i);
      }

      //Find the middle element
      int middle = findMid(list);

      System.out.println("middle = " + middle);
      System.out.println("the hash code of the middle elements is " + Integer.valueOf(middle).hashCode());

      System.out.println("size = " + list.size());

      return;
   }

   //Question2: How to find linked list has loop
   

   public static void main(String[] args) {
      
      testFindMid();
      return;
   }
}
