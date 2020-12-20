import java.util.*;
import java.io.*;

public class SumOfTwoValues {

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
        int x = sc.nextInt();

        Value[] values = new Value[n];

        for (int i = 0; i < n; i++) {
            Value v = new Value(sc.nextInt(), i+1);
            values[i] = v;
        }

        Arrays.sort(values);

        int left = 0;
        int right = n-1;

        boolean works = false;

        while (left < right) {
            int sum = values[left].val + values[right].val;

            if (sum < x) {
                left++;
            }

            else if (sum == x) {
                works = true;
                break;
            }

            else {
                right--;
            }
        }

        if (works) {
            pw.println(values[left].pos + " " + values[right].pos);
        }

        else {
            pw.println("IMPOSSIBLE");
        }

        pw.close();
    }

    static class Value implements Comparable<Value> {
        int val, pos;

        Value(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }

        @Override
        public int compareTo(Value o) {
            return this.val - o.val;
        }
    }
}