interface Duck {
   public void quack();
   public void fly();
}

class MallardDuck implements Duck {
   public void quack() {
      System.out.println("Quack");
   }

   public void fly() {
      System.out.println("I'm flying");
   }
}

interface Turkey {
   public void gobble();
   public void fly();
}

class WildTurkey implements Turkey {
   public void gobble() {
      System.out.println("Gobble gobble");
   }

   public void fly() {
      System.out.println("I'm flying a short distance");
   }
}

//Now, let's say you're short on Duck objects and you'd like to use some
//Turkey objects in their place. Obviously we can't use the turkeys outright
//because they have a different interface.

//First, you need to implement the interface of the type you're adapting to(Turykey is the adaptee here).
//This is the interface your client expects to see.
class TurkeyAdapter implements Duck {
   Turkey turkey;

   //Next, we need to get a reference to the object that we are adapting to;
   //here we do that through the constuctor
   public TurkeyAdapter(Turkey turkey) {
      this.turkey = turkey;
   }

   public void quack() {
      turkey.gobble(); //Now we need to implement all the methods in the interface; the quack() translation between classes is easy; just call the gobble() method.
   }

   public void fly() { //Even though both interfaces have a fly() method, Turkey fly in short spurts.
      for(int i = 0; i < 5; i++) {
         turkey.fly();
      }
   }
}

class DuckTestDrive {
   public static void main(String[] args) {
      MallardDuck duck = new MallardDuck(); //Let's create a Duck...

      WildTurkey turkey = new WildTurkey(); //and a turkey.

      Duck turkeyAdapter = new TurkeyAdapter(turkey); //And then wrap the turkey in a TurkeyAdapter, which makes it look like a Duck.

      System.out.println("The Turkey says...");
      turkey.gobble();
      turkey.fly();
      
      System.out.println("\nThe Duck says...");
      testDuck(duck);

      System.out.println("\nThe TurkeyAdapter says...");
      testDuck(turkeyAdapter);
   }

   static void testDuck(Duck duck) {
      duck.quack();
      duck.fly();
   }
}
