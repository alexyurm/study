import java.util.*;
import java.lang.*;

class InsertSort {
   public static <T extends Comparable<? super T>> void sort(T[] array) {
      int i, j;
      T key;
      //j = 1. Start from the second element
      for (j = 1; j < array.length; j++) {
         key = array[j];
         i = j - 1;
         //key is comparing to all the elemetns ahead.
         while (i >= 0 && key.compareTo(array[i]) < 0 ) {
            array[i+1] = array[i]; //move the bigger element to the right slot
            i = i - 1; //the left slot moving ahead
         }
         array[i+1] = key; //key is copied to the slot where its value is larger than the left slot value(when the for loop terminates)
      }
   }
   
   public static void main(String[] args) {
      Integer array[] = new Integer[]{4, 2, -10, 3, 19, 99, -21, 8, 67, -9};
      sort(array);
      for (int i: array) {
         System.out.println(i);
      }
   }
}
