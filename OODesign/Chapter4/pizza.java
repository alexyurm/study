/*
*   This example shows an advanced Facotry method called "Abstract Factory Method": 
*
*   The Abstract Factory Pattern: it provides an interface for creating families of related dependent objects without specifying their concrete
*   classes. We've certainly seen that Abstract Factory allows a client to use an abstract interface to create a set of related products without
*   knowing (or caring) about the concrete products that are actually produced.
*
*   Difference between Factory Method vs Abstract Factory Method:
*
*   1)  The Factory Method is a concrete class and it can only create an object;  
*       The Abstract Method is an abstract interface which can generate a couple objects by using its concrete factory subclasses.
*   2)  The Abstract Factory Method: the Actual Pizza concrete class composites the concrete factory class.
*       The Factory Method: its Pizza concrete class doesn't composites the concrete factory class.
*   3)  The Factory Method: There is only one PizzaStore.
*       The Abstract Factory Method: There are a couple different Stores and each store corresponds to a corresponding factory. E.g.
*
*       NYPizzaStore <------------> NYPizzaIngredientFactory
*
*       This is why in 2), the actual pizza concrete class composites the concrete factory class, because: it can generate that local style
*       pizza 
*
*   1.  The incredients inheritance map:
*
*   Dough   <--- ThinCrustdough
*   Sauce   <--- MarinaraSauce, PlumTomatoSauce
*   Cheese  <--- ReggianoCheese, Mozzzrella
*   Veggies <--- Garlic, Onion, Mushroom, RedPepper, Spinach, EggPlant, BlackOlives
*   Pepperoni <--- SlicedPepperoni
*   Clams <---  FrozenClams, FreshClams
*
*   2.  Ingredient Factory inheritance map:
*
*   PizzaIngredientFactory(interface) <--- NYPizzaIngredientFactory(concrete class), ChicagoPizzaIngredientFactory(concrete class)
*
*   The PizzaIngredientFactory class has a whole bunch abstract methods to create incredients objects. E.g. createDough, createVeggies.
*   its subclass(e.g. PizzaIngredientFactory) overrides those methods to create different types of actual incredients.
*
*   The PizzaIngredientFactory interface is the so called Abstract Factory pattern so that it can create families of related dependent 
*   objects without specifying their concrete classes.
*
*   3.  Pizza inheritance map:
*
*   Pizza <--- CheesePizza, ClamPizza, PepperoniPizza, VeggiePizza
*
*   1) The Pizza class has the following members:
*
*   Doug
*   Sauce
*   Cheese
*   Veggies[]
*   Pepperoni
*   Clams
*
*   2) The Pizza class has the following methods:
*
*   parepare(), bake(), cut() and box()...
*
*   3) Each of its subclass includes:
*
*   -   a PizzaIngredientFactory object.
*
*   -   with that PizzaIngredientFactory object, overrides the prepare() method to create different combinations of ingredients. E.g.
*
*       CheesePizza: Dough + Source + Cheese
*       ClamPizza: Dough + Source + Cheese + Clam
*
*   4.  The PizzaStore inheritance map:     
*
*   PizzaStore <--- NYPizzaStore, ChicagoPizzaStore
*
*   1) The PizzaStore has two methods: 
*
*   createPizza(type) and orderPizza(type)
*
*   2) Their subtypes overrides the createPizza(type) methods.
*
*/


import java.util.*;
import java.lang.*;

interface PizzaIngredientFactory {
   public Dough createDough();
   public Sauce createSauce();
   public Cheese createCheese();
   public Veggies[] createVeggies();
   public Pepperoni createPepperoni();
   public Clams createClam();
}

abstract class Dough {
}

abstract class Sauce {
}

abstract class Cheese {
}

abstract class Veggies {
}

abstract class Pepperoni {
}

abstract class Clams {
}

class ThinCrustDough extends Dough {
}

class MarinaraSauce extends Sauce {
}

class PlumTomatoSauce extends Sauce {
}

class ReggianoCheese extends Cheese {
}

class Mozzzrella extends Cheese {
}

class Garlic extends Veggies {
}

class Onion extends Veggies {
}

class Mushroom extends Veggies {
}

class RedPepper extends Veggies {
}

class Spinach extends Veggies {
}

class EggPlant extends Veggies {
}

class BlackOlives extends Veggies {
}

class SlicedPepperoni extends Pepperoni {
}

class FrozenClams extends Clams {
}

class FreshClams extends Clams {
}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {

   public Dough createDough() {
      return new ThinCrustDough();
   }
   
   public Sauce createSauce() {
      return new MarinaraSauce();
   }

   public Cheese createCheese() {
      return new ReggianoCheese();
   }

   public Veggies[] createVeggies() {
      Veggies[] veggies = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
      return veggies;
   }

   public Pepperoni createPepperoni() {
      return new SlicedPepperoni();
   }
   
   public Clams createClam() {
      return new FreshClams();
   }
}

class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

   public Dough createDough() {
      return new ThinCrustDough();
   }
   
   public Sauce createSauce() {
      return new PlumTomatoSauce();
   }

   public Cheese createCheese() {
      return new Mozzzrella();
   }

   public Veggies[] createVeggies() {
      Veggies[] veggies = { new Spinach(), new EggPlant(), new BlackOlives() };
      return veggies;
   }

   public Pepperoni createPepperoni() {
      return new SlicedPepperoni();   
   }
   
   public Clams createClam() {
      return new FrozenClams();
   }
}

abstract class Pizza {
   protected String name;
   protected Dough dough;
   protected Cheese cheese;
   protected Sauce sauce;
   protected Veggies[] veggies;
   protected Pepperoni pepperoni;
   protected Clams clam;

   abstract protected void prepare();//We've now made the prepare method abstract. This is where we are going to collect the ingredients needed for the pizza, which of course will
                                     //be come from the ingredient factory.

   protected void bake() {
      System.out.println("Bake for 25 minutes at 350");
   }

   protected void cut() {
      System.out.println("Cutting the pizza into diagnonal slices");
   }

   protected void box() {
      System.out.println("Place pizza in official PizzaStore box");
   }

   protected String getName() {
      return name;
   }

   public String toString() {
      //code to print pizza here
      return null;
   }

   protected void setName(String name) {
      this.name = name;
   }
}

class CheesePizza extends Pizza {
   PizzaIngredientFactory ingredientFactory;
   
   public CheesePizza(PizzaIngredientFactory ingredientFactory) {
      this.ingredientFactory = ingredientFactory;
   }

   //Here's where the magic happen!
   //The prepare() method steps through creating a cheese pizza, and each time it needs an ingredient, it asks the factory to produce it.
   public void prepare() {
      System.out.println("Preparing " + name);
      dough = ingredientFactory.createDough();
      sauce = ingredientFactory.createSauce();
      cheese = ingredientFactory.createCheese(); 
   }
}

class ClamPizza extends Pizza {
   PizzaIngredientFactory ingredientFactory;
   
   public ClamPizza(PizzaIngredientFactory ingredientFactory) {
      this.ingredientFactory = ingredientFactory;
   }

   public void prepare() {
      System.out.println("Preparing " + name);
      dough = ingredientFactory.createDough();
      sauce = ingredientFactory.createSauce();
      cheese = ingredientFactory.createCheese(); 
      clam = ingredientFactory.createClam(); 
   }
}

class PepperoniPizza extends Pizza {
   PizzaIngredientFactory ingredientFactory;
   
   public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
      this.ingredientFactory = ingredientFactory;
   }

   public void prepare() {
      System.out.println("Preparing " + name);
      dough = ingredientFactory.createDough();
      sauce = ingredientFactory.createSauce();
      cheese = ingredientFactory.createCheese(); 
      pepperoni = ingredientFactory.createPepperoni(); 
   }
}

class VeggiePizza extends Pizza {
   PizzaIngredientFactory ingredientFactory;
   
   public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
      this.ingredientFactory = ingredientFactory;
   }

   public void prepare() {
      System.out.println("Preparing " + name);
      dough = ingredientFactory.createDough();
      sauce = ingredientFactory.createSauce();
      cheese = ingredientFactory.createCheese(); 
      veggies = ingredientFactory.createVeggies(); 
   }
}

abstract class PizzaStore {

   //Each subclass overrides the createPizza() method, while all subclasses make use of the orderPizza() method defined in PizzaStore. 
   //We could make the orderPizza() method final if we really wanted to enforce this.
   //Also, orderPizza() is defined in the abstract class PizzaStore, not the subclasses. So the method has no idea which subclass is actually
   //running the code and making the pizzas.
   public final Pizza orderPizza(String type) { 
      Pizza pizza;

      pizza = createPizza(type); //orderPizza() calls createPizza() to actually get a pizza object. But which kind of pizza will it get? The
                                 //orderPizza() method can't decide and it doesn't know how. So who does decide? Well, that is decided by the choice
                                 //of pizza store you order from, NYStylePizzaStore or ChicagoStylePizzaStore.
      
      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();

      return pizza;
   }

   protected abstract Pizza createPizza(String type); //Our "factory method" is now abstract in PizzaStore.
}

class NYPizzaStore extends PizzaStore {
   public Pizza createPizza(String type) {
      
      Pizza pizza = null;
      PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
   
      if (type.equals("Cheese")) {
         pizza = new CheesePizza(ingredientFactory);
         pizza.setName("New York Style Cheese Pizza");
      } else if (type.equals("Pepperoni")) {
         pizza = new PepperoniPizza(ingredientFactory);
         pizza.setName("New York Style Pepperoni Pizza");
      } else if (type.equals("Clam")) {
         pizza = new ClamPizza(ingredientFactory);
         pizza.setName("New York Style Clam Pizza");
      } else if (type.equals("veggie")) {
         pizza = new VeggiePizza(ingredientFactory);
         pizza.setName("New York Style Veggie Pizza");
      }

      return pizza;
   }
}

class ChicagoPizzaStore extends PizzaStore {
   public Pizza createPizza(String type) {
      
      Pizza pizza = null;
      PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
   
      if (type.equals("Cheese")) {
         pizza = new CheesePizza(ingredientFactory);
         pizza.setName("Chicago Style Cheese Pizza");
      } else if (type.equals("Pepperoni")) {
         pizza = new PepperoniPizza(ingredientFactory);
         pizza.setName("Chicago Style Pepperoni Pizza");
      } else if (type.equals("Clam")) {
         pizza = new ClamPizza(ingredientFactory);
         pizza.setName("Chicago Style Clam Pizza");
      } else if (type.equals("veggie")) {
         pizza = new VeggiePizza(ingredientFactory);
         pizza.setName("Chicago Style Veggie Pizza");
      }

      return pizza;
   }
}

class PizzaTestDrive {
   public static void main(String[] args) {
      PizzaStore nyStore = new NYPizzaStore();
      PizzaStore chicagoStore = new ChicagoPizzaStore();

      Pizza pizza = nyStore.orderPizza("Cheese");
      System.out.println("Ethan ordered a " + pizza.getName() + "\n");

      pizza = chicagoStore.orderPizza("Pepperoni");
      System.out.println("Joel ordered a " + pizza.getName() + "\n");
   }
}
