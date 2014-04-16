import alex.ds.heaptree.*;

/*
*   Heapsort is an excellent algorithm, but a good implementation of quicksort usually beats it in practice.
*   Nevertheless the heap data structure itself has many uses. There are two forms of priority queues: 1)
*   max-priority queues and min-priority queue. The following implementation focus on max-priority queues.
*
*   A priority queue is a data structure for maintaining a set S of elements, each with an assocaited value
*   called a key. A max-priority queue supports the following operations:
*   
*   1)  INSERT(S,x) inserts the element x into the set S, which is equivalent to operation of S = S U {x}.
*   2)  MAXMIMUM(S) returns the element of S with the largest key.
*   3)  EXTRACT-MAX(S) removes and returns the element of S with the largest key.
*   4)  INCREASE-KEY(S,x,k) increases the value of element x's key to the new value of k, which is assumed to be
*       at least as large as x's current key value.
*
*   The max-priority queue keeps track of the jobs to be performed and their relative priorities. 
*   When a job is finished or interrupted, the scheduler selects the highest-priority job from
*   among those pending by calling EXTRACT-MAX. The scheduler can add a new job to the queue at
*   any time by calling INSERT.
*
*   
*
*
*
*
*/



class max_p_queue {//max-priority queue

   //Constructor
   public max_p_queue() {
      heapSize = n = 0;
      A = new double[100]; //give 100 double type space
   }

   //left child
   public int left(int i) {
      //assert i < n/2 : "Position has no left child"; //?? why i < n/2
      if ( i < heapSize/2) {
         return i*2+1;
      } else {
         return -1;
      }
   }

   //right child
   public int right(int i) {
      //assert i < (n-1)/2 : "Position has no right child"; //?? why i < (n-1)/2
      if (i < (heapSize-1)/2) {
         return i*2+2;
      } else {
         return -1;
      }
   }

   //parent
   public static int parent(int i) {
      //return (int)(Math.ceil((double)i/2))-1;
      //assert i > 0 : "Position has no parent";
      if (i > 0) {
         return (i-1)/2;
      } else {
         return -1;
      }
   }

   //max-heapify, the parent is always larger than its children therefore the root stores the biggest element(it takes log(n) computation time).
   void maxheapify(int i) {
         int l = left(i);
         int r = right(i);
         int largest = i;
      
         //Find the largest elements among: me, left and right.
         if ( l != -1) {
            if(l <= heapSize && A[l] > A[i]) {
               largest = l;
            } else {
               largest = i;
            }
         }

         if (r != -1) {
            if(r <= heapSize && A[r] > A[largest]) {
               largest = r;
            }
         }

         if (largest != i) {
            double temp = A[largest];
            A[largest] = A[i];
            A[i] = temp;
            maxheapify(largest); //Recurssion occurs here. Remember "largest" is the index.
         }
   }

   //Returns the element of S with the largest key
   public double heapMax() {
      return A[0];
   }
   
   //Extract the largest element and returns
   public double heapExtractMax()
   {
      if (heapSize < 1) {
         System.out.println("heap underflow");
      }

      double max = A[0];
      A[0] = A[heapSize-1]; //exchange the last with the first element in the heap
      heapSize = heapSize - 1;
      maxheapify(0); //heapify again
      
      return max;
   }

   //Increase the key value of ith element(it takes log(n) computation time)
   public boolean increaseKey(double key, int i) {
      if (key < A[i]) {
         System.out.println("new key is smaller than current key");
         return false;
      } else {
         A[i] = key;
         while ((i > 0) && (A[parent(i)] < A[i])) {
            double temp = A[i];
            A[i] = A[parent(i)];
            A[parent(i)] = temp;
            i = parent(i);
         }
         return true;
      }
   }

   //Insert an element into the heap
   public void insert(double key) {
      n++;
      heapSize++;
      
      A[heapSize-1] = Double.NEGATIVE_INFINITY;
      increaseKey(key, heapSize-1);
   }

   private int heapSize; //the size of the heap: heapSize <= n
   private double A[]; //the array
   private int n; //the array size: heapSize <= n

   public static void main(String[] args) {
      max_p_queue mpq = new max_p_queue();
      mpq.insert(1.5);
      mpq.insert(20);
      mpq.insert(-2);
      mpq.insert(4);
      
      mpq.heapExtractMax();
      mpq.heapExtractMax();
      System.out.println(mpq.heapMax());

      return; 
   }
}
