/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// Graph operation main function.
// To use: java -ea GraphTest <graphfile>

import java.io.*;
import java.util.*;

public class GraphTest
    extends junit.framework.TestCase
{

static final int UNVISITED = 0;
static final int VISITED = 1;

// Create a graph from file
static Graph createGraph(BufferedReader file, Graph G) 
throws IOException {
  String line = null;
  StringTokenizer token;
  boolean undirected = false;
  int i, v1, v2, weight;

  assert (line = file.readLine()) != null :
         "Unable to read number of vertices";
  while(line.charAt(0) == '#')
  assert (line = file.readLine()) != null :
         "Unable to read number of vertices";
  token = new StringTokenizer(line);
  int n = Integer.parseInt(token.nextToken());
  G.Init(n);
  for (i=0; i<n; i++)
    G.setMark(i, UNVISITED);
  assert (line = file.readLine()) != null :
         "Unable to read graph type";
  if (line.charAt(0) == 'U')
    undirected = true;
  else if (line.charAt(0) == 'D')
    undirected = false;
  else assert false : "Bad graph type: " + line;

  // Read in edges
  while((line = file.readLine()) != null) {
	token = new StringTokenizer(line);
    v1 = Integer.parseInt(token.nextToken());
    v2 = Integer.parseInt(token.nextToken());
    if (token.hasMoreTokens())
      weight = Integer.parseInt(token.nextToken());
    else // No weight given -- set at 1
      weight = 1;
    G.setEdge(v1, v2, weight);
    if (undirected) // Put in edge in other direction
      G.setEdge(v2, v1, weight);
  }
  return G;
}

static void Gprint(Graph G, StringBuffer out) {
  int i, j;

  for (i=0; i<G.n(); i++) {
    for(j=0; j<G.n(); j++)
      if (G.weight(i, j) == Integer.MAX_VALUE)
        out.append("0 ");
      else
        out.append(G.weight(i, j) + " ");
  }
}


static int[] D = new int[100];
static StringBuffer out;

static void AddEdgetoMST(int v1, int v2)
  { out.append(v1 + " " + v2 + " "); }
/** Kruskal's MST algorithm */
static void Kruskal(Graph G) {
  ParPtrTree A = new ParPtrTree(G.n()); // Equivalence array
  KruskalElem[] E = new KruskalElem[G.e()]; // Minheap array
  int edgecnt = 0; // Count of edges

  for (int i=0; i<G.n(); i++)    // Put edges in the array
    for (int w = G.first(i); w < G.n(); w = G.next(i, w))
      E[edgecnt++] = new KruskalElem(G.weight(i, w), i, w);
  MinHeap<KruskalElem> H =
              new MinHeap<KruskalElem>(E, edgecnt, edgecnt);
  int numMST = G.n();            // Initially n classes
  for (int i=0; numMST>1; i++) { // Combine equiv classes
    KruskalElem temp = H.removemin(); // Next cheapest
    int v = temp.v1();  int u = temp.v2();
    if (A.differ(v, u)) {        // If in different classes
      A.UNION(v, u);             // Combine equiv classes
      AddEdgetoMST(v, u);  // Add this edge to MST
      numMST--;                  // One less MST
    }
  }
}
  /**
   * This method is automatically called once before each test case
   * method, so that all the variables are cleanly initialized for
   * each test.
   */
  public void setUp()
  {
    out = new StringBuffer(100);
  }

  public void testGraph()
  throws IOException {
    BufferedReader f;
    f = new BufferedReader(new InputStreamReader(new FileInputStream("testfile.gph")));
    Graph G = new Graphm();
    createGraph(f, G);
    Kruskal(G);
    assertEquals(out.toString(), "5 4 3 2 2 5 2 1 0 2 ");
  }

}
