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

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cbarn.in")));

        int n = nextInt();

        int[] barn = new int[n];

        for (int i = 0; i < n; i++) {
            barn[i] = nextInt();
        }

        int result = Integer.MAX_VALUE;

        for (int startIndex = 0; startIndex < n; startIndex++) {
            PriorityQueue<Integer> timesOfCows = new PriorityQueue<>();

            int curCost = 0;

            boolean finished = true;

            // go around the entire barn
            for (int i = 0; i < n; i++) {
                int curIndex = (startIndex + i) % n;

                // add all waiting cows
                for (int j = 0; j < barn[curIndex]; j++) {
                    timesOfCows.add(i);
                }

                Integer nextCow = timesOfCows.poll();

                if (nextCow == null) {
                    finished = false;
                    break;
                }

                curCost += Math.pow((i - nextCow), 2);
            }

            if (finished) {
                result = Math.min(result, curCost);
            }
        }

        PrintWriter out = new PrintWriter(new File("cbarn.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


