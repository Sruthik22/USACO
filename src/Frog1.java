// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Frog1 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

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

            if (i == 2) dp[i] = dp[i-1] + Math.abs(heights[i-2] - heights[i - 1]);

            else {
                dp[i] = Math.min(dp[i-1] + Math.abs(heights[i-2] - heights[i - 1]),
                        dp[i-2] + Math.abs(heights[i-3] - heights[i - 1]));
            }
        }

        out.println(dp[n]);

        out.close();
    }
}


