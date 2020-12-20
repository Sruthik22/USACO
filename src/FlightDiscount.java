import java.util.*;
import java.io.*;

public class FlightDiscount {

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

    static long[] distance_to;
    static long[] distance_from;

    static LinkedList<Edge>[] corDir;
    static LinkedList<Edge>[] backDir;

    static int n;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        int m = sc.nextInt();

        corDir = new LinkedList[n];
        backDir = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            corDir[i] = new LinkedList<>();
            backDir[i] = new LinkedList<>();
        }

        full_Edge[] edge = new full_Edge[m];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();

            edge[i] = new full_Edge(a, b, c);

            corDir[a].add(new Edge(b, c));
            backDir[b].add(new Edge(a, c));
        }

        distance_to = new long[n];
        distance_from = new long[n];

        compute_1();
        compute_2();

        for (int i = 0; i < n; i++) {
            if (distance_from[i] < 0) System.out.println(i);
        }

        long result = Long.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            full_Edge full_edge = edge[i];
            int a = full_edge.a;
            int b = full_edge.b;
            long cur = distance_to[a] + distance_from[b] + (long) (full_edge.weight / 2);
            if (cur < 0) continue;
            result = Math.min(result, cur);
        }

        pw.println(result);
        pw.close();
    }

    static class full_Edge {
        int a, b, weight;

        full_Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }
    static class Element implements Comparable<Element> {
        int node;

        Element(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(distance_to[node], distance_to[o.node]);
        }
    }
    static class Elem implements Comparable<Elem> {
        int node;

        Elem(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Elem o) {
            return Long.compare(distance_from[node], distance_from[o.node]);
        }
    }
    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static void compute_1() {
        boolean[] visited = new boolean[n];
        Arrays.fill(distance_to, Long.MAX_VALUE);

        distance_to[0] = 0;

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Element(0));

        while (!priorityQueue.isEmpty()) {
            Element node = priorityQueue.poll();
            if (visited[node.node]) continue;
            visited[node.node] = true;

            for (Edge e: corDir[node.node]) {
                if (visited[e.node]) continue;
                if (distance_to[e.node] > distance_to[node.node] + (long) e.weight) {
                    distance_to[e.node] = distance_to[node.node] + (long) e.weight;
                    priorityQueue.add(new Element(e.node));
                }
            }
        }
    }
    static void compute_2() {
        boolean[] visited = new boolean[n];
        Arrays.fill(distance_from, Long.MAX_VALUE);

        distance_from[n-1] = 0;

        PriorityQueue<Elem> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Elem(n-1));

        while (!priorityQueue.isEmpty()) {
            Elem node = priorityQueue.poll();
            if (visited[node.node]) continue;
            visited[node.node] = true;

            for (Edge e: backDir[node.node]) {
                if (visited[e.node]) continue;
                if (distance_from[e.node] > distance_from[node.node] + (long) e.weight) {
                    distance_from[e.node] = distance_from[node.node] + (long) e.weight;
                    priorityQueue.add(new Elem(e.node));
                }
            }
        }
    }
}