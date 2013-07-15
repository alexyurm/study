import java.util.*;

/*
*  Try to test the following statement in my Java note:
*
*  To restrict the types that can be used as type arguments in a 
*  parameterized type, use Bounded Type. E.g.   
*
*  public static <U extends Number> void inspect(U u) {
*  //Only Number subclass subclass instance can call this method.
*  ...
*  }
*
*
*/

public class TestGenerics4 {
   public static <U extends Number> void inspect(U u) {
      System.out.println(u.getClass().getName());
   }

   public static void main(String[] args) {
      //String s = "Alex Yu";
      //inspect(s); //It cannot compile because s is not a subclass of Number

      int i = 10;
      inspect(i); //It compiles because i is a subclass of Number.
   }
}
