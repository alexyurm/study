/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Sorted List ADT */
public interface SortedList<Key,E> {

  /** Reinitialize the list.  The client is responsible for
      reclaiming the storage used by the list elements. */
  public void clear();

  /** Insert an element into the list to maintain sorted order.
      @param k Key value of the element being inserted
      @param e The element being inserted */
  public void insert(Key k, E e);

  /** Remove and return the current element
      @return the element removed */
  public E remove();

  /** Place current position at list start */
  public void moveToStart();

  /** Place current position at list end */
  public void moveToEnd();

  /** Move current position one step left; no change if at beginning. */
  public void prev();

  /** Move current position one step right; no change if already at
      end. */
  public void next();

  /** @return the number of elements in the list */
  public int length();

  /** @return the position of the current element */
  public int currPos();

  /** Set current postion to "pos"
      @param pos The position to set as current */
  public void moveToPos(int pos);

  /** @return the current element */
  public E getValue();

  /** @return The current element's key */
  public Key getKey();

}
