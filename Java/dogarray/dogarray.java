class Dog
{
   String name;
   public static void main(String[] args)
   {
      Dog dog1 = new Dog();
      dog1.bark();
      dog1.name = "Bart";
   }

   public void bark()
   {
      System.out.println(name+ " says Ruff!" );
   }
} 
