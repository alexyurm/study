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
*        
*
*/ 

import java.util.*;
import java.lang.Integer;

class Vertex<T>{
   int key;
   T next;
}

class Graph {
      
}

class BFS {
   public BFS() {
      public static final int WHITE = 0;
      public static final int GRAY = 1;
      public static final int BLACK = 2;
   }
}

