/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.lang.Comparable;

class DijkElem implements Comparable<DijkElem> {
  private int vertex;
  private int weight;

  public DijkElem(int inv, int inw)
    { vertex = inv; weight = inw; }
  public DijkElem() {vertex = 0; weight = 0; }

  public int key() { return weight; }
  public int vertex() { return vertex; }
  public int compareTo(DijkElem that) {
    if (weight < that.key()) return -1;
    else if  (weight == that.key()) return 0;
    else return 1;
  }
}
