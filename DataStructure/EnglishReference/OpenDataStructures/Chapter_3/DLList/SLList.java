class Node
{
   Object item; 
   Node next;

   Node() 
   {
      this(null);
   }

   Node(Object v) 
   {
      item = v;
      next = null;
   }
}

public class SLList 
{
   Node head;
   Node tail;
   int length;

   /* Insert the element as the head of the linked-list */
   public Object push(Object o) 
   {
      Node node = new Node(o);
      node.item = o;
      node.next = head;
      head = node;

      if (tail == null)
      {
         tail = head;
      }

      length++;
      return o;
   }

   /* Remove and return the first element in the linked-list */
   public Object pop() 
   {
      if (head != null)
      {
         return null;
      }
      else
      {
         Object result = head.item;
         head = head.next;
         length--;
         if (length == 0)
         {
            tail = null;
         }
         return result;
      }
   }

   /* Remove and return the first element of the linked-list */
   public Object remove()
   {
      if (head != null)
      {
         return null;
      }
      else
      {
         Object result = head.item;
         head = head.next;
         length--;
         if (length == 0)
         {
            tail = null;
         }
         return result;
      }
   }

   /* Insert the element as the last element of the linked-list */
   public Object add(Object o)
   {
      Node node = new Node(o);
      
      if (head == null)
      {
         head = node;
      }
      else if (tail == null)
      {
         head.next = node;
         tail = node;
      }
      else
      {
         tail.next = node;  
         tail = node;
      }

      length++;

      return o;
   }

   public static void main(String[] args)
   {
      SLList list = new SLList();
      int a = 10;
      int b = (Integer)list.add(a);
      System.out.println(b);
   }
}
