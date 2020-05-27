// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class countcross {

    private static int n;
    private static int[][] farm;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("countcross.in"));
        n = in.nextInt(); // grid size
        farm = new int[2*n+1][2*n+1];

        int k = in.nextInt(); // # of cows
        int r = in.nextInt(); // # of roads

        for (int i = 0; i < r; i++) {
            int r1 = in.nextInt() * 2 - 1;
            int c1 = in.nextInt() * 2 - 1;
            int r2 = in.nextInt() * 2 - 1;
            int c2 = in.nextInt() * 2 - 1;

            int rRoad = (r1+r2) / 2;
            int cRoad = (c1+c2) / 2;

            farm[rRoad][cRoad] = -1; // blocked (road)
        }

        for (int i = 0; i < 2*n+1; i++) {
            farm[0][i] = -1;
            farm[i][0] = -1;
            farm[2*n][i] = -1;
            farm[i][2*n] = -1;
        }

        for (int i = 0; i < 2*n+1; i+=2) {
            for (int j = 0; j < 2*n+1; j+=2) {
                farm[i][j] = -1;
            }
        }

        int regionId = 1;

        for (int fr = 1; fr < 2*n; fr+=2) {
            for (int fc = 1; fc < 2*n; fc+=2) {
                if (farm[fr][fc] == 0) {
                    floodFill(fr, fc, regionId);
                    regionId++;
                }
            }
        }

        int[] cowRegions = new int[k];

        int result = 0;
        // index: cow
        // value: region of cow

        for (int i = 0; i <k; i++) {
            int cowR = in.nextInt() * 2 - 1;
            int cowC = in.nextInt() * 2 - 1;

            cowRegions[i] = farm[cowR][cowC];

            for(int j = 0; j < i; j++) {
                if(cowRegions[j] != cowRegions[i]) {
                    result++;
                }
            }
        }

        in.close();

        PrintWriter out = new PrintWriter(new File("countcross.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void floodFill(int r, int c, int id) {
        if (r < 0 || r > 2*n || c < 0 || c > 2*n) return;

        // no crossing a road
        if (farm[r][c] == -1) return;

        // no doing the same square over again
        if (farm[r][c] != 0) return;

        farm[r][c] = id;

        floodFill(r-1, c, id);
        floodFill(r+1, c, id);
        floodFill(r, c-1, id);
        floodFill(r, c+1, id);
    }
  
 /*
 ANALYSIS

 Another idea:
create a class that holds if there is a row on the north, south, east, and west
and if there is a cow

To make the array 2 times as large in both dimensions + 1 for the wall,
and then roads would be dropped in between

row number = (r*2-1)
column number = (c*2-1)

1. Go 2 by 2, after checking the in between spaces to see if allowed
2. block 3 squares per road instead of just 1
3. block off corner spaces, never move through them anyway
 --> Cause they were never part of how cows could move

 all fields are odd * odd
 all auto-blocked even * even
 all potential roads have even * odd or odd * even

one region could be 1's 2's 3's 4's ...

outline:

Goal: To mark all regions by individual id #

look at every field cell (odd coordinates)
    if we have determined region, skip
    if we have not determined: flood and assign an id
        to all cells in region

go through all cows, see where they are

*/
}


