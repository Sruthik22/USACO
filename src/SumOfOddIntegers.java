// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SumOfOddIntegers {

    static StreamTokenizer in;

    static long nextInt() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        long t = nextInt();

        for (int i = 0; i < t; i++) {
            long n = nextInt();
            long k = nextInt();

            if (n >= k * k && n % 2 == k % 2) {
                out.println("YES");
            }

            else {
                out.println("NO");
            }
        }

        out.close();
    }
}


