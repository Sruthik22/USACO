import java.util.*;
import java.io.*;

public class snowboots {

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

    static TreeMap<Integer, HashSet<Integer>> vals_at_index;
    static TreeMap<Integer, Integer> distances;
    static TreeSet<Integer> tiles_remaining;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("snowboots.in"));
        pw = new PrintWriter(new File("snowboots.out"));

        int n = sc.nextInt();
        int b = sc.nextInt();

        tiles_remaining = new TreeSet<>();
        vals_at_index = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int fi = sc.nextInt();
            add(fi, i);
            tiles_remaining.add(i);
        }

        Boot[] boots = new Boot[b];

        for (int i = 0; i < b; i++) {
            int si = sc.nextInt();
            int di = sc.nextInt();

            boots[i] = new Boot(si, di, i);
        }

        Arrays.sort(boots);

        distances = new TreeMap<>();
        distances.put(1, n-1);

        int[] result = new int[b];

        for (int i = 0; i < b; i++) {
            Boot cur = boots[i];

            // we need to get rid of all of the tiles where the depth is greater than cur.max_depth

            while (vals_at_index.higherKey(cur.max_depth) != null) {
                int key = vals_at_index.higherKey(cur.max_depth);
                for (int j : vals_at_index.get(key)) {
                    // j is the index to remove
                    // we need to get the original distances above and below, remove these, and then
                    // need to add the new distance

                    tiles_remaining.remove(j);

                    int below = tiles_remaining.floor(j);
                    int above = tiles_remaining.ceiling(j);

                    remove(j - below);
                    remove(above - j);
                    add(above - below);
                }

                vals_at_index.remove(key);
            }

            if (cur.max_step >= distances.lastKey()) result[cur.id] = 1;
            else result[cur.id] = 0;
        }

        for (int i = 0; i < b; i++) {
            pw.println(result[i]);
        }

        pw.close();
    }

    static class Boot implements Comparable<Boot> {
        int max_depth, max_step, id;

        Boot(int max_depth, int max_step, int id) {
            this.max_depth = max_depth;
            this.max_step = max_step;
            this.id = id;
        }

        @Override
        public int compareTo(Boot o) {
            return -(this.max_depth - o.max_depth);
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

    static void add(int value, int index) {
        vals_at_index.putIfAbsent(value, new HashSet<>());
        vals_at_index.get(value).add(index);
    }
}