import java.util.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class fcolor {

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
        sc = new InputReader(new FileInputStream("fcolor.in"));
        pw = new PrintWriter(new File("fcolor.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++)
            linkedLists[i] = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            linkedLists[a].add(b);
        }

        DSU dsu = new DSU(n);
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (linkedLists[i].size() > 1) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int key = queue.poll();
            if (linkedLists[key].size() <= 1) continue;

            int a = linkedLists[key].pollLast();
            int b = linkedLists[key].getLast();

            int par_a = dsu.find(a);
            int par_b = dsu.find(b);

            if (par_a != par_b) {
                if (dsu.counts.get(par_a).size() < dsu.counts.get(par_b).size()) {
                    dsu.merge(par_a, par_b);
                    linkedLists[par_b].addAll(linkedLists[par_a]);
                    linkedLists[par_a].clear();
                    if (linkedLists[par_b].size() > 1) queue.add(par_b);
                }

                else {
                    dsu.merge(par_b, par_a);
                    linkedLists[par_a].addAll(linkedLists[par_b]);
                    linkedLists[par_b].clear();
                    if (linkedLists[par_a].size() > 1) queue.add(par_a);
                }
            }
        }

        HashMap<Integer, Integer> colorMap = new HashMap<>();

        int lastColor = 0;

        for (int i = 0; i < n; i++) {
            int rep = dsu.find(i);
            if (!colorMap.containsKey(rep)) {
                colorMap.put(rep, ++lastColor);
            }

            pw.println(colorMap.get(rep));
        }

        pw.close();
    }

    static class DSU {
        HashMap<Integer, ArrayList<Integer>> counts;
        int[] parents;
        DSU(int N) {
            parents = new int[N];
            counts = new HashMap<>();

            for (int i = 0; i < N; i++) {
                parents[i] = i;
                counts.put(i, new ArrayList<>());
                counts.get(i).add(i);
            }
        }
        int find(int a) {
            if (parents[a] == a) return a;
            return parents[a] = find(parents[a]);
        }
        void merge(int a, int b) {
            int parent_a = find(a);
            int parent_b = find(b);
            if (parent_a != parent_b) {
                parents[parent_a] = parent_b;

                for (int i: counts.get(parent_a)) {
                    parents[i] = parent_b;
                    counts.get(parent_b).add(i);
                }

                counts.remove(parent_a);
            }
        }
    }
}


