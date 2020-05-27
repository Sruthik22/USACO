// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class helpcross {

    private static int CHICKEN = 2;
    private static int COW = 1;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("helpcross.in"));
        int c = in.nextInt();
        int n = in.nextInt();
        Event[] events = new Event[n+c];
        for (int i = 0; i < c; i++) {
            events[i] = new Event(CHICKEN, in.nextInt());
        }
        for (int i = 0; i < n; i++) {
            events[i+c] = new Event(COW, in.nextInt(),in.nextInt());
        }
        in.close();

        Arrays.sort(events);

        int result = 0;

        PriorityQueue<Integer> giveUpTimes = new PriorityQueue<>();

        for (Event e:events) {
            if (e.type == CHICKEN) {
                int nextGiveUpTime = -1;


                while (giveUpTimes.size() > 0) {
                    nextGiveUpTime = giveUpTimes.poll();

                    if (nextGiveUpTime >= e.startTime) {
                        break;
                    }

                }

                if (nextGiveUpTime >= e.startTime) {
                    result++;
                }
            }

            else {
                giveUpTimes.add(e.endTime);
            }
        }


        PrintWriter out = new PrintWriter(new File("helpcross.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static class Event implements Comparable<Event> {
        int type, startTime, endTime;

        private Event(int type, int startTime) {
            this.type = type;
            this.startTime = startTime;
        }

        private Event(int type, int startTime, int endTime) {
            this.type = type;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Event o) {
            int result = this.startTime - o.startTime;

            if (result != 0) {
                return result;
            }

            return this.type - o.type;
        }
    }
  
 /*
 ANALYSIS
 
 */
}


