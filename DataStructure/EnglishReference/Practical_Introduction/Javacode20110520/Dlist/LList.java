/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// Doubly linked list implementation
class LList<E> implements List<E> {
private DLink<E> head;        // Pointer to list header
private DLink<E> tail;        // Pointer to last element in list 
protected DLink<E> curr;      // Pointer ahead of current element
int cnt;		      // Size of list

//Constructors
LList(int size) { this(); }  // Ignore size
LList() {
  curr = head = new DLink<E>(null, null); // Create header node
  tail = new DLink<E>(head, null);
  head.setNext(tail);
  cnt = 0;
}

public void clear() {         // Remove all elements from list
  head.setNext(null);         // Drop access to rest of links
  curr = head = new DLink<E>(null, null); // Create header node
  tail = new DLink<E>(head, null);
  head.setNext(tail);
  cnt = 0;
}

public void moveToStart()  // Set curr at list start
{ curr = head; }

public void moveToEnd()  // Set curr at list end
{ curr = tail.prev(); }
/** Insert "it" at current position */
public void insert(E it) {
  curr.setNext(new DLink<E>(it, curr, curr.next()));  
  curr.next().next().setPrev(curr.next());
  cnt++;
}

/** Append "it" to list */
public void append(E it) {
  tail.setPrev(new DLink<E>(it, tail.prev(), tail));
  tail.prev().prev().setNext(tail.prev());
  cnt++;
}

/** Remove and return current element */
public E remove() {
  if (curr.next() == tail) return null; // Nothing to remove
  E it = curr.next().element();      // Remember value
  curr.next().next().setPrev(curr);
  curr.setNext(curr.next().next());  // Remove from list
  cnt--;			     // Decrement the count
  return it;                         // Return value removed
}

/** Move curr one step left; no change if at front */
public void prev() {
  if (curr != head)   // Can't back up from list head
    curr = curr.prev();
}
// Move curr one step right; no change if at end
public void next()
  { if (curr != tail.prev()) curr = curr.next(); }

public int length() { return cnt; }

// Return the position of the current element
public int currPos() {
  DLink<E> temp = head;
  int i;
  for (i=0; curr != temp; i++)
    temp = temp.next();
  return i;
}

// Move down list to "pos" position
public void moveToPos(int pos) {
  assert (pos>=0) && (pos<=cnt) : "Position out of range";
  curr = head;
  for(int i=0; i<pos; i++) curr = curr.next();
}

public E getValue() {   // Return current element
  if (curr.next() == tail) return null;
  return curr.next().element();
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
