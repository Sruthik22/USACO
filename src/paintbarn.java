import java.util.*;
import java.io.*;

public class paintbarn {

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
        sc = new InputReader(new FileInputStream("paintbarn.in"));
        pw = new PrintWriter(new File("paintbarn.out"));

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] forest = new int[1001][1001];

        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            forest[x2][y2] += 1;
            forest[x2][y1] -= 1;
            forest[x1][y2] -= 1;
            forest[x1][y1] += 1;
        }

        int[][] prefix = new int[1002][1002];

        for (int i = 1; i <= 1001; i++) {
            for (int j = 1; j <= 1001; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j-1] - prefix[i-1][j-1] + forest[i-1][j-1];
            }
        }

        int total = 0;

        for (int i = 1; i <= 1001; i++) {
            for (int j = 1; j <= 1001; j++) {
                if (prefix[i][j] == k) total++;
            }
        }

        pw.println(total);
        pw.close();
    }
}