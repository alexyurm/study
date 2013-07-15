import java.util.*;
import java.lang.*;

/*
*
*  This program shows how to use the sleep method in the class Thread:
*
*  1) Thread.sleep(xxx) //xxx is the sleep period in milliseconds.
*
*  2) Before the thread kicks off, we should throw an InterruptedException to make sure the current thread's interrupted status is cleared.
*
*/

public class SleepMessages {
   public static void main(String[] args)
      //Notice that main declares that it throws InterruptedException. 
      //This is an exception that sleep throws when another thread
      //interrupts the current thread while sleep is active.

      //sleep(): if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.
      throws InterruptedException {
      String importantInfo[] = {"Mares eat oats", 
                               "Does eat oats",
                               "Little lambs eat ivy",
                               "A kid will eat ivy too"};

      for (int i = 0;
           i < importantInfo.length;
           i++) {
         //pause for 4 seconds
         Thread.sleep(4000);
         //Print a message
         System.out.println(importantInfo[i]);
      }
   }
}
