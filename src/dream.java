import java.util.*;
import java.io.*;

public class dream {

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

    static final int RED = 0;
    static final int PINK = 1;
    static final int ORANGE = 2;
    static final int BLUE = 3;
    static final int PURPLE = 4;

    static final int DOWN = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int LEFT = 3;

    static int n;
    static int m;

    static int[] X = new int[] {1, 0, -1, 0};
    static int[] Y = new int[] {0, 1, 0, -1};

    static int[][] grid;
    static Queue<Tile> queue;
    static int[][][][] dp;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("dream.in"));
        pw = new PrintWriter(new File("dream.out"));

        n = sc.nextInt();
        m = sc.nextInt();

        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        Tile firstTile = new Tile(0, 0, DOWN, false);
        queue = new LinkedList<>();
        queue.add(firstTile);

        dp = new int[4][1001][1001][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= 1000; j++) {
                for (int k = 0; k <= 1000; k++) {
                    for (int l = 0; l < 2; l++) {
                        dp[i][j][k][l] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        firstTile.put(0);

        int result = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            Tile tile = queue.poll();
            int valTile = tile.get();

            if (tile.isFinal()) {
                result = valTile;
                break;
            }

            for (Tile t: tile.transition()) {
                t.put(valTile + 1);
                queue.add(t);
            }
        }

        if (result == Integer.MAX_VALUE) result = -1;
        pw.println(result);
        pw.close();
    }

    static class Tile {
        int direction;
        int x, y;
        boolean smell;

        Tile(int x, int y, int direction, boolean smell) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.smell = smell;
        }

        boolean isFinal() {
            return (this.x == n-1 && this.y == m-1);
        }

        ArrayList<Tile> transition() {

            ArrayList<Tile> tile = new ArrayList<>();

            boolean done = false;

            if (grid[this.x][this.y] == PURPLE) {
                int newX = this.x + X[this.direction];
                int newY = this.y + Y[this.direction];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    int color = grid[newX][newY];
                    Tile t = new Tile(newX, newY, this.direction, this.smell);
                    if (color == ORANGE) {
                        t.smell = true;
                        done = true;
                    }
                    if (color == PURPLE) {
                        t.smell = false;
                        done = true;
                    }
                    if (color == PINK) {
                        done = true;
                    }
                    if (done && t.get() == Integer.MAX_VALUE) {
                        tile.add(t);
                        return tile;
                    }
                }
            }

            if (done) return tile;

            for (int i = 0; i < 4; i++) {
                int newX = X[i] + this.x;
                int newY = Y[i] + this.y;

                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    int color = grid[newX][newY];

                    Tile t = new Tile(newX, newY, i, this.smell);

                    if (color == RED) continue;
                    if (color == ORANGE) {
                        t.smell = true;
                    }
                    if (color == BLUE && !t.smell) continue;
                    if (color == PURPLE) {
                        t.smell = false;
                    }
                    if (t.get() == Integer.MAX_VALUE) {
                        tile.add(t);
                    }
                }
            }

            return tile;
        }

        int get() {
            return dp[this.direction][this.x][this.y][this.smell?1:0];
        }

        void put(int x) {
            dp[this.direction][this.x][this.y][this.smell?1:0] = x;
        }
    }
}


