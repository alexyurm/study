#include "header.h"

#define  STACK_INIT_SIZE   100
#define  STACKINCREMENT    10

typedef struct
{
   ElemType  *base_p;
   ElemType  *top_p;
   int   stacksize;
}SqStack;

/* Initialize an empty stack */
Status InitStack(SqStack *stack_p);

/* Destroy a stack */
Status DestroyStack(SqStack  *stack_p);

/* Clear the content of a stack */
Status ClearStack(SqStack *stack_p);

/* The stack is empty? */
Status StackEmpty(SqStack stack);

/* The length of a stack */
Status StackLength(SqStack stack);

/* Get the top element of a stack (if it is not empty)*/
Status GetTop(SqStack stack, ElemType *elem_p);

/* Push an element into a stack */
Status Push(SqStack *stack_p, ElemType elem);

/* Pop the top element of a stack */
Status Pop(SqStack *stack_p, ElemType *elem_p);

/* From the base to the top, every element is called by visit(). If visit() returns error */
/* this function returns error */
Status StackTraverse(SqStack stack, Status(*visit )() );
















  



