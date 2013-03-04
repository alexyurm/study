import java.util.*;

public class TestGenerics1 {
   public static void main(String[] args) {
      new TestGenerics1().go();
   }

   public void go() {
      Animal animals[] = {new Dog(), new Cat(), new Dog()};
      Dog[] dogs = {new Dog(), new Dog(), new Dog()};
      takeAnimals(animals);
      takeAnimals(dogs);
      takeAnimals2(dogs);
   }

   public void takeAnimals(Animal[] animals) {
      for (Animal a: animals) {
         a.eat();
      }
   }

   public void takeAnimals2(Animal[] animals) {
      animals[0] = new Cat();
   }
}

abstract class Animal {
   void eat() {
      System.out.println("animal eating");
   }
}   

class Dog extends Animal {
   void eat() {
      System.out.println("dog eating");
   }

   void bark() {};
}

class Cat extends Animal {
   void eat() {
      System.out.println("cat eating");
   }

   void bark() {};
}
