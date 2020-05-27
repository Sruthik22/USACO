// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class slowdown {

    final public static int END = 1000;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("slowdown.in"));
        int n = in.nextInt();

        PriorityQueue<Integer> tEvents = new PriorityQueue<Integer>();
        PriorityQueue<Integer> dEvents = new PriorityQueue<Integer>();

        for (int i = 0; i < n; i++) {
            char c = in.next().charAt(0);

            int j = in.nextInt();

            if (c == 'T') {
                tEvents.offer(j);
            }

            else {
                dEvents.offer(j);
            }
        }

        tEvents.offer(END*(n+1));
        dEvents.offer(END+1);

        in.close();

        int result = (int)(Math.round(solve(tEvents, dEvents)));

        PrintWriter out = new PrintWriter(new File("slowdown.out"));
        System.out.println(Math.round(result));
        out.println(Math.round(result));
        out.close();
    }

    public static double solve(PriorityQueue<Integer> tEvents, PriorityQueue<Integer> dEvents) {

        double curDist = 0, curTime = 0;
        int curDen = 1;
        int result = -1;

        // This is as far as we have to go.
        while (curDist < END) {

            // No more events to occur before we get past the next switch point.
            if (curDist + (tEvents.peek()-curTime)/curDen >= END && dEvents.peek() >= END)
                return curTime + (END-curDist)*curDen;

                // Something MUST occur before the end...
            else {

                // Get distances till next event.
                int nextD = dEvents.peek();
                int nextT = tEvents.peek();
                double dTimeEvent = curDist + ((nextT-curTime)/curDen);


                // Go to the distance event first.
                if (nextD < dTimeEvent) {
                    dEvents.poll();
                    curTime += ((nextD - curDist)*curDen);
                    curDist = nextD;
                }

                // Go to the time event first.
                else {
                    tEvents.poll();
                    curDist += ((nextT - curTime)/curDen);
                    curTime = nextT;
                }

                // We slow down!
                curDen++;
            }
        }

        // Should never get here...
        return END;
    }
}


