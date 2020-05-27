// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=431

import java.util.*;
import java.io.*;

public class fairphoto {

    private static int n;
    private static Cow[] cows;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("fairphoto.in"));
        n = in.nextInt();

        cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(in);
        }
        in.close();

        Arrays.sort(cows);

        int result = 0;

        int[] prefixSum = new int[n];

        int index = 0;
        for (Cow c:cows) {
            if (index == 0) {
                prefixSum[index] = c.breed;
                index++;
                continue;
            }
            prefixSum[index] = prefixSum[index-1] + c.breed;
            index++;
        }

        HashMap<Integer, Integer> seenBefore = new HashMap<>();

        for (int i = 0; i < n; i++) {

            if (prefixSum[i] == 0) {
                result = Math.max(result, cows[i].x - cows[0].x);
                continue;
            }

            Object indexOf = seenBefore.get(prefixSum[i]);

            if (indexOf != null) {

                result = Math.max(result, cows[i].x - cows[(int)indexOf+1].x);
            }

            else {
                seenBefore.put(prefixSum[i], i);
            }

        }

        int longestStringLength = longestLength();

        result = Math.max(result, longestStringLength);

        PrintWriter out = new PrintWriter(new File("fairphoto.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int longestLength() {
        // try to do both at once

        int startG = cows[0].x;
        int startH = cows[0].x;
        int largestLength = 0;
        int lastType = 0;

        for (int i = 0; i < n; i++) {
            if (cows[i].breed == 1) {
                if (lastType == 1) {
                    largestLength = Math.max(cows[i].x - startG, largestLength);
                }

                else {
                    startG = cows[i].x;
                    lastType = 1;
                }

            }
            else {
                if (lastType == -1) {
                    largestLength = Math.max(cows[i].x - startH, largestLength);
                }

                else {

                    startH = cows[i].x;
                    lastType = -1;
                }

            }
        }

        return largestLength;
    }

    private static class Cow implements Comparable<Cow>{
        int x;
        int breed;

        private Cow(Scanner in) {
            this.x = in.nextInt();

            if (in.next().charAt(0) == 'G') this.breed = 1;
            else this.breed = -1;
        }

        @Override
        public int compareTo(Cow o) {
            return this.x-o.x;
        }
    }
}


