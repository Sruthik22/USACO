// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class coincombosii {

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

        int[] coins = new int[n+1];

        for (int i = 1; i <= n; i++) {
            coins[i] = nextInt();
        }

        Arrays.sort(coins);

        int[][] dp = new int[n+1][x+1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= x; j++) {

                dp[i][j] = dp[i-1][j];

                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                    dp[i][j] %= 1000000007;
                }
            }
        }

        int result = dp[n][x];

        out.println(result);

        out.close();
    }
}


