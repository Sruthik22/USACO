import java.util.*;
import java.io.*;

public class Grid2 {

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

    static int mod = (int) (1e9 + 7);
    static int nax = (int) (2e5 + 5);

    static int[] fac;
    static int[] inv_fac;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();

        fac = new int[nax];
        inv_fac = new int[nax];

        fac[0] = 1;
        inv_fac[0] = 1;

        for (int i = 1; i < nax; i++) {
            fac[i] = CPMath.multiply(fac[i-1], i);
            inv_fac[i] = CPMath.inverse(fac[i]);
        }

        Point[] points = new Point[n + 1];

        for (int i = 0; i < n; i++) {
            points[i] = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
        }

        points[n] = new Point(h - 1, w - 1);

        Arrays.sort(points);

        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            Point p = points[i];
            int numWays = CPMath.paths(p.x, p.y);

            for (int j = 0; j < i; j++) {
                Point p_j = points[j];
                if (p_j.x <= p.x && p_j.y <= p.y) {
                    numWays = CPMath.sub(numWays, CPMath.multiply(dp[j], CPMath.paths(p.x - p_j.x, p.y - p_j.y)));
                }
            }

            dp[i] = numWays;
        }

        pw.println(dp[n]);
        pw.close();
    }

    static class CPMath {
        static int add(int a, int b) {
            a += b;

            if (a >= mod) a -= mod;

            return a;
        }
        static int sub(int a, int b) {
            a -= b;
            if (a < 0) a+= mod;
            return a;
        }
        static int multiply(int a, long b) {
            b = a * b;
            return (int) (b % mod);
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
        static int paths(int a, int b) {
            return binomial(a + b, b);
        }
        static int binomial(int a, int b) {
            if (a < 0 || b < 0 || b > a) return 0;
            return multiply(multiply(fac[a], inv_fac[b]), inv_fac[a - b]);
        }
    }

    static class Point implements Comparable<Point>{
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return (this.x + this.y) - (o.x + o.y);
        }
    }
}


