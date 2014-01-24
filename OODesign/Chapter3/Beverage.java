/*
*  This example shows Decorating Objects and Design Principle: Classes should be open for extension, but closed for modification.
*
*
*  -  Inheritance Map: 
*  
*     1. Bevarage(abstract class) <----- CondimentDecorator(abstract class) <----- Mocha, SteamedMilk, Soy, Whip
*     
*     2. Bevarage(abstract class) <----- Espresso, HouseBlend, DarkRoast, Decaf
*
*     Note: A <----- B: B is inhertated from A
*
*  -  The keypoint is that CondimentDecorator(Mocha, SteamedMilk, Soy and Whip) and Main Beverage(Espresso, HouseBlend, DarkRoast, Decaf)
*     are both inheritated from the same superclass, which is Beverage. 
*
*  -  We can use condiment types as wrappers to wrap a beverage(including condiments):
*     
*     In each condiment type, we have a member type :
*
*     1. Beverage beverage; 
*     
*     and its constructor, for example:
*
*     2.
*     
*     public Mocha(Beverage beverage) {
*        this.beverage = beverage;
*     }
*     
*     With 1 and 2, we can use CondimentDecorator to wrap any beverage objects. A decorator wraps an object to add new behaviors and responsibilities.
*
*    Question: It looks CondimentDecorator overrides the getDescription() method by adding "abstract". Is this legal in Java?
*
*/

abstract class Beverage {
   String description = "Unknown Beverage";
   
   public String getDescription() {
      return description;
   }

   public abstract double cost();
}

abstract class CondimentDecorator extends Beverage {
   public abstract String getDescription(); //!!the getDescription is changed back to "abstract". That means its subclasses must implement this method.
}

class Espresso extends Beverage {
   public Espresso() {
      description = "Espresso";
   }

   public double cost() {
      return 1.99;
   }
}

class HouseBlend extends Beverage {
   public HouseBlend() {
      description = "HouseBlend";
   }

   public double cost() {
      return .89;
   }
}

class DarkRoast extends Beverage {
   public DarkRoast() {
      description = "DarkRoast";
   }

   public double cost() {
      return .99;
   }
}

class Decaf extends Beverage {
   public Decaf() {
      description = "Decaf";
   }

   public double cost() {
      return 1.05;
   }
}

class Mocha extends CondimentDecorator {
   Beverage beverage; //the Beverage object being wrapped(decorated)

   public Mocha(Beverage beverage) {
      this.beverage = beverage;
   }

   public String getDescription() {
      return beverage.getDescription() + ", Mocha";
   }

   public double cost() {
      return .20 + beverage.cost();
   }
}

class SteamedMilk extends CondimentDecorator {
   Beverage beverage; //the Beverage object being wrapped(decorated)

   public SteamedMilk(Beverage beverage) {
      this.beverage = beverage;
   }

   public String getDescription() {
      return beverage.getDescription() + ", Steamed Milk";
   }

   public double cost() {
      return .10 + beverage.cost();
   }
}

class Soy extends CondimentDecorator {
   Beverage beverage; //the Beverage object being wrapped(decorated)

   public Soy(Beverage beverage) {
      this.beverage = beverage;
   }

   public String getDescription() {
      return beverage.getDescription() + ", Soy";
   }

   public double cost() {
      return .15 + beverage.cost();
   }
}


class Whip extends CondimentDecorator {
   Beverage beverage; //the Beverage object being wrapped(decorated)

   public Whip(Beverage beverage) {
      this.beverage = beverage;
   }

   public String getDescription() {
      return beverage.getDescription() + ", Whip";
   }

   public double cost() {
      return .10 + beverage.cost();
   }
}

class StarbuzzCoffee {
   public static void main(String[] args) {
      Beverage beverage = new Espresso(); //Create a main beverage object.
      System.out.println(beverage.getDescription() + " $" + beverage.cost());

      Beverage beverage2 = new DarkRoast(); //Create a main beverage object
      beverage2 = new Mocha(beverage2); //Wrap it with a Moca
      beverage2 = new Mocha(beverage2); //Wrap it with a second Moca
      beverage2 = new Whip(beverage2); //Wrap it with a Whip
      System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

      Beverage beverage3 = new HouseBlend(); //Create a main beverage object
      beverage3 = new Soy(beverage3); //Wrap it with a Moca
      beverage3 = new Mocha(beverage3); //Wrap it with a second Moca
      beverage3 = new Whip(beverage3); //Wrap it with a Whip
      System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
   }
}
