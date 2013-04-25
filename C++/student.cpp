#include <iostream>
using namespace std;

//-   声明派生类的一般形式:
//    class 派生类名: [继承方式] 基类名
//    {
//       派生类新增的成员
//    }
//
//-   对基类的继承方式有三种:
//    1) 公用继承: (public inheritance)
//    基类的公用成员和保护成员在派生类中保持原有访问属性，其私有成员仍为基类所有.
//
//    2) 私有继承: (private inheritance)
//    基类的公用成员和保护成员在派生类中成了私有成员. 其私有成员仍为基类所有.
//    
//    3) 受保护继承(protected inheritance)
//    基类的公用成员和保护成员在派生类中成了保护成员，其私有成员仍爲基类所有.
//
//    注: 保护成员: 不能被外界引用，但可以被派生类的成员引用。
//    
//

class Student
{
   public:
   void display()
   {
      cout<<"num: "<<num<<endl;
      cout<<"name: "<<name<<endl;
      cout<<"sex: "<<sex<<endl;
   }
   private:
   int num;
   string name;
   char sex;
};

class Student1 : public Student
{
   public:
   void display_1()
   {
      cout<<"age: "<<age<<endl;
      cout<<"address: "<<addr<<endl;
   }
   private:
   int age;
   string addr;
};

int main()
{
   Student1 st1;
   st1.display();
   return 0;
   
}
