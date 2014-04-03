/*
*   This example shows a factory method that handles object creation and encapsulates it in a subclass.
*
*   Ths definition of Factory method: The Factory Method Pattern defines an interface for creating an object, but lets subclass
*   decide which class to instantiate. Factory method lets a class defer instantiation to subclass.
*
*   There are three classes in this example to demonstrate the usage of Factory method.
*
*   1.  Pizza(abstract) <--- CheesePizza(concrete), PepperoniPizza(concrete), ClamPizza(concrete) and VeggiePizza(concrete)
*
*   2.  SimplePizzaFactory includes: an createPizza(int type) method which returns an concrete Pizza object. This is the key in Factory method.
*       It decouples the client code in the super class Pizza from the object creation code in the subclass(e.g. CheesePizza). It handles
*       object creation and encapsulates it in a subclass.
*
*   3.  PizzaStore has an SimplePizzaFactory object(called factory) and an orderPizza(int type). In orderPizza(type), based on the actual type, 
*       it calls factory.createPizza(type) to create the concrete pizza then call:
*
*       pizza.prepare()
*       pizza.bake()
*       pizza.cut()
*       pizza.box()
*       ...
*       
*   4.  PizzaStore calls ----> 1) SimplePizzaFactory.createPizza(type) to create the concrete pizza object based on the value of type; then
*                        -----> 2) the returned pizza object: pizza.prepare(), pizza.bake(), pizza.cut() and pizza.box()
*/

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
