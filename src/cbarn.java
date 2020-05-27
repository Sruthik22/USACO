// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cbarn {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int indexOfMax;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cbarn.in")));

        int n = nextInt();

        int[] circularBarn = new int[n];

        indexOfMax = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            int num = nextInt();

            circularBarn[i] = num;

            if (num >= max) {
                indexOfMax = i;
                max = num;
            }
        }

        int result = 0;

        PriorityQueue<Cow> cows = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int index = ( indexOfMax + i ) % n;

            if (circularBarn[index] != 0) {
                int numCows = circularBarn[index];

                for (int j = 0; j < numCows; j++) {
                    cows.add(new Cow(i));
                }
            }

            if (cows.peek().start == i) continue;

            Cow c = cows.poll();
            result += Math.pow(i - c.start, 2);
        }

        PrintWriter out = new PrintWriter(new File("cbarn.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int start; // the iteration that it was added

        Cow (int value) {
            this.start = value;
        }

        @Override
        public int compareTo(Cow o) {
            return this.start - o.start; // want the least to be the first in the queue
        }
    }
}


