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


static StringBuffer out;

static void concom(Graph G) {
  int i;
  for (i=0; i<G.n(); i++)  // For n vertices in graph
    G.setMark(i, 0);       // Vertex i in no component
  int comp = 1;            // Current component
  for (i=0; i<G.n(); i++)
    if (G.getMark(i) == 0) // Start a new component
      DFS_component(G, i, comp++);
  for (i=0; i<G.n(); i++)
    out.append(i + " " + G.getMark(i) + " ");
}

static void DFS_component(Graph G, int v, int comp) {
  G.setMark(v, comp);
  for (int w = G.first(v); w < G.n(); w = G.next(v, w))
    if (G.getMark(w) == 0)
      DFS_component(G, w, comp);
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
    concom(G);
    assertEquals(out.toString(), "0 1 1 2 2 2 3 2 4 1 5 2 ");
  }

}
