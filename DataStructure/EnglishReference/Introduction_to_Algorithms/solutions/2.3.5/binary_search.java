import java.util.*;

public class binary_search {
    
    //find if v exist in array, if yes, returns the index# else returns -1
    public static int search(int[] array, int v) {
        
        int start = 0;
        int end = array.length - 1;
        
        int mid = (start + end) / 2;
        int count = 0;

        while( start < array.length && end >= 0 ) {

            count++;

            if (count == 10) {
                return -1;
            }

            if (v < array[mid]) {
                //update end
                end = mid - 1;
                //update mid

                int newEnd = (start + end) / 2;
                if (mid == newEnd || start > end) {
                    return -1;
                } else {
                    mid = newEnd;
                }

                continue;
            }

            if (v == array[mid]) {
                return mid;
            }

            if (v > array[mid]) {
                //update start
                start = mid + 1;
                //update mid
                int newEnd = (start + end) / 2;
                if (mid == newEnd || start > end) {
                    return -1;
                } else {
                    mid = newEnd;
                }
            
                continue;
            }
        }
    
        return -1;
    }

    public static void main(String[] args)  {
        //int[] array = new array[10];
        //int[] array = {2, 3, 19, 23, 28, 33, 42, 98, 120, 1090};
        //int[] array = {2, 3, 19, 28, 33, 42, 98, 120, 1090};
        int[] array = {3, 4};

        int result = binary_search.search(array, 4);
    
        System.out.println("result = " + result);

        return;
    }
}
