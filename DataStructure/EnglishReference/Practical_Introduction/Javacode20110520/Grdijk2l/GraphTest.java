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

/** Dijkstra's shortest-paths: priority queue version */
static void Dijkstra(Graph G, int s, int[] D) {
  int v;                              // The current vertex
  DijkElem[] E = new DijkElem[G.e()]; // Heap for edges
  E[0] = new DijkElem(s, 0);          // Initial vertex
  MinHeap<DijkElem> H = new MinHeap<DijkElem>(E, 1, G.e());
  for (int i=0; i<G.n(); i++)         // Initialize distance
    D[i] = Integer.MAX_VALUE;
  D[s] = 0;
  for (int i=0; i<G.n(); i++) {       // For each vertex
    do { v = (H.removemin()).vertex(); }    // Get position
      while (G.getMark(v) == VISITED);
    G.setMark(v, VISITED);
    if (D[v] == Integer.MAX_VALUE) return;  // Unreachable
    for (int w = G.first(v); w < G.n(); w = G.next(v, w))
      if (D[w] > (D[v] + G.weight(v, w))) { // Update D
        D[w] = D[v] + G.weight(v, w);
        H.insert(new DijkElem(w, D[w]));
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
    Graph G = new Graphl();
    createGraph(f, G);
    Dijkstra(G, 0, D);
    for (int i=0; i< G.n(); i++) out.append(D[i] + " ");
    assertEquals(out.toString(), "0 5 3 10 18 ");
  }

}
