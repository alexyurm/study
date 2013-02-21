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

class Linklist
{
   int length;
   Node head;

   Linklist(Node head)
   {
      this.head = head;
      length = 1;
   }
   
   public boolean insert(Node o)
   {
      int index = length-1;
      Node target = head;
      int i;

      //Find the target after which is inserted by o
      for (i = 0; i < index; i++)
      {
         target = target.next; 
      }

      o.next = target.next;
      target.next = o;
      length++;

      return true;
   }
   
   public boolean insert(int index, Node o)
   {
      if (index <= length-1)
      {
         int i;
         Node target = head;
            
         //Find the target after which is inserted by o
         for (i = 0; i < index; i++)
         {
            target = target.next;
         }
         
         o.next = target.next;
         target.next = o;
         length++;

         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean remove()
   {
      if (length == 0)
      {
         System.out.println("The list is already empty. Nothing to remove.");
         return false;
      }
      else if (length == 1)
      {
         head = null;
         length--;
         return true;
      }
      else
      {
         int index = length-1;
         int i;
         Node target = head;
         Node targetPrior = null;
         //Find the target which will be removed from the list
         for (i = 0; i < index; i++)
         {
            targetPrior = target;
            target = target.next;
         }

         length--;
         return true;
      }
   }

   public boolean remove(int index)
   {
      if (length == 0)
      {
         System.out.println("The list is already empty. Nothing to remove.");
         return false;
      }
      else if (index <= length-1)
      {
         int i;
         Node target = head;
         Node targetPrior = null;

         if (index == 0)
         {
            head = target.next;
            length--;
            return true;
         }
         else
         {
            //Find the target which will be removed from the list
            for (i = 0; i < index; i++)
            {
               targetPrior = target;
               target = target.next;
            }
            
            targetPrior.next = target.next;

            length--;
            return true;
         }
      }
      else
      {
         System.out.println("The index is not correct. Remove failed!");
         return false;
      }
   }

   public static void main(String[] args)
   {
      //Create 5 new nodes
      Node[] nodes = new Node[5];
      int i;
      for (i = 0; i < 5; i++)
      {
         nodes[i] = new Node(i);
      }

      //Create a new Linklist
      Linklist list = new Linklist(nodes[0]);
      //Insert a new node
      list.insert(nodes[1]);
      //Insert another new node
      list.insert(nodes[2]);
      //remove nodes
      list.remove();
      list.remove();
      list.remove();
      System.out.println("list.length = " + list.length);
   }
} 
