// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SequentialNim {

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

            int[] stones = new int[n];

            for (int j = 0; j < n; j++) {
                stones[j] = nextInt();
            }

            int parity = -1;

            for (int j = 0; j < n; j++) {
                if (stones[j] == 1) continue;

                parity = j;
                break;
            }

            if (parity == -1) parity = n-1;

            if (parity % 2 == 0) {
                out.println("First");
            }

            else {
                out.println("Second");
            }
        }

        out.close();
    }
}


