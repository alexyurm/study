/*
* Singleton design pattern demonstration.
*/

class ChocolateBoiler {
   private boolean empty;
   private boolean boiled;

   private volatile static ChocolateBoiler uniqueCBInstance;

   private ChocolateBoiler() { //Only ChocolateBoiler can instantiate a ChocolateBoiler object
      empty = true;
      boiled = false;
   }

   //(double-check locking!!)
   public static ChocolateBoiler getInstance() {
         if (uniqueCBInstance == null) { //check for an instance and if there isn't one enter a synchronized block.
            synchronized(ChocolateBoiler.class) {
            if (uniqueCBInstance == null) { //note we only synchronize the first time through.
               uniqueCBInstance = new ChocolateBoiler(); //Once in the block, 
            }
         }
      }

      return uniqueCBInstance;
   }

   public void fill() {
      if (isEmpty()) {
         empty = false;
         boiled = false;
         //fill the boiler with a milk/chocolate mixture
      }
   }

   public void drain() {
      if (!isEmpty() && isBoiled()) {
         //drain the boiled milk and chocolate
         empty = true;
      }
   }

   public void boil() {
      if (!isEmpty() && !isBoiled()) {
         //drain the boiled milk and chocolate
         boiled = true;
      }
   }

   public boolean isEmpty() {
      return empty;
   }

   public boolean isBoiled() {
      return boiled;
   }
}

class TestDrive {
   public static void main(String[] args) {
      ChocolateBoiler cb = ChocolateBoiler.getInstance();
      
      //ChocolateBoiler cb = new ChocolateBoiler(); //You will get the error: ChocolateBoiler() has private access in ChocolateBoiler...

      System.out.println(cb.isEmpty());
      System.out.println(cb.isBoiled());
   }
}
