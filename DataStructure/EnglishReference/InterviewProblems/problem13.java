/*

Problem 13

Two strings are called anagram if one of them is a rearrangement of the other one like "dog" and "god". Write an algorithm that takes two string and determined if they are 
anagram or not.

    Solution: 

    FIND-ANAGRAM(String s1, String s2)
    0   if s1.length() != s2.length() return false
    1   sum = 0;
    2   for i = 0 to s1.length()-1
    3       if hmap.containsKey(s1[i]) == false
    4           hmap.put(s1[i], 1)
    5           sum += 1
    6       else
    7           value = hmap.get(s1[i])
    8           value += 1
    9           hmap.put(s1[i], value)
    10
    11  for i = 0 to s2.length()-1
    12      if hmap.containsKey(s2[i]) == false
    13          return false
    14      else
    15          value = hmap.get(s2[i])
    16          if value == 1
    17              hmap.remove(s2[i])
    18              sum -= 1
    19          else
    20              value -=1
    21              hmap.put(s2[i], value)
    22  if sum != 0
    23      return false
    24  else
    25      return true

*/

import java.util.*;
import java.lang.Integer;

public class problem13 {

    public static boolean findAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int sum = 0;
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < s1.length(); i++) {
            Integer k = (Integer)s1.codePointAt(i); //the key

            if (hmap.containsKey(k) == false ) {
                hmap.put(k, 1); //input (k, 1) into the hashmap
                sum += 1;
            } else {
                Integer v = hmap.get(k);//the value
                Integer new_v = new Integer(v.intValue()+1);
                hmap.put(k, new_v);
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            
            Integer k = (Integer)s1.codePointAt(i); //the key

            if (hmap.containsKey(k) == false ) {
                return false;
            } else {
                Integer v = hmap.get(k);//the value
                if (v.intValue() == 1) {
                    hmap.remove(k);
                    sum -= 1;
                } else {
                
                    Integer new_v = new Integer(v.intValue()-1);
                    hmap.put(k, new_v);
                }
            }
        }

        if (sum != 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("the answer is " + findAnagram("silent", "listen"));
    }

}
