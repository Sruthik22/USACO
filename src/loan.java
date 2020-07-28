// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class loan {

    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static long n;
    static long k;
    static long m;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("loan.in")));

        n = nextLong();
        k = nextLong();
        m = nextLong();

        long low = -1; // nothing is desired this point and lower
        long high = (long) 1E12; // at this point and higher everything is always true

        while (high - low > 1) {
            long mid = (low + high)/2;
            if (check(mid)) low = mid;
            else high = mid;
        }

        long result = low;

        PrintWriter out = new PrintWriter(new File("loan.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static boolean check(long x) {
        long orig_val = n / x;
        long days_left = k;
        long gallons_left = n;

        for (long i = orig_val; i > m; i--) {
            long g = gallons_left - x * i;
            long num_days = (long) Math.ceil((double) g / i);

            long days_used = Math.min(num_days, days_left);

            gallons_left -=  days_used * i;
            if (gallons_left <= 0) return true;
            days_left -= days_used;
            if (days_left <= 0) return false;
        }

        return m * days_left >= gallons_left;
    }
}


