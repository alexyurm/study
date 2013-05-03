#include <iostream>
#include <string>

using namespace std;

//-   poly:很多; morph(形态)
//
//-   C++的多态指的是具有不同功能的函数可以用同一个函数名，这样就可以用一个函数名调用不同内容的函数.
//
//-   在面向对象方法中一般是这样表述多态性的: 向不同的对象发送同一个消息，不同的对象在接收时会产生不同
//    的行为(即方法). 所谓消息, 就是调用函数, 不同的行为即不同的实现，即执行不同的函数.
//
//-   从系统实现的角度来看, 多态性分为两类: 静态多态性能和动态多态性:
//
//    1) 静态多态性: 程序编译时就能决定调用哪个函数: 例如函数重载和运算符重载
//
//    2) 动态多态性: 在程序运行过程中才动态地确定操作所针对的对象. 它又称运行时多态性, 它是通过虚函数实现.
//
//-   虚函数
//
//    虚函数的作用就是允许在派生类中重新定义与基类同名的函数, 并且可以通过基类指针或引用来访问基类和派生类中的同名函数
//    
//    通过虚函数与指向基类对象的指针变量的配合使用, 就能方便地调用统一类族中不同类的同名函数, 只要先用基类指针指向即可
//
//    专业人员一般都习惯声明虚析构函数, 即使类不需要析构函数, 也显式地定义一个函数体为空的虚析构函数, 以保证在撤销动态
//    分配空间时能得到正确的处理
//
//-   纯虚函数
//
//    virtual 函数类型 函数名 (参数列表) = 0;
//
//    纯虚函数的作用是在基类中为其派生类保留一个函数的名字, 以便派生类根据需要时对它进行定义. 如果在基类中没有保留函数名字,
//    则无法实现多态性 (如下面例子中Point类型的area函数)
//
//-   抽象基类
//
//    -  在实际应用中往往有一些类不用来生成对象. 定义这些类的唯一目的是用它作为基类去建立派生类.
//
//    -  一个基类如果包含一个或一个以上纯虚函数, 就是抽象基类. 抽象基类不能也不必要定义对象
//
//
// 

class Shape
{
   public:
   virtual float area() const{ return 0.0; }
   virtual float volumn() const{ return 0.0; } 
   virtual void shapeName() const = 0;
};

class Point : public Shape
{
   public:
   Point(float x = 0, float y = 0); //带默认参数的构造函数
   void setPoint(float, float);
   float getX() const {return x;} //常成员函数, 只允许函数引用类中的数据而不允许修改它
   float getY() const {return y;} //常成员函数, 只允许函数引用类中的数据而不允许修改它
   virtual void shapeName() const;
   friend ostream & operator <<(ostream &, const Point &); //重载运算符"<<"

   protected:
   float x, y;
};

void Point::shapeName() const
{
   cout<<"Point"<<endl;
}

Point::Point(float a, float b)
{
   x = a;
   y = b;
}

void Point::setPoint(float a, float b)
{
   x = a;
   y = b;
}

ostream & operator <<(ostream &output, const Point &p)
{
   output<<"["<<p.x<<","<<p.y<<"]"<<endl;
   return output;
}


class Circle : public Point
{
   public:
      Circle(float x = 0, float y = 0, float r = 0);
      virtual void shapeName() const;
      void setRadius(float);
      float getRadius() const;
      virtual float area() const; //计算圆面积
      friend ostream & operator << (ostream &, const Circle &);
   protected:
      float radius;
};

void Circle::shapeName() const
{
   cout<<"Circle"<<endl;
}

void Circle::setRadius(float r)
{
   radius = r;
}

//构造函数
Circle::Circle(float a, float b, float r):Point(a, b), radius(r){}

float Circle::getRadius() const
{
   return radius;
}

float Circle::area() const
{
   return 3.14159 * radius * radius;
}

ostream & operator <<(ostream &output, const Circle &c)
{
   output<<"Center = ["<<c.x<<","<<c.y<<"], r = "<<c.radius<<", area = "<<c.area()<<endl;
   return output;
}

class Cylinder:public Circle
{
   public:
   
   Cylinder (float x = 0, float y = 0, float r = 0, float h = 0 );
   virtual void shapeName() const;
   void setHeight(float);
   float getHeight() const;
   virtual float area() const;
   float volumn() const;
   friend ostream& operator << (ostream&, const Cylinder&);

   protected:
   
   float height;
};

void Cylinder::shapeName() const
{
   cout<<"Cylinder"<<endl;
}

Cylinder::Cylinder(float x, float y, float r, float h ):Circle(x, y, r), height(h){}

void Cylinder::setHeight(float h)
{
   height = h;
}

float Cylinder::getHeight() const
{
   return height;
}

float Cylinder::area() const
{
   return 2*Circle::area() + 2*3.14159*radius*height;
}

float Cylinder::volumn() const
{
   return Circle::area()*height;
}

ostream &operator <<(ostream &output, const Cylinder &cy)
{
   output<<"Center = ["<<cy.x<<","<<cy.y<<"], r = "<<cy.radius<<" , h = " <<cy.height<<", \narea = "<<cy.area()
         <<", volumn = "<<cy.volumn()<<endl;
}

int main()
{
   Circle* c_p;
   Circle c(3.5, 4.6, 9.1);

   Cylinder cy(3.5, 4.6, 9.1, 10);

   c_p = &cy;
   cout<<c_p->area()<<endl;

   c = cy;
   cout<<c.area()<<endl;

   Shape* p_p = &cy;
   cout<<p_p->area()<<endl;

   return 0;
}
