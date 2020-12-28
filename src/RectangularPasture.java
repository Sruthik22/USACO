import java.util.*;
import java.io.*;

public class RectangularPasture {

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
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        Cow[] cowsByX = new Cow[n];
        Cow[] cowsByY = new Cow[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            Cow c = new Cow(x, y, i);
            cowsByX[i] = c;
            cowsByY[i] = c;
        }

        Arrays.sort(cowsByX, Comparator.comparingInt(o -> o.x));
        Arrays.sort(cowsByY, Comparator.comparingInt(o -> o.y));

        int[][] prefix_sum = new int[n + 1][n + 1];

        // i is the x coordinate by cowsByX
        // j is the y coordinate by cowsByY

        HashMap<Integer, Integer> xs_indices = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefix_sum[i][j] = prefix_sum[i][j - 1] + prefix_sum[i - 1][j] - prefix_sum[i - 1][j - 1];
                if (cowsByY[j-1].x == cowsByX[i-1].x) {
                    prefix_sum[i][j]++;
                }
            }

            xs_indices.put(cowsByX[i-1].x, i-1);
        }

        long result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int yb = cowsByY[j].y;
                int xb = cowsByY[j].x;
                int ya = cowsByY[i].y;
                int xa = cowsByY[i].x;

                // need to get the indices of these values

                int x_min_i = xs_indices.get(Math.min(xa, xb));

                long first_res = prefix_sum[x_min_i + 1][j + 1] - prefix_sum[0][j + 1]
                        - prefix_sum[x_min_i + 1][i] + prefix_sum[0][i];

                int x_max_i = xs_indices.get(Math.max(xa, xb));

                long second_res = prefix_sum[n][j + 1] - prefix_sum[x_max_i][j + 1]
                        - prefix_sum[n][i] + prefix_sum[x_max_i][i];

                result += first_res * second_res;
            }
        }

        pw.println(result+1);
        pw.close();
    }

    static class Cow {
        int x, y, id;

        Cow(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
}