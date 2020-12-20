import java.util.*;
import java.io.*;

public class CellularNetwork {

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

        // get the closest greater and less
        // take the less distance between
        // find the max of all these less distance

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(sc.nextInt());
        }

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < m; i++) {
            treeSet.add(sc.nextInt());
        }

        int r = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int val = arrayList.get(i);

            if (treeSet.ceiling(val) != null && treeSet.floor(val) != null) {
                int above = treeSet.ceiling(val);
                int less = treeSet.floor(val);

                int min = Math.min(Math.abs(above - val), Math.abs(less - val));

                r = Math.max(min, r);
            }

            else if (treeSet.ceiling(val) != null) {
                int above = treeSet.ceiling(val);

                int min = Math.abs(above - val);

                r = Math.max(min, r);
            }

            else if (treeSet.floor(val) != null) {
                int less = treeSet.floor(val);

                int min = Math.abs(less - val);

                r = Math.max(min, r);
            }
        }

        pw.println(r);

        pw.close();
    }
}