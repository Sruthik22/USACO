// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class grid1 {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int h = in.nextInt();
        int w = in.nextInt();

        char[][] grid = new char[h + 1][w + 1];

        for (int i = 1; i <= h; i++) {

            String row = in.next();

            for (int j = 1; j <= w; j++) {
                grid[i][j] = row.charAt(j - 1);
            }
        }

        int[][] dp = new int[h + 1][w + 1];

        dp[0][1] = 1;

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                if (grid[i][j] == '#') continue;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                dp[i][j] %= 1000000007;
            }
        }

        int result = dp[h][w];
        out.println(result);
        out.close();
    }
}


