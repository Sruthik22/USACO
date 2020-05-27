// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class hshoe {

    private static int n;
    private static char[][] grid;

    private static boolean[][] visited;
    private static int result;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("hshoe.in"));
        n = in.nextInt();

        grid = new char[n][];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String line = in.next();
            grid[i] = line.toCharArray();
        }

        in.close();

        result = 0;

        explore(0, 0, 0, 0);
        PrintWriter out = new PrintWriter(new File("hshoe.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void explore(int r, int c, int opens, int closes) {

        if (r < 0 || r >= n || c < 0 || c >= n) return;

        if (opens == closes && opens > 0) {
            result = Math.max(result, opens + closes);
            return;
        }

        if (grid[r][c] == '(' && closes > 0) return;

        if (visited[r][c]) return;

        visited[r][c] = true;

        if (grid[r][c] == '(') {
            opens++;
        }
        else closes++;

        explore(r + 1, c, opens, closes);
        explore(r - 1, c, opens, closes);
        explore(r, c + 1, opens, closes);
        explore(r, c - 1, opens, closes);

        visited[r][c] = false;
    }


}

 /*
 ANALYSIS

recursion is a much better approach than dfs, because then can choose whether to include it or not, rather than auto include


 */


