import java.util.*;
import java.io.*;

public class cownav {

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

    static int n;
    static char[][] grid;
    static Point finish;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("cownav.in"));
        pw = new PrintWriter(new File("cownav.out"));

        n = sc.nextInt();

        grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            String row = sc.next();

            for (int j = 0; j < n; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        HashMap<Pair, Integer> hashMap = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        Pair start = new Pair(new Point(n-1, 0, 0), new Point(n-1, 0, 1));
        queue.add(start);
        hashMap.put(start, 0);

        finish = new Point(0, n-1, 0);

        int result = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (p.p1.finish() && p.p2.finish()) {
                result = hashMap.get(p);
                break;
            }

            for (Pair pair: p.transition()) {
                if (!hashMap.containsKey(pair)) {
                    hashMap.put(pair, hashMap.get(p) + 1);
                    queue.add(pair);
                }
            }
        }

        pw.println(result);
        pw.close();
    }

    static class Pair {
        Point p1, p2;

        Pair(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        Pair[] transition() {
            return new Pair[] {
                    new Pair(this.p1.right90(), this.p2.right90()),
                    new Pair(this.p1.left90(), this.p2.left90()),
                    new Pair(this.p1.forward(), this.p2.forward()),
            };
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return (Objects.equals(p1, pair.p1) &&
                    Objects.equals(p2, pair.p2)) || (Objects.equals(p2, pair.p1) &&
                    Objects.equals(p1, pair.p2));
        }

        @Override
        public int hashCode() {
            return Objects.hash(p1, p2);
        }
    }
    static class Point {
        int x, y;
        int direction; // 0 - is up, 1 - is right, 2 is down, 3 - left

        Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        Point forward() {
            Point p = new Point(this.x, this.y, this.direction);

            if (p.finish()) return p;

            if (p.direction == 0 && p.x != 0 && properSquare(p.x-1, p.y)) p.x--;
            else if (p.direction == 1 && p.y != n-1 && properSquare(p.x, p.y + 1)) p.y++;
            else if (p.direction == 2 && p.x != n-1 && properSquare(p.x + 1, p.y)) p.x++;
            else if (p.direction == 3 && p.y != 0 && properSquare(p.x, p.y - 1)) p.y--;
            return p;
        }
        Point left90() {
            Point p = new Point(this.x, this.y, this.direction);
            if (p.finish()) return p;
            if (p.direction == 0) p.direction = 3;
            else if (p.direction == 1) p.direction = 0;
            else if (p.direction == 2) p.direction = 1;
            else p.direction = 2;
            return p;
        }
        Point right90() {
            Point p = new Point(this.x, this.y, this.direction);
            if (p.finish()) return p;
            if (p.direction == 0) p.direction = 1;
            else if (p.direction == 1) p.direction = 2;
            else if (p.direction == 2) p.direction = 3;
            else p.direction = 0;
            return p;
        }

        boolean finish() {
            return (this.x == finish.x && this.y == finish.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y &&
                    direction == point.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction);
        }
    }

    static boolean properSquare(int x, int y) {
        return grid[x][y] != 'H';
    }
}