// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CommonPrefixes {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t= nextInt();

        for (int i = 0; i < t; i++) {
            int n = nextInt();
            int[] integers = new int[n];

            for (int j = 0; j < n; j++) {
                integers[j] = nextInt();
            }

            int[] lengths = new int[n+1];
            lengths[0] = integers[0];
            lengths[n] = integers[n-1];

            for (int j = 0; j < n-1; j++) {
                lengths[j+1] = Math.max(integers[j], integers[j+1]);
            }

            char last = 'z';

            for (int j = 0; j < n+1; j++) {

                if (lengths[j] == 0) {
                    out.print(last);
                    last = (char) ('a' + ((last - 'a' + 1) % 26));
                }

                for (int k = 0; k < lengths[j]; k++) {
                    out.print(last);
                }
                out.println();
            }
        }

        out.close();
    }
}


