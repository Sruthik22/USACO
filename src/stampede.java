// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class stampede {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("stampede.in")));

        int n = nextInt();

        int[] times = new int[2*n];
        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int x = nextInt() + 1;
            int y = nextInt();
            int r = nextInt(); // the amount of time it takes to move 1 unit

            // all cows are one unit long so they stay in the frame for r units of time

            times[i * 2] = r * Math.abs(x);
            times[i*2+1] = r * Math.abs(x) + r;
            Cow c = new Cow (x, y, r);

            cows[i] = c;
        }

        Arrays.sort(times);
        Arrays.sort(cows, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.time - o2.time;
            }
        });

        int time = 0;
        int timeIndex = 0;


        int cowIndex = 0;

        PriorityQueue<Cow> active = new PriorityQueue<>();

        HashSet<Integer> seen = new HashSet<>();

        while (time <= times[2 * n - 1] && timeIndex < 2 * n) {
            while (timeIndex + 1 < 2*n && times[timeIndex] == times[timeIndex+1]) timeIndex++;
            time = times[timeIndex];

            // need to add all of the cows that are added at this time

            while (cowIndex < n && cows[cowIndex].time == time) {
                active.add(cows[cowIndex]);
                cowIndex++;
            }

            // now need to add the head of this to the seen, if in the correct time

            while (active.size() > 0 && active.peek().time + active.peek().r <= time) active.poll();

            if (active.size() > 0) seen.add(active.peek().y);

            // move to the next time step
            timeIndex++;
        }

        int result = seen.size();

        PrintWriter out = new PrintWriter(new File("stampede.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int x, y, r, time;

        Cow (int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;

            this.time = r * Math.abs(x);
        }

        @Override
        public int compareTo(Cow o) {
            return this.y - o.y;
        }
    }
}


