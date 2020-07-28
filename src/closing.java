// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class closing {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("closing.in")));

        int N = nextInt();
        int M = nextInt();

        LinkedList<Integer>[] linkedLists = new LinkedList[N];

        for (int i = 0; i < N; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            int barn_1 = nextInt() - 1;
            int barn_2 = nextInt() - 1;

            linkedLists[barn_1].add(barn_2);
            linkedLists[barn_2].add(barn_1);
        }

        HashSet<Integer> closed = new HashSet<>();
        HashSet<Integer> open = new HashSet<>();

        for (int i = 0; i < N; i++) {
            open.add(i);
        }

        PrintWriter out = new PrintWriter(new File("closing.out"));

        boolean still_connected = connected(linkedLists, closed, N, open);
        String result = "";

        if (still_connected) {
            result = "YES";
        }
        else result = "NO";

        System.out.println(result);
        out.println(result);

        for (int i = 0; i < N-1; i++) {
            int closing = nextInt() - 1;

            closed.add(closing);
            open.remove(closing);

            still_connected = connected(linkedLists, closed, N, open);

            if (still_connected) {
                result = "YES";
            }
            else result = "NO";

            System.out.println(result);
            out.println(result);
        }

        out.close();
    }

    public static boolean connected(LinkedList<Integer>[] linkedLists, HashSet<Integer> closed, int N, HashSet<Integer> open) {

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N];
        if (open.isEmpty()) return true;

        Iterator<Integer> value = open.iterator();
        int add = value.next();

        stack.add(add);
        visited[add] = true;

        int connected = 1;

        while (!stack.isEmpty()) {
            int next = stack.pop();

            for (int i: linkedLists[next]) {
                if (!closed.contains(i) && !visited[i]) {
                    visited[i] = true;
                    connected++;
                    stack.add(i);
                }
            }
        }

        return connected == open.size();

    }
}


