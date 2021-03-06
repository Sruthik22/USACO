import java.util.*;
import java.io.*;

public class Sort {

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
        sc = new InputReader(new FileInputStream("sort.in"));
        pw = new PrintWriter(new File("sort.out"));

        int n = sc.nextInt();

        int[] sorted_nums = new int[n];
        int[] unsorted_nums = new int[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            sorted_nums[i] = val;
            unsorted_nums[i] = val;
        }

        Arrays.sort(sorted_nums);

        HashMap<Integer, Integer> val_to_pos = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int val = sorted_nums[i];
            val_to_pos.put(val, i);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            int difference_pos = val_to_pos.get(unsorted_nums[i]) - i;
            result = Math.max(-difference_pos, result);
        }

        pw.println(result + 1);
        pw.close();
    }
}