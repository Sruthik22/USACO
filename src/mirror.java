// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class mirror {

    // direction: 0  1   2   3
    static int dr[] =   {1, 0, -1,  0};
    static int dc[] =   {0, 1,  0, -1};

    // new direction after hitting / mirror
    static int bounce1[] = {3, 2, 1, 0};

    // new direction after hitting \ mirror
    static int bounce2[] = {1, 0, 3, 2};

    static int n;
    static int m;
    static char[][] mirrorField;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("mirror.in"));

        n = in.nextInt();
        m = in.nextInt();

        mirrorField = new char[n][m];

        for (int i = 0; i < n; i++) {

            String s = in.next();

            for (int j = 0; j < m; j++) {
                mirrorField[i][j] = s.charAt(j);
            }
        }

        int result = 0;

        for(int i = 0; i < n; i++) {
            result = Math.max(result, trace(i, 0, 1));
            result = Math.max(result, trace(i, m - 1, 3));
        }
        for(int i = 0; i < m; i++) {
            result = Math.max(result, trace(0, i, 0));
            result = Math.max(result, trace(n - 1, i, 2));
        }

        PrintWriter out = new PrintWriter(new File("mirror.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int trace(int r, int c, int dir) {
        int result = 0;
        while(0 <= r && r < n && 0 <= c && c < m) {
            if(mirrorField[r][c] == '/')
                dir = bounce1[dir];
            else
                dir = bounce2[dir];
            r += dr[dir];
            c += dc[dir];
            result++;
        }
        return result;
    }
}


