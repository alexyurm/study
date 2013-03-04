import java.util.*;

public class TestGenerics3 {
   public static void main(String[] args) {
      new TestGenerics3().go();
   }

   public void go() {
      ArrayList<Animal> animals = new ArrayList<Animal>();//Create a arraylist to store animal objects only.
      animals.add(new Dog());
      animals.add(new Cat());
      animals.add(new Dog());
      takeAnimals2(animals);

      ArrayList<Dog> dogs = new ArrayList<Dog>(); //Create an arraylist to store dog instances only.
      dogs.add(new Dog());
      dogs.add(new Dog());
      dogs.add(new Dog());
      takeAnimals2(dogs);
   }

   public void takeAnimals(ArrayList<Animal> animals) {
      for (Animal a: animals) {
         a.eat();
      }
   }

   public void takeAnimals2(ArrayList<? extends Animal> animals) {
      for (Animal a : animals) {
         a.eat();
      }
   }

   public void takeAnimals3(ArrayList<? extends Animal> animals) {
      
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
