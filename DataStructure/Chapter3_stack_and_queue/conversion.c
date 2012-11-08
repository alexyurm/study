#include "stack.h"

void conversion()
{
   ElemType N;

   /* Input a number */
   printf("Please input a number:\n");
   scanf("%d", &N);
   printf("\n");
   
   /* Create a new stack */
   SqStack sqstack;
   InitStack(&sqstack);

   while(N != 0)
   {
      Push(&sqstack, N % 2);
      N = N / 2;
   }

   ElemType e;
   while (!StackEmpty(sqstack))
   {
      Pop(&sqstack, &e);
      printf("%d", e);
   }

   printf("\n");
}
