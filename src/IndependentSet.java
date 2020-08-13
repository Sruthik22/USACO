import java.util.*;
import java.io.*;

public class IndependentSet {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int mod = (int) (1e9 + 7);
    static LinkedList<Integer>[] linkedLists;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int node1 = nextInt() - 1;
            int node2 = nextInt() - 1;

            linkedLists[node1].add(node2);
            linkedLists[node2].add(node1);
        }

        Pair p = dfs(0, -1);

        out.println((p.numBlack + p.numWhite) % mod);

        out.close();
    }

    static int multiply(long a, int b) {

        return (int) ((a * b) % mod);
    }

    static Pair dfs(int node, int parent) {
        int white = 1; // always can be white
        int black = 0; // can't always be black

        for (int i : linkedLists[node]) {
            if (i != parent) {
                Pair p = dfs(i, node);
                int oldWhite = white;
                white = multiply(white, p.numWhite);
                black  = (multiply(black, (p.numWhite + p.numBlack) % mod) + multiply(oldWhite, p.numBlack)) % mod;
            }
        }

        return new Pair((white + black) % mod, white);
    }

    static class Pair {
        int numWhite, numBlack; // p.numWhite is the number of ways to color the subtree such that p is white, and conversely p.numBlack is the number of ways when this is black

        Pair(int numWhite, int numBlack) {
            this.numWhite = numWhite;
            this.numBlack = numBlack;
        }
    }
}