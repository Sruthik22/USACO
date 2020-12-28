import java.util.*;
import java.io.*;

public class measurement {

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

    static TreeMap<Integer, Integer> cows_at_output;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("measurement.in"));
        pw = new PrintWriter(new File("measurement.out"));

        int n = sc.nextInt();
        int g = sc.nextInt();

        HashMap<Integer, Integer> values_of_specific_cows = new HashMap<>();
        cows_at_output = new TreeMap<>();

        cows_at_output.put(g, (int) (1e9));

        Measurement[] measurements = new Measurement[n];

        for (int i = 0; i < n; i++) {
            int day = sc.nextInt();
            int id = sc.nextInt();
            int change = sc.nextInt();

            measurements[i] = new Measurement(day, id, change);
        }

        Arrays.sort(measurements);

        int result = 0;

        for (int i = 0; i < n; i++) {
            Measurement cur = measurements[i];

            int id = cur.id;
            int change = cur.change;

            int previous_val = 0;

            if (values_of_specific_cows.containsKey(id)) {
                previous_val = values_of_specific_cows.get(id);
                values_of_specific_cows.put(id, previous_val + change);
            }
            else {
                previous_val = g;
                values_of_specific_cows.put(id, change + g);
            }

            int new_val = values_of_specific_cows.get(id);

            int previous_best = cows_at_output.lastKey();

            if (new_val > previous_val) {
                if (previous_val == previous_best && cows_at_output.get(previous_best) != 1) result++;
                if (previous_val != previous_best && new_val >= previous_best) result++;
            }

            else {
                if (previous_val == previous_best && cows_at_output.get(previous_best) != 1) result++;
                if (previous_val == previous_best && cows_at_output.get(previous_best) == 1) {
                    int new_best = Math.max(cows_at_output.lowerKey(previous_best), new_val);
                    if (new_val < new_best) result++;
                    else {
                        if (cows_at_output.containsKey(new_best)) result++;
                    }
                }
            }

            remove(previous_val);
            add(new_val);
        }

        pw.println(result);
        pw.close();
    }

    static void remove(int value) {
        cows_at_output.put(value, cows_at_output.get(value) - 1);
        if (cows_at_output.get(value) == 0) cows_at_output.remove(value);
    }

    static void add(int value) {
        cows_at_output.putIfAbsent(value, 0);
        cows_at_output.put(value, cows_at_output.get(value) + 1);
    }

    static class Measurement implements Comparable<Measurement> {
        int day, id, change;

        Measurement(int day, int id, int change) {
            this.day = day;
            this.id = id;
            this.change = change;
        }

        @Override
        public int compareTo(Measurement o) {
            return this.day - o.day;
        }
    }
}