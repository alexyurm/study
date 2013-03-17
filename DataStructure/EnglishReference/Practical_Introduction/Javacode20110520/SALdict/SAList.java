/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.lang.Comparable;

/** Array-based sorted list implementation */
class SAList<Key extends Comparable<? super Key>, E>
            implements SortedList<Key, E> {

private static final int defaultSize = 10; // Default array size

private int maxSize;            // Maximum size of list
private int listSize;           // Actual number of elements in list
private int curr;               // Position of current element
private KVpair<Key,E>[] listArray; // Array holding list elements

SAList() { setup(defaultSize); } // Constructor: use default size

SAList(int size) { setup(size); }// Constructor: user-specified size

@SuppressWarnings("unchecked")  // Allow the generic array allocation
private void setup(int size) {  // Do actual initialization work
  maxSize = size;
  listSize = curr = 0;
  listArray = (KVpair<Key,E>[])new KVpair[size];   // Create listArray
}

public void clear()             // Reinitialize the list
{ listSize = curr = 0; }       // Simply reinitialize values

/** Insert an element into the list in sorted order.
    @param k The key of the element being inserted.
    @param it The element to be inserted. At the end, it will then be
    at the current position */ 
public void insert(Key k, E it) {
  assert listSize < maxSize : "List is full";
  curr = 0;
  while ((curr < listSize) &&
         (listArray[curr].key().compareTo(k) < 0))
    curr++;
  for (int i=listSize; i>curr; i--) // Shift elements up
    listArray[i] = listArray[i-1];   //   to make room
  listArray[curr] = new KVpair<Key,E>(k, it);
  listSize++;                   // Increment list size
}

// Remove and return the current element
// Return null if there is no such element to remove.
public E remove() {
  if ((curr < 0) || (curr >= listSize)) return null;
  E it = listArray[curr].value();     // Copy the element
  for(int i=curr; i<listSize-1; i++) // Shift them down
    listArray[i] = listArray[i+1];
  listSize--;                   // Decrement size
  return it;
}

public void moveToStart() { curr = 0; } // Reset current
public void moveToEnd() { curr = listSize; } // Reset current
public void prev() { if (curr != 0) curr--; } // Back up
public void next() { if (curr < listSize) curr++; } // Next

// Return list size
public int length() { return listSize; }

public void moveToPos(int pos) { // Reset list position to "pos"
  assert (pos >= 0) && (pos <= listSize) : "Pos out of range";
  curr = pos;
}

public int currPos() { // Return current position
  return curr;
}

public E getValue() {     // Return current element
  if ((curr < 0) || (curr >= listSize)) return null;
  return listArray[curr].value();
}

public Key getKey() {     // Return current element
  if ((curr < 0) || (curr >= listSize)) return null;
  return listArray[curr].key();
}

// Extra stuff not printed in the book.

  /**
   * Generate a human-readable representation of this list's contents
   * that looks something like this: < 1 2 3 | 4 5 6 >.  The vertical
   * bar represents the current location of the fence.  This method
   * uses toString() on the individual elements.
   * @return The string representation of this list
   */
  public String toString()
  {
    // Save the current position of the list
    int oldPos = currPos();
    int length = length();
    StringBuffer out = new StringBuffer((length() + 1) * 4);

    moveToStart();
    out.append("< ");
    for (int i = 0; i < oldPos; i++) {
      out.append(getValue());
      out.append(" ");
      next();
    }
    out.append("| ");
    for (int i = oldPos; i < length; i++) {
      out.append(getValue());
      out.append(" ");
      next();
    }
    out.append(">");
    moveToPos(oldPos); // Reset the fence to its original position
    return out.toString();
  }

}
