// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CCChessboard {

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
            int b = nextInt();
            int w = nextInt();

            if (b * 3 + 1 < w || w * 3 + 1 < b) {
                out.println("NO");
            }

            else {
                out.println("YES");


                if (b > w) {
                    out.println("2 1");
                    b--;
                    for (int j = 1; j <= w; j++) {
                        out.println("2 " + (j * 2));

                        if (b > w) {
                            out.println("1 " + (j * 2));
                            b--;
                        }

                        if (b > w) {
                            out.println("3 " + (j * 2));
                            b--;
                        }

                        out.println("2 " + (j * 2 + 1));
                    }
                }

                else {
                    if (w > b) {
                        out.println("3 1");
                        w--;
                    }

                    for (int j = 1; j <= w; j++) {
                        out.println("3 " + (j * 2));

                        if (w > b) {
                            out.println("4 " + (j * 2));
                            w--;
                        }

                        if (w > b) {
                            out.println("2 " + (j * 2));
                            w--;
                        }

                        out.println("3 " + (j * 2 + 1));
                    }
                }
            }
        }

        out.close();
    }
}


