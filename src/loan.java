// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class loan {

    private static long n;
    private static long k;
    private static long m;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("loan.in"));
        n = in.nextLong(); // number of gallons
        k = in.nextLong(); // number of days
        m = in.nextLong(); // least gallons per day

        in.close();

        long low = 0;
        long high = 1000000000000L;

        while (low + 1 < high) {
            long medium = (high + low) / 2L;

            boolean works = simulate(medium);

            if (works) {
                low = medium;
            }

            else {
                high = medium - 1;
            }
        }

        long result = low;

        PrintWriter out = new PrintWriter(new File("loan.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean simulate(long x) {

        /* need to skip fractions based on the size of g */

        long g = 0; // how much given so far
        long daysLeft = k; // how many days left

        while (daysLeft >= 0 && g < n) {

            long y = (n-g) / x;

            if (y < m) {
                return m*k + g >= n; // going to decrease no matter what
            }

            // not the minimum value
            // how to use the most possible

            long maxmatch = n - y * x; // <-- this is the maximum value possible
            long numdays = (maxmatch - g) / y + 1; // <--

            if (numdays > k) numdays = k;

            g += y;

            daysLeft--;
        }

        return g >= n;
    }
}