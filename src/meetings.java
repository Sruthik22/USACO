// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class meetings {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("meetings.in"));
        int n = in.nextInt();
        int l = in.nextInt();

        Cow[] cows = new Cow[n];

        double weightToReach = 0;

        for (int i = 0; i < n; i++) {
            int weight = in.nextInt();
            int x = in.nextInt();
            int direction = in.nextInt();

            cows[i] = new Cow(weight, x, direction);

            weightToReach+=weight;
        }

        in.close();

        weightToReach /= 2.0;

        Arrays.sort(cows, (c1, c2) -> c1.x - c2.x);


        int numRight = n-1;
        int numLeft = 0;

        for (int i = 0; i < n; i++) {
            if (cows[i].direction == -1) {
                cows[numLeft].time = cows[i].x;
                numLeft++;
            }
        }

        for (int i = n-1; i >= 0; i--) {
            if (cows[i].direction == 1) {
                cows[numRight].time = l - cows[i].x;
                numRight--;
            }
        }

        Arrays.sort(cows);

        int T = 0;
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {

            if (totalWeight >= weightToReach) break;

            totalWeight+=cows[i].weight;
            T=cows[i].time;
        }

        int result = 0;

        Arrays.sort(cows, (c1, c2) -> c1.x - c2.x);

        Queue<Integer> cowMeetings = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (cows[i].direction == -1) {
                while (cowMeetings.size() > 0 && cowMeetings.peek() + 2 * T < cows[i].x) cowMeetings.poll();

                result+= cowMeetings.size();
            }

            else cowMeetings.add(cows[i].x);
        }

        PrintWriter out = new PrintWriter(new File("meetings.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int direction, weight, time, x;

        Cow(int weight, int x, int direction) {
            this.direction = direction;
            this.x = x;
            this.weight = weight;
        }

        @Override
        public int compareTo(Cow o) {
            return this.time - o.time;
        }
    }
}