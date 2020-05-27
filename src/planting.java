// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class planting {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("planting.in")));

        int n = nextInt();

        int[] nodes = new int[n];

        for (int i = 0; i < n - 1; i++) {
            int node1 = nextInt()-1;
            int node2 = nextInt()-1;

            nodes[node1]++;
            nodes[node2]++;
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (nodes[i] > result) result = nodes[i];
        }

        result++;

        PrintWriter out = new PrintWriter(new File("planting.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


