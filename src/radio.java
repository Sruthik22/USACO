// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.awt.im.spi.InputMethod;
import java.util.*;
import java.io.*;

public class radio {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("radio.in")));

        int n = nextInt();
        int m = nextInt();

        int fx = nextInt();
        int fy = nextInt();

        int bx = nextInt();
        int by = nextInt();

        String fjPath = next();
        String bPath = next();

        Point[] fj = new Point[n + 1];
        fj[0] = new Point(fx, fy);
        for (int i = 1; i <= n; i++) {
            Point lastPoint = fj[i - 1];
            char c = fjPath.charAt(i - 1);

            Point thisPoint;

            if (c == 'N') {
                thisPoint = new Point(lastPoint.x, lastPoint.y + 1);
            }

            else if (c == 'S') {
                thisPoint = new Point(lastPoint.x, lastPoint.y - 1);
            }

            else if (c == 'E') {
                thisPoint = new Point(lastPoint.x + 1, lastPoint.y);
            }

            else {
                thisPoint = new Point(lastPoint.x - 1, lastPoint.y);
            }

            fj[i] = thisPoint;
        }
        Point[] b = new Point[m + 1];
        b[0] = new Point(bx, by);
        for (int i = 1; i <= m; i++) {
            Point lastPoint = b[i - 1];
            char c = bPath.charAt(i - 1);

            Point thisPoint;

            if (c == 'N') {
                thisPoint = new Point(lastPoint.x, lastPoint.y + 1);
            }

            else if (c == 'S') {
                thisPoint = new Point(lastPoint.x, lastPoint.y - 1);
            }

            else if (c == 'E') {
                thisPoint = new Point(lastPoint.x + 1, lastPoint.y);
            }

            else {
                thisPoint = new Point(lastPoint.x - 1, lastPoint.y);
            }

            b[i] = thisPoint;
        }

        long[][] dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Point bessie = b[j];

                if (i != n) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + fj[i+1].distance(bessie));
                }

                Point farmer = fj[i];

                if (j != m) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + b[j+1].distance(farmer));
                }

                if (i != n && j != m) {
                    dp[i + 1][j+1] = Math.min(dp[i + 1][j+1], dp[i][j] + b[j+1].distance(fj[i+1]));
                }
            }
        }

        long result = dp[n][m];
        PrintWriter out = new PrintWriter(new File("radio.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distance(Point other) {
            return Math.abs(other.x - x) * Math.abs(other.x - x) + Math.abs(other.y - y) * Math.abs(other.y - y);
        }
    }
}


