import java.util.*;
import java.io.*;

public class RobotTurtles {

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

    static int[][][] distance;
    static int n;
    static int RIGHT = 0;
    static int LEFT = 2;
    static int UP = 1;
    static int DOWN = 3;

    static int[] movesX = {0, -1, 0, 1};
    static int[] movesY = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = 8;
        distance = new int[4][n][n];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }
        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();

        char[][] orig_grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            for (int j = 0; j < n; j++) {
                orig_grid[i][j] = row.charAt(j);
                if (orig_grid[i][j] == 'T') {
                    distance[RIGHT][i][j] = 0;
                    priorityQueue.add(new Element(i, j, RIGHT, new StringBuilder()));
                }
            }
        }

        String result = "no solution";

        while(!priorityQueue.isEmpty()) {
            Element cur_node = priorityQueue.poll();
            int x = cur_node.x;
            int y = cur_node.y;

            if (orig_grid[x][y] == 'D') {
                result = cur_node.path.toString();
                break;
            }

            // forward
            int new_x = x + movesX[cur_node.direction];
            int new_y = y + movesY[cur_node.direction];

            if (withinBounds(new_x, new_y)) {
                if (orig_grid[new_x][new_y] == 'I') {
                    StringBuilder next = new StringBuilder(cur_node.path);
                    next.append('X').append('F');
                    if (distance[cur_node.direction][new_x][new_y] >  2 + distance[cur_node.direction][x][y]) {
                        distance[cur_node.direction][new_x][new_y] = 2 + distance[cur_node.direction][x][y];
                        Element next_node = new Element(new_x, new_y, cur_node.direction, next);
                        priorityQueue.add(next_node);
                    }
                }

                if (orig_grid[new_x][new_y] == '.' || orig_grid[new_x][new_y] == 'D') {
                    StringBuilder next = new StringBuilder(cur_node.path);
                    next.append('F');
                    if (distance[cur_node.direction][new_x][new_y] >  1 + distance[cur_node.direction][x][y]) {
                        distance[cur_node.direction][new_x][new_y] = 1 + distance[cur_node.direction][x][y];
                        Element next_node = new Element(new_x, new_y, cur_node.direction, next);
                        priorityQueue.add(next_node);
                    }
                }
            }

            // right
            int newDir = (cur_node.direction-1) % 4;
            if (newDir == -1) newDir = 3;
            StringBuilder next = new StringBuilder(cur_node.path);
            next.append('R');
            if (distance[newDir][x][y] >  1 + distance[cur_node.direction][x][y]) {
                distance[newDir][x][y] = 1 + distance[cur_node.direction][x][y];
                Element next_node = new Element(x, y, newDir, next);
                priorityQueue.add(next_node);
            }

            // left
            newDir = (cur_node.direction+1) % 4;
            next = new StringBuilder(cur_node.path);
            next.append('L');
            if (distance[newDir][x][y] >  1 + distance[cur_node.direction][x][y]) {
                distance[newDir][x][y] = 1 + distance[cur_node.direction][x][y];
                Element next_node = new Element(x, y, newDir, next);
                priorityQueue.add(next_node);
            }
        }

        pw.println(result);
        pw.close();
    }

    static boolean withinBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    static class Element implements Comparable<Element> {
        int x, y, direction;
        StringBuilder path;

        Element(int x, int y, int direction, StringBuilder path) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.path = path;
        }

        @Override
        public int compareTo(Element o) {
            return distance[this.direction][this.x][this.y] - distance[o.direction][o.x][o.y];
        }
    }
}