/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Extend the standard Dictionary to 2D spatial point data */
public interface SpatialDictionary<E> extends Dictionary<int[], E> {
  /** Visit all records within rad distance of point k */
  public void regionSearch(int[] k, int rad);
}
