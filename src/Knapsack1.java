// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Knapsack1 {

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

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(nextInt(), nextInt());
        }

        long[][] dp = new long[w + 1][n+1];

        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j-1];
                if (i - items[j-1].weight < 0) continue;
                dp[i][j] = Math.max(dp[i][j], dp[i - items[j-1].weight][j-1] + items[j-1].value);
            }
        }

        long result = 0;

        for (int i = 0; i <= w; i++) {
            result = Math.max(result, dp[i][n]);
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


