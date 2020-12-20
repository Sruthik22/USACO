import java.util.*;
import java.io.*;

public class dining {

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

    static int[] distance;
    static int[] newDistance;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("dining.in"));
        pw = new PrintWriter(new File("dining.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        LinkedList<Edge>[] linkedLists = new LinkedList[n + 1];

        for (int i = 0; i <= n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int t = sc.nextInt();

            linkedLists[a].add(new Edge(b, t));
            linkedLists[b].add(new Edge(a, t));
        }

        Haybale[] haybales = new Haybale[k];

        for (int i = 0; i < k; i++) {
            haybales[i] = new Haybale(sc.nextInt() - 1, sc.nextInt());
        }

        distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[n-1] = 0;

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Element(n-1));

        while (!priorityQueue.isEmpty()) {
            Element node = priorityQueue.poll();

            for (Edge edge : linkedLists[node.node]) {
                if (distance[edge.node] > distance[node.node] + edge.t) {
                    distance[edge.node] = distance[node.node] + edge.t;
                    priorityQueue.add(new Element(edge.node));
                }
            }
        }

        for (int i = 0; i < k; i++) {
            Haybale haybale = haybales[i];
            linkedLists[n].add(new Edge(haybale.index, distance[haybale.index] - haybale.y));
        }

        newDistance = new int[n + 1];
        Arrays.fill(newDistance, Integer.MAX_VALUE);
        newDistance[n] = 0;

        PriorityQueue<Elem> pq = new PriorityQueue<>();
        pq.add(new Elem(n));

        while (!pq.isEmpty()) {
            Elem node = pq.poll();

            for (Edge edge : linkedLists[node.node]) {
                if (newDistance[edge.node] > newDistance[node.node] + edge.t) {
                    newDistance[edge.node] = newDistance[node.node] + edge.t;
                    pq.add(new Elem(edge.node));
                }
            }
        }

        for (int i = 0; i < n-1; i++) {
            if (newDistance[i] <= distance[i]) {
                pw.println(1);
            }

            else {
                pw.println(0);
            }
        }
        pw.close();
    }

    static class Element implements Comparable<Element> {
        int node;

        Element(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Element o) {
            return distance[this.node] - distance[o.node];
        }
    }
    static class Elem implements Comparable<Elem> {
        int node;

        Elem(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Elem o) {
            return newDistance[this.node] - newDistance[o.node];
        }
    }

    static class Edge {
        int node, t;

        Edge(int node, int t) {
            this.node = node;
            this.t = t;
        }
    }

    static class Haybale {
        int index, y;

        Haybale(int index, int y) {
            this.index = index;
            this.y = y;
        }
    }
}


