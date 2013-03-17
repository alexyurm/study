/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

class TOHobj {
  public operation op;
  public int num;
  public Pole start, goal, temp;

  /** Recursive call operation */
  TOHobj(operation o, int n, Pole s, Pole g, Pole t)
  { op = o; num = n; start = s; goal = g; temp = t; }

  /** MOVE operation */
  TOHobj(operation o, Pole s, Pole g)
  { op = o; start = s; goal = g; }
}
