import java.util.*;
import java.lang.Integer;
import java.lang.Math;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class topQueue {
    private MyEntry[] entries;
    private int heapSize;

    public topQueue() {
        entries = null;
        heapSize = 0;
    }

    public topQueue(MyEntry[] entries) {
        this.entries = entries.clone();
        heapSize = entries.length;

        buildminheap();
    }

    public int left(int i) {
      if ( i < heapSize/2) {
         return i*2+1;
      } else {
         return -1;
      }
   }

   public int right(int i) {
      if (i < (heapSize-1)/2) {
         return i*2+2;
      } else {
         return -1;
      }
   }

   public static int parent(int i) {
      if (i > 0) {
         return (i-1)/2;
      } else {
         return -1;
      }
   }

   private void buildminheap() {
      if (heapSize == 0) {
         return;
      }

      for (int i = heapSize/2-1; i >= 0; i--) {
         minheapify(i);
      }
   }

   void minheapify(int i) {
      int smallest = i;
      int l, r, current;

      current = smallest; //assume the the current node is the smallest
      l = left(current);
      r = right(current);
      

      if (l != -1) {
          if ((l <= heapSize) && entries[l].getValue().compareTo(entries[smallest].getValue()) < 0) {
              smallest = l;
          }
      }

      if (r != -1) {
          if ((r <= heapSize) && entries[r].getValue().compareTo(entries[smallest].getValue()) < 0) {
              smallest = r;
          }
      }

      if (smallest != i) {
          MyEntry temp = entries[smallest];
          entries[smallest] = entries[i];
          entries[i] = temp;
          minheapify(smallest); //Recurssion occurs here. Remember "largest" is the index of left or right
      } 
  }

   public MyEntry heapMin() {
       if (heapSize != 0) {
          System.out.println("heap underflow");
          return entries[0];
       } else {
          return null;
       }
   }

   public MyEntry heapExtractMin() {
      if (heapSize < 1) {
         System.out.println("heap underflow");
         return null;
      } else {
         MyEntry min = entries[0];
         entries[0] = entries[heapSize-1];
         heapSize = heapSize - 1;
         minheapify(0);
         return min;
      }
   }

   public void insertKey(MyEntry entry) {

      if (entry.getValue().compareTo(entries[0].getValue()) > 0) {
          entries[0] = entry;
          minheapify(0);
      }
   }

   public void print() {
       if (heapSize == 0) {
           System.out.println("nothing to print");
       } else {
           for (int i = 0; i < heapSize; i++) {
               entries[i].print();
           }
       }
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

            //Transfer the map to set, this takes  another O(n')
            Set<Map.Entry<String, Integer>> set = hmap.entrySet();
            MyEntry[] entrys = new MyEntry[set.size()];
            int i = 0;
            for (Map.Entry<String, Integer> element : set) {
                entrys[i] = new MyEntry(element.getKey(), element.getValue());
                i++;
            }

            int top = 6;
            MyEntry[] first = Arrays.copyOfRange(entrys, 0, top);
            MyEntry[] second = Arrays.copyOfRange(entrys, top, entrys.length);

            //Create a top=6 queue with the heapsize(=6 in this example), this takes: k*log(k)
            topQueue tQueue = new topQueue(first);

            //Start inserting the rest n'-k elements. This takes log(k)*(n'-k)
            for (MyEntry entry : second) {
                tQueue.insertKey(entry);
            }

            tQueue.print();

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
