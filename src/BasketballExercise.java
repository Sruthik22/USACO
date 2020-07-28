// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class BasketballExercise {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int[][] rows;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        n = nextInt();

        rows = new int[n+1][2];

        for (int i = 0; i < n; i++) {
            rows[i+1][0] = nextInt();
        }

        for (int i = 0; i < n; i++){
            rows[i+1][1] = nextInt();
        }

        long[][] dp = new long[n+1][2];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + rows[i][0]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + rows[i][1]);
        }

        long result = Math.max(dp[n][0], dp[n][1]);

        out.println(result);
        out.close();
    }
}


