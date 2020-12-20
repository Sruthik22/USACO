import java.util.*;
import java.io.*;

public class FlightRoutes {

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

    static ArrayList<Long>[] distance;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        LinkedList<Edge>[] linkedList = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedList[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();

            linkedList[a].add(new Edge(b, c));
        }

        distance = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            distance[i] = new ArrayList<>();
        }
        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Element(0, 0));

        while (!priorityQueue.isEmpty()) {
            Element element = priorityQueue.poll();

            if (distance[element.node].size() >= k) continue;
            distance[element.node].add(element.value);

            for (Edge edge: linkedList[element.node]) {
                priorityQueue.add(new Element(edge.node, element.value + edge.weight));
            }
        }

        for (int i = 0; i < k; i++) {
            pw.print(distance[n-1].get(i) + " ");
        }
        pw.close();
    }

    static class Element implements Comparable<Element>{
        int node;
        long value;

        Element(int node, long value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(this.value, o.value);
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


