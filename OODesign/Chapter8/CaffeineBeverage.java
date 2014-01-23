public abstract class CaffeineBeverage {
   //We don't want our subclasses to be able to override this method
   //and change the recipe!
   final void prepareRecipe() {
      boilWater();
      brew();
      pourInCup();
      addCondiments();
   }

   abstract void brew();

   abstract void addCondiments();

   void boilWater() {
      System.out.println("Boiling water");
   }

   void pourInCup() {
      System.out.println("Pouring into cup");
   }
}
