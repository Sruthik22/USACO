// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class hps {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("hps.in")));

        int n = nextInt();
        int k = nextInt();

        int[] moves = new int[n];

        for (int i = 0; i < n; i++) {
            char c = next().charAt(0);
            if (c == 'H') moves[i] = 0;
            if (c == 'P') moves[i] = 1;
            if (c == 'S') moves[i] = 2;
        }

        int[][][] dp = new int[n+1][k+1][3];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int state = 0; state < 3; state++) {
                    if (i == 0) {
                        dp[i][j][state] = 0;
                    } else {
                        if (j == 0) {
                            dp[i][j][state] = dp[i-1][j][state] + (moves[i-1] == state ? 1 : 0);
                        } else {
                            int ostate1 = (state + 1) % 3;
                            int ostate2 = (state + 2) % 3;
                            dp[i][j][state] = Math.max(Math.max(dp[i-1][j][state], dp[i-1][j-1][ostate1]), dp[i-1][j-1][ostate2]) + (moves[i-1] == state ? 1 : 0);
                        }
                    }
                }
            }
        }

        int result = Math.max(Math.max(dp[n][k][1], dp[n][k][2]), dp[n][k][0]);

        PrintWriter out = new PrintWriter(new File("hps.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


