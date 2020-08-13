// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Sushi {

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

        int num1 = 0;
        int num2 = 0;
        int num3 = 0;

        for (int i = 0; i < n; i++) {
            int next = nextInt();

            if (next == 1) num1++;
            else if (next == 2) num2++;
            else num3++;
        }

        double[][][] dp = new double[n+3][n+3][n+3];
        double[][][] ev = new double[n+3][n+3][n+3];

        dp[num1][num2][num3] = 1;

        for (int k = n; k >= 0; k--) {
            for (int j = n; j >= 0; j--) {
                for (int i = n; i >= 0; i--) {
                    if (k == 0 && j == 0 && i == 0) break;
                    if (k + j + i > n) continue;

                    double p_waste = (double) (n - (i + k + j)) / n;
                    double ev_wasted = p_waste / (1 - p_waste) + 1;

                    ev[i][j][k] += ev_wasted * dp[i][j][k];

                    if (i != 0) {
                        double prob = ((double) i / (i + j + k));
                        dp[i-1][j][k] += dp[i][j][k] * prob;
                        ev[i-1][j][k] += ev[i][j][k] * prob;
                    }
                    if (j != 0) {
                        double prob = ((double) j / (i + j + k));
                        dp[i+1][j-1][k] += dp[i][j][k] * prob;
                        ev[i+1][j-1][k] += ev[i][j][k] * prob;
                    }
                    if (k != 0) {
                        double prob = ((double) k / (i + j + k));
                        dp[i][j+1][k-1] += dp[i][j][k] * prob;
                        ev[i][j+1][k-1] += ev[i][j][k] * prob;
                    }
                }
            }
        }

        out.println(ev[0][0][0]);

        out.close();
    }
}


