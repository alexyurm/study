#include <iostream>

/*
* Function templates are special functions that can operate with 
* generic types. This allows us to create a function template whose 
* functionality can be adapted to more than one type or class without 
* repeating the entire code for each type.
*
* In C++ this can be achieved using template parameters. A template 
* parameter is a special kind of parameter that can be used to pass a 
* type as argument: just like regular function parameters can be used 
* to pass values to a function, template parameters allow to pass also 
* types to a function. These function templates can use these parameters 
* as if they were any other regular type.
*
* The format for declaring function templates with type parameters is:
*
* template <class identifier> function_declaration;
* template <typename identifier> function_declaration;
*
* The only difference between both prototypes is the use of either the 
* keyword class or the keyword typename. Its use is indistinct, since 
* both expressions have exactly the same meaning and behave exactly 
* the same way.
* 
* For example, to create a template function that returns the greater 
* one of two objects we could use: 
*
* templete <class myType>
* myType GetMax (myType a, myType b) {
*  return (a>b?a:b)
* }
*
* To use the function template we use the following format for the function call:
*
* function_name <type> (parameters);
*
* For example, to call GetMax to compare two integer values of type int, we can write
*
* int x,y;
* GetMax <int> (x,y);
*
*/

//Function template
#include <iostream>
using namespace std;

template <class T>
T GetMax (T a, T b) {
  T result;
  result = (a>b)? a : b;
  return (result);
}

int main () {
  int i=5, j=6, k;
  long l=10, m=5, n;
  k=GetMax<int>(i,j);
  n=GetMax<long>(l,m);
  cout << k << endl;
  cout << n << endl;
  return 0;
}
