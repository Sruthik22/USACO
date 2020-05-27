// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class buffet {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int e;
    static LinkedList<Integer>[] adjacencyList;

    static int[] quality;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("buffet.in")));

        n = nextInt();
        e = nextInt();

        adjacencyList = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        quality = new int[n];

        for (int i = 0; i < n; i++) {
            int q = nextInt(); // quality
            quality[i] = q;
            int d = nextInt(); // neighbors

            for (int j = 0; j < d; j++) {
                int neighbor = nextInt();
                adjacencyList[i].add(neighbor-1);
            }
        }

        int[][] distance = new int[n][];

        for (int i = 0; i < n; i++) {
            int[] dist = bfs(i);
            distance[i] = dist;
        }

        // Sort by value.
        pair[] energyPair = new pair[n];
        for (int i=0; i<n; i++)
            energyPair[i] = new pair(quality[i], i);
        Arrays.sort(energyPair);

        int[] dp = new int[n];
        dp[0] = energyPair[0].value;
        int res = dp[0];

        // Outer DP loop.
        for (int i=1; i<n; i++) {

            // j is where we are building off of.
            dp[i] = energyPair[i].value;
            for (int j=0; j<i; j++) {
                if (distance[energyPair[j].index][energyPair[i].index] == -1) continue;
                if (dp[j]-distance[energyPair[j].index][energyPair[i].index] < 0) continue;
                dp[i] = Math.max(dp[i], dp[j]-distance[energyPair[j].index][energyPair[i].index]+energyPair[i].value);
            }
            res = Math.max(res, dp[i]);
        }

        PrintWriter out = new PrintWriter(new File("buffet.out"));
        System.out.println(res);
        out.println(res);
        out.close();
    }

    // A typical BFS from v, returns all distances from v to the other vertices.
    public static int[] bfs(int v) {

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[v] = 0;

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(v);

        while (q.size() > 0) {

            int cur = q.poll();
            int curD = dist[cur];

            for (int next: adjacencyList[cur]) {
                if (dist[next] == -1) {
                    q.offer(next);
                    dist[next] = curD+e;
                }
            }
        }

        return dist;
    }

    static class pair implements Comparable<pair> {

        public int value;
        public int index;


        public pair(int cost, int where) {
            value = cost;
            index = where;
        }

        public int compareTo(pair other) {
            return this.value - other.value;
        }

        public String toString() {
            return index+": "+value;

        }
    }

}


