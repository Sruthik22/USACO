import java.util.*;
import java.io.*;

public class Projects {

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

    static ArrayList<Pair>[] arrayLists;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> points = new ArrayList<>();

        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt() + 1;
            long value = sc.nextLong();

            Pair p = new Pair(start, value, end);
            pairs[i] = p;

            if (!hashSet.contains(start)) {
                points.add(start);
                hashSet.add(start);
            }
            if (!hashSet.contains(end)) {
                points.add(end);
                hashSet.add(end);
            }
        }

        assert points.size() != hashSet.size();
        assert points.size() < 2e5 + 5;

        Collections.sort(points);

        arrayLists = new ArrayList[points.size()];

        for (int i = 0; i < points.size(); i++) {
            arrayLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < pairs.length; i++) {
            Pair p = pairs[i];

            int sI = Collections.binarySearch(points, p.start);
            int sE = Collections.binarySearch(points, p.end);

            p.start = sI;
            p.end = sE;

            arrayLists[sE].add(p);
        }

        long[] dp = new long[points.size()];

        for (int i = 0; i < points.size(); i++) {
            if (i != 0) dp[i] = dp[i - 1];

            for (Pair p: arrayLists[i]) {
                dp[i] = Math.max(dp[i], dp[p.start] + p.value);
            }
        }

        pw.println(dp[points.size() - 1]);
        pw.close();
    }

    static class Pair {
        int start, end;
        long value;

        Pair(int start, long value, int end) {
            this.start = start;
            this.value = value;
            this.end = end;
        }
    }
}


