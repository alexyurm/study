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
*     Closed Hashing:Closed hashing stores all records directly in the hash table. If record R is to be inserted and another record
*                    already occupies R's home position, then R will be stored at some other slot in the table. E.g.
*
*
*     Bucket Hashing:One implementation of closed hashing is to group table slots into buckets. The M slots of the hash table are divided
*                    into buckets. The M slots of the hashtable are divided into B buckets, with each bucket consisting of M/B slots. The hash
*                    function assigns each record to the first slot within one of the buckets. If this slot is already occupied, then the bucket
*                    slots are searched sequentially until open slot is found. If a bucket is entirely full, then the record is stored in an
*                    overflow bucket of infinite capacity at the end of the table.
*
*     Linear Probing:Closed hashing with no bucketing, and a collision resolution policy that can use any slot in the hash table. Linear probe
*                    is actually the worst collision resolution methods because of "primary clustering" it introduced. The ideal behavior for 
*                    a collision resolution mechanism is that each empty slot in the table will have equal probability of receiving the next 
*                    record inserted. (p(h, i)= i)
*     
*                    How to improve: solution 1: p(K,i) = ci. This method is to skip slots by a constant c other 1. Here, c must be relatively prime to
*                    M to generate a linear probing sequence that visits all slots in the table(??). This solution still cannot sovle the prime clustering
*                    problem. E.g. If c = 2, home = 1, the hash sequence will be 1, 3, 5, 7...if home = 3, the hash sequence will be 3, 5, 7...
*
*                    Solution 2: The ideal probe function would select the next position on the probe sequence at random from among the unvisited
*                    slots. that is the probe sequence should be a random permutation of the hash table positions. Unfortunatelly, we cannot retrieve the value back
*                    if the slot is truly random. We can do something similar called "pseudo-random probing":
*
*                    p(K,i) = Perm[i-1], where Perm is an array of length M-1 containing a random permutation of the values from 1 to M-1.
*        
*                    Another probe function that eliminates primary clustering is called quadratic probing: p(K, i) = c1*i.^2 + c2*i + c3. It has a disadvantage that 
*                    typically not all hash table slots will be on the probe sequence. 
*
*                    Both pseudo-random probing and quadratic probing eliminate primary clustering, which is the problem of keys sharing substantial segments of a 
*                    probe squence. If two keys hash to the same home position, however, then they will always follow the same probe sequence for every collision
*                    resolution method that we have seen so far. This problem is called secondary clustering. To avoid this, we need to have the probe sequence make
*                    use of the original key value in its decision-making process. A simple technique for doing this it to return to linear probing dy a constant
*                    step size for the probe function, but to have that constant to be determined by a second hash function, h2. Thus, the probe sequence would be
*                    the form p(K,i) = i*h2(K). the mdthod is called double hashing.
*                    
*
*/

import java.util.*;

/** Container class for a key-value pair */
class KVpair<Key,E> {
   private Key k;
   private E e;

   /** Constructors */
   KVpair() {
      k = null;
      e = null;
   }
   KVpair(Key kval, E eval) {
      k = kval;
      e = eval;
   }

   /** Data member access functions */
   public Key key() {return k;}
   public E value() {return e;}
}

class Hash<Key extends Comparable<? super Key>, E> {
   private KVpair<Key,E>[] HT;  //the hash table
   private int M; //the maximum size of the hash table.

   /** Constructors */
   Hash() {
      HT = null;
      M = 0;
   }

   Hash(int M) {
      this.M = M;
      //HT = new KVpair<Key,E>[M];//This is Generics array creation!! Not allowed!!
      HT = (KVpair<Key,E>[])new KVpair[M];
   }

   // A hash function with 16 slots
   public int h(Key k) {
      return M-1;
   }

   //the probe function p
   private int p(Key key, int slot) {
      return slot;
   } 


   // A hash function with 16 slots.
   public int h (int x) {
      return x % 16;
   }

   // A hash function for strings of characters(a folding approach)
   public int h(String x) {
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
   public long sfold(String s) {
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

   /** Insert record r with key k into HT  */
   public void hashInsert(Key k, E r) {
      int home;
      int pos = home = h(k);//Home position for r
      for (int i = 1; HT[pos] != null; i++) {
         pos = (home + p(k,i)) % M; //Next probe slot
         assert HT[pos].key().compareTo(k) != 0 : "Duplicates not allowed";
      }
      HT[pos] = new KVpair<Key,E>(k,r); //Insert R
   }

   /** Search in hash table HT for the record with key k */
   E hashSearch(Key k) {
      int home;   //home position for k
      int pos = home = h(k);  //Initial position
      for (int i = 1; (HT[pos] != null) && (HT[pos].key().compareTo(k) != 0); i++ ) {
         pos = home + p(k,i) % M; //Next probe position
      }
      if (HT[pos] == null) return null;
      else return HT[pos].value();
   }

   public static void main(String[] args) {
      
   }
}
