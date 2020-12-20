import java.util.*;
import java.io.*;

public class piepie {

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
        sc = new InputReader(new FileInputStream("piepie.in"));
        pw = new PrintWriter(new File("piepie.out"));

        int n = sc.nextInt();
        int d = sc.nextInt();

        int[] distances = new int[2 * n]; // distance to each node

        int[] X = new int[2 * n];
        int[] Y = new int[2 * n];

        for (int i = 0; i < 2*n; i++) {
            X[i] = sc.nextInt();
            Y[i] = sc.nextInt();
        }

        Queue<Integer> queue = new LinkedList<>();

        TreeMap<Integer, Integer> elsiePie = new TreeMap<>();
        TreeMap<Integer, Integer> bessiePie = new TreeMap<>();

        for (int i = 0; i < 2*n; i++) {
            if (i >= n) {
                if (X[i] == 0) {
                    distances[i] = 1;
                    queue.add(i);
                }

                else {
                    distances[i] = Integer.MAX_VALUE;
                    elsiePie.put(X[i], i);
                }
            }

            else {
                if (Y[i] == 0) {
                    distances[i] = 1;
                    queue.add(i);
                }

                else {
                    distances[i] = Integer.MAX_VALUE;
                    bessiePie.put(Y[i], i);
                }
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node < n) {
                SortedMap<Integer, Integer> sortedMap = elsiePie.subMap(X[node] - d, X[node] + 1);

                ArrayList<Integer> arr = new ArrayList<>();
                arr.addAll(sortedMap.values());

                for (int i : arr) {
                    if (distances[i] == Integer.MAX_VALUE) {
                        distances[i] = 1 + distances[node];
                        queue.add(i);
                        elsiePie.remove(X[i]);
                    }
                }
            }

            else {
                SortedMap<Integer, Integer> sortedMap = bessiePie.subMap(Y[node] - d, Y[node] + 1);
                ArrayList<Integer> arr = new ArrayList<>();
                arr.addAll(sortedMap.values());
                for (int i : arr) {
                    if (distances[i] == Integer.MAX_VALUE) {
                        distances[i] = 1 + distances[node];
                        queue.add(i);
                        bessiePie.remove(Y[i]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int val = distances[i];
            if (val == Integer.MAX_VALUE) val = -1;
            pw.println(val);
        }

        pw.close();
    }
}


