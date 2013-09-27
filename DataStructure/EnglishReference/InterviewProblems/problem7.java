import java.util.*;
import java.lang.*;

class Coordinate {
   public int x;
   public int y;

   Coordinate() {
      x = 0;
      y = 0;
   }

   Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   public void print() {
      System.out.println("(" + x + "," + y + ")");
   }

   public boolean moveAndPrint(int direction) {
   // d is the direction:
   //
   // 0: down -> (x,y--)
   // 1: right -> (x++, y)
   // 2: up -> (x, y++)
   // 3: left -> (x--, y)
   
      switch (direction) {
         case 0: 
            y = y - 1;
            print();
            //System.out.println("down");
            return true;

         case 1: 
            x = x + 1;
            print();
            //System.out.println("right");
            return true;

         case 2: 
            y = y + 1;
            print();
            //System.out.println("up");
            return true;

         case 3: 
            x = x - 1;
            print();
            //System.out.println("left");
            return true;
      
         default:
            System.out.println("The direction must be an integer in [0,3]!");
            return false;
      }
   }
   
   public void printRoute(int n) {
      if (n <= 0) {
         System.out.println("The parameter n must be larger than 0 ");
         return;
      }

      //n = 1 + 3 + 5 + ... + An, so we first calculate x
      int An = n * 2 - 1;
      //the direction
      int d = 0;
      //three tracking pointers
      int i = n - 1;
      int j;
      int k;
      
      print(); //Print the first one at the very first beginning.
      while(i > 0) {
         j = An - 1;
         k = i;
         
         while (j > 0) {
            moveAndPrint(d % 4);
            k--;
            if (k == 0) {
               d++; //Change the direction here
               k = i; //refresh the k again
            }
            j--;
         }

         moveAndPrint(d % 4); //move to the next starting point and print

         i = i - 1;
         An = An - 2;
      }
   }

}

class problem7 {
   
   public static void main(String[] args) {
      
      Coordinate c = new Coordinate();
      c.printRoute(5);

      return;
   }
}
