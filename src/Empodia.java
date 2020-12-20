import java.util.*;
import java.io.*;

public class Empodia {

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
        sc = new InputReader(new FileInputStream("empodia.in"));
        pw = new PrintWriter(new File("empodia.out"));

        int m = sc.nextInt();
        int[] sequence = new int[m];


        for (int i = 0; i < m; i++) {
            sequence[i] = sc.nextInt();
        }

        ArrayList<Range> empodias = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int max = sequence[i];
            for (int j = i + 1; j < m; j++) {
                if (sequence[j] == sequence[i] + j - i && sequence[j] > max) {
                    empodias.add(new Range(i, j));
                    break;
                }
                max = Math.max(max, sequence[j]);
            }
        }

        // now we need to validate all of the empodias

        for (int i = 0; i < empodias.size(); i++) {
            Range range = empodias.get(i);

            for (int j = i + 1; j < empodias.size(); j++) {
                if (range.within(empodias.get(j))) {
                    // we need to delete the first range
                    empodias.remove(range);
                    i--;
                    break;
                }
            }
        }

        pw.println(empodias.size());

        for (Range range: empodias) {
            pw.println(range);
        }

        pw.close();
    }

    static class Range {
        int a, b;

        Range(int a, int b) {
            this.a = a;
            this.b = b;
        }

        boolean within(Range r) {
            return r.a >= this.a && r.b <= this.b;
        }

        @Override
        public String toString() {
            return (a + 1) + " " + (b + 1);
        }
    }
}


