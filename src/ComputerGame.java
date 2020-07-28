// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class ComputerGame {

    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        long q = nextLong();

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < q; i++) {
            long k = nextLong();
            long n = nextLong();
            long a = nextLong();
            long b = nextLong();

            if (k <= b * n) {
                out.println(-1);
            }

            else {
                long result = Math.min(n, (k - b*n - 1) / (a-b));
                out.println(result);
            }
        }

        out.close();
    }
}


