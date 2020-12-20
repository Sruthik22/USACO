import java.util.*;
import java.io.*;

public class pump {

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

    static int n;
    static long[] distance;
    static LinkedList<Pipe>[] linkedLists;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("pump.in"));
        pw = new PrintWriter(new File("pump.out"));

        n = sc.nextInt();
        int m = sc.nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();
            int f = sc.nextInt();

            linkedLists[a].add(new Pipe(b, c, f));
            linkedLists[b].add(new Pipe(a, c, f));
        }

        double result = 0;

        for (int i = 0; i < 1000; i++) {
            // i is the low value, we will only pick larger flow valued edges

            compute(i);

            result = Math.max(result, i / (double) distance[n-1]);
        }

        int ans = (int) ((int) (1e6) * result);

        pw.println(ans);
        pw.close();
    }

    static class Element implements Comparable<Element> {
        int node;

        Element(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(distance[node], distance[o.node]);
        }
    }

    static void compute(int min_flow) {
        boolean[] visited = new boolean[n];

        distance = new long[n];

        Arrays.fill(distance, Long.MAX_VALUE);

        distance[0] = 0;

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Element(0));

        while (!priorityQueue.isEmpty()) {
            Element node = priorityQueue.poll();
            if (visited[node.node]) continue;
            visited[node.node] = true;

            for (Pipe e: linkedLists[node.node]) {
                if (visited[e.node]) continue;

                // we need to check if we are allowed to visit this node, is this flow value greater than the min

                if (e.f >= min_flow) {
                    if (distance[e.node] > distance[node.node] + e.c) {
                        distance[e.node] = distance[node.node] + e.c;
                        priorityQueue.add(new Element(e.node));
                    }
                }
            }
        }
    }

    static class Pipe {
        int node, c, f;

        Pipe(int node, int c, int f) {
            this.node = node;
            this.c = c;
            this.f = f;
        }
    }
}


