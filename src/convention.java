// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class convention {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int m;
    static int c;
    static int[] cows;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("convention.in")));

        n = nextInt();
        m = nextInt();
        c = nextInt();

        cows = new int[n];

        for (int i = 0; i < n; i++) {
            cows[i] = nextInt();
        }

        Arrays.sort(cows);

        int low = -1; // nothing is desired this point and lower
        int high = 1000000000; // at this point and higher everything is always true

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (check(mid)) high = mid;
            else low = mid;
        }

        int result = high;
        PrintWriter out = new PrintWriter(new File("convention.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static boolean check(int waiting_time) {
        int bus_remaining = m;

        int last_time = 0;
        int cows_used = 0;

        for (int i = 0; i < n; i++) {
            int cowTime = cows[i];

            if (last_time < cowTime || cows_used == c) {
                bus_remaining--;
                cows_used = 0;
                last_time = cowTime + waiting_time;
            }

            cows_used++;

            if (bus_remaining < 0) return false;
        }

        return true;
    }
}


