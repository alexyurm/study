#include <iostream>
#include <string>
#include <memory>
using namespace std;

/*  
*  std::auto_ptr can be used to manage the memory of a single object, but we should pay attention to the following:
*
*  1) Try NOT to use the operator '='. If it was used, don't use the previous auto_ptr object anymore. See the example: TestAutoPtr2() 
*  2) The function release() does not release the internal object's memory, it is just return its management ownership of that memory.
*  3) std:auto_ptr 
*
*
*
*
*
*
*
*/

class Simple 
{
   public:
      Simple(int param = 0)
      {
         number = param;
         std::cout<<"Simple:"<<number<<std::endl;
      }

      ~Simple()
      {
         std::cout<<"~Simple:"<<number<<std::endl;
      }

      void PrintSomething()
      {
         std::cout<<"PrintSomething:"<<info_extend.c_str()<<std::endl;
      }
   
      std::string info_extend;
      int number;
};

void TestAutoPtr()
{
   std::auto_ptr<Simple>my_memory(new Simple(1)); //Create an object "my_memory", output as Simple:1
   if(my_memory.get()) //Is the pointer empty?
   {
      my_memory->PrintSomething(); //Use operator-> to call the PrintSomething function.
      my_memory.get()->info_extend = "Addition"; //Use get() to return the "raw" pointer. Change the value of info_extend;
      my_memory->PrintSomething(); //Print again. Make sure info_extend is changed.
      (*my_memory).info_extend += "other"; //Use operator* to get the object pointed by my_memory. Use '.' to change info_extend
      my_memory->PrintSomething(); //Print again. Make sure info_extend is changed.
   }
}

void TestAutoPtr2()
{
   std::auto_ptr<Simple>my_memory(new Simple(1));
   if (my_memory.get())
   {
      Simple* temp_memory = my_memory.release(); //You should notice that the destructor is not called.
   }
}

void TestAutoPtr3()
{
   std::auto_ptr<Simple>my_memory(new Simple(1));
   if (my_memory.get())
   {
      Simple* temp_memory = my_memory.release();
      delete temp_memory; //Why we need this line? Simply calling my_memory.release() does not call the destructor
   }
}

void TestAutoPtr4()
{
   std::auto_ptr<Simple>my_memory(new Simple(1));

   my_memory.reset();//release the internal memory managed by my_memory
}

int main()
{
   TestAutoPtr3();
   return 0;
}
