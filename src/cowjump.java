// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowjump {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("cowjump.in")));
        int n = nextInt();

        line[] lines = new line[n];
        point[] points = new point[2*n];

        for (int i = 0; i < n; i++) {
            int x1 = nextInt();
            int y1 = nextInt();
            int x2 = nextInt();
            int y2 = nextInt();

            points[i*2] = new point(x1, y1, i);
            points[i*2+1] = new point(x2, y2, i);

            lines[i] = new line(points[i*2], points[i*2+1]);
        }

        Arrays.sort(points);

        // the index of line, and the number of times it has been intersected

        int result = -1;

        HashMap<Integer, Integer> cowsIntersections = new HashMap<>();
        HashSet<Integer> curCows = new HashSet<>();

        for (point currentPoint : points) {
            cowsIntersections.putIfAbsent(currentPoint.indexLine, 0);

            // check if it ends a line, then remove
            // if not
            // check if the line this point is part of intersects the lines within the map array
            // add one to each index it intersects in the cowsIntersections map
            // if any of the intersections is 2 or greater than end program by outputting that index
            // otherwise continue and if they are all equal, output the first index in the cowsIntersections Map with 1 intersection

            if (curCows.contains(currentPoint.indexLine)) {
                curCows.remove(currentPoint.indexLine);
            } else {
                for (int j : curCows) {
                    if (doIntersect(lines[j].a, lines[j].b, lines[currentPoint.indexLine].a, lines[currentPoint.indexLine].b)) {
                        int jIntersections = cowsIntersections.get(j) + 1;

                        if (jIntersections >= 2) {
                            result = j;
                            break;
                        }

                        cowsIntersections.put(j, jIntersections);

                        int curIntersections = cowsIntersections.get(currentPoint.indexLine) + 1;

                        if (curIntersections >= 2) {
                            result = currentPoint.indexLine;
                            break;
                        }

                        cowsIntersections.put(currentPoint.indexLine, curIntersections);
                    }

                }
                curCows.add(currentPoint.indexLine);
            }
        }

        if (result == -1) {
            // then find the first index in the intersections map with a non zero intersection
            for (Map.Entry<Integer,Integer> entry : cowsIntersections.entrySet()) {

                int key = entry.getKey();
                int value = entry.getValue();

                if (value == 1) {
                    result = key;
                    break;
                }
            }
        }

        result++;

        PrintWriter out = new PrintWriter(new File("cowjump.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean onSegment(point p, point q, point r)
    {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    private static int orientation(point p, point q, point r)
    {
        int val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // colinear

        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1'
// and 'p2q2' intersect.
    static boolean doIntersect(point p1, point q1, point p2, point q2)
    {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    private static class point implements Comparable<point>{
        int x, y;

        int indexLine;

        point(int x, int y, int indexLine) {
            this.x = x;
            this.y = y;
            this.indexLine = indexLine;
        }


        @Override
        public int compareTo(point o) {
            return this.x - o.x;
        }
    }

    private static class line {
        point a;
        point b;


        line(point a, point b) {
            this.a = a;
            this.b = b;
        }
    }
}


