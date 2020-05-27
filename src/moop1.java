// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moop1 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("moop.in")));

        int n = nextInt();

        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();

            points.add(new Point(x, y));
        }

        int result = n;

        for (int i = 0; i < n; i++) {

            Point curMax = points.get(i);

            for (Point p : points) {
                if (lessThan(curMax, p) == -1) {
                    curMax = p;
                }
            }

            for (Point p: points) {
                int resultLess = lessThan(curMax, p);
                if (resultLess == 1) {
                    result--;
                }
            }
        }

        /*for (int i = 1; i < n; i++) {
            int result = lessThan(curMax, points.get(i));
            if (result == 1) {
                curMax = points.get(i);
            }
        }*/

        PrintWriter out = new PrintWriter(new File("moop.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int lessThan (Point p1, Point p2) {
        if (p1.x <= p2.x && p1.y <= p2.y) {
            return 1;
        }

        else if (p2.x <= p1.x && p2.y <= p1.y) {
            return -1;
        }

        return 0;
    }

    static class Point {
        int x, y;

        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


