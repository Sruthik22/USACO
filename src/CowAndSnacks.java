// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CowAndSnacks {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < k; i++) {
            int v1 = nextInt()-1;
            int v2 = nextInt()-1;

            linkedLists[v1].add(v2);
            linkedLists[v2].add(v1);
        }

        int cc = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            cc++;

            Stack<Integer> stack = new Stack<>();
            stack.add(i);
            visited[i] = true;

            while (!stack.isEmpty()) {
                int in = stack.pop();

                for (int b: linkedLists[in]) {
                    if (!visited[b]) {
                        stack.add(b);
                        visited[b] = true;
                    }
                }
            }
        }

        out.println(k - n + cc);

        out.close();
    }
}


