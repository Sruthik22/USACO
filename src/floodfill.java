// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class floodfill {

    public static void main(String[] args) throws FileNotFoundException {
        String[] data = new String[] {
                "AABBC",
                "CAABC",
                "BBACC"
        };

        char [][] grid = new char [data.length][];

        // # of rows = data.length

        for (int r = 0; r < grid.length; r++) {
            grid[r] = data[r].toCharArray();
        }

        System.out.println(Arrays.deepToString(grid));

        int numChanged = floodChange (grid, 0, 0, 'Z');

        int numChangedC = floodChange (grid, 0, 4, 'O');

        System.out.println(numChanged);
        System.out.println(numChangedC);

        System.out.println(Arrays.deepToString(grid));
    }

    static int floodChange (char[][] g, int r, int c, char tgt) {
        return floodChange(g, r, c, tgt, g[r][c]);
    }

    static int floodChange (char[][] g, int r, int c, char tgt, char orig) {
        if (r < 0 || r >= g.length || c < 0 || c >= g[r].length) return 0;

        if (g[r][c] != orig) return 0;

        g[r][c] = tgt;

        return 1 + floodChange(g, r+1, c, tgt, orig) +
                   floodChange(g, r-1, c, tgt, orig) +
                   floodChange(g, r, c+1, tgt, orig) +
                   floodChange(g, r, c-1, tgt, orig);
    }
}


