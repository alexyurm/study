#include "stack.h"

Status InitStack(SqStack *stack_p)
{
   stack_p->base_p = (ElemType *)malloc(STACK_INIT_SIZE*sizeof(ElemType));

   if (!(stack_p->base_p))
   {
      printf("Not enough heap memory to construct a new stack\n");
      return OVERFLOW;   
   }
   else
   {
      stack_p->stacksize = STACK_INIT_SIZE;
      stack_p->top_p = stack_p->base_p;
      return OK;
   }
}

Status DestroyStack(SqStack *stack_p)
{
   if ( stack_p->base_p != NULL )
   {
      free(stack_p->base_p);
      stack_p->base_p = NULL;
      stack_p->top_p = NULL;
      stack_p->stacksize = 0;
      stack_p = NULL;

      return OK;
   }
   else
   {
      return INFEASIBLE;  
   }
}

Status GetTop(SqStack stack, ElemType *elem_p)
{
   if (stack.base_p == stack.top_p)
   {
      return ERROR;
   }
   else
   {
      *elem_p = *(stack.top_p - 1);
      return OK;
   }
}

Status Push(SqStack *stack_p, ElemType elem)
{

   if ( stack_p->top_p - stack_p->base_p >= stack_p->stacksize )
   {
      /* Reallocate the memory */
      stack_p->base_p = (ElemType *)realloc( stack_p->base_p, (stack_p->stacksize + STACKINCREMENT)*sizeof(ElemType) );
      if (stack_p->base_p != NULL)
      {
         /* Successfully reallocate the memory */
         stack_p->top_p = stack_p->base_p + stack_p->stacksize * sizeof(ElemType);
         stack_p->stacksize = stack_p->stacksize + STACKINCREMENT;
         
         /* Push the element into the stack */
         *(stack_p->top_p) = elem;
         stack_p->top_p++;
         
         return OK;
      }
      else
      {
         printf("Not enough heap memory to construct a new stack\n");
         return OVERFLOW;   
      }
   }
   else
   {
      /* Push the element into the stack */
      *(stack_p->top_p) = elem;
      stack_p->top_p++;

      return OK;
   }
}

Status Pop(SqStack *stack_p, ElemType *elem_p )
{
   if ( stack_p->top_p == stack_p->base_p )
   {
      printf(" The stack is empty, nothing to pop,\n");
      return INFEASIBLE;
   }
   else
   {
      *(elem_p) = *(--stack_p->top_p);
      return OK;
   }
}

int main()
{
   Status st;
   SqStack sq;
   int i;
   
   ElemType e1 = 1;
   ElemType e2 = 100;
   ElemType e3 = -200;
   ElemType e4 = 34;

   /* Initizlize a stack */
   st = InitStack(&sq);

   printf("InitStack's status = %d\n", st);
 
   for (i = 0; i < 100; i++)
   {
      Push(&sq, e1);
      Push(&sq, e2);
      Push(&sq, e3);
      Push(&sq, e4);
   }

   printf("The size of the stack is %d\n", sq.stacksize);

   /* Destroy a stack */
   st = DestroyStack(&sq);
   printf("DestroyStack's status = %d\n", st);

   return 0;
}


