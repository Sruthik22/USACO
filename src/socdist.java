// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class socdist {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    private static int n;
    private static int m;
    private static Interval[] intervals;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("socdist.in")));

        n = nextInt();
        m = nextInt();

        intervals = new Interval[m];

        for (int i = 0; i < m; i++) {
            long a = nextLong();
            long b = nextLong();

            intervals[i] = new Interval(a, b);
        }

        Arrays.sort(intervals);

        long low = 1; // nothing is desired this point and lower
        long high = 1000000000000000000L; // at this point and higher everything is always true

        while (high - low > 1) {
             long mid = (low + high)/2;
             if (simulate(mid)) low = mid;
             else high = mid;
        }

        long result = low;

        PrintWriter out = new PrintWriter(new File("socdist.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static boolean simulate(long D) {
        long lastPosition = -D;
        int numPlaced = 0;

        for (Interval interval: intervals) {
            long startPoint = Math.max(lastPosition + D, interval.a);

            int numCows = (int) Math.ceil(((double) (interval.b - startPoint + 1)) / D);

            if (numCows < 0) numCows = 0;

            numPlaced += numCows;

            if (numPlaced >= n) return true;

            lastPosition = startPoint + D * (numCows - 1);
        }


        return (numPlaced >= n);
    }

    static class Interval implements Comparable<Interval> {
        long a, b;

        Interval (long a, long b) {
            this.a = a;
            this.b = b;
        }


        @Override
        public int compareTo(Interval o) {
            if (this.a > o.a) return 1;
            if (this.a < o.a) return -1;
            else return 0;
        }
    }
}


