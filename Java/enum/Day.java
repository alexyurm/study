import java.util.*;
import java.lang.Integer;

public enum Day {
   SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; //';' is optional
}

class EnumTest {
   Day day;

   public EnumTest(Day day) {
      this.day = day;
   }

   public void tellItLikeItIs() {
      switch (day) {
         case MONDAY:
            System.out.println("Mondays are bad.");
            break;

         case FRIDAY:
            System.out.println("Fridays are better.");
            break;

         case SATURDAY:
         case SUNDAY:
            System.out.println("Weekends are best.");
            break;

         default:
            System.out.println("Midweek days are so-so.");
            break;
            
      }
   }
}

