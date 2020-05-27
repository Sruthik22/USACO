// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class convention {

    private static int n;
    private static int m;
    private static int c;
    private static int[] cowsTimes;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("convention.in"));
        n = in.nextInt(); // number of cows
        m = in.nextInt(); // number of buses
        c = in.nextInt(); // max number of cows per bus

        cowsTimes = new int[n];
        for (int i = 0; i < n; i++) {
            cowsTimes[i] = in.nextInt();
        }

        Arrays.sort(cowsTimes);

        in.close();

        int low = 0;
        int high = Integer.MAX_VALUE;

        while (low < high) {
            int medium = (high + low) / 2;

            boolean works = simulate(medium);

            if (works) {
                high = medium;
            }

            else {
                low = medium + 1;
            }
        }

        int result = low - 1;

        PrintWriter out = new PrintWriter(new File("convention.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean simulate(int maxWeightingTime) {
        int startTime = 0;
        int busNumber = 0;
        int cowNumber = 0;

        for (int i : cowsTimes) {
            if (busNumber == 0) {
                busNumber = 1;
                startTime = i;
                cowNumber = 1;
            }

            else {
                if (startTime + maxWeightingTime > i) {
                    // then provided the bus has enough space, we can add the cow
                    if (cowNumber < c) {
                        cowNumber++;
                    }

                    else {
                        startTime = i;
                        busNumber++;
                        cowNumber = 1;
                    }
                }

                else {
                    startTime = i;
                    busNumber++;
                    cowNumber = 1;
                }
            }

            if (busNumber > m) return false;
        }

        return true;
    }
  
 /*
 ANALYSIS
    cows in line are the next to be assigned to the bus

    We are trying to minimize the maximum waiting time of the cows

    So therefore we do a binary search of all times to figure out the most efficient

    With the maximum time, we are trying to use all of it:

    therefore bus 1 starts at the first cow
    and ends only when cow 1 + maximum time ends
    or when there are too many cows on the bus

    the next bus begins with the same rules
    fails when all of the cows don't get on the bus

    IF YOU CAN'T FIND EFFICIENTLY IN AN ALGORITHM,
    THINK ABOUT TRYING UNTIL IT WORKS WITH SIMPLE TRIALS BASED ON ONE VARIABLE


    data structures:
    array for the times
    sort the array
    int for start time of current bus
    int for number of the current bus
 */
}


