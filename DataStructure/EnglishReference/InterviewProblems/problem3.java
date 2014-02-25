/*
*
* Given a set of number ranges, i.e. [1,5], [3,6], [7,9], write a program to merge overlapping number ranges. In above example, the result should be [1,6], [7,9].
*
*/
import java.util.*;

class Range {
   public int lower;
   public int upper;
   
   //The constructor
   Range(int x, int y) {
      lower = x;
      upper = y;
   }

   //The default constructor
   Range() {
      lower = 0;
      upper = 0;
   }

   public int lower() {
      return lower;
   }

   public int upper() {
      return upper;
   }
   
   // Merge this range with [x, y]
   public boolean merge(int x, int y) {
      boolean result = false;
      
      if ((x < lower) && (y <= upper) && (y >= lower)) {
          lower = x;
          result = true;
      }
      else if ((x >= lower) && (y <= upper)) {
         result = true;
      }
      else if ((x <= upper) && (y > upper)) {
         upper = y;
         result = true;
      }

      return result;
   }

   //Single Merge
   public static boolean singleMerge(ArrayList<Range>list, int index) {
      int size = list.size();
      if (size <= 1 ) {
          return false;
      }
      else {
          if ((index <= (size-1)) && (index >= 0) ) {
              Range target = list.get(index);
              for(int i = 0; i < size; i++) {
                 if (i != index) {
                    Range cur = list.get(i);
                    if (cur.merge(target.lower(), target.upper()) == true) {
                        list.remove(index);
                        return true;
                    }
                 }
              }
          } else {
              return false;
          }
      }
   
      return false;
   }

   //List Merge(This algorithm currently takes O(n.^2) computation time)
   public static void rangeMerge(ArrayList<Range>list) {
      boolean merged= false;
      
      for (int i = 0; i < list.size(); i++) {
          if (Range.singleMerge(list, i) == true) {
             merged = true;
             break;
          }
      }
      if (merged == true) {
         rangeMerge(list);
      } else {
         return;
      }
   }

   public static void printArray(ArrayList<Range>list) {
      for (Range rg : list) {
         System.out.println("[" + rg.lower() + "," + rg.upper() + "]");
      }
   }  
}

/*
class RangeHeap {
    private ArrayList<Range>list;
    private heapSize;

    public RangeHeap(ArrayList<Range>list) {
        this.list = list;
        heapSize = list.size();
    }

    private boolean singleMerge(int index) {
        if (heapSize <= 1) {
            return false;
        } else {
            if ((index <= (heapSize-1)) && (index >= 0) ) {
              Range target = list.get(index);
              boolean result = false;

              for(int i = 0; i < heapSize; i++) {
                 if (i != index) {
                    Range cur = list.get(i);
                    if (cur.merge(target.lower(), target.upper()) == true) {
                        list.remove(index);
                        heapSize--;
                        result = true;
                        break;
                    }
                 }
              }

              if (result == false) {
                 //No need to merge:
                 Range temp = list.get(heapSize-1);
                 list.set(index);                 

                 heapSize--;
              }


          } else {
              return false;
          }

        }
    }

    public void rangeMerge() {
        for (int i = 0; i < heapSize; i++) {
            if (Range.singleMerge(list, i) == true) {
                merged = true;
                break;
            }
        }
        
        if (merged == false) {
           
        }
    }
}*/

//Test the algorithm result
class problem3 {

   public static void main(String[] argv) {  

       ArrayList<Range> list = new ArrayList<Range>();
       list.add(new Range(3,6));
       list.add(new Range(7,9));
       list.add(new Range(1,5));
      
       Range.rangeMerge(list);
       Range.printArray(list);
   }
}
