#include <iostream>
#include <unordered_map>
#include <map>
#include <string>
//#include <boost/unordered_map.hpp>
using namespace std;

typedef void (*StudentFunction) (void);
typedef map<string, StudentFunction> student_map;

class Student
{
   public:
   Student(string Name, char Sex, int Id)
   {
      name = Name;
      sex = Sex;
      id = Id;
   }

   void static printName()
   {
      //cout<<name<<endl;
      cout<<"blabla"<<endl;
   }

   ~Student();

   private:
      string name;
      char sex;
      int id;
};

int main()
{
   //Create a hash map to map the function name to a function.
   student_map m;
   m.insert(std::make_pair("printName", &(Student::printName)));

   (*(m["printName"]))();

   return 0;
}
