// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SquareFilling {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int m;
    static int[][] A;
    static int[][] B;

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        n = nextInt();
        m = nextInt();

        A = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = nextInt();
            }
        }

        B = new int[n][m];

        boolean not_work = false;

        StringBuilder sb = new StringBuilder();

        int steps = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1 && B[i][j] != 1) {
                    // we need to check the 4 (2 by 2s) surrounding

                    String check_results = check(i, j);

                    if (check_results.equals("-1")) {
                        out.println(check_results);
                        not_work = true;
                        break;
                    }

                    sb.append(check_results);
                    sb.append('\n');
                    steps++;
                }
            }

            if (not_work) break;
        }

        if (!not_work) {
            out.println(steps);
            out.println(sb.toString());
        }

        out.close();
    }

    static String check(int i, int j) {
        if (i - 1 >= 0 && j + 1 < m) {
            if (A[i-1][j] == 1 && A[i-1][j+1] == 1 && A[i][j] == 1 && A[i][j+1] == 1) {
                B[i-1][j] = 1;
                B[i-1][j+1] = 1;
                B[i][j] = 1;
                B[i][j+1] = 1;
                return (i) + " " + (j+1);
            }
        }

        if (i - 1 >= 0 && j - 1 >= 0) {
            if (A[i-1][j] == 1 && A[i-1][j-1] == 1 && A[i][j] == 1 && A[i][j-1] == 1) {
                B[i-1][j] = 1;
                B[i-1][j-1] = 1;
                B[i][j] = 1;
                B[i][j-1] = 1;
                return (i) + " " + (j);
            }
        }

        if (i + 1 < n && j + 1 < m) {
            if (A[i][j] == 1 && A[i][j+1] == 1 && A[i+1][j] == 1 && A[i+1][j+1] == 1) {
                B[i][j] = 1;
                B[i][j+1] = 1;
                B[i+1][j] = 1;
                B[i+1][j+1] = 1;

                return (i+1) + " " + (j+1);
            }
        }

        if (i + 1 < n && j - 1 >= 0) {
            if (A[i+1][j] == 1 && A[i+1][j-1] == 1 && A[i][j] == 1 && A[i][j-1] == 1) {
                B[i+1][j] = 1;
                B[i+1][j-1] = 1;
                B[i][j] = 1;
                B[i][j-1] = 1;

                return (i+1) + " " + (j);
            }
        }

        return "-1";
    }
}


