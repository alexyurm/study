/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

public class HashSim {

public static void main(String args[]) throws IOException {

  assert args.length == 2 :
    "Usage: hashsim <#_of_records> <#_of_iterations>";

  int recs = Integer.parseInt(args[0]);
  int iterations = Integer.parseInt(args[1]);

  // This is the pseudo hash table, twice the # of records
  int hashtable[] = new int[2*recs];

  // Data collecting variables
  int maxlen = 0;   // Maximum length of any chain, ever
  int avglen = 0;   // Average for the maximum (per iteration) chain length
  int minslots = (2*recs); // Minimum slots used by any interation
  int avgslots = 0; // Average number of slots used per iteration

  for(int i=0; i<iterations; i++) {
    int j;
    int slots = 0;
    int len = 0;

    // Intialize the table for this iteration
    for (j=0; j<(2*recs); j++) hashtable[j] = 0;

    // "insert" records into the hash table
    for (j=0; j<recs; j++) {
      int homeslot = DSutil.random(2*recs);
      assert homeslot <= 2*recs : "ERROR";
      // Imagine that we are inserting here, bump the count
      hashtable[homeslot]++;
    }

    // Update the statistics
    for (j=0; j<(2*recs); j++) {
      if (hashtable[j] > maxlen) maxlen = hashtable[j]; // longest chain ever
      if (hashtable[j] > len)
        len = hashtable[j]; // longest chain this iteration
      if (hashtable[j] > 0) slots++; // Something hit this slot
    }
    if (slots < minslots) minslots = slots;
    avglen += len;
    avgslots += slots;
  }

  // Calculate the final statistics
  System.out.println("For " + iterations + " iterations on "
                            + recs + " records");
  System.out.println("The longest chain ever generated was " + maxlen);
  System.out.println("The average length for the maximum chain was " +
                     ((double)avglen)/((double)iterations));
  System.out.println("The minimum number of slots ever used was " + minslots);
  System.out.println("The average number of slots used was "
		     + ((double)avgslots)/((double)iterations));
}
}
