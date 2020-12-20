import java.util.*;
import java.io.*;

public class visitfj {

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

    static int[][] distance;



    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("visitfj.in"));
        pw = new PrintWriter(new File("visitfj.out"));

        int n = sc.nextInt();
        int t = sc.nextInt();

        int[][] grassTime = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grassTime[i][j] = sc.nextInt();
            }
        }

        PriorityQueue<State> priorityQueue = new PriorityQueue<>();
        distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        int[] change_x = new int[] {0, 1, 2, 3, 2, 1, 0, -1, -2, -3, -2, -1, -1, 1, 0, 0};
        int[] change_y = new int[] {3, 2, 1, 0, -1, -2, -3, -2, -1, 0, 1, 2, 0, 0, -1, 1};

        distance[0][0] = 0;
        priorityQueue.add(new State(0, 0));

        while (!priorityQueue.isEmpty()) {
            State s = priorityQueue.poll();

            int timeLeft = Math.abs(n-1-s.x) + Math.abs(n-1-s.y);

            if (timeLeft <= 2) {
                distance[n-1][n-1] = Math.min(distance[n-1][n-1], distance[s.x][s.y] + timeLeft * t);
                continue;
            }

            for (int i = 0; i < change_x.length; i++) {
                int newX = s.x + change_x[i];
                int newY = s.y + change_y[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                    if (distance[newX][newY] > distance[s.x][s.y] + 3 * t + grassTime[newX][newY]) {
                        distance[newX][newY] = distance[s.x][s.y] + 3 * t + grassTime[newX][newY];
                        priorityQueue.add(new State(newX, newY));
                    }
                }
            }
        }

        pw.println(distance[n-1][n-1]);
        pw.close();
    }

    static class State implements Comparable<State> {
        int x, y;

        State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(State o) {
            return distance[this.x][this.y] - distance[o.x][o.y];
        }
    }
}


