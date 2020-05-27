// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class marathon {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("marathon.in"));
        int n = in.nextInt();

        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            points[i] = new Point(x, y);
        }

        in.close();

        int saving = Integer.MIN_VALUE;

        int totalTime = 0;

        for (int i = 1; i < n-1; i++) {
            if (i == 1)
                totalTime += distance(points[i-1], points[i]);
            totalTime += distance(points[i+1], points[i]);
            int direct = distance(points[i-1], points[i+1]);
            int intermediate = distance(points[i-1], points[i]) + distance(points[i], points[i+1]);

            saving = Math.max(saving, intermediate - direct);
        }


        PrintWriter out = new PrintWriter(new File("marathon.out"));
        System.out.println(totalTime - saving);
        out.println(totalTime - saving);
        out.close();
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int distance (Point i, Point j) {
        return Math.abs(i.x - j.x) + Math.abs(i.y - j.y);
    }
}




