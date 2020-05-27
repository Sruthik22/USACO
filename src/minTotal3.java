// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class minTotal3 {

    public static void main(String[] args) {
        int[] arr = new int[] {8,2,4,7};

        System.out.println(minTotal(arr, new int[]{0,0,0,0}, 0, 0));
    }

    static int minTotal(int[] arr, int[] counts, int sum, int count) {
        if (count == 3) {
            return sum;
        }

        int best = Integer.MAX_VALUE;

        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (Math.pow(3, counts[i]) * arr[i] < best) {
                best = (int) Math.pow(3, counts[i]) * arr[i];
                index = i;
            }
        }

        counts[index]++;
        sum += best;

        return minTotal(arr, counts, sum, count+1);
    }
  
 /*
 ANALYSIS
 
 */
}


