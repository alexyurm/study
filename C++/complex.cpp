#include <iostream>
using namespace std;

//运算符重载
//- 运算符重载实际是函数重载
//- 格式: 函数类型 operator 运算符名称 (形参列表){对运算的的重载处理}
//- 运算符重载既可以声明为类成员函数，也可以成名为友元函数.
class Complex
{
   public:
   Complex() {real = 0; imag = 0;}
   Complex(double r, double i) {real = r; imag = i;}
   Complex complex_add(Complex &c2);
   //Complex operator + (Complex &c2);
   friend Complex operator + (Complex &c1, Complex &c2);
   void display();

   private:
   double real;
   double imag;
};

Complex Complex::complex_add(Complex &c2)
{
   Complex c;
   c.real = real + c2.real;
   c.imag = imag + c2.imag;
   return c;
}

/*
Complex Complex::operator + (Complex &c2)
{
   Complex c;
   c.real = real + c2.real;
   c.imag = imag + c2.imag;
   return c;
}
*/

void Complex::display()
{
   cout<<"("<<real<<","<<imag<<"i)"<<endl;
}

Complex operator + (Complex &c1, Complex &c2)
{
   return Complex(c1.real+c2.real, c1.imag+c2.imag);
}

int main()
{
   Complex c1(3,4), c2(5,-10),c3;
   c3 = c1 + c2;
   c3.display();
   
   return 0;
}
