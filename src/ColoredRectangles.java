import java.util.*;
import java.io.*;

public class ColoredRectangles {

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

        int R = sc.nextInt();
        int G = sc.nextInt();
        int B = sc.nextInt();

        Integer[] rs = new Integer[R];
        for (int i = 0; i < R; i++) {
            rs[i] = sc.nextInt();
        }

        Integer[] gs = new Integer[G];

        for (int i = 0; i < G; i++) {
            gs[i] = sc.nextInt();
        }

        Integer[] bs = new Integer[B];

        for (int i = 0; i < B; i++) {
            bs[i] = sc.nextInt();
        }

        Arrays.sort(rs, Collections.reverseOrder());
        Arrays.sort(bs, Collections.reverseOrder());
        Arrays.sort(gs, Collections.reverseOrder());

        int[][][] dp = new int[R + 1][G + 1][B + 1];

        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= G; j++) {
                for (int k = 0; k <= B; k++) {
                    int lastI = i - 1;
                    int lastJ = j - 1;
                    int lastK = k - 1;

                    if ((lastI < 0 && lastJ < 0) || (lastK < 0 && lastI < 0) || (lastK < 0 && lastJ < 0)) continue;

                    if ((lastI >= 0 && lastJ >= 0)) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[lastI][lastJ][k] + rs[lastI] * gs[lastJ]);
                    }

                    if ((lastI >= 0 && lastK >= 0)) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[lastI][j][lastK] + rs[lastI] * bs[lastK]);
                    }

                    if ((lastK >= 0 && lastJ >= 0)) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][lastJ][lastK] + gs[lastJ] * bs[lastK]);
                    }
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= G; j++) {
                for (int k = 0; k <= B; k++) {
                    result = Math.max(result, dp[i][j][k]);
                }
            }
        }

        pw.println(result);
        pw.close();
    }
}


