// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowdance {

    private static int n;
    private static int[] cowDurations;
    private static int tMax;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cowdance.in"));
        n = in.nextInt(); // # of cows

        tMax = in.nextInt();
        cowDurations = new int[n];

        for (int i = 0; i < n; i++) {
            cowDurations[i] = in.nextInt();
        }

        in.close();

        int low = -1; // nothing is desired this point and lower
        int high = n; // at this point and higher everything is always true

        while (high - low > 1) {
            int mid = (low + high)/2;

            if (kWorks(mid)) high = mid;
            else low = mid;
        }

        int result = high;

        PrintWriter out = new PrintWriter(new File("cowdance.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean kWorks(int kValue) {
        // use k cows on the stage

        int currentTime = 0;

        int[] kTimes = new int[kValue];

        // initial filling
        System.arraycopy(cowDurations, 0, kTimes, 0, kValue);

        for (int i = 0; i < n - kValue; i++) {
            // get the min value
            int[] stuffs = getMinValue(kTimes);

            int minValue = stuffs[0];
            int index = stuffs[1];

            kTimes[index] = minValue + cowDurations[kValue + i];

            currentTime += minValue - currentTime;
        }

        currentTime += getMaxValue(kTimes) - currentTime;

        return currentTime <= tMax;
    }


    private static boolean classDiscussion(int kValue) {
        PriorityQueue<Integer> finishTimes = new PriorityQueue<>();

        for (int i = 0; i < kValue; i++) {
            finishTimes.add(cowDurations[i]);
        }

        for (int i = kValue; i < n; i++) {
            int t = finishTimes.poll();
            t += cowDurations[i];

            if (t > tMax) return false;
            finishTimes.add(t);
        }

        return true;
    }

    /*
    STACKS and QUEUES

    stack first in last out
    queue first in first out

    */



    // getting the maximum value
    private static int getMaxValue(int[] array) {
        int maxValue = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        return maxValue;
    }

    // getting the miniumum value
    private static int[] getMinValue(int[] array) {
        int minValue = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                index = i;
                minValue = array[i];
            }
        }
        int[] returnableArray = new int[2];
        returnableArray[0] = minValue;
        returnableArray[1] = index;
        return returnableArray;
    }
}

/*


Find the min of the first k numbers, then add the next value, k+the value, then repeat until no more to loop through.

Final time is the current time + the max in the array.

*/


