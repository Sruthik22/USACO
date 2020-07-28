// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class YetAnotherCrossesProblem {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = in.nextInt();

        for (int i = 0; i < q; i++) {
            int n = in.nextInt();
            int m = in.nextInt();

            int[][] grid = new int[n][m];

            for (int j = 0; j < n; j++) {

                String row = in.next();

                for (int k = 0; k < m; k++) {
                    if (row.charAt(k) == '*') {
                        grid[j][k] = 1;
                    }

                    else {
                        grid[j][k] = 0;
                    }
                }
            }

            int[] rowVal = new int[n];

            for (int j = 0; j < n; j++) {

                int sum = 0;

                for (int k = 0; k < m; k++) {
                    sum += grid[j][k];
                }

                rowVal[j] = sum;
            }

            int[] columnVal = new int[m];

            for (int j = 0; j < m; j++) {

                int sum = 0;

                for (int k = 0; k < n; k++) {
                    sum += grid[k][j];
                }

                columnVal[j] = sum;
            }

            int result = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    int curRes = n - rowVal[j] + m - columnVal[k];

                    if (grid[j][k] == 0) curRes--;

                    result = Math.min(result, curRes);
                }
            }

            out.println(result);
        }


        out.close();
    }
}


