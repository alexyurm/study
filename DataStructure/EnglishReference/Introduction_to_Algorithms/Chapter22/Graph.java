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
*     -  BFS search constructs a breadth-first tree, initially containing only its root, which is the source vertex s. Whenever the search discovers vertex u
*        , the vertex v and the edge (u,v) are added to the tree. We say that u is the predecessor or parent of v in the breadth-first tree. Since a vertex
*        is discovered at most once, it has at most one parent. Ancestor and descendant relatiohships in the breadth-first tree are defined relative to the root
*        s as usual: if u is on the simple path in the tree from the root s to vertex v, then u is an ancestor of v and v is a decendant of u. 
*        
*
*/ 

import java.util.*;
import java.lang.Integer;

class Vertex<T>{

   private int key;
   private Vertex pre;        //the predecessor
   private Vertex next;       //the next vertex
   private Color color; //the color of a node
   private int dist;

   Vertex() {
      key = 0;
      next = null;
      color = Color.WHITE; //Can I just use WHITE instead?
      dist = 0;
   }

   Vertex(Vertex v) {
      key = v.getKey();
      next = v.getNext();
      color = v.getColor();
      dist = v.getDist();
   }

   Vertex(int k, Vertex v, Color c, int d) {
      key = k;
      next = v;
      color = c;
      dist = d;
   }

   public void setKey(int k) {
      key = k;
   }

   public void setPre(Vertex p) {
      pre = p;
   }

   public void setNext(Vertex n) {
      next = n;
   }

   public void setColor(Color c) {
      color = c;
   }

   public void setDist(int d) {
      dist = d;
   }

   public int getKey() {
      return key;
   }

   public Vertex getPre() {
      return pre;
   }

   public Vertex getNext() {
      return next;
   }

   public Color getColor() {
      return color;
   }

   public int getDist() {
      return dist;
   }

}

class Graph {

   private Vertex[] vertices;
   private Vertex s; //the source vertex

   Graph() {
      s = null;
      vertices = null;
   }

   Graph(Vertex[] vts) {
      vertices = vts;
      s = vertices[0];  //Default source vertex is the first element in the vertics string.
   }

   // Breadth-First-Search
   void BFS() {
      //Step 1: Initialization
      for(Vertex u: vertices) {
         if (u == s) continue;

         u.setColor(Color.WHITE);
         u.setDist(-1);
         u.setPre(null);
      }
      s.setColor(Color.GRAY);
      s.setDist(0);
      Queue<Vertex> Q = new LinkedList<Vertex>();
      Iterator it = Q.iterator();
      
   }

   public static void main(String[] args) {

      Queue<Vertex> queue = new Queue<Vertex>();

      return;
   }

}
