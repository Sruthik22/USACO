// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class tracing {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    private static int n;
    private static LinkedList<Node>[] adjacencyList;
    private static ArrayList<Integer> possibleStart;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("tracing.in"));

        n = in.nextInt();
        int T = in.nextInt();

        String s = in.next();

        possibleStart = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') possibleStart.add(i);
        }

        Time[] times = new Time[T];

        for (int i = 0; i < T; i++) {
            int t = in.nextInt();
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;

            Time time = new Time(t, x, y);
            times[i] = time;
        }

        Arrays.sort(times);

        adjacencyList = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        for (Time t: times) {
            adjacencyList[t.x].add(new Node (t.y, t.t));
            adjacencyList[t.y].add(new Node (t.x, t.t));
        }

        int cowsWork = 0;
        int smallestK = Integer.MAX_VALUE;
        int largestK = Integer.MIN_VALUE;

        for (int i : possibleStart) {
            // i is a possible starting point -- we need to validate it

            int[] results = bfs(i);

            if (results[0] != -1) {
                cowsWork++;
                smallestK = Math.min(smallestK, results[0]);
                largestK = Math.max(largestK, results[1]);
            }

        }

        PrintWriter out = new PrintWriter(new File("tracing.out"));

        if (largestK == Integer.MAX_VALUE) {
            System.out.println(cowsWork + " " + smallestK + " Infinity");
            out.println(cowsWork + " " + smallestK + " Infinity");
        }

        else {
            System.out.println(cowsWork + " " + smallestK + " " + largestK);
            out.println(cowsWork + " " + smallestK + " " + largestK);
        }

        out.close();
    }

    // A typical BFS from v, returns all distances from v to the other vertices.
    public static int[] bfs(int v) {

        int[] ks = new int[n]; // holds the k values
        Arrays.fill(ks, -1);
        ks[v] = 0;

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(v);

        int time = 0;

        int maxBoundK = Integer.MAX_VALUE;

        while (q.size() > 0) {

            int cur = q.poll();

            for (Node next: adjacencyList[cur]) {
                int k = 0;
                if (ks[next.to] == -1) {
                    if (next.time < time) continue;
                    if (possibleStart.contains(next.to)) {
                        if (k + 1 >= maxBoundK) {
                            // this doesn't work
                            return new int[] {-1, -1};
                        }
                        ks[next.to] = ++k;
                        time = next.time;
                        q.offer(next.to);
                    }
                    else {
                        // doesn't contain it, need to break
                        maxBoundK = k;
                        break;
                    }
                }
            }
        }

        int min = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // check each of the values of k
            if (ks[i] == -1 && possibleStart.contains(i)) {
                // then this doesn't work
                return new int[] {-1, -1};
            }

            if (possibleStart.contains(i)) {
                min = Math.max(min, ks[i]);
            }
        }

        return new int[] {min, maxBoundK};
    }

    static class Node {
        int to, time;

        Node (int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    static class Time implements Comparable<Time> {
        int t, x, y;

        Time (int t, int x, int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Time o) {
            return this.t - o.t;
        }
    }
}


