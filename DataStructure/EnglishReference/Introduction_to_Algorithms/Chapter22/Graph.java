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
*
*/ 

import java.util.*;

class Vertex{

   private int key;
   private Vertex pre;  //the predecessor
   private Color color; //the color of a node
   private int dist;    //the distance
   //private Vertex[] adj;//the adjacency vertex nodes
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

   Graph() {};

   Graph(Vertex[] vs) {
      this.vs = vs;
      s = vs[0]; //we set the first node as the source vertex by default.
   }

   // Breadth-First-Search
   void BFS() {
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
      Iterator it = Q.iterator();
      Q.add(s);

      Vertex u;
      Vertex v;

      while(Q.peek() != null) {
         u = Q.poll(); //Retrieve one node from the queue.
         for (int i = 0; i < u.getAdj().size(); i++) {
            v = u.getAdj().get(i);
            if (v.getColor() == Color.WHITE) {
               v.setColor(Color.GRAY);
               v.setDist(u.getDist()+1);
               v.setPre(u);
               Q.add(v);
            }
         }
         u.setColor(Color.BLACK);
         System.out.println("the key = " + u.getKey() + " the distance = " + u.getDist());
      }
   }

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

      return;
   }

}
