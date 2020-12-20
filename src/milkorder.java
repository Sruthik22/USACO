import java.util.*;
import java.io.*;

public class milkorder {

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

    static int[] in_degree;
    static ArrayList<Integer>[] edge;
    static ArrayList<Integer>[] backEdge;
    static int n, m;

    static HashMap<Long, Integer> daysAfter;
    static Observation[] observations;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("milkorder.in"));
        pw = new PrintWriter(new File("milkorder.out"));

        n = sc.nextInt();
        m = sc.nextInt();

        observations = new Observation[m];

        for (int i = 0; i < m; i++) {
            int mi = sc.nextInt();

            ArrayList<Integer> ob = new ArrayList<>();

            for (int j = 0; j < mi; j++) {
                ob.add(sc.nextInt() - 1);
            }

            observations[i] = new Observation(ob);
        }

        int low = -1;
        int high = m;
        ArrayList<Integer> topSort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            topSort.add(i);
        }

        while (high - low > 1) {
            int mid = (low + high)/2;
            ArrayList<Integer> a = check(mid);
            if (a.size() == n) {
                topSort = a;
                low = mid;
            }
            else high = mid;
        }

        for (int i : topSort) {
            pw.print(i + 1);

            if (i != topSort.get(topSort.size() - 1)) pw.print(" ");
        }

        pw.close();
    }

    static class Observation {
        ArrayList<Integer> a;

        Observation(ArrayList<Integer> a) {
            this.a = a;
        }
    }

    static ArrayList<Integer> check(int val) {
        in_degree = new int[n];
        edge = new ArrayList[n];
        backEdge = new ArrayList[n];
        daysAfter = new HashMap<>();

        for(int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
            backEdge[i] = new ArrayList<>();
        }

        for (int i = 0; i < val; i++) {
            Observation o = observations[i];

            int cur = o.a.get(0);

            for (int j = 1; j < o.a.size(); j++) {
                int b = o.a.get(j);
                in_degree[b]++;
                edge[cur].add(b);
                backEdge[b].add(cur);
                cur = b;
            }
        }

        return compute();
    }

    static ArrayList<Integer> compute() {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            if(in_degree[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> topSort = new ArrayList<>();

        while(!q.isEmpty()) {
            int node = q.poll();
            topSort.add(node);

            Collections.sort(edge[node]);

            for(int next : edge[node]) {
                in_degree[next]--;
                if(in_degree[next] == 0) q.add(next);
            }
        }

        return topSort;
    }
}


