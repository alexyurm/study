class ChocolateBoiler {
   private boolean empty;
   private boolean boiled;

   //static initializer method !!
   private static ChocolateBoiler uniqueCBInstance = new ChocolateBoiler(); //Go ahead and create an instance of Singleton in a static initializer. This can guarantee thread safe
   //because the JVM guarantees that the instance will be created before any thread access the static uniqueInstance variable.

   private ChocolateBoiler() {}

   public static ChocolateBoiler getInstance() {
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
