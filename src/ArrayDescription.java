import java.util.*;
import java.io.*;

public class ArrayDescription {

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

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[][] dp = new int[n][m + 1];

        if (arr[0] == 0) {
            for (int i = 1; i <= m; i++) {
                dp[0][i] = 1;
            }
        }

        else {
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int curVal = arr[i];

            if (curVal == 0) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = CPMath.add(dp[i][j], dp[i - 1][j]);
                    dp[i][j] = CPMath.add(dp[i][j], dp[i - 1][j - 1]);
                    if (j != m) dp[i][j] = CPMath.add(dp[i][j], dp[i - 1][j + 1]);
                }
            }

            else {
                dp[i][curVal] = CPMath.add(dp[i][curVal], dp[i - 1][curVal]);
                dp[i][curVal] = CPMath.add(dp[i][curVal], dp[i - 1][curVal - 1]);
                if (curVal != m) dp[i][curVal] = CPMath.add(dp[i][curVal], dp[i - 1][curVal + 1]);
            }
        }

        int result = 0;

        for (int i = 1; i <= m; i++) {
            result = CPMath.add(result, dp[n-1][i]);
        }

        pw.println(result);
        pw.close();
    }
}


