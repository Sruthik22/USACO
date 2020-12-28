import java.util.*;
import java.io.*;

public class diamond {

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
        sc = new InputReader(new FileInputStream("diamond.in"));
        pw = new PrintWriter(new File("diamond.out"));

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] diamond_sizes = new int[n];

        for (int i = 0; i < n; i++) {
            diamond_sizes[i] = sc.nextInt();
        }

        Arrays.sort(diamond_sizes);

        int right_point = 0;

        TreeMap<Integer, Integer> prefixMax = new TreeMap<>(); // largest case size when ending at integer
        TreeMap<Integer, Integer> postfixMax = new TreeMap<>(); // largest case size when starting at integer

        for (int left_point = 0; left_point < n; left_point++) {
            int size = diamond_sizes[left_point];

            while (right_point+1 < n && diamond_sizes[right_point+1] <= k + size) {
                right_point++;
            }

            int case_size = right_point - left_point + 1;

            if (prefixMax.containsKey(right_point)) {
                prefixMax.put(right_point, Math.max(prefixMax.get(right_point), case_size));
            }
            else prefixMax.put(right_point, case_size);

            if (postfixMax.containsKey(left_point)) {
                postfixMax.put(left_point, Math.max(postfixMax.get(left_point), case_size));
            }
            else postfixMax.put(left_point, case_size);
        }

        for (int i : prefixMax.keySet()) {
            int value = 0;
            if (prefixMax.lowerKey(i) != null) value = prefixMax.get(prefixMax.lowerKey(i));

            prefixMax.put(i, Math.max(prefixMax.get(i), value));
        }
        for (int i : postfixMax.descendingKeySet()) {
            int value = 0;
            if (postfixMax.higherKey(i) != null) value = postfixMax.get(postfixMax.higherKey(i));

            postfixMax.put(i, Math.max(postfixMax.get(i), value));
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            // i is the point where case 2 starts
            int case_1 = 0;

            if (prefixMax.lowerKey(i) != null) {
                case_1 = prefixMax.get(prefixMax.lowerKey(i));
            }

            int case_2 = 0;

            if (postfixMax.ceilingKey(i) != null) {
                case_2 = postfixMax.get(postfixMax.ceilingKey(i));
            }

            result = Math.max(result, case_1 + case_2);
        }

        pw.println(result);
        pw.close();
    }
}