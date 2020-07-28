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

        int N = nextInt();
        int Q = nextInt();

        LinkedList<Edge>[] linkedLists = new LinkedList[N];

        for (int i = 0; i < N; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < N-1; i++) {
            int p = nextInt() - 1;
            int q = nextInt() - 1;
            int r = nextInt();

            linkedLists[p].add(new Edge(q, r));
            linkedLists[q].add(new Edge(p, r));
        }

        PrintWriter out = new PrintWriter(new File("mootube.out"));

        for (int i = 0; i < Q; i++) {

            int k = nextInt();
            int v = nextInt() - 1;

            boolean[] nodes = new boolean[N];

            Stack<Integer> stack = new Stack<>();

            stack.add(v);
            nodes[v] = true;

            int result = 0;

            while (!stack.isEmpty()) {
                // we need to add all of the values this current stack is connected to

                int node = stack.pop();

                for (Edge e: linkedLists[node]) {
                    if (e.weight >= k && !nodes[e.destination]) {
                        result++;
                        nodes[e.destination] = true;
                        stack.add(e.destination);
                    }
                }
            }

            System.out.println(result);
            out.println(result);
        }

        out.close();
    }

    public static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    /*
    * */
}


