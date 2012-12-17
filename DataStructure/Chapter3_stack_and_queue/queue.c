#include "queue.h"

Status InitQueue(LinkQueue *queue_p)
{
   queue_p->front_p = queue_p->rear_p = (QNode *)malloc(sizeof(QNode));
   if (queue_p->front_p == NULL)
   {
      printf("Failed to initialize a queue!\n");
      return OVERFLOW;
   }
   else
   {
      queue_p->front_p->next_p = NULL;   
      return OK;
   }
}

Status DestroyQueue(LinkQueue *queue_p)
{
   while(queue_p->front_p->next_p)
   {
      queue_p->rear_p = queue_p->front_p->next_p;
      free(queue_p->front_p);
      queue_p->front_p = queue_p->rear_p; /* rear_p is a temporary variable here. It is not useful anymore. But does this is a good approach in terms of coding quality? */
   }

   free(queue_p->front_p);

   return OK;
}

Status EnQueue(LinkQueue *queue_p, ElemType elem)
{
   QNode *node_p = (QNode *)malloc(sizeof(QNode));
   if (node_p == NULL)
   {
      printf("Failed to create a new node!\n");
      return OVERFLOW;
   }
   else
   {
      (queue_p->rear_p->next_p) = node_p;
      node_p->data = elem;
      node_p->next_p = NULL;
      queue_p->rear_p = node_p;
      
      printf("%d\n", queue_p->rear_p->data);

      return OK;
   }
}

Status DeQueue(LinkQueue *queue_p, ElemType *elem_p)
{
   /* If the queue is not empty */
   if (queue_p->front_p != queue_p->rear_p)
   {
      QNode *node_p = queue_p->front_p->next_p;
      queue_p->front_p->next_p = node_p->next_p;
      *(elem_p) = node_p->data;
      
      printf("%d\n", *(elem_p));

      free(node_p);

      /* No more element to dequeue(or the last deququed element is the rear element), set front and rear the same */
      //if (queue_p->front_p->next_p == NULL)
      if (queue_p->rear_p == node_p)
      {
         queue_p->rear_p = queue_p->front_p;
      }

      return OK;
      
   }
   else
   {
      return ERROR;
   }
}

Status QueueEmpty(LinkQueue queue)
{
   if(queue.front_p == NULL)
   {
      return TRUE;
   }
   else
   {
      return FALSE;
   }
}

Status QueueLength(LinkQueue queue)
{
   int num = 0;
   QNode *node_p = queue.front_p->next_p;
   while(node_p != queue.rear_p->next_p)
   {
      num++;
      node_p = (node_p->next_p);  
   }

   return num;
}
