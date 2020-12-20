import java.util.*;
import java.io.*;

public class Crocodile {

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

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("Crocodile.in"));
        pw = new PrintWriter(new File("Crocodile.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] r = new int[m][2];
        int[] l = new int[m];

        for (int i = 0; i < m; i++) {
            r[i][0] = sc.nextInt();
            r[i][1] = sc.nextInt();
            l[i] = sc.nextInt();
        }

        int[] p = new int[k];

        for (int i = 0; i < k; i++) {
            p[i] = sc.nextInt();
        }

        pw.println(travel_plan(n, m, r, l, k, p));

        pw.close();
    }

    static int travel_plan(int n, int m, int[][] r, int[] l, int k, int[] p) {
        LinkedList<Edge>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = r[i][0];
            int b = r[i][1];
            int weight = l[i];
            linkedLists[a].add(new Edge(b, weight));
            linkedLists[b].add(new Edge(a, weight));
        }

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();

        int[] used = new int[n];

        for (int i = 0; i < k; i++) {
            int node = p[i];
            used[node] = 1;
            priorityQueue.add(new Element(node, 0));
        }

        int result = 0;

        while (!priorityQueue.isEmpty()) {
            Element node = priorityQueue.poll();

            if (++used[node.node] != 2) continue;

            if (node.node == 0) {
                result = node.value;
                break;
            }

            for (Edge edge: linkedLists[node.node]) {
                priorityQueue.add(new Element(edge.node, node.value + edge.weight));
            }
        }

        return result;
    }

    static class Element implements Comparable<Element> {
        int node, value;

        Element(int node, int value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(Element o) {
            return this.value - o.value;
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