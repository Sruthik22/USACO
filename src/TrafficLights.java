import java.util.*;
import java.io.*;

public class TrafficLights {

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

        int x = sc.nextInt();
        int n = sc.nextInt();

        TreeSet<Integer> placed_traffic_lights = new TreeSet<>();

        placed_traffic_lights.add(0);
        placed_traffic_lights.add(x);

        TreeMap<Integer, Integer> distances = new TreeMap<>();

        distances.put(x, 1);

        for (int i = 0; i < n; i++) {
            int p = sc.nextInt();

            int below = placed_traffic_lights.floor(p);
            int above = placed_traffic_lights.ceiling(p);

            int distance = above - below;

            distances.put(distance, distances.get(distance) - 1);
            if (distances.get(distance) == 0) distances.remove(distance);

            distances.putIfAbsent(p - below, 0);
            distances.put(p - below, distances.get(p - below) + 1);

            distances.putIfAbsent(above - p, 0);
            distances.put(above - p, distances.get(above - p) + 1);

            placed_traffic_lights.add(p);

            int largest_distance = distances.lastKey();
            pw.print(largest_distance + " ");
        }

        pw.close();
    }
}