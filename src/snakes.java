// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class snakes {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("snakes.in")));

        int n = nextInt();
        int k = nextInt();

        int[] count = new int[n+1];
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            count[i] = nextInt();
            sum += count[i];
        }

        int[][] dp = new int[n+1][k+1];
        int high = 0;

        for (int i = 1; i <= n; i++) {

            high = Math.max(high, count[i]);

            dp[i][0] = high * i; // because we are doing k - 1, we need to have 0 as the base case

            for (int j = 1; j <= k; j++) {
                int max = count[i];
                dp[i][j] = Integer.MAX_VALUE;
                for (int l = i-1; l >= 0; l--) {
                    dp[i][j] = Math.min(dp[i][j], dp[l][j-1] + max * (i - l));
                    max = Math.max(max, count[l]);
                }
            }
        }

        int result = dp[n][k] - sum;

        PrintWriter out = new PrintWriter(new File("snakes.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}