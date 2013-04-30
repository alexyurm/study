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
//-   派生类构造函数一般形式:
//
//    派生类构造函数名(总参数列表):基类构造函数名(参数列表)
//    {
//       派生类中新增数据成员初始化语句
//    }
//
//-   类的数据成员中还可以包括类对象
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

   Student(int num, string name, char sex) 
   {
      this->num = num;
      this->name = name;
      this->sex = sex;
   }

   Student() 
   {
      this->num = 0;
      this->name = "";
      this->sex = 0;
   }

   private:
   int num;
   string name;
   char sex;
};

class Student1 : public Student
{
   public:
   
   void display()
   {
      cout<<"age: "<<age<<endl;
      cout<<"address: "<<addr<<endl;
   }
   
   void display_1()
   {
      cout<<"age: "<<age<<endl;
      cout<<"address: "<<addr<<endl;
   }

   Student1(int num, string name, char sex, int age, string addr) : Student(num, name, sex)
   {
      this->age = age;
      this->addr = addr;
   }

   Student1() : Student()
   {
      this->age = 0;
      this->addr = "";
   }

   Student1(int n, string nam, char s, int n1, string nam1, char s1, int a, string ad) : Student(n, nam, s), monitor(n1, nam1, s1)
   {
      this->age = a;
      this->addr = ad;
   }

   private:
   int age;
   string addr;
   Student monitor; //子对象
};

int main()
{
   Student1 st1(7, "Alex", 'f', 30, "3048 59th E Avenue");
   st1.display();
   return 0;
}
