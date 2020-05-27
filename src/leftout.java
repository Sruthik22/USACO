// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class leftout {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("leftout.in"));
        int n = in.nextInt();

        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == 'R') {
                    grid[i][j] = 0;
                }

                else grid[i][j] = 1;
            }
        }

        in.close();

        //first row

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = (grid[i][j] + 1) % 2; // toggle
                }
            }
        }

        // first column

        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                for (int j = 0; j < n; j++) {
                    grid[j][i] = (grid[j][i] + 1) % 2; // toggle
                }
            }
        }

        // check if there is one 1, a row of 1

        int x = 0;
        int y = 0;

        if (grid[1][1] == 1 && grid[2][2] == 1) {
            x = 1;
            y = 1;
        }

        else {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if (i+1 < n && grid[i+1][j] == 1) {
                            x = 1;
                            y = j+1;
                            break;
                        }

                        if (j+1 < n && grid[i][j+1] == 1) {
                            x = i+1;
                            y = 1;
                            break;
                        }
                    }
                }

                if (x != 0 || y != 0) break;
            }
        }

        boolean already = false;

        if (x == 0 && y == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && already) {
                        x = 0;
                        y = 0;
                        break;
                    }
                    if (grid[i][j] == 1) {
                        x = i + 1;
                        y = j + 1;

                        already = true;
                    }
                }
            }
        }

        String result = "" + x + " " + y;

        if (x == 0 && y == 0) {
            result = "-1";
        }

        PrintWriter out = new PrintWriter(new File("leftout.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }


  
 /*
 ANALYSIS

WHEN TRYING TO SOLVE AN UNSOLVABLE PROBLEM
find an invariant, a case which prevents solution

using logic, to find out how to find bad rectangles

then based on results find out which element is bad.

 */
}


