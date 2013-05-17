class ChocolateBoiler {
   private boolean empty;
   private boolean boiled;

   public ChocolateBoiler() {
      empty = true;
      boiled = false;
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
