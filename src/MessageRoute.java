import java.util.*;
import java.io.*;

public class MessageRoute {

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

        int n = sc.nextInt();
        int m = sc.nextInt();

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            linkedLists[a].add(b);
            linkedLists[b].add(a);
        }

        Queue<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        queue.add(new Pair(0, 0, -1));

        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        visited[0] = true;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            parent[p.node] = p.parent;
            if (p.node == n-1) break;

            for (int i: linkedLists[p.node]) {
                if (visited[i]) continue;
                visited[i] = true;
                queue.add(new Pair(i, p.distance + 1, p.node));
            }
        }

        int cur = n - 1;
        ArrayList<Integer> path = new ArrayList<>();

        while (cur != -1) {
            path.add(cur);
            cur = parent[cur];
        }

        if (path.size() != 1) {
            pw.println(path.size());

            Collections.reverse(path);

            for (int i : path) {
                pw.print(i + 1);
                pw.print(" ");
            }
        }

        else {
            pw.println("IMPOSSIBLE");
        }

        pw.close();
    }

    static class Pair {
        int node, distance, parent;

        Pair(int node, int distance, int parent) {
            this.node = node;
            this.distance = distance;
            this.parent = parent;
        }
    }
}


