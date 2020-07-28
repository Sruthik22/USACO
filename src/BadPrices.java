// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class BadPrices {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            int[] prices = new int[n];

            for (int j = 0; j < n; j++) {
                prices[j] = nextInt();
            }

            int[] prefixMins = new int[n];

            prefixMins[n-1] = prices[n-1];

            for (int j = n-2; j >= 0; j--) {
                prefixMins[j] = Math.min(prefixMins[j+1], prices[j]);
            }

            int result = 0;

            for (int j = 0; j < n; j++) {
                if (prices[j] > prefixMins[j]) result++;
            }

            out.println(result);
        }

        out.close();
    }
}


