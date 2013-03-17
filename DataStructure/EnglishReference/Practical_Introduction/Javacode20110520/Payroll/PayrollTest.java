/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A JUnit test class for the payroll dictionary. */

import java.io.*;

public class PayrollTest
    extends junit.framework.TestCase
{

  /**
   * This method is automatically called once before each test case method,
   * so that all the variables are cleanly initialized for each test.
   */
  public void setUp()
  {
  }

  /** Test syntax of what appears in the book */
  public void testBook()
  {
// IDdict organizes Payroll records by ID
Dictionary<Integer, Payroll> IDdict =
      new UALdictionary<Integer, Payroll>();

// namedict organizes Payroll records by name
Dictionary<String, Payroll> namedict =
      new UALdictionary<String, Payroll>();

Payroll foo1 = new Payroll(5, "Joe", "Anytown");
Payroll foo2 = new Payroll(10, "John", "Mytown");

IDdict.insert(foo1.getID(), foo1);
IDdict.insert(foo2.getID(), foo2);
namedict.insert(foo1.getname(), foo1);
namedict.insert(foo2.getname(), foo2);

Payroll findfoo1 = IDdict.find(5);
Payroll findfoo2 = namedict.find("John");
  }

  /** Here is an actual test to verify that the dictionary works. */
  public void testPayroll()
  {
    // IDdict organizes Payroll records by ID
    Dictionary<Integer, Payroll> IDdict =
         new UALdictionary<Integer, Payroll>();

    // namedict organizes Payroll records by name
    Dictionary<String, Payroll> namedict =
         new UALdictionary<String, Payroll>();

    Payroll foo1 = new Payroll(5, "Joe", "Anytown");
    Payroll foo2 = new Payroll(10, "John", "Mytown");

    IDdict.insert(foo1.getID(), foo1);
    IDdict.insert(foo2.getID(), foo2);
    namedict.insert(foo1.getname(), foo1);
    namedict.insert(foo2.getname(), foo2);

    Payroll findfoo1 = IDdict.find(5);
    assertEquals(findfoo1.getname(),"Joe");
    Payroll findfoo2 = namedict.find("John");
    assertEquals(findfoo2.getID(),new Integer(10));
  }
}
