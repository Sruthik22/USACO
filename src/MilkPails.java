import java.util.*;
import java.io.*;

public class MilkPails {

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
        sc = new InputReader(new FileInputStream("pails.in"));
        pw = new PrintWriter(new File("pails.out"));

        int x = sc.nextInt();
        int y = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();

        int[][] dp = new int[x + 1][y + 1];

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(0, 0, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (p.opNumber >= dp[p.row][p.col]) continue;
            dp[p.row][p.col] = p.opNumber;

            queue.add(new Pair(0, p.col, p.opNumber + 1));
            queue.add(new Pair(p.row, 0, p.opNumber + 1));
            queue.add(new Pair(x, p.col, p.opNumber + 1));
            queue.add(new Pair(p.row, y, p.opNumber + 1));

            int total = p.row + p.col;

            queue.add(new Pair(Math.min(total, x), Math.max(0, total - x), p.opNumber + 1));
            queue.add(new Pair(Math.max(0, total - y), Math.min(total, y), p.opNumber + 1));
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y ;j++) {
                if (dp[i][j] <= k) result = Math.min(result, Math.abs((i + j) - m));
            }
        }

        pw.println(result);
        pw.close();
    }

    static class Pair {
        int row, col, opNumber;

        Pair(int row, int col, int opNumber) {
            this.row = row;
            this.col = col;
            this.opNumber = opNumber;
        }
    }
}


