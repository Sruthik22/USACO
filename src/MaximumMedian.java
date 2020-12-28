import java.util.*;
import java.io.*;

public class MaximumMedian {

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

    static int k;
    static int[] uppers;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        k = sc.nextInt();

        int[] integers = new int[n];

        for (int i = 0; i < n; i++) {
            integers[i] = sc.nextInt();
        }

        Arrays.sort(integers);

        uppers = Arrays.copyOfRange(integers, n / 2, n);

        long low = uppers[0];
        long high = uppers[0] + k + 1;

        while (high - low > 1) {
            long mid = (low + high)/2;
            if (check(mid)) low = mid;
            else high = mid;
        }

        pw.println(low);
        pw.close();
    }

    static boolean check(long median) {
        int total = 0;
        for (int i : uppers) {
            if (i < median) {
                total += median - i;
                if (total > k) return false;
            }

            else break;
        }

        return total <= k;
    }
}