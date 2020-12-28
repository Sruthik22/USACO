import java.util.*;
import java.io.*;

public class JuryMarks {

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

        int k = sc.nextInt();
        int n = sc.nextInt();

        int[] prefix = new int[k + 1];

        for (int i = 0; i < k; i++) {
            int new_val = sc.nextInt();

            prefix[i + 1] = prefix[i] + new_val;
        }

        HashSet<Integer> possible = new HashSet<>();

        int val = sc.nextInt();

        for (int i = 1; i <= k; i++) {
            possible.add(val - prefix[i]);
        }

        for (int i = 0; i < n - 1; i++) {
            int new_val = sc.nextInt();

            HashSet<Integer> new_possible = new HashSet<>();

            for (int j = 1; j <= k; j++) {
                int new_pos = new_val - prefix[j];
                if (possible.contains(new_pos)) new_possible.add(new_pos);
            }

            possible = new_possible;
        }

        int result = possible.size();

        pw.println(result);
        pw.close();
    }
}