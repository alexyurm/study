/*
** -  Assuming the hash table's size length (length) >= the maximum elements can be added (n).
** -  "List" is an interface that extends "Collection".
** -  "Iterator" enables you to cycle through a collection, obtaining or removing elements. Listiterator extends iterator to allow bidirectional traversal of a list, and the
**    modification of elements.
*/

import java.util.*;

class ChainedHashTable {

   private List[] hashTable;
   private int length;
   private int w = 32;
   private int d = 8;

   boolean add (Object o) {
      if (find(o) != null) return false;
      //if (length + 1 > hashTable.size()) resize();
      hashTable[hash(o)].add(o);
      length++;
      return true;
   }

   Object remove (Object o) {
      Iterator itr = hashTable[hash(o)].iterator();
      while (itr.hasNext()) {
         Object temp = itr.next();
         if (temp.equals(o)) {
            itr.remove();
            length--;
            return temp;
         }
      }

      return null;
   }

   Object find(Object o) {
      for (Object temp : hashTable[hash(o)]) {
         if (temp.equals(o)) {
            return temp;
         }
      }
      return null;
   }

   int hash(Object o) {
      Random random = new Random();
      int z = random.nextInt();
      return (z * o.hashCode()) >>> (w-d);
   }

   
} 
