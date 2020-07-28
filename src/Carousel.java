// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Carousel {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            int n = nextInt();

            int[] figures = new int[n];

            HashSet<Integer> set = new HashSet<>();

            for (int j = 0; j < n; j++) {
                figures[j] = nextInt();
                set.add(figures[j]);
            }

            int posRepeat = -1;

            for (int j = 1; j < n; j++) {
                if (figures[j] == figures[j-1]) {
                    posRepeat = j;
                    break;
                }
            }

            if (set.size() == 1) {
                out.println(1);
                for (int j = 0; j < n; j++) {
                    out.print(1);
                    if (j != n-1) out.print(" ");
                }
            }

            else {
                if (n % 2 == 0 || figures[0] == figures[n-1]) {
                    out.println(2);
                    for (int j = 0; j < n; j++) {
                        if (j % 2 == 0) out.print(1);
                        else out.print(2);
                        if (j != n-1) out.print(" ");
                    }
                }

                else if (posRepeat == -1) {
                    out.println(3);
                    for (int j = 0; j < n-1; j++) {
                        if (j % 2 == 0) out.print(1);
                        else out.print(2);
                        out.print(" ");
                    }
                    out.print(3);
                }

                else {
                    out.println(2);
                    int lastVal = 2;
                    for (int j = 0; j < n; j++) {
                        if (j == posRepeat) {
                            out.print(lastVal);
                        }

                        else {
                            if (lastVal % 2 == 0) lastVal = 1;
                            else lastVal = 2;
                        }

                        if (j != posRepeat) out.print(lastVal);
                        if (j != n-1) out.print(" ");
                    }
                }
            }

            out.println();
        }

        out.close();
    }
}


