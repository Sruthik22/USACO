import java.util.*;
import java.io.*;

public class shortcut {

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Float.parseFloat(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class CPMath {
        static int add(int a, int b) {
            a += b;

            if (a >= mod) a -= mod;

            return a;
        }

        static int sub(int a, int b) {
            a -= b;
            if (a < 0) a += mod;
            return a;
        }

        static int multiply(int a, long b) {
            b = a * b;
            return (int) (b % mod);
        }

        static int divide(int a, int b) {
            return multiply(a, inverse(b));
        }

        static int inverse(int a) {
            return power(a, mod - 2);
        }

        static int power(int a, int b) {
            int r = 1;

            while (b > 0) {
                if (b % 2 == 1) {
                    r = multiply(r, a);
                }

                a = multiply(a, a);
                b /= 2;
            }

            return r;
        }
    }

    static InputReader sc;
    static PrintWriter pw;

    static int mod = (int) (1e9 + 7);

    static long[] distance;

    static LinkedList<Integer>[] djikTree;
    static boolean[] visited;
    static int[] cows_here;
    static int[] numCows;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("shortcut.in"));
        pw = new PrintWriter(new File("shortcut.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();

        numCows = new int[n];

        for (int i = 0; i < n; i++) {
            numCows[i] = sc.nextInt();
        }

        LinkedList<Edge>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();

            linkedLists[a].add(new Edge(b, c));
            linkedLists[b].add(new Edge(a, c));
        }

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();
        distance = new long[n];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[0] = 0;
        priorityQueue.add(new Element(0));

        int[] parent = new int[n];
        parent[0] = -1;
        cows_here = new int[n];

        while (!priorityQueue.isEmpty()) {
            Element node = priorityQueue.poll();

            for (Edge edge: linkedLists[node.node]) {

                // if something has equal length, then if the parent is

                if (distance[edge.node] == distance[node.node] + edge.weight) {
                    parent[edge.node] = Math.min(node.node, parent[edge.node]);
                }

                if (distance[edge.node] > distance[node.node] + edge.weight) {
                    distance[edge.node] = distance[node.node] + edge.weight;
                    parent[edge.node] = node.node;
                    priorityQueue.add(new Element(edge.node));
                }
            }
        }

        djikTree = new LinkedList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            djikTree[i] = new LinkedList<>();
        }

        for (int i = 1; i < n; i++) {
            djikTree[i].add(parent[i]);
            djikTree[parent[i]].add(i);
        }

        dfs(0, -1);

        /*for (int i = 0; i < n; i++) {
            int cur = i;
            while (cur != -1) {
                cows_here[cur] += numCows[i];
                cur = parent[cur];
            }
        }*/

        long result = 0;

        for (int i = 0; i < n; i++) {
            result = Math.max(result, (distance[i] - t) * (long) ((cows_here[i])));
        }

        pw.println(result);

        pw.close();
    }

    static void dfs(int node, int parent) {
        if (visited[node]) return;
        visited[node] = true;

        int sum = numCows[node];

        for (int i: djikTree[node]) {
            if (i != parent) {
                dfs(i, node);
                sum += cows_here[i];
            }
        }

        cows_here[node] = sum;
    }

    static class Element implements Comparable<Element> {
        int node;

        Element(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(distance[this.node], distance[o.node]);
        }
    }
    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}


