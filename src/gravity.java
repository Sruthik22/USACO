import java.util.*;
import java.io.*;

public class gravity {

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
        sc = new InputReader(new FileInputStream("gravity.in"));
        pw = new PrintWriter(new File("gravity.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] grid = new char[n][m];

        Point startingPoint = new Point(0, 0, 0, true);
        Point endingPoint = new Point(0, 0, 0, true);

        for (int i = 0; i < n; i++) {
            String row = sc.next();

            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);

                if (grid[i][j] == 'C') {
                    startingPoint = new Point(i, j, 0, true);
                }

                if (grid[i][j] == 'D') {
                    endingPoint = new Point(i, j, Integer.MAX_VALUE, true);
                }

            }
        }

        int[][] mFU = new int[n][m];
        int[][] mFD = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mFU[i][j] = Integer.MAX_VALUE;
                mFD[i][j] = Integer.MAX_VALUE;
            }
        }

        // should have a min flips for each direction

        ArrayDeque<Point> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(startingPoint);

        while (!arrayDeque.isEmpty()) {
            Point cur = arrayDeque.poll();

            int curX = cur.x;
            int curY = cur.y;

            if (cur.gravity && cur.flips >= mFU[curX][curY]) continue;
            if (!cur.gravity && cur.flips >= mFD[curX][curY]) continue;

            if (cur.gravity) mFU[curX][curY] = cur.flips;
            if (!cur.gravity) mFD[curX][curY] = cur.flips;

            if (curX == endingPoint.x && curY == endingPoint.y) continue;

            if (cur.gravity) {
                if (curX == n-1)  continue;
                if (grid[curX + 1][curY] != '#') {
                    arrayDeque.addFirst(new Point(curX + 1, curY, cur.flips, true));
                    continue;
                }
            }

            else {
                if (curX == 0) continue;
                if (grid[curX - 1][curY] != '#') {
                    arrayDeque.addFirst(new Point(curX - 1, curY, cur.flips, false));
                    continue;
                }
            }

            if (curY + 1 < m && (grid[curX][curY + 1] != '#')) {
                arrayDeque.addFirst(new Point(curX, curY + 1, cur.flips, cur.gravity));
            }

            if (curY - 1 >= 0 && (grid[curX][curY - 1] != '#')) {
                arrayDeque.addFirst(new Point(curX, curY - 1, cur.flips, cur.gravity));
            }

            // we can also switch the direction of gravity
            arrayDeque.addLast(new Point(curX, curY, cur.flips + 1, !cur.gravity));
        }

        int min = Math.min(mFD[endingPoint.x][endingPoint.y], mFU[endingPoint.x][endingPoint.y]);

        if (min == Integer.MAX_VALUE) {
            pw.println(-1);
        }

        else {
            pw.println(min);
        }

        pw.close();
    }

    static class Point {
        int x, y, flips;
        boolean gravity;

        Point(int x, int y, int flips, boolean gravity) {
            this.x = x;
            this.y = y;
            this.flips = flips;
            this.gravity = gravity;
        }
    }
}


