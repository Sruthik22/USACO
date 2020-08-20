import java.util.*;
import java.io.*;

public class MoortalCowmbat {

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

    static int m;
    static int[][] dist;
    static int[][] cheapest_path;

    static int INF = 100000000;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("cowmbat.in"));
        pw = new PrintWriter(new File("cowmbat.out"));

        int n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt();

        String s = sc.next();

        dist = new int[m][m];

        // cost changing letter [i] -> [j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = sc.nextInt();
            }
        }

        cheapest_path = new int[m][m];

        floydWarshall();

        // we should precompute the prefixsums

        int[][] prefixSums = new int[n + 1][m];

        for (int i = 0; i < n; i++) {
            int curLet = s.charAt(i) - 'a';

            for (int j = 0; j < m; j++) {
                // j is the current letter we are turning into
                prefixSums[i + 1][j] = prefixSums[i][j] + cheapest_path[curLet][j];
            }
        }

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = INF;
            }
        }

        int[] dp_min = new int[n];

        for (int i = 0; i < n; i++) {
            dp_min[i] = INF;
        }

        // base case
        for (int j = 0; j < m; j++) {
            dp[k-1][j] = prefixSums[k][j];
            dp_min[k-1] = Math.min(dp_min[k-1], dp[k-1][j]);
        }

        // dp

        for (int i = k; i < n; i++) { // prefix index
            for (int j = 0; j < m; j++) { // current letter
                dp[i][j] = Math.min(dp[i-1][j] + cheapest_path[s.charAt(i) - 'a'][j],
                        dp_min[i - k] + prefixSums[i + 1][j] - prefixSums[i - k + 1][j]);

                dp_min[i] = Math.min(dp_min[i], dp[i][j]);
            }
        }

        // output

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            result = Math.min(result, dp[n-1][i]);
        }

        pw.println(result);

        pw.close();
    }

    static void floydWarshall() {
        int i, j, k;
        for (k = 0; k < m; k++)
        {
            for (i = 0; i < m; i++)
            {
                for (j = 0; j < m; j++)
                {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        cheapest_path = dist.clone();
    }
}


