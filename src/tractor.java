// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class tractor {

    static int n;
    static boolean[][] fieldTracker;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tractor.in"));
        n = in.nextInt();

        int[][] field = new int[n][n];

        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }

        in.close();

        int high = 1000000; // always works
        int low = 0; // test

        while (low < high) {
            fieldTracker = new boolean[n][n];
            int medium = (high + low) / 2;

            boolean works = false;

            int totalTiles = (int) (Math.ceil(Math.pow(n, 2) / 2));

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!fieldTracker[i][j] && floodfill(field, i, j, medium, field[i][j]) >= totalTiles) {
                        works = true;
                        break;
                    }
                }

                if (works) {
                    break;
                }
            }

            if (works) {
                high = medium;
            }

            else {
                low = medium + 1;
            }
        }

        int result = high;
        PrintWriter out = new PrintWriter(new File("tractor.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int floodfill (int[][] f, int r, int c, int power, int pastElevation) {
        if (r < 0 || r >= n || c < 0 || c >= n) return 0;

        if (!(f[r][c] >= pastElevation - power && f[r][c] <= pastElevation + power)) return 0;

        if (fieldTracker[r][c]) return 0;

        fieldTracker[r][c] = true;

        return
                1 + floodfill(f, r+1, c, power, f[r][c]) +
                floodfill(f, r-1, c, power, f[r][c]) +
                floodfill(f, r, c+1, power, f[r][c]) +
                floodfill(f, r, c-1, power, f[r][c]);
    }
  
 /*
 ANALYSIS

 5
0 0 0 3 3
0 0 0 0 3
0 9 9 3 3
9 9 9 3 3
9 9 9 9 3

floodfill to check, binary search for the tractor strength
 
 */
}


