/* 
*
*  This program is to test one way to impelment currency in Java: 
*
*  class yourClass extends Thread {
*     public void run() {...}
*  }
*
*  Why we can use this method? because the Class Thread has a method called start() to kick off the run().
*
*
*
*/

class HelloThread extends Thread {
   public void run() {
      System.out.println("Hello from a thread!");
   }

   public static void main(String[] args) {
      (new HelloThread()).start();//start a new thread on a Thread subclass object
   }
}
