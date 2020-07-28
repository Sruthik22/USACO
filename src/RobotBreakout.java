// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class RobotBreakout {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            int n = nextInt();

            int minX = -100000;
            int maxX = 100000;
            int minY = -100000;
            int maxY = 100000;

            boolean works = true;

            for (int j = 0; j < n; j++) {
                int x = nextInt();
                int y = nextInt();

                int f1 = nextInt();
                int f2 = nextInt();
                int f3 = nextInt();
                int f4 = nextInt();

                if (f1 == 0) {
                    if (maxX < x) {
                        works = false;
                    }

                    minX = Math.max(minX, x);
                }

                if (f2 == 0) {
                    if (minY > y) {
                        works = false;
                    }

                    maxY = Math.min(maxY, y);
                }

                if (f3 == 0) {
                    if (minX > x) {
                        works = false;
                    }

                    maxX = Math.min(maxX, x);
                }

                if (f4 == 0) {
                    if (maxY < y) {
                        works = false;
                    }

                    minY = Math.max(minY, y);
                }
            }

            if (works) {
                out.println("1 " + minX + " " + minY);
            }

            else {
                out.println(0);
            }
        }

        out.close();
    }
}


