// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Vacation {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        Day[] days = new Day[n];

        for (int i = 0; i < n; i++) {
            days[i] = new Day(nextInt(), nextInt(), nextInt());
        }

        int[][] dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.max(dp[i-1][(j + 1) % 3], dp[i-1][(j + 2) % 3]) + days[i-1].get(j);
            }
        }

        out.println(Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]));

        out.close();
    }

    static class Day {
        int a, b, c;

        Day(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        private int get(int val) {
            if (val == 0) return a;
            if (val == 1) return b;
            return c;
        }
    }
}


