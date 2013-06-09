/*
*  -  A Balanced Search Tree can guarantee that the basic operations(search, insert, delete...) have log2(n) in the worst case(really??), 
*     whereas a binary search tree can only do log2(n) in average and n in the worst case(called chaining). Red Black tree is one of the
*     Balanced Search Tree.
*
*  -  A red-black tree is a binary search tree with one extra bit of storage per node: its color, which
*     can be either RED or BLACK. By constraining the node colors on any simple path from the root to a 
*     leaf, red-black trees ensure that no such path is more than twice as long as any other, so the 
*     tree is approximately balanced.
*
*  -  Each node now contains the attributes color, key, left, right, and p(parent). 
*
*  -  If a child or the parent of a node does not exist, the corresponding pointer attribute of the node
*     contains the value NIL(??). We shall regard these NILs as being pointers to leaves (external nodes) of
*     the binary search tree and the normal, key-bearing nodes as being internal nodes of the tree.
*
*  -  A red-black tree is a binary tree that satisfies the following red-black properties:
*
*     1. Every node is either red or black.
*     2. The root is black.
*     3. Every leaf(NIL) is black.
*     4. If a node is red, then both its children are black.
*     5. For each node, all simple paths from the node to descendant leaves contain the same number of black
*        nodes.
*
*     
*
*
*
*
*
*
*
*
*
*
*
*
*
*/
