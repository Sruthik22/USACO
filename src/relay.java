// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class relay {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int result;
    static LinkedList<Integer>[] linkedLists;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("relay.in")));

        int n = nextInt();

        linkedLists = new LinkedList[n+1];

        for (int i = 0; i < n+1; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 1; i <= n; i++){
            int num = nextInt();

            linkedLists[num].add(i);
        }

        result = 0;

        bfs(0);

        result--;

        PrintWriter out = new PrintWriter(new File("relay.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static void bfs(int v) {

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(v);

        while (q.size() > 0) {

            int cur = q.poll();
            result++;

            for (int next: linkedLists[cur]) {
                q.offer(next);
            }
        }
    }
}


