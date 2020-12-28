import java.util.*;
import java.io.*;

public class maxcross {

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
        sc = new InputReader(new FileInputStream("maxcross.in"));
        pw = new PrintWriter(new File("maxcross.out"));

        int n = sc.nextInt();
        int k = sc.nextInt();
        int b = sc.nextInt();

        TreeMap<Integer, Integer> broken_light = new TreeMap<>();

        for (int i = 0; i < b; i++) {
            int val = sc.nextInt();
            broken_light.put(val, 0);
        }

        int index = 1;

        for (Map.Entry<Integer, Integer> entry: broken_light.entrySet()) {
            broken_light.put(entry.getKey(), index);
            index++;
        }

        int result = k;

        for (int i = k; i <= n; i++) {
            int end_pos = i - k;

            int to_repair_upper = 0;

            if (broken_light.floorKey(i) != null) {
                to_repair_upper = broken_light.get(broken_light.floorKey(i));
            }

            int to_repair_lower = 0;

            if (broken_light.floorKey(end_pos) != null) {
                to_repair_lower = broken_light.get(broken_light.floorKey(end_pos));
            }

            result = Math.min(result, to_repair_upper - to_repair_lower);
        }

        pw.println(result);
        pw.close();
    }
}