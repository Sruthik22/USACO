import java.util.*;
import java.io.*;

public class SubstringRemovalGame {

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
            String s = sc.next();

            ArrayList<Integer> intervals = new ArrayList<>();

            boolean cur = false;
            int start = -1;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    if (cur) continue;
                    cur = true;
                    start = i;
                }

                else {
                    if (cur) {
                        intervals.add(i - start);
                        start = -1;
                        cur = false;
                    }
                }
            }

            if (cur) {
                intervals.add(s.length() - start);
            }

            Collections.sort(intervals, Collections.reverseOrder());

            int result = 0;

            for (int i = 0; i < intervals.size(); i++) {
                if (i % 2 == 0) {
                    result += intervals.get(i);
                }
            }

            pw.println(result);
        }

        pw.close();
    }
}


