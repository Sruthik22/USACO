// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class alchemy {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new File("alchemy.out"));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            out.print("Case #" + (i+1) + ": ");
            int n = nextInt();
            String s = next();

            int bs = 0;
            int as = 0;

            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == 'A') as++;
                else bs++;
            }

            if (Math.abs(bs - as) == 1) {
                out.println("Y");
            }
            else out.println("N");
        }

        out.close();
    }
}


