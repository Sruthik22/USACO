import java.util.*;
import java.io.*;

public class BFSDFS {

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

        int[] bfs = new int[n];

        for (int i = 0; i < n; i++) {
            bfs[i] = sc.nextInt();
        }

        int[] dfs = new int[n];

        for (int j = 0; j < n; j++) {
            dfs[j] = sc.nextInt();
        }

        if (n == 1) {
            pw.println(0);
        }

        else if (bfs[1] == dfs[1]) {

            pw.println(2 * (n-1) - 1);

            for (int i = 1; i < n; i++) {
                pw.println(dfs[i] + " " + dfs[i - 1]);
            }

            for (int i = 2; i < n; i++) {
                pw.println(bfs[i] + " " + bfs[0]);
            }
        }

        else {
            pw.println(-1);
        }

        pw.close();
    }
}