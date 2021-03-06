import java.util.*;
import java.io.*;

public class GraphGirth {

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
        int result = Integer.MAX_VALUE;
        int[] distance = new int[n];

        for (int node = 0; node < n; node++) {
            Arrays.fill(distance, Integer.MAX_VALUE);
            queue.add(new Pair(node, -1));
            distance[node] = 0;
            while (!queue.isEmpty()) {
                Pair p = queue.poll();
                for (int i : linkedLists[p.curNode]) {
                    if (i == p.parent) continue;
                    if (distance[i] != Integer.MAX_VALUE) {
                        result = Math.min(result, distance[p.curNode] + distance[i] + 1);
                    }
                    else {
                        distance[i] = 1 + distance[p.curNode];
                        queue.add(new Pair(i, p.curNode));
                    }
                }
            }
        }

        if (result == Integer.MAX_VALUE) {
            pw.println(-1);
        }
        else {
            pw.println(result);
        }
        pw.close();
    }

    static class Pair {
        int curNode, parent;
        Pair(int curNode, int parent) {
            this.curNode = curNode;
            this.parent = parent;
        }
    }
}


