/*

Trie tree implementation

-   The classic applications include huge volume string sorting/stastics.
    The search engine uses trie tree for syntax stastics because it can
    substantialy decrease unnecessary string comparisons so its effeciency
    is higher than hash table.

-   The core of this algorithm is using space to exchange for time. It takes
    advantage of the common prefix in order to decrease the time for
    searching a word.

-   Three properties:

    1)  The root node does not have a character. Other nodes have and only
        have one character.

    2)  Starting from the root to a specific node forms a path. Put all 
        the characters along the path, here we have the corresponding 
        string.

    3)  Every child of a node has a different character.

-   One problem: Here come 100,000 words and each word's length is not larger than 10. For each
    word, you need to make a judgement that whether that word has shown up or not. if yes, show 
    the position of its first appearance.

*/


import java.util.*;

class Entry {
    private String word;
    private Integer count;

    public Entry(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void addCount() {
        this.count++;
    }
}

class HeapTree {
    private final int heapSize;
    private Entry[] entries;
    
    public HeapTree(int heapSize) {
        this.heapSize = heapSize;
        this.entries = new Entry[heapSize];
        for (int i = 0; i < entries.length; i++) {
            entries[i] = new Entry("\0", Integer.MIN_VALUE);
        }
    }

    public int left(int i) {
        if (i < heapSize/2) {
            return i*2+1;
        } else {
            return -1;
        }
    }

    public int right(int i) {
        if (i < heapSize/2-1) {
            return i*2+2;
        } else {
            return -1;
        }
    }

    public int parent(int i) {
        if (i > 0 && i < heapSize) {
            return (i-1)/2;
        } else {
            return -1;
        }
    }

    public void minHeapify(int i) {
        int current, left, right;
        int smallest = i;

        do {
            current = smallest; //Assume the current node is the smallest node
            left = left(current);
            right = right(current);

            if (left != -1) {
                if (entries[left].getCount() < entries[smallest].getCount() ) {
                    smallest = left;
                }
            }

            if (right != -1) {
                if (entries[right].getCount() < entries[smallest].getCount() ) {
                    smallest = right;
                }
            }

            if (smallest != current) {
                Entry temp = entries[current];
                entries[current] = entries[smallest];
                entries[smallest] = temp;
            }
            
        } while(current != smallest);
    }

    public void buildHeapTree() {
        //Start from the non-left nodes
        for (int i = heapSize/2-1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    //If successfully insert a new Entry, return the original root entry;
    //Else return null
    public Entry insertHeap(Entry newEntry) {

        Entry result = null;

        //See if the entry has already presented in the heap. It taks O(k*logk) where k is the size of the sortedHeap
        for (int i = 0; i < heapSize; i++) {
            if (entries[i].getWord() == newEntry.getWord()) {
                if (newEntry.getCount() > entries[i].getCount()) {
                    entries[i].setCount(newEntry.getCount());
                    minHeapify(i);
                    result = entries[0];
                    return result;
                }
            }
        }

        //If the entry is a new entry, see if it's qualify for insertion
        if (newEntry.getCount().compareTo(entries[0].getCount()) > 0) {
            result = entries[0];
            entries[0] = newEntry;
            minHeapify(0);
        }

        return result;
    }

    public Entry[] getEntries() {
        return entries;
    }
}

public class Trie {
    private enum Color {
        WHITE, RED; //A node with RED color means this word exists in the tree, whereas WHITE means not.
    }

    private class Node {
        ArrayList<Node> children = null;
        
        char value = '\0';
        Color color = Color.WHITE;
        int count = 0;

        public Node() {
            this.value = '\0';
            this.children = null;
        }

        public Node(char value) {
            this.value = value;
        }

        public Node(Color color) {
            this.color = color;
        }

        public Node(ArrayList<Node> children) {
            this.children = children;
        }

        public Node(char value, ArrayList<Node> children) {
            this.value = value;
            this.children = children;
        }

        public Node(char value, ArrayList<Node> children, Color color) {
            this.value = value;
            this.children = children;
            this.color = color;
        }

        public char getValue() {
            return this.value;
        }

        public void setValue(char value) {
            this.value = value;
        }

        public void addChild(Node child) {
            if ( this.children == null ) {
                this.children = new ArrayList<Node>();
            }
            
            children.add(child);
        }

        public ArrayList<Node> getChildren() {
            return this.children;
        }

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    private Node root;
    private HeapTree sortedHeap;
    
    public Trie() {
        root = new Node();
        sortedHeap = new HeapTree(10);//Default, set the heap tree as top 10 tree
    }

    public HeapTree getHeapTree() {
        return sortedHeap;
    }

    private int findCharacter(char value, ArrayList<Node> nodes) {
        if ( nodes == null ) {
            return -1;
        }

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getValue() == value) {
                return i;
            }
        }

        return -1;
    }

    /*
        If a word exists in the tree, return true;
        if a word doesn't exit in the tree, return false and add the word in the tree
    */
    public boolean findWord(String word) {
        
        if ( word.length() < 1 ) {
            return false;
        }

        Node parent = root;
        ArrayList<Node> children = parent.getChildren();
        boolean result = false;
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            int j = findCharacter(word.charAt(i), children);
            if ((j != -1)) {

                if (i == word.length()-1) {
                    if (children.get(j).getColor() == Color.RED) {
                        result = true;
                    } else {
                        children.get(j).setColor(Color.RED);
                        result = false;
                    }
                    
                    children.get(j).addCount();
                    count = children.get(j).getCount();
                    sortedHeap.insertHeap(new Entry(word, count));
                    return result;
                } else {
                    parent = children.get(j);
                    children = parent.getChildren();
                }
            } else {
                result = false;
                //Start Insertion
                Node newNode = new Node(word.charAt(i));
                if (i == word.length()-1) {
                    newNode.setColor(Color.RED);
                    newNode.addCount();
                    count = newNode.getCount();
                }

                parent.addChild(newNode);
                parent = newNode;
                children = parent.getChildren();
            }
        }

        //update the top heap tree
        sortedHeap.insertHeap(new Entry(word, count));
        return result;
    }

    public static void printEntries(Trie tree) {
        HeapTree sortedHeap = tree.getHeapTree();
        Entry[] entries = sortedHeap.getEntries();
        for (Entry e : entries) {
            System.out.println(e.getWord() + ": " + e.getCount());
        }
    }

    public static void main(String[] args) {
        //Create a new Trie Tree
        Trie tree = new Trie();
        tree.findWord("inn");
        tree.findWord("in");
        tree.findWord("in");
        tree.findWord("inn");
        tree.findWord("ab");
        
        tree.findWord("abg");
        tree.findWord("abc");
        tree.findWord("ere");
        tree.findWord("abg");
        tree.findWord("abc");
        tree.findWord("ere");
        tree.findWord("ab");
        tree.findWord("ab");
        tree.findWord("ab");
        tree.findWord("ab");
        tree.findWord("ab");
        tree.findWord("ab");
        tree.findWord("ac");
        tree.findWord("ac");
        Trie.printEntries(tree);
    }
}
