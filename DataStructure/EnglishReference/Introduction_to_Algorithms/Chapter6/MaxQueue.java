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
   }

   //Some useful methods
   public int left(int i) {
      //assert i < n/2 : "Position has no left child"; //?? why i < n/2
      if ( i < heapSize/2) {
         return i*2+1;
      } else {
         return -1;
      }
   }


   //Returns the element of S with the largest key
   public int heapMax(int[] A) {
      return A[0];
   }
   
   //Extract the largest element and returns
   public int heapExtractMax(int[] A)
   {
      if (A.heapSize < 1) {
         System.out.println("heap underflow");
      }

      int max = A[0];
      A[0] = A[A.heapSize-1];
      A.heapSize = A.heapSize - 1;

      return 1;
        
      
   
      //return A[]
   }

   private int heapSize; //the size of the heap: heapSize <= n
   private int A[]; //the array
   private int n; //the array size: heapSize <= n
}
