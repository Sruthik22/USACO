import java.util.*;
import java.io.*;

public class Monsters {

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
        int m = sc.nextInt();

        char[][] grid = new char[n][m];

        ArrayList<Pair> monsters = new ArrayList<>();
        Parent start = null;

        for (int i = 0; i < n; i++) {
            String row = sc.next();

            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);

                if (grid[i][j] == 'M') monsters.add(new Pair(i, j));
                if (grid[i][j] == 'A') start = new Parent(new Pair(i, j), null);
            }
        }

        int[][] distMonster = new int[n][m];
        int[][] distPersonal = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distMonster[i][j] = Integer.MAX_VALUE;
                distPersonal[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] x = new int[] {1, 0, -1, 0};
        int[] y = new int[] {0, -1, 0, 1};

        Queue<Pair> queue = new LinkedList<>();

        for (Pair p: monsters) {
            queue.add(p);
            distMonster[p.x][p.y] = 0;
        }

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int j = 0; j < 4; j++) {
                int new_x = p.x + x[j];
                int new_y = p.y + y[j];

                if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < m) {
                    if (grid[new_x][new_y] == '#' || distMonster[new_x][new_y] <= distMonster[p.x][p.y] + 1) continue;
                    distMonster[new_x][new_y] = distMonster[p.x][p.y] + 1;
                    queue.add(new Pair(new_x, new_y));
                }
            }
        }

        Pair[][] parents = new Pair[n][m];
        Queue<Parent> parentQueue = new LinkedList<>();
        parentQueue.add(start);
        distPersonal[start.p.x][start.p.y] = 0;

        while (!parentQueue.isEmpty()) {
            Parent par = parentQueue.poll();
            Pair p = par.p;

            for (int j = 0; j < 4; j++) {
                int new_x = p.x + x[j];
                int new_y = p.y + y[j];

                if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < m) {
                    if (grid[new_x][new_y] != '.' || distPersonal[new_x][new_y] <= distPersonal[p.x][p.y] + 1) continue;
                    distPersonal[new_x][new_y] = distPersonal[p.x][p.y] + 1;
                    parents[new_x][new_y] = p;
                    parentQueue.add(new Parent(new Pair(new_x, new_y), p));
                }
            }
        }

        ArrayList<Character> direction = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (distMonster[i][0] > distPersonal[i][0]) {
                Pair cur = new Pair(i, 0);

                while (true) {
                    Pair next = parents[cur.x][cur.y];
                    if (next == null) break;
                    direction.add(direction(cur, next));
                    cur = next;
                }

                direction.add('0');

                break;
            }

            else if (distMonster[i][m - 1] > distPersonal[i][m-1]) {
                Pair cur = new Pair(i, m-1);

                while (true) {
                    Pair next = parents[cur.x][cur.y];
                    if (next == null) break;
                    direction.add(direction(cur, next));
                    cur = next;
                }
                direction.add('0');

                break;
            }
        }
        if (!direction.isEmpty()) {
            pw.println("YES");
            pw.println(direction.size() - 1);
            Collections.reverse(direction);
            for (char c: direction) {
                if (c == '0') continue;
                pw.print(c);
            }
            pw.close();
            return;
        }
        for (int i = 0; i < m; i++) {
            if (distMonster[0][i] > distPersonal[0][i]) {
                Pair cur = new Pair(0, i);

                while (true) {
                    Pair next = parents[cur.x][cur.y];
                    if (next == null) break;
                    direction.add(direction(cur, next));
                    cur.x = next.x;
                    cur.y = next.y;
                }
                direction.add('0');

                break;
            }

            else if (distMonster[n - 1][i] > distPersonal[n - 1][i]) {
                Pair cur = new Pair(n - 1, i);

                while (true) {
                    Pair next = parents[cur.x][cur.y];
                    if (next == null) break;
                    direction.add(direction(cur, next));
                    cur = next;
                }
                direction.add('0');

                break;
            }
        }
        if (!direction.isEmpty()) {
            pw.println("YES");
            pw.println(direction.size() - 1);
            Collections.reverse(direction);
            for (char c: direction) {
                if (c == '0') continue;
                pw.print(c);
            }
        }
        else pw.println("NO");
        pw.close();
    }

    static class Parent {
        Pair p, parent;

        Parent(Pair p, Pair parent) {
            this.p = p;
            this.parent = parent;
        }
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char direction(Pair cur, Pair next) {
        if (next.x == cur.x + 1) {
            return 'U';
        }

        else if (next.x + 1 == cur.x) {
            return 'D';
        }

        else if (next.y + 1 == cur.y) {
            return 'R';
        }

        else return 'L';
    }
}


