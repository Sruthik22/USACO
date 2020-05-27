/*
ID: sruthi.2
LANG: JAVA
TASK: barn1
*/

import java.util.*;
import java.io.*;

public class barn1 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("barn1.in")));

        int m = nextInt();
        int s = nextInt(); // total number of stalls
        int c = nextInt();

        int[] stalls = new int[c];

        for (int i = 0; i < c; i++) {
            // the stalls
            stalls[i] = nextInt();
        }

        Arrays.sort(stalls);

        ArrayList<Interval> intervals = new ArrayList<>();

        for (int i = 1; i < c; i++) {
            // every iteration is going to split a board into 2

            if (stalls[i] > 1 + stalls[i-1]) {
                // they are not adjacent
                Interval interval = new Interval(stalls[i-1], stalls[i]);
                intervals.add(interval);
            }
        }

        Collections.sort(intervals);

        int result = stalls[stalls.length-1]-stalls[0]+1;

        if (intervals.size() < m - 1) result = c;

        else {
            for (int i = 0; i < m-1; i++) {
                Interval interval = intervals.get(i);

                result-=interval.span();
            }
        }

        PrintWriter out = new PrintWriter(new File("barn1.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Interval implements Comparable<Interval> {
        int start, end;

        Interval(int start, int end) {
            // the start and the end points are going to be included

            this.start = start;
            this.end = end;
        }

        private int span() {
            return end - start - 1;
        }

        @Override
        public int compareTo(Interval o) {
            return -(this.end - this.start - o.end + o.start);
        }
    }
}


