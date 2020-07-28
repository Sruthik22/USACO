// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Box {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int t = nextInt();

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            int[] prefixSums = new int[n];

            prefixSums[0] = nextInt();

            ArrayList<Integer> changePoints = new ArrayList<>();

            for (int j = 1; j < n; j++) {
                prefixSums[j] = nextInt();

                if (prefixSums[j] != prefixSums[j-1]) changePoints.add(j);
            }

            int[] ans = new int[n];

            ans[0] = prefixSums[0];

            boolean[] used = new boolean[n+1];

            used[ans[0]] = true;

            int lastSure = 1;

            int last_change_point = 0;

            boolean fail = false;

            for (int j : changePoints) {
                ans[j] = prefixSums[j];
                used[ans[j]] = true;

                for (int k = last_change_point + 1; k < j; k++) {
                    while (used[lastSure]) {
                        lastSure++;
                    }

                    if (lastSure >= j) {
                        // FAIL - PRINT -1
                        fail = true;
                        break;
                    }

                    used[lastSure] = true;
                    ans[k] = lastSure;
                }

                last_change_point = j;

                if (fail) break;
            }

            // check from last change point to the end

            for (int k = last_change_point + 1; k < n; k++) {
                while (used[lastSure]) {
                    lastSure++;
                }

                if (lastSure >= ans[last_change_point]) {
                    // FAIL - PRINT -1
                    fail = true;
                    break;
                }

                used[lastSure] = true;
                ans[k] = lastSure;
            }

            if (fail) out.print(-1);

            else {
                for (int j = 0; j < n; j++) {
                    out.print(ans[j]);

                    if (j != n-1) out.print(" ");
                }
            }

            out.println();
        }

        out.close();
    }
}



