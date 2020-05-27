// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// https://docs.google.com/document/d/1_7TVGur8qJU3Ts-UlhEktA19RlAiwBjuMY8tCi3Zk60/edit

import java.util.*;
import java.io.*;

public class skyline {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("skyline.in"));
        int n = in.nextInt(); // # of buildings
        int b = in.nextInt(); // # of buildings in one photo

        int[] heights = new int[n+1];
        // index: building number, 0 is unused

        for (int i = 1; i <= n; i++) {
            heights[i] = in.nextInt() / b;
        }
        in.close();

        int avgHeight = 0;
        int windowBegin = 1;

        for (int i = 1; i <= b; i++) {
            avgHeight += heights[i];
        }

        int bestAvgHeight = avgHeight;
        int bestWindowBegin = 1;

        while (windowBegin + b - 1 < n) {
            // if windowStart = 7, b = 3, buildings 7, 8, 9
            // 7 + 3 - 1 = 9
            avgHeight -= heights[windowBegin];
            avgHeight += heights[windowBegin + b];
            windowBegin++;

            if (avgHeight > bestAvgHeight) {
                bestAvgHeight = avgHeight;
                bestWindowBegin = windowBegin;
            }
        }

        String result = "" + (bestWindowBegin + (b-1)/2);

        if ((b ) % 2 == 0) {
            result += ".5";
        }

        result+= " " + avgHeight;

        PrintWriter out = new PrintWriter(new File("skyline.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


