import java.util.*;
import java.io.*;

public class RoundTripII {

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
    static int m;
    static LinkedList<Integer>[] linkedLists;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            linkedLists[a].add(b);
        }

        parent = new int[n];
        state = new int[n];

        for (int i = 0; i < n; i++) {
            if (state[i] == 0) {
                parent[i] = -1;
                dfs(i);
            }
        }

        pw.println("IMPOSSIBLE");

        pw.close();
    }

    static int[] parent;
    static int[] state;

    static void dfs(int a) {
        state[a] = 1;
        for (int i : linkedLists[a]) {
            if (state[i] == 1) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(a);

                while (i != a) {
                    a = parent[a];
                    if (a == -1) break;
                    arrayList.add(a);
                }
                Collections.reverse(arrayList);
                arrayList.add(i);

                pw.println(arrayList.size());
                for (int j : arrayList) {
                    pw.print(j + 1);
                    pw.print(" ");
                }

                pw.close();
                System.exit(0);
            }

            if (state[i] == 0) {
                parent[i] = a;
                dfs(i);
            }
        }
        state[a] = 2;
    }
}


