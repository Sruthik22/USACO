// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class angry {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int n;
    static int k;
    static int[] positions;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("angry.in")));

        n = nextInt();
        k = nextInt();

        positions = new int[n];

        for (int i = 0; i < n; i++) {
            positions[i] = nextInt();
        }

        Arrays.sort(positions);

        int low = -1; // nothing is desired this point and lower
        int high = 1000000; // at this point and higher everything is always true

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (check(mid)) high = mid;
            else low = mid;
        }

        int result = high;
        PrintWriter out = new PrintWriter(new File("angry.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean check(int r) {
        int until = 0;
        int cows_remaining = k;

        for (int i = 0; i < n; i++) {
            int pos  = positions[i];

            if (pos <= until) continue;

            cows_remaining--;
            until = pos + 2 * r;

            if (cows_remaining < 0) return false;
        }

        return true;
    }
}


