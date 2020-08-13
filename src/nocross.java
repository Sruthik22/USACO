// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class nocross {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("nocross.in")));

        int n = nextInt();

        int[] road_1 = new int[n];
        int[] road_2 = new int[n];

        for (int i = 0; i < n; i++) {
            road_1[i] = nextInt();
        }

        for (int i = 0; i < n; i++) {
            road_2[i] = nextInt();
        }

        int[][] dp = new int[n][n];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], (Math.abs(road_2[0] - road_1[i]) <= 4) ? 1: 0);
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i-1], (Math.abs(road_2[i-1] - road_1[0]) <= 4) ? 1: 0);
        }

        dp[0][0] = (Math.abs(road_2[0] - road_1[0]) <= 4) ? 1: 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int toAdd = (Math.abs(road_2[i] - road_1[j]) <= 4) ? 1 : 0;
                dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1] + toAdd));
            }
        }

        int result = dp[n-1][n-1];

        PrintWriter out = new PrintWriter(new File("nocross.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


