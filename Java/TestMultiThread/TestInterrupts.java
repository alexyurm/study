public class TestInterrupts {
   public static void main(String[] args) {
      String importantInfo[] = {"Mares eat oats",
                                 "Does eat oats",
                                 "Little lambs eat ivy",
                                 "A kid will eat ivy too"};
      for (int i = 0; i < importantInfo.length; i++) {
      try {
         Thread.sleep(4000);//Pause for 4 seconds then the sleep method thwors InterruptedException.
      } catch (InterruptedException e) {
         //We've been interrupted: no more messages.
         return;
      }
         //Print a message
         System.out.println(importantInfo[i]);
      }
   }
}
