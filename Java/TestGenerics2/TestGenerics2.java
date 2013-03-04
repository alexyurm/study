import java.util.*;

public class TestGenerics2 {
   public static void main(String[] args) {
      new TestGenerics2().go();
   }

   public void go() {
      ArrayList<Animal> animals = new ArrayList<Animal>();//Create a arraylist to store animal instances only.
      animals.add(new Dog());
      animals.add(new Cat());
      animals.add(new Dog());

      takeAnimals(animals);
   }

   public void takeAnimals(ArrayList<Animal> animals) {
      for (Animal a: animals) {
         a.eat();
      }
   }

   public void takeAnimals2(ArrayList<? extends Animal> animals) {
      for (Animal a: animals) {
         a.eat();
      }
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
 
