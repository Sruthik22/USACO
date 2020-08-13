// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class minimizingcoins {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int x = nextInt();

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = nextInt();
        }

        int[] dp = new int[x+1];

        for (int i = 0; i <= x; i++) {
            dp[i] = 100000000;
        }

        dp[0] = 0;

        for (int i = 1; i <= x; i++) {
            for (int c: coins) {
                if (i - c >= 0) {
                    dp[i] = Math.min(dp[i - c] + 1, dp[i]);
                }
            }
        }

        if (dp[x] == 100000000) out.println(-1);
        else out.println(dp[x]);

        out.close();
    }
}


