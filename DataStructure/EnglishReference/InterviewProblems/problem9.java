
/*

Problem 9:

   Print Sprial matrix. For example:

   (0,0)    (0,1)    (0,2)
   (0,-1)   (1,-1)   (2,-1)
   (0,-2)   (1,-2)   (2,-2)

   print out: (0,0) (0,-1) (0,-2) (1,-2) (2,-2) (2,-1) (0,2) (0,1) (1,-1)

   solution:

   the solution is similar to problem 7.

*/

import java.util.*;
import java.lang.*;

class Coordinate {
   public int x;
   public int y;
   public int array[][];

   Coordinate() {
      x = 0;
      y = 0;
      array = null;
   }

   Coordinate(int x, int y, int array[][]) {
      this.x = x;
      this.y = y;
      this.array = array;
   }
   
   public void print() {
      //System.out.println("(" + x + "," + y + ")");
      System.out.println(array[x][y]);
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
            x = x + 1;
            print();
            //System.out.println("down");
            return true;

         case 1: 
            y = y + 1;
            print();
            //System.out.println("right");
            return true;

         case 2: 
            x = x - 1;
            print();
            //System.out.println("up");
            return true;

         case 3: 
            y = y - 1;
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

class problem9 {
   
   public static void main(String[] args) {
      
      int array[][] = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
      Coordinate c = new Coordinate(0, 0, array);
      c.printRoute(3);

      return;
   }
}
