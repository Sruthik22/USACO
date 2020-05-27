// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowart {

    static boolean[][] fieldTracker;
    static int n;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cowart.in"));
        n = in.nextInt();

        char[][] humanField = new char[n][n];
        char[][] cowField = new char[n][n];


        for (int i = 0 ; i < n; i++) {
            in.nextLine();
            String line = in.next();
            humanField[i] = line.toCharArray();
            cowField[i] = line.toCharArray();
        }

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                if (cowField[i][j] == 'G') {
                    cowField[i][j] = 'R';
                }
            }
        }

        in.close();

        fieldTracker = new boolean[n][n];

        int humanResult = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                    if (!fieldTracker[i][j]) {
                        humanResult++;
                        floodfill(humanField, i, j, humanField[i][j]);
                    }
            }
        }

        fieldTracker = new boolean[n][n];

        int cowResult = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!fieldTracker[i][j]) {
                    cowResult++;
                    floodfill(cowField, i, j, cowField[i][j]);
                }
            }
        }

        String result = humanResult + " " + cowResult;
        PrintWriter out = new PrintWriter(new File("cowart.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void floodfill (char[][] field, int r, int c, char currentLetter) {
        if (r < 0 || r >= n || c < 0 || c >= n) return;

        if (field[r][c] != currentLetter) return;

        if (fieldTracker[r][c]) return;

        fieldTracker[r][c] = true;

        floodfill(field, r, c + 1, currentLetter);
        floodfill(field, r, c - 1, currentLetter);
        floodfill(field, r + 1, c, currentLetter);
        floodfill(field, r - 1, c, currentLetter);
    }
}


