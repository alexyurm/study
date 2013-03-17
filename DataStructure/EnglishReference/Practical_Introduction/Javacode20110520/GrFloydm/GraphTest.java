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
/** Compute all-pairs shortest paths */
static void Floyd(Graph G, int[][] D) {
  for (int i=0; i<G.n(); i++) // Initialize D with weights
    for (int j=0; j<G.n(); j++)
      if (G.weight(i, j) != 0) D[i][j] = G.weight(i, j);
  for (int k=0; k<G.n(); k++) // Compute all k paths
    for (int i=0; i<G.n(); i++)
      for (int j=0; j<G.n(); j++)
        if ((D[i][k] != Integer.MAX_VALUE) &&
            (D[k][j] != Integer.MAX_VALUE) &&
            (D[i][j] > (D[i][k] + D[k][j])))
          D[i][j] = D[i][k] + D[k][j];
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
    int i,j;
    BufferedReader f;
    f = new BufferedReader(new InputStreamReader(new FileInputStream("testfile.gph")));
    Graph G = new Graphm();
    createGraph(f, G);
    int[][] D = new int[G.n()][G.n()];
    for (i=0; i< G.n(); i++)
      for (j=0; j< G.n(); j++)
        D[i][j] = Integer.MAX_VALUE;
    for (i=0; i< G.n(); i++)
      D[i][i] = 0;

    Floyd(G, D);
    for (i=0; i< G.n(); i++)
      for (j=0; j< G.n(); j++)
         if (D[i][j] == Integer.MAX_VALUE)
           out.append("-1 ");
         else out.append(D[i][j] + " ");
    assertEquals(out.toString(), "0 1 -1 8 4 0 -1 7 2 3 0 10 7 3 -1 0 ");
  }

}
