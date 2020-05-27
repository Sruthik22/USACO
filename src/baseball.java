// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class baseball {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("baseball.in"));
        int n = in.nextInt();

        int[] xs = new int[n];

        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
        }

        in.close();

        Arrays.sort(xs);

        int result = 0;

        for (int i = 0 ; i < n; i ++) {
            for (int j = i+1; j < n; j++) {
                int x1 = xs[i];
                int x2 = xs[j];

                int dist = x2 - x1;

                int x3Low = x2 + dist;
                int x3High = x2 + 2 * dist;

                int kHigh = Arrays.binarySearch(xs, x3High); // gives the index that is the same or near to
                int kLow = Arrays.binarySearch(xs, x3Low);

                if (kLow < 0) {
                    kLow = -kLow - 1;
                }

                if (kHigh < 0) {
                    kHigh = -kHigh - 2;
                }

                result += kHigh - kLow + 1;
            }
        }


        PrintWriter out = new PrintWriter(new File("baseball.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS

 Brute Force:
 choose first cow
 choose second cow
 choose the third cow
 if they define a valid pair, count it

 O(n^3) = 1 Billion (Danger Zone)

 Alternative:
 Find the range of third cow, given the first 2 cows

 We can use Binary Search to find lower and upper bound in log 2 n time

 Subtract to find the number of cows

 2 Loops with a binary search
 */
}


