// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class paintbarn {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("paintbarn.in"));
        int n = in.nextInt();
        int k = in.nextInt();

        int[][] grid = new int[1001][1001];

        for (int i = 0; i < n; i++) {

            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            /* Planning to add one before every horizontal line */

            for (; y1 < y2; y1++) {
                // add +1 to the x1 position, and then -1 to the x2 position
                grid[y1][x1] += 1;

                if (x2 + 1 <= 1000) {
                    grid[y1][x2] -= 1;
                }
            }
        }

        in.close();

        int result = 0;

        for (int i = 0; i < 1000; i++) {
            int count = 0;

            for (int j = 0; j < 1000; j++) {
                count+= grid[i][j];

                if (count == k) {
                    result++;
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("paintbarn.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS
 Brute Force, every time he pains a rectangle, add 1 to all units
 in that space, then at the end just find the minimum value in the array

Coordinate Compression:
Coordinate Compression in the x direction:
+1 in the x and y direction (find problem with similar circumstance for
linear)

+add 1 when the rectangle starts, and subtract one when it ends, then just loop
through every row

3 2
1 1 5 5
4 4 7 6
3 3 8 7



 */
}


