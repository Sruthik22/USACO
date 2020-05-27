// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class pails {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("pails.in"));
        int x = in.nextInt();
        int y = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        in.close();

        boolean[][] can = new boolean[x+1][y+1];

        can[0][0] = true;

        for (int steps = 0; steps < k; steps++) {

            boolean[][] next = new boolean[x+1][y+1];

            for (int i = 0; i < x+1; i++) {
                for (int j = 0; j < y+1; j++) {
                    if (!can[i][j]) continue;

                    next[i][j] = true;
                    next[x][j] = true;
                    next[0][j] = true;
                    next[i][0] = true;
                    next[i][y] = true;

                    if (i + j > y) {
                        int toGive = y - j;
                        next[i - toGive][y] = true;
                    }

                    else next[0][i+j] = true;

                    if (i + j > x) {
                        int toGive = x - i;
                        next[x][j - toGive] = true;
                    }

                    else next[i+j][0] = true;
                }
            }


            can = next;
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < x+1; i++) {
            for (int j = 0; j < y+1; j++) {
                if (!can[i][j]) continue;

                result = Math.min(result, Math.abs(m - (i+j)));
            }
        }


        PrintWriter out = new PrintWriter(new File("pails.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


