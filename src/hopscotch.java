// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class hopscotch {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int r;
    static int c;

    static long result;

    static int[][] grid;

    static long[][] moves;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("hopscotch.in")));

        r = nextInt();
        c = nextInt();
        int kc = nextInt();

        grid = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = nextInt();
            }
        }

        result = 0;

        moves = new long[r][c];

        moves[0][0] = 1;

        final int MOD = 1000000007;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                for(int k = i+1; k < r; k++) {
                    for(int l = j+1; l < c; l++) {
                        if(grid[i][j] != grid[k][l]) {
                            moves[k][l] += moves[i][j];
                            moves[k][l] %= MOD;
                        }
                    }
                }
            }
        }

        result = moves[r-1][c-1];

        PrintWriter out = new PrintWriter(new File("hopscotch.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


