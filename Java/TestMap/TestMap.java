import java.util.*;

public class TestMap {
   public static void main(String[] args) {
      HashMap <String, Integer> scores = new HashMap<String, Integer>(); //<---HashMap needs TWO type parameters - one for the key and one for the value.

      scores.put("Kathy", 42);
      scores.put("Bert", 343);
      scores.put("Skyler", 420);

      System.out.println(scores);
      System.out.println(scores.get("Bert"));
   }
}
