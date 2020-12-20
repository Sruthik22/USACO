import java.util.*;
import java.io.*;

public class QuantumSuperposition {

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

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();

        boolean[][] dist_1 = new boolean[n1][5000];
        int[] in_degree_1 = new int[n1];
        ArrayList<Integer>[] edge_1 = new ArrayList[n1];
        ArrayList<Integer>[] backEdge_1 = new ArrayList[n1];

        for(int i = 0; i < n1; i++) {
            in_degree_1[i] = 0;
            edge_1[i] = new ArrayList<>();
            backEdge_1[i] = new ArrayList<>();
        }

        boolean[][] dist_2 = new boolean[n2][5000];
        int[] in_degree_2 = new int[n2];
        ArrayList<Integer>[] edge_2 = new ArrayList[n2];
        ArrayList<Integer>[] backEdge_2 = new ArrayList[n2];

        for(int i = 0; i < n2; i++) {
            in_degree_2[i] = 0;
            edge_2[i] = new ArrayList<>();
            backEdge_2[i] = new ArrayList<>();
        }
        for (int i = 0; i < m1; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            a--; b--;
            in_degree_1[b]++;
            edge_1[a].add(b);
            backEdge_1[b].add(a);
        }
        for (int i = 0; i < m2; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            a--; b--;
            in_degree_2[b]++;
            edge_2[a].add(b);
            backEdge_2[b].add(a);
        }

        dist_1 = compute(n1, in_degree_1, edge_1, backEdge_1);
        dist_2 = compute(n2, in_degree_2, edge_2, backEdge_2);

        boolean[] condense_dp = new boolean[5000];

        for (int j = 0; j < 5000; j++) {
            for (int k = 0; k < 5000; k++) {
                if (dist_1[n1 - 1][j] && dist_2[n2 - 1][k]) {
                    condense_dp[j + k] = true;
                }
            }
        }

        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {
            int query = sc.nextInt();
            if (condense_dp[query]) {
                pw.println("Yes");
            }

            else {
                pw.println("No");
            }
        }

        pw.println();
        pw.close();
    }

    //does a topological sort
    static boolean[][] compute(int n, int[] in_degree, ArrayList<Integer>[] edge, ArrayList<Integer>[] backEdge) {

        boolean[][] dist = new boolean[n][5000];

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
            for(int prev : backEdge[node]) {
                for (int i = 0; i < 5000; i++) {
                    if (dist[prev][i]) {
                        dist[node][i + 1] = true;
                    }
                }
            }

            if(node == 0) dist[node][0] = true;
        }

        return dist;
    }
}


