// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class MaxSum {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }

        long[] max_arrays = new long[n];

        max_arrays[0] = array[0];

        for (int i = 1; i < n; i++) {
            long max = Math.max(max_arrays[i-1] + array[i], array[i]);

            max_arrays[i] = max;
        }

        long global_max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            global_max = Math.max(global_max, max_arrays[i]);
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        out.print(global_max);

        out.close();
    }
}



