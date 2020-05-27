// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class reduce {

    public static Cow[] cows;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("reduce.in"));

        int n = in.nextInt();

        ArrayList<Cow> xs = new ArrayList<>();
        ArrayList<Cow> ys = new ArrayList<>();

        cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            Cow c = new Cow(x, y, i);

            cows[i] = c;

            xs.add(c);
            ys.add(c);
        }

        xs.sort(new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.x - o2.x;
            }
        });

        ys.sort(new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.y - o2.y;
            }
        });

        in.close();

        int result = Integer.MAX_VALUE;

        HashSet<Cow> extremes = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            extremes.add(xs.get(i));
            extremes.add(ys.get(i));
        }

        for (int i = n-1; i >= n-4; i--) {
            extremes.add(xs.get(i));
            extremes.add(ys.get(i));
        }

        List<Cow> extremeList = new ArrayList<Cow>(extremes);

        for (int i = 0; i < extremeList.size() - 2; i++) {
            for (int j = i+1; j < extremeList.size() - 1; j++) {
                for (int x = j+1; x < extremeList.size(); x++) {
                    result = Math.min(result, area(extremeList.get(i).id, extremeList.get(j).id, extremeList.get(x).id));
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("reduce.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static int area(int c1, int c2, int c3) {
        int minX = 100000, maxX = 0;
        int minY = 100000, maxY = 0;

            // Go through all pts but the excluded one.
        for (int i=0; i<cows.length; i++) {
            if (i == c1 || i == c2 || i == c3) continue;
                minX = Math.min(minX, cows[i].x);
                maxX = Math.max(maxX, cows[i].x);
                minY = Math.min(minY, cows[i].y);
                maxY = Math.max(maxY, cows[i].y);
        }

            // Here is our box.
        return (maxX-minX)*(maxY-minY);
    }

    static class Cow {
        int x, y, id;

        Cow (int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
}


