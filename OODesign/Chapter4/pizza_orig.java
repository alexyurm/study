

abstract class Pizza {
   public abstract void prepare();
   public abstract void bake();
   public abstract void cut();
   public abstract void box();
}

class CheesePizza extends Pizza {
   public void prepare() {
      System.out.println("Preparing the cheese pizza.");
   }

   public void bake() {
      System.out.println("Backing the cheese pizza.");
   }

   public void cut() {
      System.out.println("Backing the cheese pizza.");
   }

   public void box() {
      System.out.println("Wrapping the cheese pizza.");
   }
}

class PepperoniPizza extends Pizza {
   public void prepare() {
      System.out.println("Preparing the pepperoni pizza.");
   }

   public void bake() {
      System.out.println("Backing the pepperoni pizza.");
   }

   public void cut() {
      System.out.println("Backing the pepperoni pizza.");
   }

   public void box() {
      System.out.println("Wrapping the oeooeroni pizza.");
   }
}

class ClamPizza extends Pizza {
   public void prepare() {
      System.out.println("Preparing the clam pizza.");
   }

   public void bake() {
      System.out.println("Backing the clam pizza.");
   }

   public void cut() {
      System.out.println("Backing the clam pizza.");
   }

   public void box() {
      System.out.println("Wrapping the clam pizza.");
   }
}

class VeggiePizza extends Pizza {
   public void prepare() {
      System.out.println("Preparing the veggie pizza.");
   }

   public void bake() {
      System.out.println("Backing the veggie pizza.");
   }

   public void cut() {
      System.out.println("Backing the veggie pizza.");
   }

   public void box() {
      System.out.println("Wrapping the veggie pizza.");
   }
}

class SimplePizzaFactory {
   public static Pizza createPizza(String type) {
      Pizza pizza = null;

      if (type.equals("cheese")) {
         pizza = new CheesePizza();
      } else if (type.equals("Pepperoni")) {
         pizza = new PepperoniPizza();
      } else if (type.equals("Clam")) {
         pizza = new ClamPizza();
      } else if (type.equals("veggie")) {
         pizza = new VeggiePizza();
      }
      
      return pizza;
   }
}

class PizzaStore {
   protected SimplePizzaFactory factory;

   public PizzaStore(SimplePizzaFactory factory) {
      this.factory = factory;
   }

   public Pizza orderPizza(String type) {
      Pizza pizza;
      //Notice that we've replaced the new operator with a create method on the factory object. No more concrete instantiations here!
      pizza = factory.createPizza(type); //And the orderPizza() method was the factory to create its pizzas by simply passing on the type of the order.
      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();
      return pizza;
   }
   //Other methods here
}
