import java.util.*;
import java.io.*;

public class MahmoudEhabFunction {

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
        int m = sc.nextInt();
        int q = sc.nextInt();

        long sum = 0;

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            if (i % 2 == 1) val = -val;
            sum += val;
        }

        int[] b_elements = new int[m];

        for (int i = 0; i < m; i++) {
            b_elements[i] = sc.nextInt();
        }

        TreeSet<Long> treeSet = new TreeSet<>();

        long b_sum = 0;

        for (int j = 0; j < n; j++) {
            int val = b_elements[j];
            if (j % 2 == 0) val = -val;
            b_sum += val;
        }

        treeSet.add(b_sum);

        for (int i = 0; i < m - n; i++) {

            b_sum = -b_sum;
            b_sum -= b_elements[i];

            int new_val = b_elements[n + i];

            if ((n - 1) % 2 == 0) {
                new_val = -new_val;
            }

            b_sum += new_val;
            treeSet.add(b_sum);
        }

        long min = Long.MAX_VALUE;

        if (treeSet.ceiling(-sum) != null) {
            long closest_val_above = treeSet.ceiling(-sum);
            min = Math.min(min, Math.abs(closest_val_above + sum));
        }

        if (treeSet.floor(-sum) != null) {
            long closest_val_below = treeSet.floor(-sum);
            min = Math.min(min, Math.abs(closest_val_below + sum));
        }

        pw.println(min);

        for (int i = 0; i < q; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int x = sc.nextInt();

            if (l % 2 == 1 && r % 2 == 1) {
                sum += x;
            }

            else if (l % 2 == 0 && r % 2 == 0) {
                sum -= x;
            }

            min = Long.MAX_VALUE;

            if (treeSet.ceiling(-sum) != null) {
                long closest_val_above = treeSet.ceiling(-sum);
                min = Math.min(min, Math.abs(closest_val_above + sum));
            }

            if (treeSet.floor(-sum) != null) {
                long closest_val_below = treeSet.floor(-sum);
                min = Math.min(min, Math.abs(closest_val_below + sum));
            }

            pw.println(min);
        }

        pw.close();
    }
}