import java.util.*;
import java.io.*;

public class GoodSubarrays {

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

        int t = sc.nextInt();

        for (int tt = 0; tt < t; tt++) {
            int n = sc.nextInt();
            String s = sc.next();

            long[] prefixSums = new long[n + 1];

            for (int i = 1; i <= n; i++) {
                int digit = Character.getNumericValue(s.charAt(i - 1));
                prefixSums[i] = digit + prefixSums[i-1];
            }

            HashMap<Long, Integer> nums = new HashMap<>();
            nums.put(0L, 1);
            long result = 0;

            for (int i = 1; i <= n; i++) {
                long val = prefixSums[i] - i;
                result += nums.getOrDefault(val, 0);
                nums.put(val, nums.getOrDefault(val, 0) + 1);
            }

            pw.println(result);
        }

        pw.close();
    }
}


