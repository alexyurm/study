interface FlyBehavior {
   public:
   void fly();
}

class FlyWithWings implements FlyBehavior {
   public:
   void fly() {
      System.out.println("I am flying...");
   }
}

class FlyNoWay implements FlyBehavior {
   public:
   void fly() {
      System.out.println("I cannot fly.");
   }
}

interface QuackBehavior{
   public:
   void quack();
}

class Quack implements QuackBehavior{
   public:
   void quack() {
      System.out.println("Quack");
   }
}

class MuteQuack implements QuackBehavior{
   public:
   void quack() {
      System.out.println("<< Silence >>");
   }
}

class Squeak implements QuackBehavior{
   public:
   void quack() {
      System.out.println("Squeak");
   }
}


class Duck {
   public:
   FlyBehavior flyBehavior;
   QuackBehavior quackBehavior;

   void swim();
   void display();
   void PerformQuack();
   void PerformFly();
   void setFlyBehavior();
   void setQuackBehavior();
}

class MallardDuck extends Duck {
   void display {
      System.out.println("I am a MallarDuck!");
   }
}

class RedHeadDuck extends Duck {
   void display {
      System.out.println("I am a RedHeadDuck!");
   }
}


class RubberDuck extends Duck {
   void display {
      System.out.println("I am a RubberDuck!");
   }
}

class DecoyDuck extends Duck {
   void display {
      System.out.println("I am a DecoyDuck!");
   }
}
