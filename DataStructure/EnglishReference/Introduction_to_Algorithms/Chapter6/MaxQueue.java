import alex.ds.heaptree.*;

//The max-priority queue keeps track of the jobs to be performed and their relative priorities. 
//When a job is finished or interrupted, the scheduler selects the highest-priority job from
//among those pending by calling EXTRACT-MAX. The scheduler can add a new job to the queue at
//any time by calling INSERT.

class max_p_queue {//max-priority queue

   public int heapMax(int[] A) {
      return A[i];
   }
   
   public int heapExtractMax(int[] A)
   {
      if (A.heapSize < 1) {
         System.out.println("heap underflow");
      }

      int max = A[0];
      A[0] = A[A.heapSize];
      A.heapSize = A.heapSize - 1;

      return 1;   
   
      //return A[]
   }

   private int heapSize; //the size of the heap: heapSize <= n
   private int A[]; //the array
   private int n; //the array size: heapSize <= n
}
