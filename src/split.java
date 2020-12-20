import java.util.*;
import java.io.*;

public class split {

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
        sc = new InputReader(new FileInputStream("split.in"));
        pw = new PrintWriter(new File("split.out"));

        int n = sc.nextInt();

        Cow[] cows_x = new Cow[n];
        Cow[] cows_y = new Cow[n];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            cows_x[i] = new Cow(a, b);
            cows_y[i] = new Cow(a, b);
        }

        Arrays.sort(cows_x, new XSort());
        Arrays.sort(cows_y, new YSort());

        long orig_area = ((long) (cows_x[n-1].x) - cows_x[0].x) * ((long) (cows_y[n-1].y) - cows_y[0].y);

        long smallest_area = Long.MAX_VALUE;

        int[] prefix_min_y = new int[n];
        int[] prefix_max_y = new int[n];
        int[] postfix_max_y = new int[n];
        int[] postfix_min_y = new int[n];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, cows_x[i].y);
            max = Math.max(max, cows_x[i].y);

            prefix_min_y[i] = min;
            prefix_max_y[i] = max;
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for (int i = n-1; i >= 0; i--) {
            min = Math.min(min, cows_x[i].y);
            max = Math.max(max, cows_x[i].y);

            postfix_min_y[i] = min;
            postfix_max_y[i] = max;
        }

        for (int i = 0; i < n; i++) {
            if (i != n-1) {
                if (cows_x[i].x == cows_x[i+1].x) continue;
            }

            long area_1 = (long) ((prefix_max_y[i] - prefix_min_y[i])) * (cows_x[i].x - cows_x[0].x);
            long area_2 = 0;
            if (i + 1 != n) {
                area_2 = (long) ((postfix_max_y[i+1] - postfix_min_y[i + 1])) * (cows_x[n-1].x - cows_x[i+1].x);
            }

            smallest_area = Math.min(smallest_area, area_1 + area_2);
        }

        int[] prefix_min_x = new int[n];
        int[] prefix_max_x = new int[n];
        int[] postfix_max_x = new int[n];
        int[] postfix_min_x = new int[n];

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, cows_y[i].x);
            max = Math.max(max, cows_y[i].x);

            prefix_min_x[i] = min;
            prefix_max_x[i] = max;
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for (int i = n-1; i >= 0; i--) {
            min = Math.min(min, cows_y[i].x);
            max = Math.max(max, cows_y[i].x);

            postfix_min_x[i] = min;
            postfix_max_x[i] = max;
        }

        for (int i = 0; i < n; i++) {
            if (i != n-1) {
                if (cows_y[i].y == cows_y[i+1].y) continue;
            }

            long area_1 = (long) ((prefix_max_x[i] - prefix_min_x[i])) * (cows_y[i].y - cows_y[0].y);
            long area_2 = 0;
            if (i + 1 != n) {
                area_2 = (long) ((postfix_max_x[i+1] - postfix_min_x[i + 1])) * (cows_y[n-1].y - cows_y[i+1].y);
            }

            smallest_area = Math.min(smallest_area, area_1 + area_2);
        }

        pw.println(orig_area - smallest_area);

        pw.close();
    }

    static class XSort implements Comparator<Cow> {
        public int compare(Cow a, Cow b){
            return a.x - b.x;
        }
    }

    static class YSort implements Comparator<Cow> {
        public int compare(Cow a, Cow b){
            return a.y - b.y;
        }
    }

    static class Cow {
        int x, y;

        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}