// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class triangles {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("triangles.in"));

        int n = in.nextInt();
        HashMap<Integer, ArrayList<Point>> x = new HashMap<>();
        HashMap<Integer, ArrayList<Point>> y = new HashMap<>();

        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int xCoordinate = in.nextInt();
            int yCoordinate = in.nextInt();

            Point p = new Point(xCoordinate, yCoordinate, i);

            points[i] = p;

            Object o = x.get(xCoordinate);

            if (o != null) {
                ArrayList s = (ArrayList) o;
                s.add(p);
            }

            else {
                ArrayList<Point> s = new ArrayList<>();
                s.add(p);
                x.put(xCoordinate, s);
            }

            Object obj = y.get(yCoordinate);

            if (obj != null) {
                ArrayList<Point> s = (ArrayList) obj;
                s.add(p);
            }

            else {
                ArrayList<Point> s = new ArrayList<>();
                s.add(p);
                y.put(yCoordinate, s);
            }

        }

        in.close();

        for (int xCoord : x.keySet()) {
            ArrayList<Point> ys = x.get(xCoord);

            if (ys.size() < 2) continue;

            ys.sort(Comparator.comparingInt(o -> o.y));

            int sum = 0;

            for (int i = 1; i < ys.size(); i++) {
                sum+=ys.get(i).y - ys.get(0).y;
            }


            for (int i = 0; i < ys.size(); i++) {
                Point p = ys.get(i);

                points[p.id].yPrecompute = sum;

                if (i == ys.size()-1) break;

                // now to remove values from the sum

                sum += (2*(i+1)-ys.size())*(ys.get(i+1).y-ys.get(i).y);
            }
        }

        long result = 0;

        for (int yCoord : y.keySet()) {
            // the coordinates are sorted, now go through and find the distances between each

            ArrayList<Point> xs = y.get(yCoord);

            if (xs.size() < 2) continue;

            xs.sort(Comparator.comparingInt(o -> o.x));

            int sum = 0;

            for (int i = 1; i < xs.size(); i++) {
                sum+=xs.get(i).x - xs.get(0).x;
            }

            // go through each index value and calculate

            for (int i = 0; i < xs.size(); i++) {
                Point p = xs.get(i);

                points[p.id].xPrecompute = sum;

                if (i == xs.size()-1) break;

                // now to remove values from the sum

                sum += (2*(i+1)-xs.size())*(xs.get(i+1).x-xs.get(i).x);
            }
        }

        for (int i = 0; i < n; i++) {
            Point point = points[i];

            result += point.xPrecompute * point.yPrecompute;
        }

        result %= (1000*1000*1000+7);

        PrintWriter out = new PrintWriter(new File("triangles.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Point {
        int x, y, id;

        int xPrecompute, yPrecompute;

        Point(int x, int y, int id) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
}