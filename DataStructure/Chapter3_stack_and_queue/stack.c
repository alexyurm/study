#include "stack.h"

Status InitQueue(LinkQueue *queue_p)
{
   queue_p->front = queue_p->rear = (QueuePtr)malloc(sizeof(QNode));
   if (queue_p->front == NULL)
   {
      printf("Failed to initialize a queue!\n");
      return OVERFLOW;
   }
   else
   {
      queue_p->front->next = NULL;   
      return OK;
   }
}

Status DestroyQueue(LinkQueue *queue_p)
{
   while()
}
