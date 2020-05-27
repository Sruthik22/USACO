// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class speeding {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("speeding.in"));
        int n = in.nextInt(); // information about road length, speed limit
        int m = in.nextInt(); // information about Bessie

        int[] rSpeeds = new int[n];
        List< Integer > rLengths = new ArrayList<>(n);
        int[] bSpeeds = new int[m];
        List< Integer > bLengths = new ArrayList<>(m);

        Set<Integer> importantTimes = new TreeSet<>();

        // tree set: no duplicates with order
        int curLength = 0;
        for (int i = 0; i < n; i++) {
            curLength += in.nextInt();
            rLengths.add(curLength);

            rSpeeds[i] = in.nextInt();
            importantTimes.add(curLength);
        }

        curLength = 0;
        for (int i = 0; i < m; i++) {
            curLength += in.nextInt();
            bLengths.add(curLength);
            bSpeeds[i] = in.nextInt();
            importantTimes.add(curLength);
        }

        in.close();

        int result = 0;

        int roadLimitIndex = 0; int roadLimit = rSpeeds[roadLimitIndex];
        int bessieSpeedIndex = 0; int bessieSpeed = bSpeeds[bessieSpeedIndex];

        int lengthOfRoad = rLengths.get(rLengths.size()-1);

        for (int t : importantTimes) {

            result = Math.max(result, bessieSpeed-roadLimit);

            if (!(t == lengthOfRoad)) {
                if (rLengths.contains(t)) {
                    // need to update roadLimit
                    roadLimitIndex++;
                    roadLimit = rSpeeds[roadLimitIndex];
                }

                if (bLengths.contains(t)) {
                    // need to update bessieSpeed
                    bessieSpeedIndex++;
                    bessieSpeed = bSpeeds[bessieSpeedIndex];
                }
            }

        }

        PrintWriter out = new PrintWriter(new File("speeding.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}



