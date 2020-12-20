import java.util.*;
import java.io.*;

public class GameRoute {

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
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();

        dist = new int[n];
        in_degree = new int[n];
        edge = new ArrayList[n];
        backEdge = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            dist[i] = 0;
            in_degree[i] = 0;
            edge[i] = new ArrayList<>();
            backEdge[i] = new ArrayList<>();
        }

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            a--; b--;
            in_degree[b]++;
            edge[a].add(b);
            backEdge[b].add(a);
        }

        compute();

        pw.println(dist[n-1]);
        pw.close();
    }

    static int dist[], in_degree[];
    static ArrayList<Integer> edge[];
    static ArrayList<Integer> backEdge[];

    static int n, m;

    //does a topological sort
    static void compute() {
        Queue<Integer> q = new ArrayDeque<Integer>();
        for(int i = 0; i < n; i++) {
            if(in_degree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int node = q.poll();

            for(int next : edge[node]) {
                in_degree[next]--;
                if(in_degree[next] == 0) q.add(next);
            }

            //The below block computes the DP
            int mx = 0;
            for(int prev : backEdge[node]) {
                mx = CPMath.add(mx, dist[prev]);
            }

            dist[node] = mx;
            if(node == 0) dist[node] = 1;
        }
    }
}


