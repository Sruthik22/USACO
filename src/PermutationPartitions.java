// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PermutationPartitions {

    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        long n = nextLong();
        long k = nextLong();

        long sum = (n-k+1 + n) * k / 2;

        out.print(sum + " ");

        long result = 1;
        int count = 0;
        int numKEncountered = 0;

        for (int i = 0; i < n; i++) {
            long val = nextLong();

            if (val >= n-k+1) {
                // one of the values in the top k
                if (numKEncountered != 0) {
                    result *= (count+1);
                    result %= 998244353;
                }

                numKEncountered++;
                count = 0;
            }

            else {
                count++;
            }
        }

        out.println(result);

        out.close();
    }
}


