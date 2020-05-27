// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class socdist1Final {

    static ArrayList<Integer> stalls;
    static int firstSpace;
    static int lastSpace = -1;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("socdist1.in"));

        int n = in.nextInt();

        String s = in.next();

        stalls = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') stalls.add(i);
        }

        firstSpace = stalls.get(0) + 1;

        if (stalls.size() >= 2) {
            lastSpace = stalls.get(stalls.size() - 1) + 1;
        }

        int low = 1; // nothing is desired this point and lower
        int high = 100000; // at this point and higher everything is always true

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (simulate(mid)) low = mid;
            else high = mid;
        }

        long result = low;

        PrintWriter out = new PrintWriter(new File("socdist1.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static boolean simulate(int D) {
        long lastPosition = stalls.get(0) + D;
        int numPlaced = 0;

        for (int i = 1; i < stalls.size(); i++) {

            if (lastPosition + D <= stalls.get(i)) numPlaced++;

            lastPosition = stalls.get(i) + D;

            if (numPlaced >= 2) return true;
        }

        numPlaced += Math.floor(((double) firstSpace - 2) / 3) + 1;

        return numPlaced >= 2;
    }
}


