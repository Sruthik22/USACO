// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class lightson {

    private static int n;
    private static int m;
    private static boolean[][] visited;
    private static int[][] lights;
    private static Switch[] switches;
    private static int count;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("lightson.in"));

        n = in.nextInt();
        m = in.nextInt();

        visited = new boolean[n][n];
        lights = new int[n][n];

        lights[0][0] = 1;

        switches = new Switch[m];

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int xTo = in.nextInt();
            int yTo = in.nextInt();

            switches[i] = new Switch(x-1, y-1, xTo-1, yTo-1);
        }

        in.close();

        flood(0,0);

        int result = count + 1;
        PrintWriter out = new PrintWriter(new File("lightson.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Switch {
        int x, y, xTo, yTo;

        Switch(int x, int y, int xTo, int yTo) {
            this.x = x;
            this.y = y;
            this.xTo = xTo;
            this.yTo = yTo;
        }
    }

    static void flood(int r, int c) {
        if(r < n && r >= 0 && c < n && c >= 0 && !visited[r][c] && lights[r][c] == 1) {
            visited[r][c] = true;

            for (int i = 0; i < m; i++) {
                if (switches[i].x == r && switches[i].y == c) {
                    // turn that room on if it is not already on
                    if (lights[switches[i].xTo][switches[i].yTo] == 0) {
                        lights[switches[i].xTo][switches[i].yTo] = 1;
                        count++;

                        int x = switches[i].xTo;
                        int y = switches[i].yTo;

                        boolean callFunction = false;

                        if (x - 1 >= 0) {
                            if (visited[x-1][y]) callFunction = true;
                        }

                        if (!callFunction && x + 1 < n) {
                            if (visited[x+1][y]) callFunction = true;
                        }

                        if (!callFunction && y - 1 >= 0) {
                            if (visited[x][y-1]) callFunction = true;
                        }

                        if (!callFunction && y + 1 < n) {
                            if (visited[x][y+1]) callFunction = true;
                        }

                        if (callFunction) flood(switches[i].xTo, switches[i].yTo);

                    }
                }
            }

            flood(r-1, c);
            flood(r+1, c);
            flood(r, c-1);
            flood(r, c+1);
        }
    }
}


