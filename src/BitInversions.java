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

    static String s;
    static TreeSet<Integer> changes;
    static TreeMap<Integer, Integer> distances;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        s = sc.next();

        changes = new TreeSet<>();
        distances = new TreeMap<>();

        changes.add(0);
        changes.add(s.length());

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) changes.add(i + 1);
        }

        for (int i : changes) {
            if (changes.higher(i) != null) add(changes.higher(i) - i);
        }

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int bit_change = sc.nextInt();
            modify(bit_change - 1);
            modify(bit_change);

            pw.print(distances.lastKey() + " ");
        }

        pw.close();
    }

    static void modify(int value) {
        if (value == s.length() || value == 0) return;
        if (changes.contains(value)) {
            changes.remove(value);
            int below = changes.lower(value);
            int above = changes.higher(value);

            remove(value - below);
            remove(above - value);
            add(above - below);
        }

        else {
            changes.add(value);
            int below = changes.lower(value);
            int above = changes.higher(value);

            remove(above - below);
            add(value - below);
            add(above - value);
        }
    }

    static void remove(int value) {
        distances.put(value, distances.get(value) - 1);
        if (distances.get(value) == 0) distances.remove(value);
    }

    static void add(int value) {
        distances.putIfAbsent(value, 0);
        distances.put(value, distances.get(value) + 1);
    }
}