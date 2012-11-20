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
   while(queue_p->front)
   {
      queue_p->rear = queue_p->front->next;
      free(queue_p->front);
      queue_p->front = queue_p->rear;
   }

   return OK;
}

Status EnQueue(LinkQueue *queue_p, ElemType elem)
{
   QNode *node_p = (QNode)malloc(sizeof(QNode));
   if (node_p == NULL)
   {
      printf("Failed to create a new node!\n");
      return OVERFLOW;
   }
   else
   {
      queue_p->rear->next = node_p;
      node_p->data = elem;
      node_p->next = NULL;
      queue_p->rear = node_p;
      
      return OK;
   }
}

Status DeQueue(LinkQueue *queue_p, ElemType, *elem_p)
{
   /* If the queue is not empty */
   if (queue_p->front != queue_p->rear)
   {
      QNode *node_p = queue_p->front;
      queue_p->front = node_p->next;
      *(elem_p) = node_p->data;
      free(node_p);

      return OK;
      
   }
   else
   {
      return ERROR;
   }
}

Status QueueEmpty(LinkQueue queue)
{
   if(queue.front == NULL)
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
   QNode *node_p;
   while()
   
}
