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

    static ArrayList<Point> points;
    static Line[] lines;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cowjump.in")));

        int n = nextInt();

        points = new ArrayList<>();
        lines = new Line[n];

        for (int i = 0; i < n; i++) {
            int x1 = nextInt();
            int y1 = nextInt();

            Point p1 = new Point(x1, y1, i);

            points.add(p1);

            int x2 = nextInt();
            int y2 = nextInt();

            Point p2 = new Point(x2, y2, i);

            points.add(p2);

            lines[i] = new Line(p1, p2);
        }

        Collections.sort(points);

        ArrayList<Point> activeList = new ArrayList<>();
        HashSet<Point> activeHash = new HashSet<>();

        int l1Index = 0;
        int l2Index = 0;

        for (int i = 0; i < 2 * n; i++) {
            // we need to check if the other point in this line is already used

            Point cur = points.get(i);

            Point other;

            if (lines[cur.id].p1.equals(cur)) other = lines[cur.id].p2;
            else other = lines[cur.id].p1;

            if (activeHash.contains(other)) {
                int index = activeList.indexOf(other);
                // when this is removed, we need to check if the two that surrounded it are intersecting

                activeList.remove(index);
                activeHash.remove(other);

                if (index != 0 && index != activeList.size()) {
                    // then we can check if the surrounding is intersecting

                    int lineID1 = activeList.get(index-1).id;
                    int lineID2 = activeList.get(index).id;

                    boolean result = intersection(lineID1, lineID2);

                    if (result) {
                        l1Index = lineID1;
                        l2Index = lineID2;
                        break;
                    }
                }

            }

            else {
                activeList.add(cur);
                activeHash.add(cur);

                if (activeList.size() != 1) {

                    int lineID1 = cur.id;
                    int lineID2 = activeList.get(activeList.size() - 2).id;

                    boolean result = intersection(lineID1, lineID2);

                    if (result) {
                        l1Index = lineID1;
                        l2Index = lineID2;
                        break;
                    }
                }
            }
        }

        int numIntersections_l1 = 0;
        int numIntersections_l2 = 0;

        for (int i = 0; i < n; i++) {
            if (i != l1Index) {
                if (intersection(l1Index, i)) numIntersections_l1++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != l2Index) {
                if (intersection(l2Index, i)) numIntersections_l2++;
            }
        }

        int result;

        if (numIntersections_l1 > numIntersections_l2) {
            result = l1Index;
        }

        else if (numIntersections_l2 > numIntersections_l1) {
            result = l2Index;
        }

        else {
            result = Math.min(l1Index, l2Index);
        }

        result++;

        PrintWriter out = new PrintWriter(new File("cowjump.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean intersection(int index1, int index2) {
        Line l1 = lines[index1];
        Line l2 = lines[index2];

        return Point.intersectQ(l1.p1, l1.p2, l2.p1, l2.p2);
    }

    public static class Line {
        Point p1, p2;

        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public static class Point implements Comparable<Point>{
        public int x, y, id;
        public Point(int x, int y, int id) {
            this.x = x; this.y = y; this.id = id;
        }

        public static int sign(Point A, Point B, Point C) {
            int area = (B.x-A.x) * (C.y-A.y) - (C.x-A.x) * (B.y-A.y);
            return Integer.compare(area, 0);
        }
        public static boolean between(Point P, Point X, Point Y) {
            return ((X.x <= P.x && P.x <= Y.x) || (Y.x <= P.x && P.x <= X.x))
                    && ((X.y <= P.y && P.y <= Y.y) || (Y.y <= P.y && P.y <= X.y));
        }
        public static boolean intersectQ(Point P, Point Q, Point X, Point Y) {
            int[] signs = {sign(P, X, Y), sign(Q, X, Y), sign(X, P, Q), sign(Y, P, Q)};
            if (signs[0] == 0 && signs[1] == 0 && signs[2] == 0 && signs[3] == 0)
                return between(P, X, Y) || between(Q, X, Y) || between(X, P, Q);
            return signs[0] != signs[1] && signs[2] != signs[3];
        }

        public boolean equals(Point o) {
            return this.x == o.x && this.y == o.y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }

            else return this.x - o.x;
        }
    }

}


