import java.util.ArrayList;

public class Egg
{
   public static void main(String[] args)
   {
      //Make an Egg ArrayList
      ArrayList<Egg> myList = new ArrayList<Egg>();
      //put something in it
      Egg s = new Egg();
      myList.add(s);

      //put another thing in it
      Egg b = new Egg();
      myList.add(b);

      //find out how many things are in it
      int theSize = myList.size();
      System.out.println("the size of the array list is " + theSize);
      
      //find out if it contains something
     boolean isIn = myList.contains(s);
     System.out.println("Does the array list contain s? " + isIn);
     
     //find if it is empty
     boolean empty = myList.isEmpty();
     System.out.println("Is the array list empty? " + empty);
     
     //Remove something from it
     myList.remove(s);
   }
}
