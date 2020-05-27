// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class maxcross {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("maxcross.in"));
        int n = in.nextInt(); // # of crosswalks
        int k = in.nextInt(); // need at least these many working signals
        int b = in.nextInt(); // id numbers of broken signals

        int[] signals = new int[n + 1];

        // 1 is broken, and 0 is working

        for (int i = 1; i <= b; i ++) {
            int index = in.nextInt();
            signals[index] = 1;
        }

        in.close();

        int sum = 0;
        int result = Integer.MAX_VALUE;

        for (int i = 1; i < k; i++) {
            sum += signals[i];
        }

        for (int i = 0; i <= n - k; i++) {
            sum -= signals[i];
            sum += signals[k + i];

            result = Math.min(result, sum);
        }

        PrintWriter out = new PrintWriter(new File("maxcross.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS

 Modified Algorithm:

 Sort a list of all broken lights (Tree set works as well)

 maintain a starting and ending point (index) for the window

 move the start index, to lose a broken bulb

 and count up the the highest in the range

 Only consider the number of windows as broken bulbs (Very efficient
 for minimal number of bulbs)
 */
}


