// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PlusFromPicture {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int h = in.nextInt();
        int w = in.nextInt();

        char[][] grid = new char[h][w];

        for (int i = 0; i < h; i++) {
            String row = in.next();
            for (int j = 0; j < w; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        boolean[][] plus_only = new boolean[h][w];

        for(int i=1; i<h-1; i++){
            for(int j=1; j<w-1; j++){
                if (grid[i][j] == '*' && grid[i - 1][j] == '*' && grid[i + 1][j] == '*'
                        && grid[i][j + 1] == '*' && grid[i][j - 1] == '*') {
                    int upend = i, downend = i, leftend = j, rightend = j;
                    while (upend >= 0 && grid[upend][j] == '*') plus_only[upend--][j] = true;
                    while (downend < h && grid[downend][j] == '*') plus_only[downend++][j] = true;
                    while (leftend >= 0 && grid[i][leftend] == '*') plus_only[i][leftend--] = true;
                    while (rightend < w && grid[i][rightend] == '*') plus_only[i][rightend++] = true;

                    for (int i2 = 0; i2 < h; i2++) {
                        for (int j2 = 0; j2 < w; j2++) {
                            if (grid[i2][j2] == '*' && !plus_only[i2][j2]) {
                                out.println("NO");
                                out.close();
                                return;
                            }
                        }
                    }

                    out.println("YES");
                    out.close();
                    return;
                }
            }
        }

        out.println("NO");

        out.close();
    }
}


