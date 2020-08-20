import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class atlarge {

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
        sc = new InputReader(new FileInputStream("atlarge.in"));
        pw = new PrintWriter(new File("atlarge.out"));

        int n = sc.nextInt();
        int k = sc.nextInt() - 1;

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            linkedLists[a].add(b);
            linkedLists[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);

        int[] distanceBessie = new int[n];
        Arrays.fill(distanceBessie, Integer.MAX_VALUE);
        distanceBessie[k] = 0;

        ArrayList<Integer> leaves = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();

            boolean isLeaf = true;

            for (int i : linkedLists[node]) {
                if (distanceBessie[i] == Integer.MAX_VALUE) {
                    distanceBessie[i] = 1 + distanceBessie[node];
                    queue.add(i);
                    isLeaf = false;
                }
            }

            if (isLeaf) leaves.add(node);
        }

        int[] farmerDistance = new int[n];
        Arrays.fill(farmerDistance, Integer.MAX_VALUE);

        int result = 0;

        for (int leaf : leaves) {
            if (farmerDistance[leaf] <= distanceBessie[leaf]) continue;

            // we need to add a farmer here
            result++;

            Queue<Integer> farmerQueue = new LinkedList<>();
            farmerQueue.add(leaf);
            farmerDistance[leaf] = 0;

            while (!farmerQueue.isEmpty()) {
                int node = farmerQueue.poll();

                for (int o : linkedLists[node]) {
                    if (farmerDistance[o] > farmerDistance[node] + 1) {
                        farmerDistance[o] = 1 + farmerDistance[node];
                        farmerQueue.add(o);
                    }
                }
            }
        }

        pw.println(result);
        pw.close();
    }
}