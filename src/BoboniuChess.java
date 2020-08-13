import java.util.*;
import java.io.*;

public class BoboniuChess {

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

    static boolean[][] visited;

    static ArrayList<Pair> arrayList;
    static int n;
    static int m;


    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();
        int Sx = sc.nextInt() - 1;
        int Sy = sc.nextInt() - 1;

        visited = new boolean[n][m];
        arrayList = new ArrayList<>();

        dfs(Sx, Sy);

        for (Pair p: arrayList) {
            pw.println(p);
        }

        pw.close();
    }

    static void dfs(int x, int y) {
        arrayList.add(new Pair(x, y));
        visited[x][y] = true;

        for (int i = 0; i < m; i++) {
            if (!visited[x][i]) dfs(x, i);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i][y]) dfs(i, y);
        }
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return (x + 1) + " " + (y + 1);
        }
    }
}


