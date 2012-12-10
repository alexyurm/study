#include <stdlib.h> 
#include <stdlib.h>
#include "queue.h"

int main()
{
   LinkQueue queue;
   int status;
   int i;

   /* Initialize a queue */
   status = InitQueue(&queue);
   if (status != OK)
   {
      printf("Failed to initialize a queue\n");
   }

   /* Add some elements into the queue */
   EnQueue(&queue, 1);

   return 0;
}
