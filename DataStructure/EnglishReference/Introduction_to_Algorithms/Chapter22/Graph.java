/*
*  Representations of graphs
*  -  A graph G = (V,E): as 1) a collection of adjacency lists or 2)
      an adjacency matrix. Either way applies to both directed and un
      directed graphs.
*
*  -  sparse graphs: those for which |E| is much less than |V|.^2.
*     dense graphs: |E| is close to |V|.^2. or when we need to be able to tell
*                    quickly if there is an edge connecting two given vertices(??)
*
*  -  The sum of the lengths of all the adjacency lists is |E|; If G is an undirected
*     graph, the sum of the lengths of all the adjacency lists is 2|E|.
*
*  -  The adjacency-list representation has the desirable property that the amount of memory
*     it requires is O(V+E).
*
*  -  Weight Graphs: that is, graphs for which each edge has an associated weight, typically given by
*     a weight function w: E -> R. We simply store the weight w(u,v) of the edge (u,v) belongs to E with
*     vertex v in u's adjacency list.
*
*  -  Disadvantage of adjacency-list: no quicker way to determine whether a given edge(u,v) is present in the graph
*     than to search for v in the adjacency-list.
*
*  -  Breadth-first search: one of the simplest algorithms for searching a graph and the archetype for many important graph 
*     algorithms.Prim's minimum-spanning tree algorithm and Dijkstra's single-source shortest-paths algorithm use ideas 
*     similar to those in breadth-first search.
*
*     It is named so because it expands the frontier between discovered and undiscovered vertics uniformly across the breadth of
*     the frontier. That is, the algorithm discovers all vertices at distance K from s before discovering any vertices at distance k+1(??).
*
*     To keep track of progress, breadth-first search colors each vertex white, gray, or black: 
*     1) All vertics start out white and may
*     later become gray and then black. 
*     2) A vertex is discovered the first time it is encountered during the search, at which time it becomes
*        nonwhite. Gray and black vertices, therefore, have been discovered but BFS distinguishes between them to ensure that the search proceeds
*        in a breadth-first manner(We can choose not to distinguish between them. This is fine). 
*     
*        2.1) If (u,v) belong to E and vertex u is black, then vertex v is either gray or black: that is all vertices adjacent to black vertices have been
*        discovered. 
*
*        2.2) Grey vertices may have some adjacent white vertices; they represent the frontier between discovered and undiscovered vertices. 
*
*     BFS search constructs a breadth-first tree, initially containing only its root, which is the source vertex s. Whenever the search discovers vertex u
*     , the vertex v and the edge (u,v) are added to the tree. We say that u is the predecessor or parent of v in the breadth-first tree. Since a vertex
*     is discovered at most once, it has at most one parent. Ancestor and descendant relatiohships in the breadth-first tree are defined relative to the root
*     s as usual: if u is on the simple path in the tree from the root s to vertex v, then u is an ancestor of v and v is a decendant of u. 
*
*  -  Shortest Path
*
*     (!!) Understand Lemma 22,1, Lemma 22.2, Lemma 22.3, Corollary 22.4, Theorem 22.5 and Lemma 22.6
*
*     Theorem 22.5:
*
*     Let G=(V,E) be a directed or undirected graph, and suppose that BFS is run on G from a given source vertex s(s belongs to V). Then, during
*     its execution, BFS discovers every vertex v(v belongs to V) that is reachable from the source s, and upon termination, v.d = d(s,v) for all 
*     v(v belongs to V). Moreover, for any vertex v != s, that is reachable from s, one of the shortest paths from s to v is a shortest path from
*     s to v.pre followed by the edge (v.pre, v)
*     
*     note: d(s,v) is the shortest path from s to v
*
*  -  Depth-first search 
*     
*     As its name implies, to search "deeper" in the graph whenever possible. Depth-first search explores edges out of the most recently discovered
*     vertex v that still has unexplored edges leaving it. Once all of v's edges have been explored, the search "backtracks" to explore edges leaving
*     the vertex from which v was discovered. The process continues until we have discovered all the vertices that are reachable from the original
*     source vertex. If any undiscovered vertices remain, then depth-first search selects one of them as a new source, and it repeats the search
*     from the source. The algorithm repeats this entire process until it has discovered every vertex.
*     
*     As in breadth-first search, whenever depth-first search discovers a vertex v during a scan of the adjacency list of an already discovered vertex
*     u, it records this event by setting v's predecessor attribute v.pre to u. Unlike BFS, whose predecessor subgraph forms a tree, the predecessor
*     subgraph produced by a depth-first search may be composed of several trees, because the search may repeat from multiple sources. Therefore, we
*     define the predecessor subgraph of a depth-first search slightly differently from that of a breadth-first search: we let:
*
*     Gpre = (V, Epre), where
*
*     Epre = {(v.pre, v): v belongs to V and v.pre != NIL}
*
*     The predecessor subgraph of a depth-first search forms a depth-first forest comprising several depth-first trees. The edges in Epre are tree edges.
*
*     The coloring scheme in DFS is the same as BFS.
*
*     Beside creating a depth-first forest, DFS also timestamps each vertex. Each vertex v has two timestamps: first timestamp v.d records when v
*     is first discovered (and grayed), and the second timestamp v.f records when the search finishes examining v's adjacency list (and blacken v.) These
*     timestamps provide important information about the structure of the graph and are generally helpful in reasoning about the behavior of DFS. These
*     timestamps are integers between 1 and 2|V|, since there is one discovery event and one finishing event for each of the |V| vertices. For every 
*     vertex u,
*
*     u.d < u.f 
*
*     Vertex u is WHITE before time u.d, GRAY between time u.d and time u.f, and BLACK thereafter.
*
*     (!!) Understand Theorem 22.7, Corollary 22.8, Theorem 22.9 and Theorem 22.10.
*
*     We can define four edges types in terms of the depth-first forest Gpi produced by a depth-first search in G:
*
*     1. Tree edges are edges in the depth-first forest Gpi. Edge(u,v) is a tree edge if v was first discovered by exploring edge (u,v)
*     2. Back edges are those edges(u,v) connecting a vertex v to an ancestor v in a depth-first tree. We consider self-loops, which may occur in directed
*        graphs, to be back edges. (Note: Ancestors includes predecesors)
*     3. Forward edges are those nontree edges (u,v) connecting a vertex u to a decendant v in a depth-first tree. 
*     4. Cross edges are all other edges. They can go between vertices in the same depth-first tree, as long as one vertex is not an ancestor of the other(how??), or they
*        can go between vertices in different depth-first trees. 
*
*
*
*
*
*
*/   

import java.util.*;

class Vertex{

   private int key;
   private Vertex pre;  //the predecessor
   private Color color; //the color of a node
   private int dist;    //the distance
   private int d; //the timestamp right before u is discovered(used in DFS)
   private int f; //the timestamp right after u is discovered(used in DFS)
   private LinkedList<Vertex> adj;

   Vertex() {
      key = -1;
      color = Color.WHITE; //Can I just use WHITE instead?
      dist = -1;
      adj = new LinkedList<Vertex>();
   }

   Vertex(Vertex v) {
      key = v.getKey();
      color = v.getColor();
      dist = v.getDist();
      pre = v.getPre();
      adj = v.getAdj();
   }

   Vertex(int k, Vertex p, Color c, int d, LinkedList<Vertex> a) {
      key = k;
      pre = p;
      color = c;
      dist = d;
      adj = a;
   }

   public void setKey(int k) {
      key = k;
   }

   public void setPre(Vertex p) {
      pre = p;
   }

   public void setColor(Color c) {
      color = c;
   }

   public void setDist(int d) {
      dist = d;
   }

   public void setAdj(LinkedList<Vertex> a) {
      adj = a;
   }

   public void setD(int d) {
      this.d = d;
   }

   public void setF(int f) {
      this.f = f;
   }

   public int getKey() {
      return key;
   }

   public Vertex getPre() {
      return pre;
   }

   public Color getColor() {
      return color;
   }

   public int getDist() {
      return dist;
   }

   public int getD() {
      return d;
   }

   public int getF() {
      return f;
   }

   public LinkedList<Vertex> getAdj() {
      return adj;
   }

   public void addAdjNode(Vertex n) {
      adj.add(n);
   }
}

/*
*  Assuming the graph is undirected graph and we use adjacency list to store the vettexes and edges.
*
*/

class Graph {

   private Vertex[] vs; //the vertexes 
   private Vertex s; //the source vertex
   private int time;

   Graph() {};

   Graph(Vertex[] vs) {
      this.vs = vs;
      s = vs[0]; //we set the first node as the source vertex by default.
      time = 0;
   }

   // Breadth-First-Search
   public void BFS() {
      //Step 1: Initialization works
      for(Vertex u: vs) {
         if (u == s) continue;

         u.setColor(Color.WHITE);
         u.setDist(-1);
         u.setPre(null);
      }
      s.setColor(Color.GRAY);
      s.setDist(0);
      Queue<Vertex> Q = new LinkedList<Vertex>();
      Q.add(s); //there is only one node s in the queue at the beginning.

      Vertex u;
      Vertex v;

      while(Q.peek() != null) {
         u = Q.poll(); //Retrieve one node from the queue.
         for (int i = 0; i < u.getAdj().size(); i++) {
            v = u.getAdj().get(i);
            //if v was never visited before
            if (v.getColor() == Color.WHITE) {
               v.setColor(Color.GRAY); //set the color to GRAY immediately
               v.setDist(u.getDist()+1); //set the distance: v.dist = u.dist + 1;
               v.setPre(u); //set the predecessor
               Q.add(v); //enqueue v
            }
         }
         u.setColor(Color.BLACK); //all u's adjacent nodes have been visited. Mark u as BLACK.
         System.out.println("the key = " + u.getKey() + " the distance = " + u.getDist());
      }
   }

   //print the path of each node v 
   public void printPath(Vertex v) {
      if (v == s) {
         System.out.println(s.getKey());
      } else if (v.getPre() == null) {
         System.out.println("no path from \"s\" to \"v\" exists");
      } else {
         printPath(v.getPre());
         System.out.println(v.getKey());
      }
   }

   public void DFS() {
      //Step 1: Initialization works
      for(Vertex u: vs) {
         u.setColor(Color.WHITE);
         u.setPre(null);
      }
      time = 0;
      for(Vertex u: vs) {
         if ( u.getColor() == Color.WHITE ) {
            dfsVisit(u);
         }
      }
   }

   public void dfsVisit(Vertex u) {
      time = time + 1; //white vertex u has just been discovered
      u.setD(time); //set the timestamp d
      u.setColor(Color.GRAY); //set the color to GRAY immediately
      Vertex v;
      
      for (int i = 0; i < u.getAdj().size(); i++) { //expolre edge (u,v)
         v = u.getAdj().get(i);
         if (v.getColor() == Color.WHITE) {
            v.setPre(u);
            dfsVisit(v);
         }
      }
      u.setColor(Color.BLACK); //blacken u; it is finished.
      time = time + 1;
      u.setF(time); // set the timestamp f.
   }

   //print each node's timestamp
   public void printTimestamp() {
      for (Vertex v : vs) {
         System.out.println( v.getD() + "/" + v.getF());
      }
   }
}

class testDFS {
   public static void main(String[] args) {
      //Create a vertex array
      Vertex u = new Vertex();
      Vertex v = new Vertex();
      Vertex w = new Vertex();
      Vertex x = new Vertex();
      Vertex y = new Vertex();
      Vertex z = new Vertex();

      //Create the connections
      u.addAdjNode(v);
      u.addAdjNode(x);

      v.addAdjNode(y);

      w.addAdjNode(y);
      w.addAdjNode(z);

      x.addAdjNode(v);

      y.addAdjNode(x);

      z.addAdjNode(z);

      Vertex[] vs = new Vertex[]{u, v, w, x, y, z};

      //Create a graph
      Graph gr = new Graph(vs);
      
      gr.DFS();
      gr.printTimestamp();
   }
}

class testBFS {
   public static void main(String[] args) {

      //Create a vertex array.

      Vertex v1 = new Vertex();
      Vertex v2 = new Vertex();
      Vertex v3 = new Vertex();
      Vertex v4 = new Vertex();
      Vertex v5 = new Vertex();

      v1.setKey(1);
      v2.setKey(2);
      v3.setKey(3);
      v4.setKey(4);
      v5.setKey(5);

      //Create the connections
      v1.addAdjNode(v2);
      v1.addAdjNode(v5);

      v2.addAdjNode(v1);
      v2.addAdjNode(v5);
      v2.addAdjNode(v3);
      v2.addAdjNode(v4);

      v3.addAdjNode(v2);
      v3.addAdjNode(v4);

      v4.addAdjNode(v2);
      v4.addAdjNode(v5);
      v4.addAdjNode(v3);

      v5.addAdjNode(v4);
      v5.addAdjNode(v1);
      v5.addAdjNode(v2);

      Vertex[] vs = new Vertex[]{v1, v2, v3, v4, v5};

      //Create a graph
      Graph gr = new Graph(vs);

      gr.BFS();
      gr.printPath(v4);

      return;
   }
}
