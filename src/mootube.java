import java.util.*;
import java.io.*;

public class mootube {

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
        sc = new InputReader(new FileInputStream("mootube.in"));
        pw = new PrintWriter(new File("mootube.out"));

        int n = sc.nextInt();
        int Q = sc.nextInt();

        TreeMap<Integer, ArrayList<Edge>> relevance = new TreeMap<>();

        for (int i = 0; i < n-1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int r = sc.nextInt();
            relevance.putIfAbsent(r, new ArrayList<>());
            relevance.get(r).add(new Edge(p, q));
        }

        Query[] queries = new Query[Q];

        for (int i = 0; i < Q; i++) {
            queries[i] = new Query(sc.nextInt(), sc.nextInt() - 1, i);
        }

        Arrays.sort(queries);

        int prevK = Integer.MAX_VALUE;

        DSU dsu = new DSU(n);
        int[] result = new int[Q];

        for (int i = 0; i < Q; i++) {
            Query cur = queries[i];
            NavigableMap<Integer, ArrayList<Edge>> c = relevance.subMap(cur.k, true, prevK, false);

            for (Map.Entry<Integer, ArrayList<Edge>> entry : c.entrySet()) {
                ArrayList<Edge> edges = entry.getValue();
                for (Edge e: edges) {
                    dsu.merge(e.a, e.b);
                }
            }

            result[cur.origIndex] = dsu.counts.get(dsu.find(cur.v));
            prevK = cur.k;
        }

        for (int i = 0; i < Q; i++) {
            pw.println(result[i] - 1);
        }
        pw.close();
    }

    static class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static class Query implements Comparable<Query>{
        int k, v;
        int origIndex;

        Query(int k, int v, int origIndex) {
            this.k = k;
            this.v = v;
            this.origIndex = origIndex;
        }

        @Override
        public int compareTo(Query o) {
            return -(this.k - o.k);
        }
    }
    static class DSU {
        HashMap<Integer, Integer> counts;
        int[] parents;
        DSU(int N) {
            parents = new int[N];
            counts = new HashMap<>();

            for (int i = 0; i < N; i++) {
                parents[i] = i;
                counts.put(i, 1);
            }
        }
        int find(int a) {
            if (parents[a] == a) return a;
            return parents[a] = find(parents[a]);
        }
        void merge(int a, int b) {
            int parent_a = find(a);
            int parent_b = find(b);
            if (parent_a != parent_b) {
                parents[parent_a] = parent_b;
                counts.put(parent_b, counts.get(parent_b) + counts.get(parent_a));
                counts.put(parent_a, 0);
            }
        }
    }
}


