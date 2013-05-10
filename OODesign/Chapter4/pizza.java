import java.util.*;
import java.lang.*;

interface PizzaIngredientFactory {
   public Dough createDough();
   public Dough createSauce();
   public Dough createCheese();
   public Veggies[] createVeggies();
   public Clams createClam();
}

abstract class Pizza {
   protected String name;
   protected String douge;
   protected String sauce;
   protected ArrayList<String> toppings = new ArrayList<String>();

   protected void prepare() {
      System.out.println("Preparing " + name);
      System.out.println("Tossing dough...");
      System.out.println("Adding source...");
      System.out.println("Adding toppings:");
      for (int i = 0; i < toppings.size(); i++) {
         System.out.println("   " + toppings.get(i));
      }
   }

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
}

class NYStyleCheesePizza extends Pizza {
   public NYStyleCheesePizza() {
      name = "NY Style Sauce and Cheese Pizza";
      douge = "Thin Crust Dough";
      sauce = "Marinara Sauce";
      
      toppings.add("Grated Reggiano Cheese");
   }
}

class ChicagoStyleCheesePizza extends Pizza {
   public ChicagoStyleCheesePizza() {
      name = "Chicago Style Deep Dish Cheese Pizza";
      douge = "Extra Thick Crust Dough";
      sauce = "Plum Tomato Sauce";
      toppings.add("Crated Reggiano Cheese");
   }

   protected void cut() {
      System.out.println("Cutting the pizza into square slices");
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
   
      if (type.equals("Cheese")) {
         pizza = new NYStyleCheesePizza();
      } else if (type.equals("Pepperoni")) {
         pizza = new NYStylePepperoniPizza();
      } else if (type.equals("Clam")) {
         pizza = new NYStyleClamPizza();
      } else if (type.equals("veggie")) {
         pizza = new NYStyleVeggiePizza();
      }

      return pizza;

   }
}

class ChicagoPizzaStore extends PizzaStore {
   public Pizza createPizza(String type) {
      
      Pizza pizza = null;
   
      if (type.equals("Cheese")) {
         pizza = new ChicagoStyleCheesePizza();
      } else if (type.equals("Pepperoni")) {
         pizza = new ChicagoStylePepperoniPizza();
      } else if (type.equals("Clam")) {
         pizza = new ChicagoStyleClamPizza();
      } else if (type.equals("veggie")) {
         pizza = new ChicagoStyleVeggiePizza();
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

      pizza = chicagoStore.orderPizza("Cheese");
      System.out.println("Joel ordered a " + pizza.getName() + "\n");
   }
}
