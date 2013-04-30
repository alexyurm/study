#include <cstring>
#include <iostream>
using namespace std;
//
//-   声明多重继承的方法
//
//    class D: public A, private B, protected C
//    { 类D新增加的成员}
//
//    多重继承派生类的构造函数
//
//    派生类构造函数名(总参数列表) : 基类1构造函数(参数列表), 基类2构造函数(参数列表), 基类3构造函数(参数列表)
//    {
//       派生类中新增加数据成员初始化语句
//    }



class Teacher
{
   public:
   Teacher(string nam, int a, string t)
   {
      name = nam;
      age = a;
      title = t;
   }

   void display()
   {
      cout<<"name:"<<name<<endl;
      cout<<"age:"<<age<<endl;
      cout<<"title:"<<title<<endl;
   }

   protected:
   string name;
   int age;
   string title;
};

class Student
{
   public:
   Student(string nam, char s, float sco )
   {
      name1 = nam;
      sex = s;
      score = sco;
   }
      
   void display1()
   {
      cout<<"name:"<<name1<<endl;
      cout<<"sex:"<<sex<<endl;
      cout<<"score:"<<score<<endl;
   }

   protected:
   string name1;
   char sex;
   float score;
};

class Graduate : public Teacher, public Student
{
   public:
   Graduate(string nam, int a, char s, string t, float sco, float w) :
   Teacher(nam, a, t), Student(nam, s, sco), wage(w){} //The constructor
   void show() {
      cout<<"name:"<<name<<endl;
      cout<<"age:"<<age<<endl;
      cout<<"title:"<<title<<endl;

      cout<<"sex:"<<sex<<endl;
      cout<<"score:"<<score<<endl;
      
      cout<<"wages:"<<wage<<endl;
   }
   
   private:
   float wage;
};

int main()
{
   Graduate grad1("Wang", 24, 'f', "assitant", 89.5, 1234.5);
   grad1.show();
   return 0;
}
