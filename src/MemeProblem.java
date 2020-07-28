// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class MemeProblem {

    static StreamTokenizer in;

    static long nextInt() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        long t = nextInt();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < t; i++) {
            long A = nextInt();
            long B = nextInt();

            int lenB = String.valueOf(B).length() - 1;

            // we need to check if B is all 9

            if (all9s(B)) lenB++;

            long result = lenB * A;

            out.println(result);
        }

        out.close();
    }

    static boolean all9s(long b) {
        String s = String.valueOf(b);

        for (char c : s.toCharArray()) {
            if (c != '9') return false;
        }

        return true;
    }
}


