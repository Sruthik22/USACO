import java.util.*;
import java.io.*;

public class CourseScheduleII {

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

    static int[] out_degree;
    static ArrayList<Integer>[] edge;
    static int n, m;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();

        out_degree = new int[n];
        edge = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            out_degree[a]++;
            edge[b].add(a);
        }

        ArrayList<Integer> topSort = compute();
        Collections.reverse(topSort);

        for (int i : topSort) {
            pw.print(i + 1);
            if (i != topSort.get(topSort.size() - 1)) pw.print(" ");
        }

        pw.close();
    }

    static ArrayList<Integer> compute() {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; i++) {
            if(out_degree[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> topSort = new ArrayList<>();

        while(!q.isEmpty()) {
            int node = q.poll();
            topSort.add(node);

            for(int next : edge[node]) {
                out_degree[next]--;
                if(out_degree[next] == 0) q.add(next);
            }
        }

        return topSort;
    }
}


