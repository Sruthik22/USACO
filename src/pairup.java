// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class pairup {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("pairup.in"));
        int n = in.nextInt();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int numCow = in.nextInt();
            int cowO = in.nextInt();

            cows[i] = new Cow(numCow, cowO);
        }

        in.close();

        Arrays.sort(cows);

        int result = 0;

        int pointer1 = 0;
        int pointer2 = n-1;

        while (pointer1 < pointer2) {
            if (cows[pointer1].numCows >= cows[pointer2].numCows) {
                result = Math.max(result, cows[pointer1].cowOutput + cows[pointer2].cowOutput);

                cows[pointer1].numCows -= cows[pointer2].numCows;
                cows[pointer2].numCows = 0;
                pointer2 --;
            }

            else {
                result = Math.max(result, cows[pointer1].cowOutput + cows[pointer2].cowOutput);

                cows[pointer2].numCows -= cows[pointer1].numCows;
                cows[pointer1].numCows = 0;
                pointer1++;
            }


        }

        PrintWriter out = new PrintWriter(new File("pairup.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int numCows, cowOutput;

        public Cow (int numCows, int cowOutput) {
            this.numCows = numCows;
            this.cowOutput = cowOutput;
        }

        @Override
        public int compareTo(Cow o) {
            return this.cowOutput - o.cowOutput;
        }
    }
}


