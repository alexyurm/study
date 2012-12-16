#include <stdlib.h> 
#include <stdlib.h>
#include "queue.h"

int main()
{
   LinkQueue queue;
   int status, i;
   ElemType elem;

   /* Initialize a queue */
   status = InitQueue(&queue);
   if (status != OK)
   {
      printf("Failed to initialize a queue\n");
   }

   /* Add some elements into the queue */
   for (i = 0; i < 5; i++)
   {
      status = EnQueue(&queue, i);
      if ( status != OK )
      {
         printf("Enqueue failed!\n");
         exit(1);
      }
   }

   printf("The queue length is %d \n", QueueLength(queue));

   /* Dequeue all the elements */   
   for (i = 0; i < 5; i++)
   {
      status = DeQueue(&queue, &elem);
      if ( status != OK )
      {
         printf("Dequeue failed!\n");
         exit(1);
      }
   }

   printf("The queue length is %d \n", QueueLength(queue));

   /* Destroy a queue */
   status = DestroyQueue(&queue);
   if ( status != OK )
   {
      printf("Destroy failed!\n");
      exit(1);
   }

   return 0;
}
