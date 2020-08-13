import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Subtree {

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

    static int n;
    static int m;
    static LinkedList<Integer>[] linkedLists;
    static int[] dp_down;
    static int[] dp_up;
    static ArrayList<Integer>[] children;
    static ArrayList<Integer>[] prefixSum;
    static ArrayList<Integer>[] suffixSum;

    public static void main(String[] args) throws Exception {

        Thread t=new Thread(null, null, "", 1<<28) {
            public void run() {
                sc = new InputReader(System.in);
                pw = new PrintWriter(System.out);

                n = sc.nextInt();
                m = sc.nextInt();

                linkedLists = new LinkedList[n];

                for (int i = 0; i < n; i++) {
                    linkedLists[i] = new LinkedList<>();
                }

                for (int i = 0; i < n - 1; i++) {
                    int node1 = sc.nextInt() - 1;
                    int node2 = sc.nextInt() - 1;

                    linkedLists[node1].add(node2);
                    linkedLists[node2].add(node1);
                }

                dp_down = new int[n];
                dp_up = new int[n];

                children = new ArrayList[n];

                for (int i = 0; i < n; i++) {
                    children[i] = new ArrayList<>();
                }

                dfs(0, -1);

                prefixSum = new ArrayList[n];
                suffixSum = new ArrayList[n];

                for (int i = 0; i < n; i++) {
                    prefixSum[i] = new ArrayList<>();
                    suffixSum[i] = new ArrayList<>();
                }

                dp_up[0] = 1;

                for (int i = 0; i < n; i++) {
                    ArrayList<Integer> kids = children[i];

                    int num = 1;

                    for (int j = 0; j < kids.size(); j++) {
                        num = multiply(num, dp_down[kids.get(j)] + 1);
                        prefixSum[i].add(num);
                    }

                    num = 1;

                    for (int j = kids.size() - 1; j >= 0; j--) {
                        num = multiply(num, dp_down[kids.get(j)] + 1);
                        suffixSum[i].add(num);
                    }

                    Collections.reverse(suffixSum[i]);
                }

                dfs_1(0);

                for (int i = 0; i < n; i++) {
                    pw.println(multiply(dp_up[i], dp_down[i]));
                }

                pw.close();
            }
        };
        t.start();
        t.join();
    }

    static void dfs_1(int node) {
        for (int i = 0; i < children[node].size(); i++) {
            int num = 1;
            if (i != 0) {
                num = multiply(num, prefixSum[node].get(i - 1));
            }
            if (i != children[node].size() - 1) {
                num = multiply(num, suffixSum[node].get(i + 1));
            }
            num = add(multiply(num, (dp_up[node])), 1);
            int b = children[node].get(i);
            dp_up[b] = num;
            dfs_1(b);
        }
    }
    static void dfs(int node, int parent) {
        int num = 1;

        for (int i : linkedLists[node]) {
            if (i != parent) {
                children[node].add(i);
                dfs(i, node);
                num = multiply(num, dp_down[i] + 1);
            }
        }

        dp_down[node] = num;
    }
    static int multiply(long a, int b) {
        return (int) ((a * b) % m);
    }
    static int add(int a, int b) {
        a += b;
        if (a > m) a-= m;
        return a;
    }
}


