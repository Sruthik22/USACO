/*
ID: sruthi.2
LANG: JAVA
TASK: milk2
*/

import java.util.*;
import java.io.*;

public class milk2 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("milk2.in")));

        int n = nextInt();

        Time[] criticalPoints = new Time[2*n];
        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int start = nextInt();
            int end = nextInt();

            Cow c = new Cow(start, end, i);
            cows[i] = c;

            criticalPoints[2*i] = new Time(start, i);
            criticalPoints[2*i+1] = new Time(end, i);
        }

        Arrays.sort(criticalPoints);

        int maxEmpty = 0;
        int maxLastCow = 0;

        int lastEmpty = -1;
        int lastCow = -1;

        PriorityQueue<Cow> active = new PriorityQueue<>();

        for (int i = 0; i < criticalPoints.length; i++) {
            Time t = criticalPoints[i];
            int time = t.time;
            int id = t.id;

            if (i != criticalPoints.length - 1 && criticalPoints[i].time == criticalPoints[i+1].time) {
                // same time as the next point
                if (cows[id].start == time) {
                    active.add(cows[id]);
                }

                else {
                    // need to remove the cow
                    active.poll();
                }

                continue;
            }

            if (cows[id].start == time) {
                // need to add to the active priority queue
                active.add(cows[id]);

                // if this is the first cow in the priority queue, then end the last empty
                if (active.size() > 0 && lastCow == -1) {
                    if (lastEmpty != -1) {
                        maxEmpty = Math.max(maxEmpty, time - lastEmpty);
                        lastEmpty = -1;
                    }

                    // start the last cow time
                    lastCow = time;
                }
            }

            else {
                // need to remove the cow

                active.poll();

                if (active.isEmpty() && lastEmpty == -1) {
                    // the active is empty, start the last empty
                    lastEmpty = time;

                    // the lastcow also needs to end
                    if (lastCow != -1) {
                        maxLastCow = Math.max(maxLastCow, time - lastCow);
                        lastCow = -1;
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("milk2.out"));
        System.out.println(maxLastCow + " " + maxEmpty);
        out.println(maxLastCow + " " + maxEmpty);
        out.close();
    }

    public static class Time implements Comparable<Time> {
        int time, id;

        Time (int time, int id) {
            this.time = time;
            this.id = id;
        }

        @Override
        public int compareTo(Time o) {
            return this.time - o.time;
        }
    }

    public static class Cow implements Comparable<Cow> {
        int start, end, id;

        Cow (int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
        }

        @Override
        public int compareTo(Cow o) {
            return this.end - o.end;
        }
    }
}


