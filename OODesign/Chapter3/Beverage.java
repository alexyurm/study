abstract class Beverage {
   String description = "Unknown Beverage";
   
   public String getDescription() {
      return description;
   }

   public abstract double cost();
}

abstract class CondimentDecorator extends Beverage {
   public abstract String getDescription();
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
   Beverage beverage;

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
   Beverage beverage;

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
   Beverage beverage;

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
   Beverage beverage;

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
      Beverage beverage = new Espresso();
      System.out.println(beverage.getDescription() + " $" + beverage.cost());

      Beverage beverage2 = new DarkRoast();
      beverage2 = new Mocha(beverage2); //Wrap it with a Moca
      beverage2 = new Mocha(beverage2); //Wrap it with a second Moca
      beverage2 = new Whip(beverage2); //Wrap it with a Whip
      System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

      Beverage beverage3 = new HouseBlend();
      beverage3 = new Soy(beverage3); //Wrap it with a Moca
      beverage3 = new Mocha(beverage3); //Wrap it with a second Moca
      beverage3 = new Whip(beverage3); //Wrap it with a Whip
      System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
   }
}
