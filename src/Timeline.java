import java.util.*;
import java.io.*;

public class Timeline {

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

    static int[] earliest_time;
    static long[] dist;
    static int[] in_degree;
    static ArrayList<Integer>[] edge;
    static ArrayList<Integer>[] backEdge;
    static int N, M;

    static HashMap<Long, Integer> daysAfter;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new InputReader(new FileInputStream("timeline.in"));
        pw = new PrintWriter(new File("timeline.out"));

        N = sc.nextInt();
        M = sc.nextInt();
        int c = sc.nextInt();

        earliest_time = new int[N];

        for (int i = 0; i < N; i++) {
            earliest_time[i] = sc.nextInt();
        }

        dist = new long[N];
        in_degree = new int[N];
        edge = new ArrayList[N];
        backEdge = new ArrayList[N];
        daysAfter = new HashMap<>();

        for(int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
            in_degree[i] = 0;
            edge[i] = new ArrayList<>();
            backEdge[i] = new ArrayList<>();
        }

        for (int i = 0; i < c; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int x = sc.nextInt();

            if (daysAfter.containsKey(hash(a, b))) {
                daysAfter.put(hash(a, b), Math.max(daysAfter.get(hash(a, b)), x));
            }

            else daysAfter.put(hash(a, b), x);

            in_degree[b]++;
            edge[a].add(b);
            backEdge[b].add(a);
        }

        compute();

        for (int i = 0; i < N; i++) {
            pw.println(dist[i]);
        }

        pw.close();
    }

    static long hash(long a, long b) {
        return mod * a + b;
    }

    static void compute() {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
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

            long mx = 0;
            for(int prev : backEdge[node]) {
                mx = Math.max(mx, dist[prev] + daysAfter.get(hash(prev, node)));
            }

            dist[node] = Math.max(earliest_time[node], mx);
        }
    }
}


