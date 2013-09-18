// Heapsort combines merge sort and insert sort features.
//1)  Heapsort's running time is O(nlgn). This is like merge sort.
//2)  Heapsort runs in place: only a constant number of array elements are stored outside
//    the input array at any time(what is that mean??). This is like insert sort.

// -  The heap data structure is an array object that we can view as a nearly complete binary tree. 
// -  Each node of the tree corresponds to an element of the array. An array A that represents a heap
//    is an object with two attributes: 1) A.length, which is the number of elements in the array.
//    2) A.heap-size, which represents how many elements in the heap are stored within array A. That is
//    Although A[1..A.length] may contain numbers, only the elements in A[1..A.heap-size], where 0 <= A.heap-size <= A.length 
//    are valid elements of the heap.
//
// -  Viewing a heap as a tree, we define the height of a node in a heap to be the number of edges on the longest simple downward path from
//    the node to a leaf, and we define the height f the heap to be the height of its root. Since a heap of n elements is based on a complete
//    binary tree, its height is O(lgn).
//
//    PARENT(i):  return floor(i/2);
//    LEFT(i):    return 2i
//    RIGHT(i):   return 2i+1
//    
//    Note: the above folumars are with index i starts from 1. For index starting from 0. We have:
//    PARENT(i):  return (i-1)/2; (i > 0)
//    LEFT(i):    return (i*2+1);
//    RIGHT(i):   return (i*2+2);
//
// -  There are two kinds of binary heaps:
//    1) max-heap: A[PARENT(i)] >= A[i] -> The largest element at the root. We use max-heap as the heap sort algorithm.
//    2) min-heap: A[PARENT(i)] <= A[i] -> The smallest element at the root. min-heap is usually implemented in priority queue.
//    
// -  The MAX-HEAPIFY procedure, which runs in O(lgn) time. The remainder of this chapter present some basic procedures and slows how they are 
//    used in sorting algorithm and a priority-queue data structure.
//    
//    -  The MAX-HEAPIFY procedure, which runs in O(lgn) time, is the key to maintaining the max-heap property.
//    -  The BUILD-MAX-HEAP procedure, which runs in linear time, produces a max heap from unordered input array.
//    -  The HEAPSORT procedure, which runs in O(nlgn) time, sorts an array in place.
//    -  The MAX-HEAP-INSERT, HEAP-EXTRACT-MAX, HEAP-INCREASE-KEY, and HEAP-MAXIMUM procedures, which run in O(lgn) time, allow the heap data structure
//       to implement a priority queue.

import java.util.*;
import java.lang.Integer;


/** Heapsort implementation (using max-heap) */
//  
//
class HeapTree {
   
   private int[] A;          //the heap array.
   private int n;          //the size of the array.
   private int heapSize;   //the heap size. Noted that 0<=heapSize<=size.

   /* The constructor */
   public HeapTree() {
      n = heapSize = 0;
   }
   
   /* The second constructor */
   public HeapTree(int[] array) {
      //
      A = new int[array.length];
      System.arraycopy(array, 0, A, 0, array.length); //!! Using the System.arraycopy method.
      n = heapSize = A.length;
   }

   public int left(int i) {
      //assert i < n/2 : "Position has no left child"; //?? why i < n/2
      if ( i < heapSize/2) {
         return i*2+1;
      } else {
         return -1;
      }
   }

   public int right(int i) {
      //assert i < (n-1)/2 : "Position has no right child"; //?? why i < (n-1)/2
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
   //  of height as O(h)(??).
   void maxheapify(int i) {
      int l = left(i);
      int r = right(i);
      int largest = i;
   
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
         int temp = A[largest];
         A[largest] = A[i];
         A[i] = temp;
         maxheapify(largest); //Recurssion occurs here
      }
   }

   /**  The second version of maxheapify using an iterative control construct (a loop) instead of recursion */
   void maxheapify2(int i) {
      int largest = i;
      int l;
      int r;
      int current;

      do {
         current = largest;
         l = left(current);
         r = right(current);
         
         if (l != -1) {
            if(l <= heapSize && A[l] > A[largest]) {
               largest = l;
            }
         }

         if (r != -1) {
            if(r <= heapSize && A[r] > A[largest]) {
               largest = r;
            }
         }
      } while (largest != current);
   }

   void minheapify(int i) {
      int l = left(i);
      int r = right(i);
      int smallest = i;
   
      if ( l != -1) {
         if(l <= heapSize && A[l] < A[i]) {
            smallest = l;
         } else {
            smallest = i;
         }
      }

      if ( r != -1) {
         if (r <= heapSize && A[r] < A[smallest]) {
            smallest = r;
         }
      }

      if (smallest != i) {
         int temp = A[smallest];
         A[smallest] = A[i];
         A[i] = temp;
         minheapify(smallest);
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

      for (int i = n/2-1; i >=0; i--) {
         maxheapify(i);
      }
   }

   /** Since the maximum element of the array is stored at the root A[0], we can put it into its correct final position */
   //  by exchanging it with A[n-1], where n = A.length. This the new root element might violate the children of the max-heap
   //  property. The way to resolve this issue is following:
   //  Step1: exchange A[0] with A[i] (i = n-1 before the interations begin)
   //  Step2: heapsize = heapsize - 1; (the heapsize=n before the iterations begin)
   //  Step3: max-heapify(1) (remember that the calculation of max-heapify is based on heapsize not n)
   //  Step4: decrement i
   //  Repeat Step 1~3 until i < 1

   void heapsort() {
      buildmaxheap();
      for (int i = n-1; i > 0; i--) {

         int temp = A[0];
         A[0] = A[i];
         A[i] = temp;

         heapSize--;
         maxheapify(0);
      }
   }

   void showArray() {
      for (int i = 0; i < A.length; i++) {
         if (i+1 == A.length) {
            System.out.println(A[i]);
         } else {
            System.out.print( A[i] + ", ");
         }
      }
   }

   public static void main(String[] args) {
      /* Test the heapsort algorithm */

      //int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
      int[] A = new int[]{5, 13, 2, 25, 7, 17, 20, 8, 4};
      HeapTree ht = new HeapTree(A);
      ht.heapsort();
      ht.showArray();

      return;
   }
}
