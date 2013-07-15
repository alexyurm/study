import java.util.*;
import java.lang.*;

/*
*  算法的概括:
*  1. 把A[j]抽出設置爲key. 注: j的範圍: [1, length-1]
*  2. key開始跟它之前的元素比較,如果key < A[i], A[i+1] = A[i], 即把較大的元素後移. 注: i的範圍[0, j-1]
*  3. 當key>=A[i]或i<0時, 停止比較, 把A[i+1]設置爲key. 這一步的目的是左移key到它該出現的位置.
*  4. 重複1~3直到j的範圍遍歷結束.
*/


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
