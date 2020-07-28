// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class HarmoniousGraph {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int m;
    static boolean[] visited;
    static int[] components;
    static LinkedList<Integer>[] linkedLists;
    static int result;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        n = nextInt();
        m = nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int node_1 = nextInt() - 1;
            int node_2 = nextInt() - 1;

            linkedLists[node_1].add(node_2);
            linkedLists[node_2].add(node_1);
        }

        components = new int[n];
        visited = new boolean[n];
        result = 0;

        setComponents();

        out.println(result);

        out.close();
    }

    static void setComponents() {
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            dfs(i);
        }
    }

    static void dfs(int i) {
        Stack<Integer> stack = new Stack<>();
        stack.add(i);

        TreeSet<Integer> needToCover = new TreeSet<>();
        needToCover.add(i);

        int min = i;
        int max = i;

        while (!needToCover.isEmpty()) {
            while (!stack.empty()) {
                int node = stack.pop();
                needToCover.remove(node);
                visited[node] = true;

                for (int j : linkedLists[node]) {
                    if (!visited[j]) {
                        stack.add(j);

                        if (j > max) {
                            // we need to add max + 1 to j to the need to cover set
                            for (int k = max + 1; k <= j; k++) {
                                needToCover.add(k);
                            }

                            max = j;
                        }

                        if (j < min) {
                            for (int k = min-1; k >= j; k--) {
                                needToCover.add(k);
                            }

                            min = j;
                        }
                    }
                }
            }

            // we need to add an edge to another node

            if (!needToCover.isEmpty()) {
                result++;
                stack.add(needToCover.ceiling(0));
            }
        }
    }
}


