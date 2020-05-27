// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moop {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static boolean[] visited;
    static LinkedList<Integer>[] linkedLists;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("moop.in")));

        int n = nextInt();

        linkedLists = new LinkedList[n];

        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();

            points[i] = new Point(x, y);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                Point p1 = points[i];
                Point p2 = points[j];

                if (p1.x <= p2.x && p1.y <= p2.y) {
                    linkedLists[i].add(j);
                    linkedLists[j].add(i);
                }

                else if (p2.x <= p1.x && p2.y <= p1.y) {
                    linkedLists[i].add(j);
                    linkedLists[j].add(i);
                }
            }
        }

        visited = new boolean[n];

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);

                result++;
            }
        }

        PrintWriter out = new PrintWriter(new File("moop.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void dfs (int node) {
        if (visited[node]) return;

        visited[node] = true;

        for (int i: linkedLists[node]) {
            dfs(i);
        }
    }

    static class Point {
        int x, y;

        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


