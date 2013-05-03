-  Question 1: What is the result of the following subtraction calculation?

   stack_p->top_p - stack_p->base_p >= stack_p->stacksize

   where top_p is a int pointer and base_p is also a int pointer.

-  Question 2: What is the following:

   #if defined(BFC_INCLUDE_ITC_SUPPORT)

   it only checks whether the flag is defined or not. It doesn't matter what value the flag is set if it is defined.

-  What is the difference between 

   struct Foo {...};

   and 

   typedef struct {...} Foo;

   In C, there are two different namespaces of types: a namespace of struct/union/enum tag names and a namespace of typedef names

   Note: I don't know C has namespaces. Better to look it up.

   You will get errors with the following statements:

   struct Foo { ... };
   Foo x;

   Foo is only defined in the tag namespace therefore you have to define:

   struct Foo x;

   Anytime you want to refer to a Foo, you'd always have to call it a struct Foo. This gets annoying fast, so you can add a typedef:

   struct Foo {...};
   typedef struct Foo Foo;

   or

   typedef struct Foo {...}Foo;

   Now both struct Foo (in the tag namespace) and just plain Foo (in the typedef namespace) both refer to the samething. Now you can freely declare objects with of type Foo
   without the struct keyword.

   The construct

   typedef struct {...} Foo;

   declares an anonymous structure and creates a typedef for it. Thus with this construct, it doesn't have a name in the tag namespace, only a name in the typedef namespaces.

   This means it also can't be forward-declared. 

   Note: What is forward declaration?

   In Computer programming, a forward declaration is a declaration of an identifier (denoting an entity such as a type, a variable, or a function) for which programmer has not yet
   given a complete definition.

   In C++, all struct/union/enum/class declarations act like they are implicitly typedef'ed, as long as the name is not hidden by another declaration with the same name. 
   See Michael Burr's answer for the full details.

   Why the following code gives me the warning "warning: assignment from incompatible pointer type [enabled by default]".

   typedef struct
   {
      ElemType data;
      struct QNode *next_p;
   }QNode;

   typedef struct
   {
      QNode *front_p;
      QNode *rear_p;
   }LinkQueue;

   int main()
   {
      ...
      queue_p->front_p->next_p = (QNode*)malloc(sizeof(QNode));
      return 0;
   }

   It seems to me that next_p is not refered to type QNode as there is no QNode in the tag domain. If I do:

   typedef struct QNode
   {
      ElemType data;
      struct QNode *next_p;
   }QNode;

   The warning is gone. 

-  What does const mean here?

   float getX() const {return x;}

   这个是常成员函数
