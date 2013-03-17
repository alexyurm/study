/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.io.*;

// This program tests a bunch of various code fragements from
// Chapter 3, mainly for syntactic correctness.
class Ch3 {
	
  static void sort(int[] A) {}

  static int value(int i) { return i; }

  static boolean EVEN(int x)
  { return (x % 1) == 0; }

  final static int n = 100;

  public static void main(String args[]) throws IOException {
	int i, j, k;
	int sum, sum1, sum2, sum3;
	int a;
	int b = 10;
	int tmp;

    int[] A = new int[n];

    int[][] AA = new int[n][n];

    for (i=0; i<n; i++)
      A[i] = i;

// c3p1.book
sum = 0;
for (i=1; i<=n; i++)
   for (j=1; j<=n; j++)
      sum++;

System.out.println("Test 1: For n = " + n +", sum is " + sum);
System.out.println("It should be n^2.");

/// c3p2.book
a = b;

System.out.println("Test 2: b is " + b +" and a is " + a);

/// c3p3.book
sum = 0;
for (i=1; i<=n; i++)
   sum += n;

System.out.println("Test 3: For n = " + n +", n^2 is " + sum);

// c3p4.book
sum = 0;
for (j=1; j<=n; j++)     // First for loop
   for (i=1; i<=j; i++)  //   is a double loop
      sum++;
for (k=0; k<n; k++)      // Second for loop
   A[k] = k;

System.out.println("Test 4: For n = " + n +
				   ", the sum from 1 to n is " + sum);

// c3p5.book
sum1 = 0;
for (i=1; i<=n; i++)     // First double loop
   for (j=1; j<=n; j++)  //   do n times
      sum1++;

sum2 = 0;
for (i=1; i<=n; i++)     // Second double loop
   for (j=1; j<=i; j++)  //   do i times
      sum2++;

System.out.println("Test 5: This should be n^2: " + sum1);
System.out.println("Again, the sum from 1 to n is " + sum2);

/// c3p6.x
sum1 = 0;
for (k=1; k<=n; k*=2)    // Do log n times
   for (j=1; j<=n; j++)  // Do n times
      sum1++;

sum2 = 0;
for (k=1; k<=n; k*=2)    // Do log n times
   for (j=1; j<=k; j++)  // Do k times
      sum2++;
int P, C;
P = 10;
C = 10;
int[] count = new int[C];

// c3p16.book
  for (i=0; i<C; i++)   // Initialize count
     count[i] = 0;
  for (i=0; i<P; i++)   // Look at all of the pixels
     count[value(i)]++; // Increment a pixel value count
  sort(count);          // Sort pixel value counts

System.out.println("Test 6: For " + n +
				   ", the sum of n taken log n times is: " + sum1);
System.out.println("For " + n + ", the sum of 2^j is: " + sum2);

  int c = 1;
  int d = 2;
  int e = 3;

/// c3p7.x
a = b + c;
d = a + e;
/// End c3p7.x

  a = d ;


/// c3p8.x
sum = 0;
for (i=0; i<3; i++)
   for (j=0; j<n; j++)
      sum++;
/// End c3p8.x

/// c3p9.x
sum=0;
for (i=0; i<n*n; i++)
   sum++;
/// End c3p9.x

/// c3p10.x
for (i=0; i < n-1; i++)
  for (j=i+1; j < n; j++) {
    tmp = AA[i][j];
    AA[i][j] = AA[j][i];
    AA[j][i] = tmp;
  }
/// End c3p10.book

System.out.println("Finally, do part 11.");

  for (i=0; i<n; i++)
    A[i] = i;

/// c3p11.book
sum = 0;
for (i=1; i<=n; i++)
  for (j=1; j<=n; j*=2)
    sum++;

System.out.println("Sum should be about 1/2 n^2: " + sum);

/// c3p12.book
sum = 0;
for (i=1; i<=n; i*=2)
  for (j=1; j<=n; j++)
    sum++;
for (i=0; i<n; i++) {
  for (j=0; j<n; j++)
    A[i] = DSutil.random(n);
  sort(A);
}

for (i=0; i<n; i++)
  A[i] = i;
sum = 0;
for (i=0; i<n; i++)
  for (j=0; A[j]!=i; j++)
    sum++;
sum = 0;
if (EVEN(n))
  for (i=0; i<n; i++)
    sum++;
else
  sum = sum + n;
  for (i=0; i<C; i++)   // Initialize count
     count[i] = 0;
  for (i=0; i<P; i++)   // Look at all of the pixels
     count[value(i)]++; // Increment a pixel value count
  sort(count);          // Sort pixel value counts

System.out.println("Final sum should just be 100: " + sum);

  System.in.read();
}
}
