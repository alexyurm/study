class HelloRunnable implements Runnable {
   public void run() {
      System.out.println("Hello from a thread!");
   }

   public static void main(String[] args) {
      (new Thread(new HelloRunnable())).start();//start a new thread using a Runnable Object by calling start();
   }
}
