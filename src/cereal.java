// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cereal {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cereal.in")));

        int n = nextInt();
        int m = nextInt();

        int[] first = new int[n]; //id - pos
        int[] second = new int[n];

        for (int i = 0; i < n; i++) {
            int c1 = nextInt()-1;
            int c2 = nextInt()-1;

            first[i] = c1;
            second[i] = c2;
        }

        int[] cereals = new int[m]; //pos - id

        int[] results = new int[n];

        int lastResult = 0;

        for (int i = n-1; i >= 0; i--) {

            int j = i;
            int pos = first[i];

            while (true) {
                if (cereals[pos] == 0) {
                    cereals[pos] = j;
                    lastResult++;
                    break;
                } else if (cereals[pos] < j) break;
                else {
                    int k = cereals[pos];
                    cereals[pos] = j;
                    if (pos == second[k]) break;
                    j = k;
                    pos = second[k];
                }
            }

            results[i] = lastResult;
        }

        PrintWriter out = new PrintWriter(new File("cereal.out"));

        for (int i = 0; i < n; i++) {
            System.out.println(results[i]);
            out.println(results[i]);
        }

        out.close();
    }
}


