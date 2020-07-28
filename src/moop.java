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

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("moop.in")));

        int n = nextInt();

        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();

            Point p = new Point(x, y);

            points[i] = p;
        }

        Arrays.sort(points);

        ArrayList<Integer> ys = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Point p = points[i];

            int y = p.y;

            if (ys.isEmpty()) ys.add(y);

            else {
                if (ys.get(0) > y) ys.add(0, y);

                else {
                    // we need to delete all of the values that are above the least value, and less than the y values

                    int index = next(ys, y);

                    if (index < 0) {
                        // we need to delete all of the values other than first value

                        ys.subList(1, ys.size()).clear();
                    }

                    else ys.subList(1, index).clear();
                }
            }
        }

        int result = ys.size();

        PrintWriter out = new PrintWriter(new File("moop.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int next(ArrayList<Integer> arr, int target)
    {
        int start = 0, end = arr.size() - 1;

        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            // Move to right side if target is
            // greater.
            if (arr.get(mid) <= target) {
                start = mid + 1;
            }

            // Move left side.
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }

    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {

            if (this.x - o.x == 0) {
                // we want the higher y to be after the previous

                return this.y - o.y;
            }

            return this.x - o.x;
        }
    }
}


