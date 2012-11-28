#include "header.h"

typedef struct
{
   ElemType data;
   struct QNode *next;        
}QNode, *QueuePtr;

typedef struct
{
   QueuePtr front;
   QueuePtr rear;
}LinkQueue;

/* Initialize an empty queue */
Status InitQueue(LinkQueue *queue_p);

/* Destroy a queue */
Status DestroyStack(LinkQueue *queue_p);

/* Clear the content of a queue */
Status ClearQueue(LinkQueue *queue_p);

/* The queue is empty? */
Status QueueEmpty(LinkQueue queue);

/* The length of a queue */
Status QueueLength(LinkQueue queue);

/* Get the head element of a queue (if it is not empty)*/
Status GetHead(LinkQueue queue, ElemType *elem_p);

/* Insert an element into a queue */
Status EnQueue(LinkQueue *quque_p, ElemType elem);

/* Dequeue the an element from a queue */
Status DeQueue(LinkQueue *queue_p, ElemType *elem_p);

/* From the front to the rear, every element is called by visit(). If visit() returns error */
/* this function returns error */
Status QueueTraverse(LinkQueue queue, Status(*visit )() );

