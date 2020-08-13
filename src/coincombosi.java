// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class coincombosi {

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
        dp[0] = 1;

        for (int i = 1; i <= x; i++) {
            for (int c: coins) {
                if (i - c >= 0) {
                    dp[i] += dp[i - c];
                    dp[i] %= 1000000007;
                }
            }
        }

        out.println(dp[x]);

        out.close();
    }
}


