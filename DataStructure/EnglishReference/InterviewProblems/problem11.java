/*

Problem 11

    You are given an array of integers where most of them have been repeated even number of times except one which has been repeated odd number of times. 
    How can we find the number which appears odd number of times? 

    Solution: 

    FIND-ODD-NUM(int[] array)
    1   sum = 0
    2   for i = 0 to array.length()-1            
    3       if s.contains(array[i]) == false
    4           sum += array[i]
    5           s.add(array[i])
    6       else     
    7           sum-=array[i]
    8           s.remove(array[i])
    9   return sum

*/

import java.util.*;
import java.lang.Integer;

public class problem11 {

    public static int findOddNum(int[] array) {

        int sum = 0;
        Set s = new HashSet();

        for (int i : array) {
            if (s.contains(i) == false) {
                sum += i;
                s.add(i);
            }
            else {
                sum -= i;
                s.remove(i);
            }
        }

        return sum;
    }
    

    public static void main(String[] args) {

        int[] array = {1, 24, 24, 1, 3, 3, 6, 7, 7, 3, 2, 2, 3, 6, 24};

        System.out.println("the odd number is: " + findOddNum(array));
    }
}
