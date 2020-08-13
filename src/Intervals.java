import java.util.*;
import java.io.*;

public class Intervals {

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

    static InputReader sc;
    static PrintWriter pw;

    static ArrayList<Pair>[] arrayLists;
    static Node[] tree;

    static long INF = (long) (10e18 + 5);
    static long best;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        arrayLists = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            arrayLists[i] = new ArrayList<>();
        }

        long total = 0;

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int value = sc.nextInt();

            arrayLists[end].add(new Pair(start, value));
            total += value;
        }

        int base = 1;

        while (base <= n + 1) {
            base *= 2;
        }

        tree = new Node[2 * base];

        for (int i = 0; i < 2 * base; i++) {
            tree[i] = new Node();
        }

        long result = 0;

        best = -INF;
        rec(1, 0, base - 1, 0, 0, TYPE_ADD);
        for (int i = 1; i <= n + 1; i++) {
            // adding a 1 at this index

            // these are the pairs that ended at this value -- add back all of the values that were removed
            for (Pair p: arrayLists[i - 1]) {
                best = p.value;
                rec(1, 0, base - 1, 0, p.start - 1, TYPE_ADD);
            }

            best = INF;

            rec(1, 0, base - 1, 0, base - 1, TYPE_MIN);

            if (i == n+1) {
                result = best;
            }

            best = best - INF;

            // we need to actually add this value into the segment tree
            rec(1, 0, base - 1, i, i, TYPE_ADD);
        }

        pw.println(total - result);
        pw.close();
    }

    static final int TYPE_ADD = 1;
    static final int TYPE_MIN = 2;
    static class Node {
        long minimum = INF;
        long lazyPropagation = 0;

        void merge(Node a, Node b) {
            this.minimum = Math.min(a.minimum, b.minimum);
            assert this.lazyPropagation == 0;
        }

        void add(long value) {
            minimum += value;
            lazyPropagation += value;
            // in the tree of lazy propagation we have this value to update our children as well
        }
    }
    static void rec(int id, int low, int high, int q_low, int q_high, int type) {
        if (q_low > high || q_high < low) return;
        if (q_low <= low && high <= q_high) {
            if (type == TYPE_ADD) { // we are adding a value to the segment tree
                tree[id].add(best);
            }

            else if (type == TYPE_MIN) { // we are querying for the minimum value
                best = Math.min(best, tree[id].minimum);
            }

            else {
                assert false;
            }
            return;
        }

        tree[2 * id].add(tree[id].lazyPropagation);
        tree[2 * id + 1].add(tree[id].lazyPropagation);

        tree[id].lazyPropagation = 0;

        int mid = (low + high) / 2;

        rec(2 * id, low, mid, q_low, q_high, type);
        rec(2 * id + 1, mid + 1, high, q_low, q_high, type);

        tree[id].merge(tree[2 * id], tree[2 * id + 1]);
    }
    static class Pair {
        int start, value;

        Pair(int start, int value) {
            this.start = start;
            this.value = value;
        }
    }
}


