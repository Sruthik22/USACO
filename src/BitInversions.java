import java.util.*;
import java.io.*;

public class BitInversions {

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

        TreeSet<Integer> ones = new TreeSet<>();
        TreeSet<Integer> zeroes = new TreeSet<>();

        TreeMap<Integer, Integer> distances = new TreeMap<>();

        String s = sc.next();

        char last = ' ';
        int distance = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '1') {
                ones.add(i);
            }

            else {
                zeroes.add(i);
            }

            if (c == last) {
                distance++;
                continue;
            }

            if (distance != 0) {
                distances.putIfAbsent(distance, 0);
                distances.put(distance, distances.get(distance) + 1);
            }

            distance = 1;
            last = c;
        }

        distances.putIfAbsent(distance, 0);
        distances.put(distance, distances.get(distance) + 1);

        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();

            if (ones.contains(x)) {

            }

            else {

            }
        }

        pw.close();
    }
}