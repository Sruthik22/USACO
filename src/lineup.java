// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class lineup {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("lineup.in"));
        in.close();

        int result = 0;
        PrintWriter out = new PrintWriter(new File("lineup.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS
 n = 5 * 10^4

 sort the list

 binary search on the n value - 0 -- n (log n)

 for each of the test, than sliding window to check

 hashmap for the number of values of each val -- make sure that there is at least 1 of each

 n log n

 SUGGESTED

 right and left pointers, if not all the types of cows than advance the right pointer, otherwise the left pointer




 */
}


