// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class proximity {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("proximity.in"));
        int n = in.nextInt();
        int k = in.nextInt();

        int[] cows = new int[n];

        for (int i = 0; i < n; i++) {
            cows[i] = in.nextInt();
        }

        in.close();

        int result = -1;

        ArrayList<Integer> s = new ArrayList<>();
        s.add(cows[0]);

        int curIndex = 0;
        int lowestVal = cows[0];
        int highestVal = cows[0];
        int lenientSpace = k;

        // setup for sliding window

        while (curIndex+1 < n &&
                lowestVal - lenientSpace <= cows[curIndex+1] &&
                highestVal + lenientSpace >= cows[curIndex+1]) {

            curIndex++;

            if (s.contains(cows[curIndex])) {
                result = Math.max(result, cows[curIndex]);
            }

            s.add(cows[curIndex]);

            lowestVal = Math.min(lowestVal, cows[curIndex]);
            highestVal = Math.max(highestVal, cows[curIndex]);

            lenientSpace = k - (highestVal - lowestVal);
        }

        // sliding window

        while (curIndex+1 < n) {
            // the rest of the steps

            if (s.size() == 0) {
                // then we just need to move to the next cow
                curIndex++;
                s.add(cows[curIndex]);
                lowestVal = cows[curIndex];
                highestVal = cows[curIndex];
                lenientSpace = k;
            }

            else {
                s.remove(0);

                /*
                When you remove, you need to check if you removed the highest or lowest and recalculate

                */
            }

            while (curIndex+1 < n &&
                    lowestVal - lenientSpace <= cows[curIndex+1] &&
                    highestVal + lenientSpace >= cows[curIndex+1]) {

                curIndex++;

                if (s.contains(cows[curIndex])) {
                    result = Math.max(result, cows[curIndex]);
                }

                s.add(cows[curIndex]);

                lowestVal = Math.min(lowestVal, cows[curIndex]);
                highestVal = Math.max(highestVal, cows[curIndex]);

                lenientSpace = k - (highestVal - lowestVal);
            }
        }

        PrintWriter out = new PrintWriter(new File("proximity.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}

/*
I thought that the breeds were positions and they couldn't be more than that far apart from each other
*/
