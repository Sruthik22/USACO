import java.util.*;
import java.io.*;

public class OracAndLCM {

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

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        long[] prefixGCD = new long[n];

        for (int i = 0; i < n; i++) {
            // calculate the gcd

            if (i == 0) {
                prefixGCD[i] = nums[i];
            }

            else {
                prefixGCD[i] = gcd(prefixGCD[i-1], nums[i]);
            }

        }

        long[] postfixGCD = new long[n];

        for (int i = n-1; i >= 0; i--) {
            if (i == n-1) {
                postfixGCD[i] = nums[i];
            }

            else {
                postfixGCD[i] = gcd(postfixGCD[i + 1], nums[i]);
            }
        }

        long[] result = new long[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                result[i] = postfixGCD[i + 1];
            }

            else if (i == n-1) {
                result[i] = prefixGCD[i - 1];
            }

            else {
                result[i] = gcd(prefixGCD[i - 1], postfixGCD[i + 1]);
            }
        }

        long result_f = 1;

        for (int i = 0; i < n; i++) {
            result_f = lcm(result_f, result[i]);
        }

        pw.println(result_f);

        pw.close();
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static long gcd(long a, long b){
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}


