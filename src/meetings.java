// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class meetings {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("meetings.in")));

        int n = nextInt();
        int l = nextInt();

        Cow[] cows = new Cow[n];

        int numRight = 0;

        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            int w = nextInt();
            totalWeight += w;
            int x = nextInt();
            int d = nextInt();

            if (d == 1) {
                numRight++;
            }

            cows[i] = new Cow(w,x,d);
        }

        int numLeft = n - numRight;

        Arrays.sort(cows, new CustomComparator());

        ArrayList<Integer> rightTimes = new ArrayList<>();
        ArrayList<Integer> leftTimes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Cow c = cows[i];
            if (c.d == 1) {
                rightTimes.add(l - c.x);
            }

            else {
                leftTimes.add(c.x);
            }
        }

        for (int i = 0; i < numLeft; i++) {
            Cow c = cows[i];
            c.timeToFinish = leftTimes.get(i);
        }

        for (int i = n-1; i >= n - numRight; i--) {
            Cow c = cows[i];
            c.timeToFinish = rightTimes.get(rightTimes.size() - (n - i));
        }

        Arrays.sort(cows);

        int weight = 0;
        int index = 0;
        int t = 0;
        while (weight < (double) (totalWeight) / 2) {
            Cow c = cows[index];
            weight += c.w;
            index++;
            t = c.timeToFinish;
        }

        Arrays.sort(cows, new CustomComparator());

        ArrayList<Integer> cow_to_check = new ArrayList<>();
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (cows[i].d == 1) cow_to_check.add(cows[i].x);
            else {
                // need to check if current cow intersects with any of the cow_to_check

                for (int j = cow_to_check.size()-1; j >= 0; j--) {
                    if (2 * t - (cows[i].x - cow_to_check.get(j)) >= 0) {
                        result++;
                    }
                    else {
                        // for anyone it doesn't work, we can remove them
                        cow_to_check.remove(j);
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("meetings.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static class CustomComparator implements Comparator<Cow> {
        @Override
        public int compare(Cow o1, Cow o2) {
            return o1.x - o2.x;
        }

    }

    static class Cow implements Comparable<Cow>{
        int w,x,d, timeToFinish;

        Cow (int w, int x, int d) {
            this.w = w;
            this.x = x;
            this.d = d;
        }

        @Override
        public int compareTo(Cow o) {
            return this.timeToFinish - o.timeToFinish;
        }
    }
}


