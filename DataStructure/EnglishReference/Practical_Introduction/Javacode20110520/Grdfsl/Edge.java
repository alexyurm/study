/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Edge class for Adjacency List graph representation */
class Edge {
  private int vert, wt;

  public Edge(int v, int w) // Constructor
    { vert = v;  wt = w; }

  public int vertex() { return vert; }
  public int weight() { return wt; }
}
