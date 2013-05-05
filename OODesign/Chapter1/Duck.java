interface FlyBehavior {
   public void fly();
}

class FlyWithWings implements FlyBehavior {
   public void fly() {
      System.out.println("I am flying...");
   }
}

class FlyNoWay implements FlyBehavior {
   public void fly() {
      System.out.println("I cannot fly.");
   }
}

interface QuackBehavior{
   public void quack();
}

class Quack implements QuackBehavior{
   public void quack() {
      System.out.println("Quack");
   }
}

class MuteQuack implements QuackBehavior{
   public void quack() {
      System.out.println("<< Silence >>");
   }
}

class Squeak implements QuackBehavior{
   public void quack() {
      System.out.println("Squeak");
   }
}


abstract class Duck {
   public void swim() {
      System.out.println("Every duck can swim. Even decoy!");
   }
   
   public abstract void display();

   void PerformQuack() {
      quackBehavior.quack();     
   }
   
   void PerformFly() {
      flyBehavior.fly();
   }

   void setFlyBehavior(FlyBehavior fb) {
      flyBehavior = fb;
   }
   
   void setQuackBehavior(QuackBehavior qb) {
      quackBehavior = qb;
   }

   private FlyBehavior flyBehavior;
   private QuackBehavior quackBehavior;
}

class MallardDuck extends Duck {
   public void display() {
      System.out.println("I am a MallarDuck!");
   }
}

class RedHeadDuck extends Duck {
   public void display() {
      System.out.println("I am a RedHeadDuck!");
   }
}


class RubberDuck extends Duck {
   public void display() {
      System.out.println("I am a RubberDuck!");
   }
}

class DecoyDuck extends Duck {
   public void display() {
      System.out.println("I am a DecoyDuck!");
   }
}
