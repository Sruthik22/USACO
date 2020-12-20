import java.util.*;
import java.io.*;

public class moocast {

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

    static int n;
    static Cow[] cows;
    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("moocast.in"));
        pw = new PrintWriter(new File("moocast.out"));

        n = sc.nextInt();

        cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(sc.nextInt(), sc.nextInt());
        }

        int low = -1;
        int high = mod;

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (check(mid)) high = mid;
            else low = mid;
        }

        pw.println(high);
        pw.close();
    }

    static boolean check(int distance) {
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (distance(cows[i], cows[j], distance)) {
                    dsu.merge(i, j);
                }
            }
        }

        int parent = dsu.find(0);

        for (int i = 0; i < n; i++) {
            if (dsu.find(i) != parent) return false;
        }

        return true;
    }

    static class DSU {
        static int[] parent;
        DSU(int N) {
            parent = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }
        int find(int a) {
            if (parent[a] == a) return a;
            return parent[a] = find(parent[a]);
        }
        void merge(int a, int b) {
            int parent_a = find(a);
            int parent_b = find(b);

            if (parent_a != parent_b) {
                parent[parent_a] = parent_b;
            }
        }
    }
    static class Cow {
        int x, y;

        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean distance(Cow a, Cow b, double distance) {
        return distance >= (Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
}


