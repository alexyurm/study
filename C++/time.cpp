#include <iostream>
using namespace std;


class Date;
class Time
{
   public:
      Time(int h = 7, int m = 7, int s = 7); //Constructor(The member vars are given)
      ~Time(); //Destructor
      void set_time();
      void show_time();
      void display_time() const; //??为什么声明为 const?
      static void show_area();
      friend void show_hour(Time &t); //友元函数: 外界可以任意调用
      void display_date(Date &);
      
   private:
      int hour;
      int minute;
      int second;
      static int area;
};

Time::~Time()
{
   cout<<"Time: Destructor called"<<endl;
}

Time::Time(int h, int m, int s)
{
   hour = h;
   minute = m;
   second = s;
}

void Time::show_time()
{
   cout<<hour<<":"<<minute<<":"<<second<<endl;
}

void Time::display_time() const
{
   cout<<hour<<":"<<minute<<":"<<second<<endl;
}

void Time::set_time()
{
   cin>>hour;
   cin>>minute;
   cin>>second;  
}

int Time::area = 11;

void Time::show_area()
{
   cout<<"area = "<<Time::area<<endl;
}

void show_hour(Time &t)
{
   cout<<t.hour<<endl;
}

class Date
{
   public:
      Date(int y = 1991, int m = 1, int d = 1); //Constructor
      ~Date();
      friend void Time::display_date(Date &); //声明为Time的友元函数, 只能被Time对象调用
   private:
      int year;
      int month;
      int day;
};

Date::Date(int y, int m, int d){
   year = y;
   month = m;
   day = d;
}

Date::~Date()
{
   cout<<"Date: Dectructor called"<<endl;
}

void Time::display_date(Date &d)
{
   cout<<d.year<<":"<<d.month<<":"<<d.day<<endl;
}

// 类模板应用: template <class 类型参数名>
// 注意: 类声明外定义类模板跟一般类定义成员函数不一样
// number Compare::max(){...} //不能这样定义类模板中的成员函数
// 应当写成以下形式
// template <class numtype>
// numtype Compare <numtype> :: max()
// {return (x>y)?x:y;}
// 用类模板定义对象时用以下格式:
//  类模板名 <实际类型名> 对象名;
//  类模板名 <实际类型名> 对象名(实参列表);
//  Compare<int>cmp;
//  Compare<int>cmp(3,7);

template <class numtype> //<<-----Add this line before define class Compare
class Compare
{
   public:
      Compare(numtype a = 10, numtype b = 10)
      {
         x = a;
         y = b;
      }
      
      numtype max()
      {
         return (x>y)? x : y;
      }
      
      numtype min()
      {
         return (x<y)? x:y;
      }

      numtype min2();

   private:
      numtype x;
      numtype y;
};

template <class numtype> //<<-----Add this line before define class Compare
numtype Compare<numtype>:: min2()
{
   return (x<y)? x:y;
}

int main()
{
   //Example showing how setup a pointer pointing to a class function
   //DataType (Classname:: *var_p) (parameter list) E.g. void(Time:: *p)();
   //var_p = &Classname::function
   Time t1;
   void (Time:: *p)();
   p = &Time::show_time;
   (t1.*p)();

   //Const object: Classname const obj(parameter list). This object cannot be changed.
   //Const object cannot call non-const class function. E.g. t2.show_time(); If we define
   //void Show_time const, then we can make a call t2.show_time();
   Time const t2(11, 12, 13);
   t2.display_time();
   
   //const pointer. Increase security.
   Time * const ptr = &t1;

   Time t3(14, 15, 16);
   show_hour(t3);

   Date d1;
   t3.display_date(d1);

   //定义指向常变量的指针变量的一般形式:
   //const 类型名 *指针变量名;

   //常变量: const 类名 变量名 
   //常引用: const 类名 &引用名
   //常对象: 类型 const 对象名 -> 防止引用变量的值改变
   //常成员函数 类型: func(形参)const, 常成员函数不能更新对象的数据成员
   //常数组: 类型 const 数组名[大小]
   //常指针: const 类型 *指针名, 类型 *const 指针名
   //1)指向const类型的指针 跟 2)const指针的区别
   //1)该指针能够指向const类也能够指向非const类，当它指向const类时，不能用它改变它的指向值
   //如何记忆呢，关键是以*为分界符，其后的const修饰指针，其前的const修饰指针指向的内容。可以记忆为离谁近就是修饰谁

   //
   int a=10, b=12;
   float c=18.3, d=12.4;
   Compare <int>cmp1(a,b);
   Compare <float>cmp2(c,d);
   cout<<cmp1.max()<<endl;
   cout<<cmp2.min()<<endl;
   cout<<cmp2.min2()<<endl;

   Compare<int> cmp3;

   return 0;
}
   
