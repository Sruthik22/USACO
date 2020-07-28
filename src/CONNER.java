// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CONNER {

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
            int s = nextInt();
            int k = nextInt();

            HashSet<Integer> closed_floors = new HashSet<>();

            for (int j = 0; j < k; j++) {
                closed_floors.add(nextInt());
            }

            if (!closed_floors.contains(s)) {
                out.println(0);
                continue;
            }

            int result = Math.min(above(closed_floors, s, n), below(closed_floors, s));

            out.println(result);
        }

        out.close();
    }

    static int above(HashSet<Integer> closed_floors, int s, int n) {
        for (int i = s + 1; i <= Math.min(i + 1000, n); i++) {
            if (!closed_floors.contains(i)) return i - s;
        }

        return Integer.MAX_VALUE;
    }

    static int below(HashSet<Integer> closed_floors, int s) {
        for (int i = s - 1; i >= Math.max(i - 1000, 1); i--) {
            if (!closed_floors.contains(i)) return s - i;
        }

        return Integer.MAX_VALUE;
    }
}


