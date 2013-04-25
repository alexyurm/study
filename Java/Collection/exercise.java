import java.lang.*;
import java.util.*;
import java.io.*;

//Exercise 1: Write a program that prints its arguments in random order. do not make a copy of the argument array. 
//Hint1: Use the asList method provided by Arrays to access 
//Hint2: To use the shuffle method provided by Collections class.
class Exercise1 {
   public static void main(String[] args) {
      List<String> l = Arrays.asList(args);
      Collections.shuffle(l);
      for (String arg: l) {
         System.out.format("%s ", arg);
      }
      System.out.println();
   }
}

//Take the FindDups example and modify it to use a SortedSet instead of a Set. Specify a Comparator so that case is 
//ignored when sorting and identifying set elements.
//
//import java.util.*;
// 
//public class FindDups {
//    public static void main(String[] args) {
//        Set<String> s = new HashSet<String>();
//        for (String a : args)
//            if (!s.add(a))
//                System.out.println("Duplicate detected: " + a);
// 
//        System.out.println(s.size() + " distinct words: " + s);
//    }
//}
//Hint: There is a method in String called compareToIgnoreCase.

class FindDups {

   public static void main(String[] args) {
      //Create a new comparator object
      Comparator<String> comparator = new Comparator<String>() {
         public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
         }
      };

      //Pass that comparator object to the TreeSet constructor.
      //Why we didn't use HashSet or LinkedHashSet? Because they do not
      //have a constructor which accept comparator as a parameter.
      SortedSet<String> s = new TreeSet<String>(comparator);
      for (String a : args)
         if (!s.add(a)) System.out.println("Dublicate detected: " + a);
      System.out.println(s.size() + " distinct words" + s );
   }
}

//Write a program that takes List<String> and applies String.trim to each element.
//To do this, you'll need to pick one of the three iteration idioms that you described
//in Question 1. Two of these will not give the result you want, so be sure to write a program
//that demonstrates that the method actuall work!

class Exercise3 {
   static void listTrim(List<String> strings) {
      for (ListIterator<String> lit = strings.listIterator(); lit.hasNext();) {
         lit.set(lit.next().trim());
      }
   }
   
   public static void main(String[] args) {
      List<String> l = Arrays.asList(" red ", " white ", " blue ");
      listTrim(l);
      for (String s : l) {
         System.out.format("\"%s\"%n", s);
      }
   }
}

