// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Frog2 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        int[] heights = new int[n];

        for (int i = 0; i < n; i++) {
            heights[i] = nextInt();
        }

        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++)  {
            dp[i] = 1000000000;
        }

        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = Math.max(i - k, 1); j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + Math.abs(heights[i - 1] - heights[j - 1]));
            }
        }

        out.println(dp[n]);

        out.close();
    }
}


