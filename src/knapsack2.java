// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class knapsack2 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int w = nextInt();

        Item[] items = new Item[n+1];

        int totalValues = 0;

        for (int i = 1; i <= n; i++) {
            items[i] = new Item(nextInt(), nextInt());
            totalValues += items[i].value;
        }

        long[][] dp = new long[n+1][totalValues + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= totalValues; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            Item item = items[i];
            for (int j = 0; j <= totalValues; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - item.value >= 0 && dp[i-1][j-item.value] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-item.value] + item.weight);
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalValues; j++) {
                if (dp[i][j] <= w) result = Math.max(result, j);
            }
        }

        out.println(result);

        out.close();
    }

    static class Item {
        int weight, value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}