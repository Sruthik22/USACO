import java.util.*;
import java.io.*;

public class closing {

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
        sc = new InputReader(new FileInputStream("closing.in"));
        pw = new PrintWriter(new File("closing.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int b1 = sc.nextInt() - 1;
            int b2 = sc.nextInt() - 1;
            edges[i] = new Edge(b1, b2);
        }

        int[] added = new int[n];
        int[] when = new int[n];

        for (int i = 0; i < n; i++) {
            added[i] = sc.nextInt() - 1;
            when[added[i]] = i;
        }

        HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();

        for (int i = 0; i < m; i++) {
            Edge edge = edges[i];
            adjacencyList.putIfAbsent(edge.a, new ArrayList<>());
            adjacencyList.putIfAbsent(edge.b, new ArrayList<>());

            if (when[edge.a] < when[edge.b]) {
                adjacencyList.get(edge.a).add(edge.b);
            }

            else {
                adjacencyList.get(edge.b).add(edge.a);
            }
        }

        // we need to check that all of the values that are within the DSU

        DSU dsu = new DSU(n);

        boolean[] answer = new boolean[n];

        int count = 0;

        for (int i = n - 1; i >= 0; i--) {
            int add = added[i];
            count++;

            if (adjacencyList.containsKey(add)) {
                for (int j: adjacencyList.get(add)) {
                    if (dsu.find(j) != dsu.find(add)) {
                        dsu.merge(j, add);
                        count--;
                    }
                }
            }

            answer[i] = (count <= 1);
        }

        for (int i = 0; i < n; i++) {
            if (answer[i]) pw.println("YES");
            else pw.println("NO");
        }

        pw.close();
    }

    static class DSU {
        int[] parents;
        DSU(int N) {
            parents = new int[N];

            for (int i = 0; i < N; i++) {
                parents[i] = i;
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
            }
        }
    }

    static class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}


