// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cownomics {

    private static int[][] spotted;
    private static int[][] nonspotted;

    private static int n;
    private static int m;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cownomics.in"));
        n = in.nextInt();
        m = in.nextInt();

        spotted = new int[n][m];
        nonspotted = new int[n][m];


        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < m; j++) {

                if (s.charAt(j) == 'A') spotted[i][j] = 0;
                else if (s.charAt(j) == 'C') spotted[i][j] = 1;
                else if (s.charAt(j) == 'G') spotted[i][j] = 2;
                else if (s.charAt(j) == 'T') spotted[i][j] = 3;

            }
        }

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < m; j++) {

                if (s.charAt(j) == 'A') nonspotted[i][j] = 0;
                else if (s.charAt(j) == 'C') nonspotted[i][j] = 1;
                else if (s.charAt(j) == 'G') nonspotted[i][j] = 2;
                else if (s.charAt(j) == 'T') nonspotted[i][j] = 3;

            }
        }

        in.close();

        int result = 0;

        for (int i = 0; i < m - 2; i++) {
            for (int j = i+1; j < m - 1; j++) {
                for (int k = j+1; k < m; k++) {
                    if (check(i, j, k)) {
                        result++;
                    }
                }
            }
        }


        PrintWriter out = new PrintWriter(new File("cownomics.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean check(int index1, int index2, int index3) {
        HashSet<Integer> tocheck = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            tocheck.add(100*spotted[i][index1]+10*spotted[i][index2]+spotted[i][index3]);
        }

        for (int i = 0; i < n; i++) {
            if (tocheck.contains(100*nonspotted[i][index1]+10*nonspotted[i][index2]+nonspotted[i][index3])) return false;
        }

        return true;
    }
}


