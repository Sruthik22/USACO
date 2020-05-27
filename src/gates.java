// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class gates {

    private static boolean[][] isFence;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("gates.in"));

        int n = in.nextInt();
        String s = in.next();
        int currX = 1002; // IMPORTANT: rather than making dynamic, made the center constant, reduces bugs
        int currY = 1002;
        isFence = new boolean[2005][2005];
        isFence[currX][currY] = true;
        for(int i = 0; i < s.length(); i++) {
            int dirX = 0, dirY = 0;
            if(s.charAt(i) == 'N') {
                dirX = -1;
            }
            else if(s.charAt(i) == 'S') {
                dirX = 1;
            }
            else if(s.charAt(i) == 'W') {
                dirY = -1;
            }
            else {
                dirY = 1;
            }
            for(int a = 0; a < 2; a++) {
                currX += dirX;
                currY += dirY;
                isFence[currX][currY] = true;
            }
        }

        in.close();

        int result = -1;

        for (int i = 0; i < 2005; i++) {
            for (int j = 0; j < 2005; j++) {
                if (isFence[i][j]) continue;

                Queue<Point> pointQueue = new LinkedList<>();

                pointQueue.add(new Point(i,j));

                while (pointQueue.size() > 0) {
                    Point last = pointQueue.poll();

                    int r = last.x;
                    int c = last.y;

                    if (r < 0 || r >= isFence.length || c < 0 || c >= isFence[r].length || isFence[r][c]) continue;

                    isFence[last.x][last.y] = true;

                    pointQueue.add(new Point(r, c+1));
                    pointQueue.add(new Point(r, c-1));
                    pointQueue.add(new Point(r+1, c));
                    pointQueue.add(new Point(r-1, c));

                }

                result++;
            }
        }


        PrintWriter out = new PrintWriter(new File("gates.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


