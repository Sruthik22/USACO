import java.util.*;
import java.io.*;

public class bcount {

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
        sc = new InputReader(new FileInputStream("bcount.in"));
        pw = new PrintWriter(new File("bcount.out"));

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] breed = new int[n];

        for (int i = 0; i < n; i++) {
            breed[i] = sc.nextInt();
        }

        int[] H = new int[n + 1];
        int[] G = new int[n + 1];
        int[] J = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            H[i] = H[i-1];
            G[i] = G[i-1];
            J[i] = J[i-1];
            if (breed[i-1] == 1) {
                H[i]++;
            }
            if (breed[i-1] == 2) {
                G[i]++;
            }
            if (breed[i-1] == 3) {
                J[i]++;
            }
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            pw.println((H[b] - H[a - 1]) + " " + (G[b] - G[a - 1]) + " " + (J[b] - J[a - 1]));
        }

        pw.close();
    }
}