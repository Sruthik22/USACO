// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowjog {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cowjog.in")));

        int n = nextInt();
        long t = nextLong();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(nextLong(), nextLong());
        }

        TreeSet<EndPoint> endPoints = new TreeSet<>();

        int largestId = 0;

        for (int i = 0; i < n; i++) {

            long thisPos = cows[i].startPos + t * cows[i].speed;

            if (endPoints.lower(new EndPoint(0, thisPos)) != null) {
                EndPoint endPoint = endPoints.lower(new EndPoint(0, thisPos));
                endPoints.add(new EndPoint(endPoint.id, thisPos));
                endPoints.remove(endPoint);
            }

            else {
                largestId++;
                endPoints.add(new EndPoint(largestId, thisPos));
            }
        }

        int result = largestId;
        PrintWriter out = new PrintWriter(new File("cowjog.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow {
        long startPos, speed;

        Cow(long startPos, long speed) {
            this.startPos = startPos;
            this.speed = speed;
        }
    }

    static class EndPoint implements Comparable<EndPoint>{
        long id;
        long lastPoint;

        EndPoint(long id, long lastPoint) {
            this.id = id;
            this.lastPoint = lastPoint;
        }

        @Override
        public int compareTo(EndPoint o) {
            if (this.lastPoint == o.lastPoint) {
                return (int) (this.id - o.id);
            }
            if (this.lastPoint - o.lastPoint > 0) return 1;
            else return -1;
        }
    }
}


