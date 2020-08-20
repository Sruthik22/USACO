import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Exercise {

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
        sc = new InputReader(new FileInputStream("exercise.in"));
        pw = new PrintWriter(new File("exercise.out"));

        int n = sc.nextInt();
        mod = sc.nextInt();

        ArrayList<Integer> primes = sieveOfEratosthenes(n + 1);

        int[][] dp = new int[primes.size() + 1][n + 1];

        if (primes.size() == 0) {
            pw.println(1);
            pw.close();
            return;
        }

        dp[0][0] = 1;

        for (int i = 1; i <= primes.size(); i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];

                int pp = primes.get(i - 1);

                while (pp <= j) {
                    dp[i][j] = CPMath.add(dp[i][j], CPMath.multiply(dp[i - 1][j - pp], pp));
                    pp = CPMath.multiply(pp, primes.get(i-1));
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= n; i++) {
            result = CPMath.add(result, dp[primes.size()][i]);
        }

        pw.println(result);
        pw.close();
    }

    private static ArrayList<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n+1];

        for(int i=0;i<n;i++) prime[i] = true;

        for(int p = 2; p*p <=n; p++) {
            if(prime[p]) {
                for(int i = p*p; i <= n; i += p) prime[i] = false;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 2; i <= n; i++) {
            if(prime[i]) result.add(i);
        }

        return result;
    }
}


