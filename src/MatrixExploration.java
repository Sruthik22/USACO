import java.util.*;
import java.io.*;

public class MatrixExploration {

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

    public static void main(String[] args) {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        Pair[] pairs = new Pair[k];

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;

            pairs[i] = new Pair(x, y);
        }

        int[][] distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] x = new int[] {1, 0, -1, 0};
        int[] y = new int[] {0, -1, 0, 1};

        Queue<Pair> queue = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            queue.add(pairs[i]);
            distance[pairs[i].x][pairs[i].y] = 0;
        }

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int j = 0; j < 4; j++) {
                int new_x = p.x + x[j];
                int new_y = p.y + y[j];

                if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < m) {
                    if (grid[new_x][new_y] == '#' || distance[new_x][new_y] <= distance[p.x][p.y] + 1) continue;
                    distance[new_x][new_y] = distance[p.x][p.y] + 1;
                    queue.add(new Pair(new_x, new_y));
                }
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dist = distance[i][j];
                if (dist == Integer.MAX_VALUE) continue;
                result += dist;
            }
        }

        pw.println(result);
        pw.close();
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


