// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class mootube {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("mootube.in")));
        int n = nextInt();
        int q = nextInt();

        LinkedList<Edge>[] edges = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            edges[i] = new LinkedList<Edge>();
        }

        for(int i = 0; i < n-1; i++) {
            int pi = nextInt()-1;
            int qi = nextInt()-1;
            int ri = nextInt();

            edges[pi].add(new Edge(qi, ri));
            edges[qi].add(new Edge(pi, ri));
        }

        PrintWriter printWriter = new PrintWriter(new File("mootube.out"));

        for(int query = 0; query < q; query++) {
            int threshold = nextInt();
            int start = nextInt()-1;
            int ret = 0;
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(start);
            boolean[] seen = new boolean[n];
            seen[start] = true;
            while(!queue.isEmpty()) {
                int curr = queue.removeFirst();
                for(Edge out: edges[curr]) {
                    if (!seen[out.d]) {
                        seen[out.d] = true;
                        if (out.w >= threshold) {
                            queue.add(out.d);
                            ret++;
                        }
                    }
                }
            }
            printWriter.println(ret);
            System.out.println(ret);

        }

        printWriter.close();
    }

    static class Edge {
        public int d, w;
        public Edge(int a, int b) {
            d=a;//the node connected
            w=b;//the weight
        }
    }
}


