import java.lang.*;
import java.util.*;

//Find the maximum subarray sum values using divide-and-conquer method.
class maxsubarrayfind {

   private static class found { 
      private int low;
      private int high;
      private int sum;

      public int low() {
         return low;
      }

      public int high() {
         return high;
      }

      public int sum() {
         return sum;
      }

      public void setLow(int low) {
         this.low = low;
      }

      public void setHigh(int high) {
         this.high = high;
      }

      public void setSum(int sum) {
         this.sum = sum;
      }
   }

   //max(cross) = max(left) + max(right)
   public static found find_max_subarray_cross(int[] array, int low, int mid, int high) {
      
      found result = new found();
      int sum_left = 0; //max(left)
      int sum_right = 0;//max(right)
      int sum = 0;
      int i, j, left, right;

      //Calculate max(left)
      sum_left = array[mid];
      left = mid;
      for (i = mid; i >= low; i--) {
         sum = sum + array[i];
         if (sum > sum_left) {
            sum_left = sum;
            left = i;
         }
      }

      //Calculate max(right)
      sum = 0;
      sum_right = array[mid+1]; //Don't include array[mid] as it has been included in max(left) calculation above.
      right = mid + 1;
      for( j = mid + 1; j <= high; j++ ) {
         sum = sum + array[j];
         if (sum > sum_right) {
            sum_right = sum;
            right = j;
         }
      }
      
      result.setLow(left);
      result.setHigh(right);

      result.setSum(sum_left+sum_right);

      return result;
   }

   //Any contiguous subarray A[i..j]  of A[low..high] must lie in exactly one of the following places:
   //1) entirely in the subarray A[low..mid], so that low <= i <= j <= mid
   //2) entirely in the subarray A[mid+1..high]
   //3) crossing the midpoint, so that low <= i <= mid < j <= high
   //
   public static found find_max_subarray(int[] array, int low, int high) {

      if (low == high) {
         found result = new found();
         result.setLow(low);
         result.setHigh(low);
         result.setSum(array[low]);
         return result;
      } else {
         int mid = (low + high) / 2;   // This is the formula calculate the middle index
         found result_left = new found();
         found result_cross = new found();
         found result_right = new found();

         result_left = find_max_subarray(array, low, mid);
         result_right = find_max_subarray(array, mid+1, high);
         result_cross = find_max_subarray_cross(array, low, mid, high);

         if (result_left.sum() >= result_right.sum() && result_left.sum() >= result_cross.sum()) {
            return result_left;
         } else if (result_cross.sum() >= result_left.sum() && result_cross.sum() >= result_right.sum()) {
            return result_cross;
         } else {
            return result_right;
         }
      }
   }

   public static void main(String[] args) {
      int[] array = new int[]{-1, 2, 4, -10, 4, 1, 5, 10, -12, -3};
      
      found f;
      f = find_max_subarray(array, 0, array.length-1);
      System.out.println("low = " + f.low());
      System.out.println("high = " + f.high());
      System.out.println("sum = " + f.sum());

      return;
   }
}
