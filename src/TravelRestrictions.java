// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class TravelRestrictions {

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
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            char[] input = next().toCharArray();
            char[] output = next().toCharArray();

            out.println("Case #" + (i+1) + ":");

            for (int j = 0; j < n; j++) {
                // j is the starting index

                boolean[] visited = new boolean[n];
                Stack<Integer> stack = new Stack<>();

                stack.add(j);

                while (!stack.isEmpty()) {
                    int index = stack.pop();
                    visited[index] = true;

                    if (output[index] == 'Y') {
                        if (index != n-1) {
                            if (!visited[index+1] && input[index+1] == 'Y') {
                                stack.add(index+1);
                            }
                        }

                        if (index != 0) {
                            if (!visited[index-1] && input[index-1] == 'Y') {
                                stack.add(index-1);
                            }
                        }
                    }
                }

                for (int k = 0; k < n; k++) {
                    if (visited[k]) out.print('Y');
                    else out.print('N');
                }
                out.println();
            }
        }

        out.close();
    }
}


