// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class time {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int m;
    static int c;
    static LinkedList<Integer>[] linkedLists;
    static int[] costs;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("time.in")));

        n = nextInt();
        m = nextInt();
        c = nextInt();

        linkedLists = new LinkedList[n];
        costs = new int[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            costs[i] = nextInt();
        }

        for (int i = 0; i < m; i++) {
            int node1 = nextInt() - 1;
            int node2 = nextInt() - 1;

            linkedLists[node1].add(node2);
        }

        dp = new int[n][1001];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 1000; j++) {
                dp[i][j] = -1;
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[j][i-1] == -1) continue;
                for (int k: linkedLists[j]) {
                    dp[k][i] = Math.max(dp[k][i], dp[j][i-1] + costs[k]);
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= 1000; i++) {
            result = Math.max(dp[0][i] - i * i * c, result);
        }

        PrintWriter out = new PrintWriter(new File("time.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


