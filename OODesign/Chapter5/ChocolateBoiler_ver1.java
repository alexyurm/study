class ChocolateBoiler {
   private boolean empty;
   private boolean boiled;

   private static ChocolateBoiler uniqueCBInstance;

   private ChocolateBoiler() { //Only ChocolateBoiler can instantiate a ChocolateBoiler object
      empty = true;
      boiled = false;
   }

   public static synchronized ChocolateBoiler getInstance() { //the keyword "synchronized" ensure thread safe but overheads may seriously damage the efficiency if too many objects are
                                                               //calling getInstance().
      if ( uniqueCBInstance == null ) {
         uniqueCBInstance = new ChocolateBoiler();
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
