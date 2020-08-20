import java.util.*;
import java.io.*;

public class StampPainting {

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
        sc = new InputReader(new FileInputStream("spainting.in"));
        pw = new PrintWriter(new File("spainting.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[] dp = new int[n];

        int val = 1;

        for (int i = 1; i <= k - 1; i++) {
            val = CPMath.multiply(val, m);
            dp[i - 1] = val;
        }

        int slidingWindow = 0;

        for (int i = 1; i <= k - 1; i++) {
            slidingWindow = CPMath.add(slidingWindow, dp[i - 1]);
        }

        for (int i = k; i <= n; i++) {
            dp[i - 1] = CPMath.multiply(m-1, slidingWindow);

            slidingWindow = CPMath.add(slidingWindow, dp[i - 1]);
            slidingWindow = CPMath.sub(slidingWindow, dp[i - k]);
        }

        int result = 1;

        for (int i = 0; i < n; i++) {
            result = CPMath.multiply(result, m);
        }

        result = CPMath.sub(result, dp[n-1]);

        pw.println(result);
        pw.close();
    }
}


