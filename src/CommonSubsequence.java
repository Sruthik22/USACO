// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CommonSubsequence {

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
            int m = nextInt();

            HashSet<Integer> arr_n = new HashSet<>();

            for (int j = 0; j < n; j++) {
                arr_n.add(nextInt());
            }

            int[] ms = new int[m];

            for (int j = 0; j < m; j++) {
                ms[j] = (nextInt());
            }

            boolean works = false;

            for (int j = 0; j < m; j++) {
                int next = ms[j];

                if (arr_n.contains(next)) {
                    out.println("YES");
                    out.println(1 + " " + next);
                    works = true;
                    break;
                }
            }

            if (works) continue;

            out.println("NO");

        }

        out.close();
    }
}


