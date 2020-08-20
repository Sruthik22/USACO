import java.util.*;
import java.io.*;

public class lasers {

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
        sc = new InputReader(new FileInputStream("lasers.in"));
        pw = new PrintWriter(new File("lasers.out"));

        int n = sc.nextInt();
        int xl = sc.nextInt();
        int yl = sc.nextInt();
        int xb = sc.nextInt();
        int yb = sc.nextInt();

        HashMap<Integer, ArrayList<Integer>> xs = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> ys = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (!xs.containsKey(x)) {
                xs.put(x, new ArrayList<>());
            }
            xs.get(x).add(y);

            if (!ys.containsKey(y)) {
                ys.put(y, new ArrayList<>());
            }
            ys.get(y).add(x);
        }

        Queue<Line> queue = new LinkedList<>();
        queue.add(new Line(xl, false, 0));
        queue.add(new Line(yl, true, 0));
        int result = Integer.MAX_VALUE;

        HashSet<Integer> completedY = new HashSet<>();
        HashSet<Integer> completedX = new HashSet<>();

        while (!queue.isEmpty()) {
            Line cur = queue.poll();
            if (cur.horizontal && cur.value == yb) {
                result = cur.numOps;
                break;
            }

            if (!cur.horizontal && cur.value == xb) {
                result = cur.numOps;
                break;
            }

            if (cur.horizontal) {
                completedY.add(cur.value);
            } else {
                completedX.add(cur.value);
            }

            HashMap<Integer, ArrayList<Integer>> hash = cur.horizontal ? ys : xs;

            if (hash.containsKey(cur.value)) {
                ArrayList<Integer> pos = hash.get(cur.value);
                for (int i : pos) {
                    boolean visited = cur.horizontal ? completedX.contains(i) : completedY.contains(i);
                    if (visited) continue;
                    queue.add(new Line(i, !cur.horizontal, cur.numOps + 1));
                }
            }
        }

        pw.println(result);
        pw.close();
    }

    static class Line {
        int value;
        boolean horizontal;
        int numOps;

        Line(int value, boolean horizontal, int numOps) {
            this.value = value;
            this.horizontal = horizontal;
            this.numOps = numOps;
        }
    }
}


