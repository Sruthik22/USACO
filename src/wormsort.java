import java.util.*;
import java.io.*;

public class wormsort {

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
    static int m;
    static int[] permutation;
    static Wormhole[] wormholes;
    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("wormsort.in"));
        pw = new PrintWriter(new File("wormsort.out"));

        n = sc.nextInt();
        m = sc.nextInt();

        permutation = new int[n];

        for (int i = 0; i < n; i++) {
            permutation[i] = sc.nextInt() - 1;
        }

        wormholes = new Wormhole[m];

        for (int i = 0; i < m; i++) {
            wormholes[i] = new Wormhole(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
        }

        Arrays.sort(wormholes);

        boolean alreadyDone = true;

        for (int i = 0; i < n; i++) {
            if (permutation[i] != i) {
                alreadyDone = false;
                break;
            }
        }

        if (alreadyDone) {
            pw.println(-1);
            pw.close();
            return;
        }

        int low = -1;
        int high = (int) (1e9);

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (check(mid)) low = mid;
            else high = mid;
        }

        pw.println(low);
        pw.close();
    }

    static boolean check(int minWidth) {
        DSU dsu = new DSU(n);
        for (int i = m-1; i >= 0; i--) {
            if (wormholes[i].w >= minWidth) {
                dsu.merge(wormholes[i].a, wormholes[i].b);
            }
            else break;
        }

        for (int i = 0; i < n; i++) {
            if (dsu.find(i) != dsu.find(permutation[i])) return false;
        }

        return true;
    }
    static class Wormhole implements Comparable<Wormhole> {
        int a, b, w;

        Wormhole(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Wormhole o) {
            return this.w - o.w;
        }
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
}


