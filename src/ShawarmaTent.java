// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class ShawarmaTent {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int sx = nextInt();
        int sy = nextInt();

        Point school = new Point(sx, sy);

        Point tent_loco_1 = new Point(sx, sy-1);
        Point tent_loco_2 = new Point(sx, sy+1);
        Point tent_loco_3 = new Point(sx + 1, sy);
        Point tent_loco_4 = new Point(sx - 1 , sy);

        Point[] points = new Point[4];
        points[0] = tent_loco_1;
        points[1] = tent_loco_2;
        points[2] = tent_loco_3;
        points[3] = tent_loco_4;

        int[] num = new int[4];

        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();

            Point cur = new Point(x, y);

            for (int j = 0; j < 4; j++) {
                if (distance(cur, school) == distance(cur, points[j]) + distance(points[j], school)) {
                    num[j]++;
                }
            }
        }

        int max = 0;

        for (int i = 0; i < 4; i++) {
            max = Math.max(max, num[i]);
        }

        for (int i = 0; i < 4; i++) {
            if (num[i] == max) {
                out.println(max);
                out.println(points[i]);
                out.close();
                return;
            }
        }
    }

    static int distance (Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}


