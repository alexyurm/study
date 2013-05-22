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

   //Question3: Write a function that determines whether two integers are equal without using any comparison operators.
   //hint: use bitwise operator
   
   public static int equalTwoInts(int i, int j) {
      return i ^ j;
   }

   public static void testEqualTwoInts() {
      int a = 10;
      int b = 0;
   
      //if (equalTwoInts(a,b)) System.out.println("a and b are the same integer");
      //else System.out.println("a and b are different integers");
      
      if (equalTwoInts(a,b) == 0) System.out.println("a and b are the same integer"); //do I break the "without using any comparison operators" restriction here??
      else System.out.println("a and b are different integers");
   }

   //Question 4: Given a singly linked list, devise a time- and space-efficient algorithm
   //to find the mth-to-last element of the list. Implement your algorithm, taking care
   //to handle relevant error conditions. Define mth to last such that when m = 0 the
   //last element of the list is returned.
   //
   //Note: since this problem is being resolved in Java with the utility LinkedList, we assume
   //"previous" pointer is not allowed to use in this scenario. This problem should be resolved
   //under C/C++!!

   public static Integer getMthToLast(LinkedList<Integer>list, int m) {
      if ( m < 0 ) {
         return null;
      }

      int size = 0; //the size of the linkedlist
      //int n = -1; //the index of (size-1-m)th-to-first element of the list
      Integer result = null; // the mth-to-last element of the list.
      
      Integer header = list.getFirst(); //get the first element;

      ListIterator<Integer> lit = list.listIterator();
      ListIterator<Integer> lit2 = list.listIterator();

      while(lit.hasNext()) {
         size++;
         if ( size-m-1 >= 0 ) {
            result = lit2.next();   
         } 
         
         lit.next();
      }
      
      return result;
   }

   public static void testGetMthToLast() {
      //Create a Integer LinkedList
      LinkedList<Integer> list = new LinkedList<Integer>();
      //Add 5 elements into the linked list.
      for (int i = 0; i < 5; i++) {
         list.add(i);
      }

      //Call the getMthToLastMethod
      Integer result = getMthToLast(list, 3);

      //output the result
      if (result != null) {
         System.out.println("The element is found in the linkedlist:  " + result);
      } else {
         System.out.println("There is no such element." );
      }
   }
   

   public static void main(String[] args) {
      
      //testFindMid();
      //testEqualTwoInt
      //testGetMthToLast();
      return;
   }
}
