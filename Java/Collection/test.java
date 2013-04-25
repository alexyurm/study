import java.lang.*;
import java.util.*;
import java.io.*;

class TestCollection {
   public static void main(String[] args) {
      //Define a collection of strings
      Collection<String> c = new ArrayList<String>();
      
      //Add some string elements into the collection
      String s1 = "I am Alex.";
      String s2 = "Not an evil.";
      String s3 = "Ha Ha Ha!";
      c.add(s1);
      c.add(s2);
      c.add(s3);
      
      //Implement the toArray() method:
      String[]a = c.toArray( new String[0]);
      
      try {
         System.out.println(a[3]);
      } catch (ArrayIndexOutOfBoundsException e){System.out.println("The index is not correct!");}
   }
}

class TestSet {
   public static void main(String[] args) {
      Set<String> s = new TreeSet<String>();
      for(String a: args) {
         if (!s.add(a)) //return false if a is already in the set
            System.out.println("Duplicate detected: " + a);
      }
      System.out.println(s.size() + " distinct words:" + s);
   }
}

class TestList {
   public static void main(String[] args) {
      List<Integer> l = new ArrayList<Integer>();
      l.add(10);
      l.add(154);
      l.add(11);
      l.add(-10);

      //ListIterator<Integer> listiterator = l.listIterator(); //no argument provided for listIterator() method. the index -1 is assigned
      ListIterator<Integer> listiterator = l.listIterator(l.size()); //the index n is assigned
      for (;listiterator.hasPrevious();) {
         System.out.println("previousIndex = " + listiterator.previousIndex());
         Integer i = listiterator.previous();
         System.out.println(i);
      }
   }
}

class TestQueue {
   //The following example program, a queue is used to implement a countdown timer.
   public static void main(String[] args)
      throws InterruptedException {

         int time = Integer.parseInt(args[0]);
         Queue<Integer> queue = new LinkedList<Integer>();

         for (int i = time; i >=0; i--) {
            queue.add(i);
         }

         while(!queue.isEmpty()) {
            System.out.println(queue.remove());
            Thread.sleep(1000);
         }
   }
}

class TestMap {
   public static void main(String[] args) {
      Map<String, Integer> m = new TreeMap<String, Integer>(); //you can change the constructor to HashMap, Or LinkedHashMap
      
      // Initialize frequency table from command line
      for (String a: args) {
         Integer freq = m.get(a);
         //Note that no duplicate keys are allowed and each key can map to at most one value.
         m.put(a, (freq == null) ? 1 : freq+1); //can this be used in the anagram algorithm??
      }

      System.out.println(m.size() + " distinct words:");
      System.out.println(m);
   }
}

//Illustrate Multimaps usage
//An anagram group is a bunch of words, all of which contain exactly the same letters but in different order. 
//The program takes two arguments on the command line: 1) the name of the dictionary file and 2) the minimum size of anagram group to prinout.
//Anagrams containing fewer words than the specified minimum are not printed
class Anagrams {
   public static void main(String[] args) {
      int minGroupSize = Integer.parseInt(args[1]);
      
      //read words from file and put into a simulated multimap
      Map<String, List<String>> m = new HashMap<String, List<String>>();

      //Read words from file and put into a simulated multimap
      try {
         Scanner s = new Scanner(new File(args[0]));
         while (s.hasNext()) {
            String word = s.next(); //this is the value in the hash map
            String alpha = alphabetize(word); //this is the key
            List<String> l = m.get(alpha);
            if (l == null) {
               m.put(alpha, l=new ArrayList<String>());
            }
            l.add(word);
         }
      } catch (IOException e) {
         System.err.println(e);
         System.exit(1);
      }

      //Print all permutation groups above size threshold
      for (List<String> l : m.values()) {
         if (l.size() >= minGroupSize) {
            System.out.println(l.size() + ": " + l);
         }
      }
   }

   private static String alphabetize(String s) {
      char[] a = s.toCharArray();// Use to CharArray again.
      Arrays.sort(a);//Use the sort method of Array. Interesting.
      return new String(a);
   }
}

//The following class representing a person's name implements Comparable.
//Some important points:
//1)  Name objects are immutable. All other things being equal, immutable types are the ways to go, especially for objects that will be used as elements 
//2)  The constructor checks its argument for null. This ensures that all Name objects are well formed so that none of the other methods will ever throw a NullPointerexception
//3)  The hashcode methods is redefined. This is essential for any class that redefines the equals method(Equal object must have equal hash codes). 
//    The equals method returns false if the specified object is null or an appropriate type. The comapreTo method throws a runtime exception under these circumstances. Both
//    of these behaviors are required by the general contracts of the respective method.
//4)  The toString method has been redefined so it prints the Name in human-readable form. This is alwasys a good idea, especially for objects that are going to get put into
//    collections. The various collection type's toString methods depend on the toString methods.

   

class Name implements Comparable<Name> {
   private final String firstName, lastName;
   
   public Name(String firstName, String lastName) {
      if (firstName == null || lastName == null)
         throw new NullPointerException();
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String firstName() { return firstName; }
   public String lastName() { return lastName; }

   public boolean equals(Object o) {
      if (! (o instanceof Name))
         return false;
      Name n = (Name) o;
      return n.firstName.equals(firstName) && n.lastName.equals(lastName);
   }

   public String toString() {
      return firstName + " " + lastName;
   }

   public int compareTo(Name n) {
      int lastCmp = lastName.compareTo(n.lastName());
      return (lastCmp != 0? lastCmp : firstName.compareTo(n.firstName()));
   }

   public int hashCode() {
      return 31*firstName.hashCode() + lastName.hashCode(); //Is this some kind of folding technique in hash function
   }

   public static void main(String[] args) {
      Name nameArray[] = {
         new Name("John", "Smith"),
         new Name("Karl", "Ng"),
         new Name("Jeff", "Smith"),
         new Name("Tom", "Rich")
      };

      List<Name> names = Arrays.asList(nameArray);
      Collections.sort(names);
      System.out.println(names);
   }
}
