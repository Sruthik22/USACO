// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PattyCake {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static double nextDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    static int maxFilling;
    static int n;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int T = nextInt();

        for (int t = 0; t < T; t++) {
            n = nextInt();

            int[] fillings = new int[n + 1];

            for (int i = 0; i < n; i++) {
                int val = nextInt();
                fillings[val]++;
            }

            maxFilling = 1;
            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (fillings[i] > maxFilling) {
                    maxFilling = fillings[i];
                    count = 1;
                }

                else if (fillings[i] == maxFilling) {
                    count++;
                }
            }

            out.println((n - count) / (maxFilling - 1) - 1);
        }

        out.close();
    }
}


