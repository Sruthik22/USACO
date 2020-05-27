/*
ID: sruthi.2
LANG: JAVA
TASK: numtri
*/


// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class numtri {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int[][] triangle;
    static int largeSum = 0;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("numtri.in")));
        n = nextInt();

        triangle = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = nextInt();
            }
        }

        int[] bestColumns = new int[n];
        bestColumns[0] = triangle[0][0];

        for (int i = 1; i < n; i++) {

            int[] newBestColumns = new int[n];

            for (int j = 0; j < i + 1; j++) {
                // let me get the two index

                int index1 = j - 1;
                int index2 = j;

                int maxAbove = triangle[i][j];
                int maxColumnAbove = 0;

                if (index1 >= 0 && index2 < i) {
                    maxColumnAbove = Math.max(bestColumns[index1], bestColumns[index2]);
                }
                else if (index1 >= 0) {
                    maxColumnAbove = bestColumns[index1];
                }
                else {
                    maxColumnAbove = bestColumns[index2];
                }

                newBestColumns[j] = maxAbove + maxColumnAbove;
            }

            bestColumns = newBestColumns;

        }

        int result = 0;

        for (int i : bestColumns) {
            result = Math.max(i, result);
        }

        PrintWriter out = new PrintWriter(new File("numtri.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    /*public static void traverse(int r, int index, int sumSoFar) {

        // base case
        if (r == n) {
            largeSum = Math.max(largeSum, sumSoFar);
            return;
        }

        // recursive case
        // left
        traverse(r+1, index, sumSoFar + triangle[r][index]);
        // right
        traverse(r+1, index+1, sumSoFar + triangle[r][index]);
    }*/
}


