import java.util.*;
import java.lang.Integer;

class TestQueue {
   public static void main(String[] args) {
      Queue<String> q = new LinkedList<String>(); //Why we can assign a LinkedList object to a Queue reference? 
   
      q.add("a");
      q.add("b");
      q.add("c");
      q.add("d");

      Iterator it = q.iterator();

      while(it.hasNext()) {
         String iteratorValue = (String)it.next();
         System.out.println("Queue Next Value: " + iteratorValue);
      }

      //get value and does not remove element from queue
      System.out.println("Queue peek:" + q.peek());

      //get first value and remove that object from queue
      System.out.println("Queue poll:" + q.poll());

      System.out.println("Final Size of Queue :" + q.size());

      return;
   }
}
