// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class leftout {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int n;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("leftout.in")));

        n = nextInt();

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            String row = next();
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);

                if (c == 'R') grid[i][j] = 1;
                else grid[i][j] = 0;
            }
        }

        // columns
        for (int i = 0; i < n; i++) {
            fixColumn(i);
        }

        // rows
        for (int i = 0; i < n; i++) {
            fixRow(i);
        }

        int num = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // odd one
                    num++;
                }
            }
        }

        String result = "";

        if (num == (n-1) * (n-1)) {
            result = "1 1";
        }

        else if (num == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        result = (i+1) + " " + (j+1);
                    }
                }
            }
        }

        else if (num == (n-1)) {

            boolean done = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if (grid[i][j+1] == 1) {
                            result = (i+1) + " " + (1);

                            done = true;
                            break;
                        }

                        if (grid[i+1][j] == 1) {
                            result = (1) + " " + (j+1);

                            done = true;
                            break;
                        }
                    }
                }

                if (done) {
                    break;
                }
            }
        }

        if (result.equals("")) {
            result = "-1";
        }

        PrintWriter out = new PrintWriter(new File("leftout.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void fixColumn(int index) {
        int initial = grid[0][index];

        if (initial == 0) return;

        for (int i = 0; i < n; i++) {
            grid[i][index] = (grid[i][index]+1)%2;
        }
    }

    static void fixRow(int index) {
        int initial = grid[index][0];

        if (initial == 0) return;

        for (int i = 0; i < n; i++) {
            grid[index][i] = (grid[index][i]+1)%2;
        }
    }
}


