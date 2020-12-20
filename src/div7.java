import java.util.*;
import java.io.*;

public class div7 {

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

    static int mod = 7;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("div7.in"));
        pw = new PrintWriter(new File("div7.out"));

        int n = sc.nextInt();

        int[] cows = new int[n + 1];

        int[] first = new int[7];
        Arrays.fill(first, -1);
        int[] last = new int[7];
        Arrays.fill(last, -1);

        for (int i = 1; i <= n; i++) {
            cows[i] = (cows[i-1] + sc.nextInt()) % 7;

            if (first[cows[i]] == -1) first[cows[i]] = i;
            last[cows[i]] = i;
        }

        int max = 0;

        for (int i = 0; i < 7; i++) {
            max = Math.max(max, last[i] - first[i]);
        }

        pw.println(max);
        pw.close();
    }
}