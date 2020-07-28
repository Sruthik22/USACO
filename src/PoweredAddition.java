// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PoweredAddition {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = nextInt();
            }

            int maxVal = 0;

            for (int j = 1; j < n; j++) {
                if (arr[j] >= arr[j-1]) continue;

                int diff = arr[j-1] - arr[j];

                String s = Integer.toBinaryString(diff);

                maxVal = Math.max(maxVal, s.length());

                arr[j] = arr[j-1];
            }

            out.println(maxVal);
        }

        out.close();
    }
}


