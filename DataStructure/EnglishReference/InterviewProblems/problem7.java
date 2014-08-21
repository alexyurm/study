/*

Problem 7:

   Print Sprial matrix. For example:

   (0,0)    (0,1)    (0,2)
   (0,-1)   (1,-1)   (2,-1)
   (0,-2)   (1,-2)   (2,-2)

   print out: (0,0) (0,-1) (0,-2) (1,-2) (2,-2) (2,-1) (0,2) (0,1) (1,-1)

   solution:

   PRINT-ROUTE(n)
   1    An = n + 2 - 1 
   2    i = n - 1
   3    d = 0

   Note: 

   An: n.^2 = 1 + 3 + 5...+An. n.^2 is the total # of cordinates to iterature through.
       We compute An because it is related to j that we will talk about later on.
   i:  the length of each direction. i-=1 when each item in the polynomial (1+3+5+...+An) is finished.
       E.g. when An = 5.

   k:  the count of i

   j:  the count of An-1. Why we use An-1 instead of An? Because we have "jump" steps. E.g. at the very first iteration
       we have first "jump", which is the first node; also, when j becomes zero, we have an extra "jump" step.

   d:  the direction

       0%4 == 0 -> down
       0%4 == 1 -> right
       0%4 == 2 -> up
       0%4 == 3 -> left

*/

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
   
   /*
   
   

   */

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
      c.printRoute(3);

      return;
   }
}
