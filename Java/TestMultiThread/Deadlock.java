public class Deadlock {
      static class Friend {
      private final String name; //Once the value is assigned, it cannot be changed.
      public Friend(String name) {
         this.name = name;
      }
      public String getName() {
         return this.name;
      }
      public synchronized void bow(Friend bower) {
         System.out.format("%s: %s" 
            + " has bowed to me!%n",
            this.name, bower.getName());
         bower.bowBack(this);//bower is very likely being locked here after the second thread has been created.
      }
      public synchronized void bowBack(Friend bower) {
         System.out.format("%s: %s"
            + " has bowed back to me!%n",
            this.name, bower.getName());
      }
   }

   //When Deadlock runs, it's extremely likely that both threads will block when they attempy to invoke bowBack. 
   //Neither block will ever end, because each thread is waiting for the other to exit bow.
   
   public static void main(String[] args) {
      final Friend alphonse = new Friend("Alphonse");
      final Friend gaston = new Friend("Gaston");

      new Thread(new Runnable() {
         public void run() {alphonse.bow(gaston); }
      }).start();

      new Thread(new Runnable() {
         public void run() {gaston.bow(alphonse);}
      }).start();
   }
}
