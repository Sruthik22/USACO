// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class palpath {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("palpath.in")));

        int n = nextInt();

        char[][] grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            String row = next();
            for (int j = 0; j < n; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        long[][] dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n-1; i >= 1; i--) {

            long[][] i_dp = new long[n][n];

            for (int j = 0; j < n; j++) {
                int rA = j;
                int cA = i - 1 - rA;

                if (cA < 0) continue;

                for (int k = 0; k < n; k++) {
                    int rB = k;
                    int cB = 2 * n - i - rB - 1;

                    if (cB >= n) continue;

                    if (grid[rA][cA] != grid[rB][cB]) continue;

                    i_dp[rA][rB] += dp[rA][rB];

                    if (rA + 1 < n) i_dp[rA][rB] += dp[rA + 1][rB];
                    if (rB - 1 >= 0) i_dp[rA][rB] += dp[rA][rB - 1];
                    if (rA + 1 < n && rB - 1 >= 0) i_dp[rA][rB] += dp[rA + 1][rB - 1];

                    i_dp[rA][rB] %= 1000000007;
                }
            }

            dp = i_dp;
        }

        long result = dp[0][n-1];
        PrintWriter out = new PrintWriter(new File("palpath.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


