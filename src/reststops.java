// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class reststops {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("reststops.in"));
        int L = in.nextInt(); // total path length
        int N = in.nextInt(); // number of rest stops
        int rF = in.nextInt(); // farmer john speed
        int rB = in.nextInt(); // bessie speed

        Stops[] stops = new Stops[N];

        for (int i = 0; i < N; i++) {
            stops[i] = new Stops(in.nextInt(), in.nextInt());
        }

        Arrays.sort(stops);

        in.close();

        long result = 0;

        long time = 0;

        for (Stops s: stops) {
            if (s.meter < time) continue;
            long timeAtStop = (s.meter - time) * (rF - rB);
            result += timeAtStop * s.tastiness;
            time = s.meter;
        }

        PrintWriter out = new PrintWriter(new File("reststops.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static class Stops implements Comparable<Stops> {
        long meter;
        int tastiness;

        public Stops(int meter, int tastiness) {
            this.meter = meter;
            this.tastiness = tastiness;
        }

        @Override
        public int compareTo(Stops o) {
            return o.tastiness - this.tastiness;
        }
    }
}


