// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Slimes {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int[] vals = new int[n];
        long[] prefixSum = new long[n + 1];

        for (int i = 0; i < n; i++) {
            vals[i] = nextInt();
            prefixSum[i + 1] = vals[i] + prefixSum[i];
        }

        Long[][] dp = new Long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 10000000000000L;
            }
        }

        for (int L = n-1; L >= 0; L--) {
            for (int R = L; R < n; R++) {
                if (L == R) dp[L][R] = 0L;
                else {
                    long j = prefixSum[R + 1] - prefixSum[L];

                    for (int i = L; i < R; i++) {
                        dp[L][R] = Math.min(dp[L][R], dp[L][i] + dp[i + 1][R] + j);
                    }
                }
            }
        }

        out.println(dp[0][n-1]);
        out.close();
    }
}