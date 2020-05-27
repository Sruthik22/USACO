// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class balancing {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("balancing.in"));
        int n = in.nextInt(); // total number of cows

        ArrayList<Point> xCoord = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            Point p = new Point(x, y);

            xCoord.add(p);
        }

        Collections.sort(xCoord);

        in.close();

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            // moving the horizontal line
            // how many are above, and how many below

            /*
            SOLUTION: to loop through all of the cows to find above and below the horizontal
            */

            ArrayList<Point> above = new ArrayList<>();
            ArrayList<Point> below = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if (xCoord.get(j).y > xCoord.get(i).y) below.add(xCoord.get(j));

                else above.add(xCoord.get(j));
            }

            int iAbove = 0;
            int iBelow = 0;

            while(iBelow < below.size() || iAbove < above.size()) {
                int xBorder = Integer.MAX_VALUE;
                if(iBelow < below.size()) {
                    xBorder = Math.min(xBorder, below.get(iBelow).x);
                }
                if(iAbove < above.size()) {
                    xBorder = Math.min(xBorder, above.get(iAbove).x);
                }
                while(iBelow < below.size() && below.get(iBelow).x == xBorder) {
                    iBelow++;
                }
                while(iAbove < above.size() && above.get(iAbove).x == xBorder) {
                    iAbove++;
                }
                result = Math.min(result, Math.max(Math.max(iBelow, below.size() - iBelow), Math.max(iAbove, above.size() - iAbove)));
            }
        }


        PrintWriter out = new PrintWriter(new File("balancing.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Point implements Comparable <Point>{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return this.x - o.x;
        }
    }
}


