// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moocast {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("moocast.in")));

        int n = nextInt();

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            int p = nextInt();

            cows[i] = new Cow(x, y, p);
        }

        for (int i = 0; i < n; i++) {

            Cow ci = cows[i];

            for (int j = i+1; j < n; j++) {
                Cow cj = cows[j];

                double dist = distance(ci, cj);

                if (ci.transmition >= dist) {
                    linkedLists[i].add(j);
                }

                if (cj.transmition >= dist) {
                    linkedLists[j].add(i);
                }
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            // i is the starting cow
            boolean[] visited = new boolean[n];

            Stack<Integer> stack = new Stack<>();

            stack.add(i);
            visited[i] = true;

            int result_i = 1;

            while (!stack.isEmpty()) {
                int cur = stack.pop();

                for (int friend : linkedLists[cur]) {
                    if (!visited[friend]) {
                        result_i++;
                        visited[friend] = true;
                        stack.add(friend);
                    }
                }
            }

            result = Math.max(result, result_i);
        }

        PrintWriter out = new PrintWriter(new File("moocast.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static double distance(Cow c1, Cow c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2));
    }

    private static class Cow {
        int x, y;
        int transmition;

        Cow(int x, int y, int transmition) {
            this.x = x;
            this.y = y;
            this.transmition = transmition;
        }

    }
}


