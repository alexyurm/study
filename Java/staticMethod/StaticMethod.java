public class StaticMethod
{
   private static int instVar=3; //static instance variable
   public static void main(String[] args)
   {
      instVar = 2;
      System.out.println("instVar = " + instVar);
   }
} 
