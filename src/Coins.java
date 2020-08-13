// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Coins {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static double nextDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        double[] probabilities = new double[n];

        for (int i = 0; i < n; i++) {
            probabilities[i] = nextDouble();
        }

        double[][] dp = new double[n + 1][n + 1]; // coins, and the number of heads = probabilities

        dp[0][0] = 1.0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j - 1 >= 0) dp[i][j] = dp[i-1][j-1] * probabilities[i - 1];
                dp[i][j] += dp[i-1][j] * (1 - probabilities[i - 1]);
            }
        }

        double result = 0;

        for (int j = 0; j <= n; j++) {
            if (j > n - j) {
                result += dp[n][j];
            }
        }

        out.println(result);

        out.close();
    }
}


