// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class GFG {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(uglynumber(n));
        }
    }

    private static long uglynumber(int n) {
        long ugly[] = new long[n];
        int index = 0;
        ugly[index++] = 1;
        int for_2 = 0, for_3 = 0, for_5 = 0;
        long multiple_of_2 = 2;
        long two = 2;
        long multiple_of_3 = 3;
        long three = 3;
        long multiple_of_5 = 5;
        long five = 5;
        for (int i = 1; i < n; i++) {
            long uglyno = Math.min(multiple_of_2, Math.min(multiple_of_3,
                    multiple_of_5));
            ugly[i] = uglyno;
            if (uglyno == multiple_of_2) {
                for_2++;
                multiple_of_2 = ugly[for_2] * two;
            }
            if (uglyno == multiple_of_3) {
                for_3++;
                multiple_of_3 = ugly[for_3] * three;
            }
            if (uglyno == multiple_of_5) {
                for_5++;
                multiple_of_5 = ugly[for_5] * five;
            }

        }
        return ugly[n - 1];

    }
}


