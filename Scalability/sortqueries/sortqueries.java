import java.util.*;
import java.lang.Integer;
import java.lang.Math;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class HeapTree {
   
   MyEntry[] entries;
   private int n;          //the size of the hashmap.
   private int heapSize;   //the heap size. Noted that 0<=heapSize<=size.

   /* The constructor */
   public HeapTree() {
      n = heapSize = 0;
      entries = null;
   }
   
   /* The second constructor */
   public HeapTree(MyEntry[] entries) {
       n = heapSize = entries.length;
       this.entries = entries.clone();
   }

   public int left(int i) {
      //assert i < n/2 : "Position has no left child"; //?? why i < n/2. Because:
      //(i * 2 + 1) <= (heapsize-1) -> 
      // i <= (heapsize/2 - 1) -> 
      //(i+1) <= (heapsize/2) -> 
      //i < heapsize/2
      if ( i < heapSize/2) {
         return i*2+1;
      } else {
         return -1;
      }
   }

   public int right(int i) {
      //assert i < (n-1)/2 : "Position has no right child"; //?? why i < (n-1)/2. Because:
      //(i * 2 + 2) <= (heapsize-1) -> 
      // i+1 <= (heapsize-1)/2 -> 
      //(i+1) <= (heapsize-1)/2 -> 
      // i < (heapsize-1)/2
      if (i < (heapSize-1)/2) {
         return i*2+2;
      } else {
         return -1;
      }
   }

   public static int parent(int i) {
      //return (int)(Math.ceil((double)i/2))-1;
      //assert i > 0 : "Position has no parent";
      if (i > 0) {
         return (i-1)/2;
      } else {
         return -1;
      }
   }

   /** The running time of MAX-HEAPIFY on a subtree of size n rooted at a given node i is the O(1) time to fix up the relationship among the elements A[i], */
   //  A[left(i)], and A[right(i)], plus the time to run MAX-HEAPIFY on a subtree rooted at one of of the children of node i (assuming that the recursive call )
   //  orrurs). The children's subtree each have size at most 2n/3(??) - the worst case occurs when the bottom level of the tree is exactly half full(??). And therefore
   //  we can describe the running time of MAX-HEAPIFY by the recurrence: T(n)<=T(2n/3)+ O(1)
   //  The solution to this recurrence, by case 2 of the master theorem is T(n) = O(lg(n)). Alternatively, we can characterize the running time of MAX-HEAPIFY on a node
   //  of height as O(h)(??). This takes O(lg(n)).
   /*
   */
   
   void maxheapify(int i) {
       int largest = i;
       int l, r, current;

       current = largest; //assume the the current node is the largest
       l = left(current);
       r = right(current);

       if (l != -1) {
           //if((l <= heapSize) && (values[l].compareTo(values[largest]) > 0)) {
           if ((l <= heapSize) && entries[l].getValue().compareTo(entries[largest].getValue()) > 0) {
               largest = l;
           }
       }

       if (r != -1) {
           //if((r <= heapSize) && (values[r].compareTo(values[largest]) > 0)) {
           if ((r <= heapSize) && entries[r].getValue().compareTo(entries[largest].getValue()) > 0) {
               largest = r;
           }
       }

       if (largest != i) {
           MyEntry temp = entries[largest];
           entries[largest] = entries[i];
           entries[i] = temp;
           maxheapify(largest); //Recurssion occurs here. Remember "largest" is the index of left or right
       } 
   }

   /** We can use the procedure MAX-HEAPIFY in a bottom-up manner to convert an array A[1..n] */
   // , where n = A.length, into a max-heap. The elements in the subarray A[floor(n/2)+1...n] are 
   // all leaves of the tree (Exercise 6.1-7) so each is a 1-element heap to begin with. The procedure
   // BUILD-MAX-HEAP goes through the remaining nodes of the tree and runs MAX_HEAPIFY on each on.
   // The running time of BUILD-MAX-HEAP is O(n).

   //Note: For index which starts from 0, the elements in the subarray A[floor(n/2), ..., n-1 ] are leaves
   // of the tree.

   void buildmaxheap() {
      heapSize = n;

      //Note: n/2-1 is the index of the last non-leaf element but why?? Because:
      //if node x is a parent node, it must has at least a left child: (x*2+1) <= (heapSize-1)  -> 
      //(x*2) <= heapSize-2     ->
      //x <= heapSize/2 - 1     ->
      //max(x) = (heapSize/2-1) and min(x) = 0
      for (int i = n/2-1; i >=0; i--) {
         maxheapify(i);
      }
   }

   /** Since the maximum element of the array is stored at the root A[0], we can put it into its correct final position */
   //  by exchanging it with A[n-1], where n = A.length. This the new root element might violate the children of the max-heap
   //  property. The way to resolve this issue is following:
   //  Step1: exchange A[0] with A[i] (i = n-1 before the interations begin)
   //  Step2: heapsize = heapsize - 1; (the heapsize=n before the iterations begin)
   //  Step3: max-heapify(0) (remember that the calculation of max-heapify is based on heapsize not n)
   //  Step4: decrement i
   //  Repeat Step 1~3 until i < 0

   void heapsort() {
      buildmaxheap();//this takes O(lg(n)*n).
      for (int i = n-1; i > 0; i--) { //this also takes O(lg(n)*n)

         MyEntry temp = entries[0];
         entries[0] = entries[i];
         entries[i] = temp;

         heapSize--;
         maxheapify(0);
      }
   }

   void showArray() {
      for (int i = 0; i < entries.length; i++) {
         if (i+1 == entries.length) {
            System.out.println(entries[i]);
         } else {
            System.out.print( entries[i] + ", ");
         }
      }
   }

   //this method is similar to heapsort except it only do n=top times of 
   //sorting, in order to find the top=n elements
   MyEntry[] findTop(int top) {
       if (top >= n ) {
           return null;
       }

       buildmaxheap(); //this takes O(lg(n)*n))
       MyEntry[] result = new MyEntry[top];
       int j = 0;

       for (int i = n-1; i > 0 && top > 0; i--) { //this also takes O(lg(n)*n)

         result[j] = entries[0];

         MyEntry temp = entries[0];
         entries[0] = entries[i];
         entries[i] = temp;

         heapSize--;
         maxheapify(0);
         top--;
         j++;
      }

      return result;
   }
}

class MyEntry {
    private String key;
    private Integer value;

    public MyEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String  getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void print() {
        System.out.println("IP: " + getKey() + ", Access Time: " + getValue());
    }
}

public class sortqueries {
    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader("./queries.txt"));
            
            /*
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
            */

            //create a HashMap
            Map<String, Integer> hmap = new HashMap<String, Integer>();
            
            //step1: iterate through all lines in the file. The time complexity: O(n). 
            while ((sCurrentLine = br.readLine()) != null) {
                if (hmap.containsKey(sCurrentLine) == false) {
                    hmap.put(sCurrentLine, 1);
                } else {
                    Integer v = hmap.get(sCurrentLine);
                    v = v + 1;
                    hmap.put(sCurrentLine, v);
                }
            }
                       
            //step2: do hash sort on hmap
            Set set = hmap.entrySet();
            ArrayList aList = new ArrayList(set);
            int size = aList.size();

            MyEntry entryArray[] = new MyEntry[size];
            
            //Take O(N'), N' is the # of distinct entries
            for (int i = 0; i < size; i++) {
                entryArray[i] = new MyEntry(((Map.Entry<String, Integer>)aList.get(i)).getKey(), ((Map.Entry<String, Integer>)aList.get(i)).getValue());
            }

            HeapTree hTree = new HeapTree(entryArray);
            int top = 5;

            MyEntry[] result = hTree.findTop(5);
            
            if (result != null) {
                for (MyEntry entry : result) {
                    entry.print();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
}
