import java.util.*;
import java.io.*;

public class PairsOfPairs {

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
    static boolean[] visited;
    static int[] depths;
    static int[] parents;
    static int[] numChildren;
    static Queue<Integer> leaves;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int t = sc.nextInt();

        for (int tt = 0; tt < t; tt++) {
            n = sc.nextInt();
            m = sc.nextInt();

            linkedLists = new LinkedList[n];

            for (int i = 0; i < n; i++) {
                linkedLists[i] = new LinkedList<>();
            }

            for (int i = 0; i < m; i++) {
                int node1 = sc.nextInt() - 1;
                int node2 = sc.nextInt() - 1;

                linkedLists[node1].add(node2);
                linkedLists[node2].add(node1);
            }

            visited = new boolean[n];
            depths = new int[n];
            parents = new int[n];
            numChildren = new int[n];
            leaves = new LinkedList<>();

            dfs(0, -1, 1);

            boolean done = false;
            StringBuilder sb = new StringBuilder();

            for (int leaf: leaves) {
                if (depths[leaf] >= Math.ceil((double) (n)/2)) {
                    pw.println("PATH");
                    pw.println(depths[leaf]);
                    int curNode = leaf;
                    pw.print(curNode + 1);
                    pw.print(' ');

                    while (parents[curNode] != -1) {
                        sb.append(parents[curNode] + 1);
                        curNode = parents[curNode];
                        sb.append(' ');
                    }

                    done = true;
                }

                if (done) break;
            }

            if (done) {
                pw.println(sb);
                continue;
            }

            // pairing process
            sb.append("PAIRING\n");
            ArrayList<Pair> arrayList = new ArrayList<>();
            while (leaves.size() >= 2) {
                int l1 = leaves.poll();
                int l2 = leaves.poll();

                arrayList.add(new Pair(l1, l2));

                numChildren[parents[l1]]--;

                if (numChildren[parents[l1]] == 0) {
                    leaves.add(parents[l1]);
                }

                numChildren[parents[l2]]--;

                if (numChildren[parents[l2]] == 0) {
                    leaves.add(parents[l2]);
                }
            }

            sb.append(arrayList.size());
            sb.append('\n');

            for (Pair p: arrayList) {
                sb.append(p);
                sb.append('\n');
            }

            pw.println(sb.toString());
        }

        pw.close();
    }

    static void dfs(int node, int parent, int depth) {
        visited[node] = true;
        depths[node] = depth;
        parents[node] = parent;

        boolean isLeaf = true;

        for (int i : linkedLists[node]) {
            if (i != parent && !visited[i]) {
                dfs(i, node, depth+1);
                isLeaf = false;
                numChildren[node]++;
            }
        }

        if (isLeaf) {
            leaves.add(node);
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


