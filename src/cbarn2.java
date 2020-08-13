// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cbarn2 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int n;
    static int K;
    static long[][] dp;
    static int[] barns;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cbarn2.in")));

        n = nextInt();
        K = nextInt();

        barns = new int[n];

        for (int i = 0; i < n; i++) {
            barns[i] = nextInt();
        }

        dp = new long[K + 1][n + 1];
        long result = 1000000000000000000L;

        for (int i = 0; i < n; i++) {
            replace();
            dp[0][n] = 0;
            for (int k = 1; k <= K; k++) {
                for (int j = 0; j < n; j++) {
                    long runningSum = 0;
                    for (int a = j + 1; a <= n; a++) {
                        runningSum += (long) (barns[a - 1]) * (a-j-1);
                        dp[k][j] = Math.min(dp[k][j], dp[k-1][a] + runningSum);
                    }
                }
            }

            result = Math.min(result, dp[K][0]);
            rotateRight();
        }

        PrintWriter out = new PrintWriter(new File("cbarn2.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void replace() {
        for (int j = 0; j <= K; j++) {
            for (int i = 0; i <= n; i++) {
                dp[j][i] = 1000000000000000000L;
            }
        }

    }

    static void rotateRight() {
        // take out the last element
        int temp = barns[n - 1];
        for (int j = n - 1; j > 0; j--) {
            barns[j] = barns[j - 1];
        }
        barns[0] = temp;
    }
}


