class HelloThread extends Thread {
   public void run() {
      System.out.println("Hello from a thread!");
   }

   public static void main(String[] args) {
      (new HelloThread()).start();//start a new thread on a Thread subclass object
   }
}
