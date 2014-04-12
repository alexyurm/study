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

   Foo is only defined in the struct tag namespace therefore you have to define:

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

   This means it also can't be forward-declared??

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

-  Why segmentation happen in the following function:
   
   #include <stdlib.h>
   #include <stdio.h>

   void func(int* A)
   {
      *A = 10;
   }

   int main()
   {
      int* a_p;
      func(a_p); 

      //int a;
      //func(&a);
   }

-  Very interesting declaration:

    ssize_t putValue(int fd, T& buf, long timeout = MP_DEFAULT_TIMEOUT) {
        return writen(fd, &buf, sizeof (T), timeout);
    }

    when passing the value to putValue, we can do this:

    putValue<int> (fd, freq)

-   Interesting...(??):

    XMLPUBFUN xmlChar * XMLCALL	
		xmlNodeListGetString	(xmlDocPtr doc,
					 xmlNodePtr list,
					 int inLine);

    Note: #define XMLPUBFUN
          #define XMLCALL

-   Difference between global variables and static variable:

    There are two separate concepts here:

    -   scope, which determines where a name can be accessed, and
    -   storage duration, which determines when a variable is created and destroyed.

    Global variables (pedantically, variables with file scope (in C) or namespace scope (in C++)) are accessible at any point after their declaration:

    Static variables 

    Static variables (pedantically, variables with static storage duration) have a lifetime that lasts until the end of the program. If they are local 
    variables, then their value persists when execution leaves their scope.

    for (int i = 0; i < 5; ++i) {
        static int n = 0;
        printf("%d ", ++n);  // prints 1 2 3 4 5  - the value persists
    }

    Note that the static keyword has various meanings apart from static storage duration. On a global variable or function, it gives it internal linkage 
    so that it's not accessible from other translation units; on a C++ class member, it means there's one instance per class rather than one per object. 
    Also, in C++ the auto keyword no longer means automatic storage duration; it now means automatic type, deduced from the variable's initialiser.

-   How to use one line to test whether a num is pow of 2 or not

    if((num != 1) && (num & (num - 1))) { /* make num pow of 2 */ }

-   A union is a special data type available in C that enables you to store different data types in the same memory location. You can define a 
    union with many members, but only one member can contain a value at any given time. Unions provide an efficient way of using the same memory 
    location for multi-purpose.

    E.g.

    union Data
    {
       int i;
       float f;
       char  str[20];
    } data;

    Now, a variable of Data type can store an integer, a floating-point number, or a string of characters. This means that a single variable ie. same 
    memory location can be used to store multiple types of data. You can use any built-in or user defined data types inside a union based on your requirement.

    The memory occupied by a union will be large enough to hold the largest member of the union. For example, in above example Data type will occupy 20 
    bytes of memory space because this is the maximum space which can be occupied by character string. Following is the example which will display total memory 
    size occupied by the above union:

    #include <stdio.h>
    #include <string.h>
     
    union Data
    {
       int i;
       float f;
       char  str[20];
    };
     
    int main( )
    {
       union Data data;        

       data.i = 10;
       data.f = 220.5;
       strcpy( data.str, "C Programming");

       printf( "data.i : %d\n", data.i);
       printf( "data.f : %f\n", data.f);
       printf( "data.str : %s\n", data.str);

       return 0;
    }

    data.i : 1917853763
    data.f : 4122360580327794860452759994368.000000
    data.str : C Programming

    Here, we can see that values of i and f members of union got corrupted because final value assigned to the variable has occupied the memory location and 
    this is the reason that the value if str member is getting printed very well.
