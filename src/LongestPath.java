// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class LongestPath {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int[] dp;
    static boolean[] visited;
    static LinkedList<Integer>[] linkedLists;
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        n = nextInt();
        m = nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int node1 = nextInt() - 1;
            int node2 = nextInt() - 1;

            linkedLists[node1].add(node2);
        }

        dp = new int[n]; // longest path starting at node i

        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            dfs(i);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }

        out.println(result);

        out.close();
    }

    static void dfs(int curNode) {
        visited[curNode] = true;

        for (int i: linkedLists[curNode]) {
            if (visited[i]) {
                dp[curNode] = Math.max(dp[curNode], dp[i] + 1);
            }

            else {
                dfs(i);
                dp[curNode] = Math.max(dp[curNode], dp[i] + 1);
            }
        }
    }
}


