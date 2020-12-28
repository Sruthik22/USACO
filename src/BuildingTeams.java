import java.util.*;
import java.io.*;

public class BuildingTeams {

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

    static LinkedList<Integer>[] linkedLists;
    static int[] assigned;
    static boolean works;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            linkedLists[a].add(b);
            linkedLists[b].add(a);
        }

        assigned = new int[n];

        works = true;

        for (int i = 0; i < n; i++) {
            if (assigned[i] != 0) continue;

            assigned[i] = 1;
            dfs(i);
        }


        if (works) {
            for (int i = 0; i < n; i++) {
                pw.print(assigned[i] + " ");
            }
        }

        else {
            pw.println("IMPOSSIBLE");
        }

        pw.close();
    }

    static void dfs(int node) {
        if (!works) return;

        for (int i : linkedLists[node]) {
            // these are all that are direct friends
            if (assigned[i] == assigned[node]) {
                works = false;
                break;
            }

            int correct_num = assigned[node] == 1 ? 2:1;

            if (assigned[i] == correct_num) continue;

            assigned[i] = correct_num;
            dfs(i);
        }
    }
}