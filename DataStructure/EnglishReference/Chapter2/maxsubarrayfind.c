#include <stdio.h>
#include <stdlib.h>

typedef struct found 
{
   int low;
   int high;
   float sum;   
}found; 

void find_max_subarray_cross(float array[], int low, int mid, int high, found *found_p)
{
   float sum_left, sum_right, sum;
   int i, j, left, right;

   sum_left = sum = array[mid]+array[mid-1];
   left = mid-1;
   for (i = mid-2; i >= low; i--)
   {
      sum = sum + array[i];
      if (sum > sum_left)
      {
         sum_left = sum;
         left = i;
      }
   }

   sum_right = sum = array[mid+1];
   right = mid + 1;
   for (j = mid+2; j <= high; j++ )
   {
      sum = sum + array[j];
      if (sum >= sum_right)
      {
         sum_right = sum;
         right = j;
      }
   }

   found_p->low = left;
   found_p->high = right;
   found_p->sum = sum_left + sum_right;

   return;
}


void find_max_subarray(float array[], int low, int high, found *found_p)
{
   if (low == high)
   {
      found_p->low = found_p->high = low;
      found_p->sum = array[low];
      return;
   }
   else
   {
      int mid = (low + high) / 2;
      found found_left, found_cross, found_right;

      find_max_subarray(array, low, mid, &found_left);

      find_max_subarray_cross(array, low, mid, high, &found_cross);

      
      find_max_subarray(array, mid+1, high, &found_right);
      
      if ( found_left.sum >= found_cross.sum && found_left.sum >= found_right.sum )
      {
         found_p->low = found_left.low;
         found_p->high = found_left.high;
         found_p->sum = found_left.sum;
      }
      else if ( found_right.sum >= found_cross.sum && found_right.sum >= found_left.sum )
      {
         found_p->low = found_right.low;
         found_p->high = found_right.high;
         found_p->sum = found_right.sum;
      }
      else
      {
         found_p->low = found_cross.low;
         found_p->high = found_cross.high;
         found_p->sum = found_cross.sum;               
      }
      
      return;
   }
}

int main()
{
   float array[16] = {-3, 3, 5, -4, -2, 5, 2, -20, 2, 5, -2, -5, 7, 9, -8, 3};
   found result;
   
   find_max_subarray(array, 0, 15, &result);
   
   printf("low = %d\n", result.low);
   printf("high = %d\n", result.high);
   printf("max sum = %f\n", result.sum);

   return 0;
}
