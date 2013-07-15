/*
*
*  This program shows the another way to implement concurrency in Java: 
*
*  class yourClass implements Runnable {
*     public void run(){...}
*  }
*
*  How does it work? 
*
*  1) Runnable has a method called run();
*  2) the Thread constructor takes a runnable object;
*  3) the Thread class has a method called start() which kicks off the run() method.
*/

class HelloRunnable implements Runnable {
   public void run() {
      System.out.println("Hello from a thread!");
   }

   public static void main(String[] args) {
      (new Thread(new HelloRunnable())).start();//start a new thread using a Runnable Object by calling start();
   }
}
