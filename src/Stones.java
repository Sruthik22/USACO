// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Stones {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static double nextDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        int[] sizes = new int[n];

        for (int i = 0; i < n; i++) {
            sizes[i] = nextInt();
        }

        boolean[] dp = new boolean[k+1];

        for (int i = 0; i <= k; i++) {
            for (int x: sizes) {
                if (i - x >= 0 && !dp[i - x]) { // if starting from before is not good, then it is possible at this state
                    dp[i] = true;
                }
            }
        }

        if (dp[k]) {
            out.println("First");
        }

        else {
            out.println("Second");
        }

        out.close();
    }
}