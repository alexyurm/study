/*
*  Hashing: the process of finding a record using some computation to map
*  its key value to a position in the table is called hashing.
*
*  -  Most hashing schemes place records in the table in whatever order satisfies the needs of the
*     address calculation, thus the records are not ordered by value of frequency. 
*
*  -  Hash function: The function that maps key value to positions is called a hash function.
*     It is usually denoted by h.
*     
*  -  Hash table:  The array that holds the records. It is denoted by HT.
*
*  -  A position in the hash table is called slot. The number of slots are denoted by M.
*
*  -  Hashing only works to store sets. That is hashing cannot be used for applications where multiple
*     records with the same key value are permitted.
*
*  -  The goal for a hashing system is to arrange things such that, for any key value K and some hash function h,
*     i = h(K) is a slot in the table such that 0<=h(K)<M, and we have the key of the record stored at HT[i] equal to
*     K. 
*
*  -  Hashing is especially efficient for applications where access involves only exact-match queries. In other words,
*     Hashing is most appropriate for answering the question. "What record, if any, has key value K?"
*
*  -  Finding a record with key value K in database organized by hashing follows a two-step procedure:
*     1) Compute the table location h(K);
*     2) Starting with slot h(K), locate the record containg key K using (if necessary) a colllision resolution policy.
*
*  -  Collisions resolution techniques can be broken into two classes: 1) open hashing; 2) closed hashing.
*
*     1) open hashing: collisions are stored outside the table
*     2) closed hashing: collisions are stored inside the table.
*
*     Open Hashing:  the simplest form of open hashing defines each slot in the hash table to be the head of a linkedlist. Records
*                    within a slot's list can be ordered in serval way: by insertion order, by key value order, or by frequency-of-
*                    access order. 
*
*/

import java.util.*;

class Hash {
   private int[] HT;  //the hash table
   private int M; //the maximum size of the hash table.

   // A hash function with 16 slots.
   public int h (int x) {
      return x % 16;
   }

   // A hash function for strings of characters(a folding approach)
   public int h(String x, int M) {
      char ch[];
      ch = x.toCharArray(); //Change the string into a character array.
      int xlength = x.length();
         
      int i, sum;
      for (sum = 0, i = 0; i < xlength; i++) {
         sum += ch[i]; //sum the ASCII values of the letters in a string. 
      }
      return sum % M;   //If M is small, this hash function should do a good job of distributing strings
                        //evenly among the hash table slots. because it gives equal weight to all 
                        //characters.
   }

   // A much better hash function for strings(a folding approach).
   public long sfold(String s, int M) {
      int intLength = s.length() / 4; //It processes the string 4 bytes at a time.
      long sum = 0;
      for (int j = 0; j < intLength; j++) {
         char c[] = s.substring(j*4, (j*4)+3).toCharArray(); //and interprets each of the four-byte chunks as a single (unsigned) long integer value.
         long mult = 1;
         for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult; //the integer values for the four-byte chunks are added together.
            mult*=256;
         }
      }

      char c[] = s.substring((intLength * 4)).toCharArray();
      long mult = 1;
      for (int k = 0; k < c.length; k++) {
         sum += c[k] * mult;
         mult += 256;
      }

      return (Math.abs(sum) % M ); //In the end, the resulting sum is converted to the range 0 to M-1 using the modulus operator.
   }

   // Using hash table to judge two strings ara anagrams. Assuming the two strings are non-capital(Ascii code: 'a' is 97; 'z' is 122).
   public static boolean anagrams(String s1, String s2) {
      if (s1.length() != s2.length()) {
         return false;   
      } else {
         int[] hashtable = new int[26];
         char[] c1 = s1.toCharArray();
         char[] c2 = s2.toCharArray();
         int size = s1.length(); //s1 and s2 have the same size
         int nzeros = 26; //number of zeros

         for(int i = 0; i < size; i++) {
            if (hashtable[c1[i]-97] == 0) {
               nzeros--;
            }
            hashtable[c1[i]-97]++;
         }

         for (int i = 0; i < size; i++) {
            if (hashtable[c2[i]-97] == 0) {
               return false;
            } else if (--hashtable[c2[i]-97] == 0) {
               nzeros++;
            }
         }

         if (nzeros != 26) {
            return false;
         } else {
            return true;
         }
      }   
   }

   //Collosion 

   public static void main(String[] args) {
      HashMap hm = new HashMap();
   }
}
