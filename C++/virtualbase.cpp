#include <iostream>
#include <string>
using namespace std;

// -  C++ 提供需基类(virtual base class)的方法，使得在继承间接共同基类时
//    只保留一份成员. 
//
//    注意: 虚基类并不是在声明基类时声明的, 而是在声明派生类时, 指定继承方式
//          时声明的. 因为一个基类可以在生成一个派生类时作为虚基类, 而在生成
//          另一个派生类时不作为虚基类, 声明虚基类的一般方式为:
//
//          class 派生类名: virtual 继承方式 基类名
//
//          经过这样的声明后, 当基类通过多条派生路径被一个派生类继承时, 该派生类
//          只继承该基类一次, 也就是说, 基类成员只保留一次.
//
// -  虚基类的初始化
//
//    class A
//       { A(int i){}
//       ...};
//
//    class B : virtual public A
//       { B (int n): A(n){}
//       ...};
//
//    class C : virtual public A
//       { C(int n): A(n){}
//       ...};
//
//    class D : public B, public C
//       { D(int n):A(n),B(n),C(n){}
//       ...};
//
class Person
{
   public:
      Person(string nam, char s, int a)
      {
         name = nam;
         sex = s;
         age = a;
      }

   protected:
      string name;
      char sex;
      int age;
};

//声明Person的直接派生类Teacher
class Teacher:virtual public Person
{
   public:
   Teacher(string nam, char s, int a, string t) : Person(nam, s, a) //构造函数
   {
      title = t;
   }

   protected:
      string title;
};


//声明Person的直接派生类Student
class Student:virtual public Person
{
   public:
   Student(string nam, char s, int a, float sco) : Person(nam, s, a), score(sco) {}//构造函数

   protected:
      float score;
};

//声明多重继承的派生类graduate
class Graduate : public Teacher, public Student //Teacher和Student是直接基类
{
   public:
      Graduate(string nam, char s, int a, string t, float sco, float w)
      :  Person(nam, s, a), Teacher(nam, s, a, t), Student(nam, s, a, sco), wage(w){}

      void show()
      {
         cout<<"name: "<<name<<endl;
         cout<<"age: "<<age<<endl;
         cout<<"sex: "<<sex<<endl;
         cout<<"name: "<<name<<endl;
         cout<<"title: "<<title<<endl;
         cout<<"wages: "<<wage<<endl;
      }

   private:
   float wage;
};

//主函数
int main()
{
   Graduate grad1("Wang Li", 'f', 24, "assistant", 89.5, 1234.5);
   grad1.show();
   return 0;
}
