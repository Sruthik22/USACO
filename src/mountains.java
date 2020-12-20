import java.util.*;
import java.io.*;

public class mountains {

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
        sc = new InputReader(new FileInputStream("mountains.in"));
        pw = new PrintWriter(new File("mountains.out"));

        int n = sc.nextInt();

        Mountain[] mountains = new Mountain[n];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            mountains[i] = new Mountain(a-b, a+b);
        }

        Arrays.sort(mountains);

        int farthest_right = Integer.MIN_VALUE;

        int result = 0;

        for (int i = 0; i < n; i++) {
            Mountain cur = mountains[i];

            if (cur.end > farthest_right) {
                result++;
            }

            farthest_right = Math.max(cur.end, farthest_right);
        }

        pw.println(result);
        pw.close();
    }

    static class Mountain implements Comparable<Mountain> {
        int start, end;

        Mountain(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Mountain o) {
            if (this.start == o.start) {
                return -(this.end - o.end);
            }
            return this.start - o.start;
        }
    }
}