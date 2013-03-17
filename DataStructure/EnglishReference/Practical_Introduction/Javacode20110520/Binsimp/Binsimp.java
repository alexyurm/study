/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

class Binsimp {

static final int MaxKey = 10;

static void output(Integer it) {}

static void binsort(Integer A[]) {
  List<Integer>[] B = (LList<Integer>[])new LList[MaxKey];
  Integer item;
  for (int i=0; i<MaxKey; i++)
    B[i] = new LList<Integer>();
  for (int i=0; i<A.length; i++) B[A[i]].append(A[i]);
  for (int i=0; i<MaxKey; i++)
    for (B[i].moveToStart();
         (item = B[i].getValue()) != null; B[i].next())
      output(item);
}

  public static void main(String args[]) throws IOException {
    assert args.length == 2 : "Usage: Binsimpmain <size> <num>";
    int n = Integer.parseInt(args[0]);
    int num = Integer.parseInt(args[1]);
    int i, j;

    Integer[] A = new Integer[n];

    for (i=0; i<n; i++)
      A[i] = i;

    Integer[] B = new Integer[n];
    Integer[] temp = new Integer[n];

    DSutil.permute(A);

for (i=0; i<n; i++)
  temp[i] = A[i];

    System.out.println("Initial permutation:");

    for(i=0; i<n; i++)
      System.out.print(A[i] + "  ");
    System.out.println();

    System.out.println("Do binsimp1");

/// binsimp1.x
for (i=0; i<n; i++)
  B[A[i]] = A[i];
/// End binsimp1.x

System.out.println("Do binsimp2");

/// binsimp2.x
for (i=0; i<n; i++)
  while (A[i] != i) // Swap element A[i] with A[A[i]]
    DSutil.swap(A, i, A[i]);
/// End binsimp2.x

System.out.println("Now, what is temp?");
for (i=0; i<n; i++)
  System.out.print(temp[i] + " ");
System.out.println();

  System.out.println("Let's check to be sure this works.");
  for(i=0; i<n; i++)
    System.out.print(A[i] + "  ");
  System.out.println();
  for(i=0; i<n; i++)
    System.out.print(B[i] + "  ");
  System.out.println();

  System.out.println("OK, now for timings.");

long time1 = System.currentTimeMillis();
for (j=0; j<num; j++)
  for (i=0; i<n; i++)
    B[A[i]] = A[i];
long time2 = System.currentTimeMillis();
System.out.println("Time for binsimp1 is " + (time2-time1));


// Factor out overhead
time1 = System.currentTimeMillis();
for (j=0; j<num; j++)
  for (i=0; i<n; i++)
    A[i] = temp[i];
time2 = System.currentTimeMillis();
long temptime = time2-time1;
System.out.println("Overhead time is " + (time2-time1));

time1 = System.currentTimeMillis();
for (j=0; j<num; j++)
{
  for (i=0; i<n; i++)
    A[i] = temp[i];

  for (i=0; i<n; i++)
    while (A[i] != i) // Swap element A[i] with A[A[i]]
      DSutil.swap(A, i, A[i]);
}
time2 = System.currentTimeMillis();
System.out.println("Raw time is " + (time2-time1));
System.out.println("Time for binsimp2 is " + (time2-time1-temptime));

  System.in.read();
}
}
