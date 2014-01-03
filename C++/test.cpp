#include <iostream>
#include <string>

using namespace std;

class MyClass
{

public:
   MyClass()
   {
      cout<<"Constructor is called."<<endl;
      myStr = "";
   }

   MyClass(const MyClass& thatClass)
   {
      cout<<"Copy Constructor is called."<<endl;
      myStr = thatClass.myStr;
   }
   
   virtual ~MyClass()
   {
      cout<<"Destructor is Called."<<endl;
   }
   
   std::string setStr(std::string str)
   {
      myStr = str;
      return myStr;
   }

   std::string getStr()
   {
      return myStr;
   }

private:
   std::string myStr;
};

class testMyClass
{
   public:
      MyClass getMyClassObj()
      {
         return myObj;
      }
   private:
      MyClass myObj;
};

int main()
{
   /*
   testMyClass tmc;
   cout<<"--------------getting into getMyClassObj----------------"<<endl;
   tmc.getMyClassObj();
   cout<<"--------------getting out of getMyClassObj--------------"<<endl;
   */
   
   MyClass *myclass_p = new MyClass;
   cout<<"myclass_p = "<<myclass_p<<endl;
   //MyClass myclass;
   
   return 1;
}
