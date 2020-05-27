// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class mooyomooyo {

    private static int n;
    private static int k;
    private static boolean[][] fieldTracker;
    private static int[][] regionTracker;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("mooyomooyo.in"));
        n = in.nextInt();
        k = in.nextInt();

        int[][] board = new int[n][10];

        for (int i = 0; i < n; i++) {
            String line = in.next();
            for (int j = 0; j < 10; j++) {
                board[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        in.close();

        while (true) {
            int [][] startBoard = new int[n][10];
            for(int i = 0; i < n; i++)
                startBoard[i] = board[i].clone();

            fieldTracker = new boolean[n][10];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!fieldTracker[i][j]) {
                        regionTracker = new int[n][10];
                        for(int ri = 0; ri < n; ri++)
                            regionTracker[ri] = board[ri].clone();

                        // the board swap is properly occuring
                        int connectedRegions = floodfill(board, i, j, board[i][j]);

                        if (board[i][j] != 0 && connectedRegions >= k) {
                            for(int ri = 0; ri < n; ri++)
                                board[ri] = regionTracker[ri].clone();
                        }
                    }
                }
            }

            if (Arrays.deepEquals(startBoard, board)) {
                break;
            }

            // gravity

            /*
                loop through each column and for the number of consecutive zeroes, move the column down
                Unless the zeroes go up to the top

                once you set the lower value to the top value, the top spot must be filled with a zero
            */

            for (int j=0; j<10; j++) {
                int top = n-1, bottom = n-1;
                while (top >= 0) {
                    while (top >= 0 && board[top][j] == 0) top--;
                    if (top >= 0)
                        board[bottom--][j] = board[top--][j];
                }
                while (bottom >= 0) board[bottom--][j] = 0;
            }

            /*for (int i = 0; i < 10; i++) {

                int bottom = -1;

                for (int j = n - 1; j >= 0; j--) {
                    // column is:

                    int cur = board[j][i];

                    if (bottom == -1 && cur == 0) {
                        bottom = j;
                    }

                    else if (bottom != -1 && cur != 0) {
                        // this is moving code
                        // change is bottom - j
                        for (int c = j; c >= 0; c--) {
                            board[c + bottom - j][i] = board[c][i];
                            board[c][i] = 0;
                        }
                        bottom = -1;
                    }

                }
            }*/
        }

        StringBuilder result = new StringBuilder();

        for (int i=0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                result.append(board[i][j]);

                if (j == 9 && i != n-1) result.append('\n');
            }

        }

        PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    /*
    *
    * i is the number of bales that are connected, when there is a return statement
    * need to check if there is more than k connected bales */

    private static int floodfill (int[][] board, int r, int c, int num) {
        if (r < 0 || r >= n || c < 0 || c >= 10) return 0;

        if (board[r][c] != num) return 0;

        if (fieldTracker[r][c]) return 0;

        fieldTracker[r][c] = true;
        regionTracker[r][c] = 0;

    return 1 +  floodfill(board, r, c + 1, num)
        + floodfill(board, r, c - 1, num)
        + floodfill(board, r + 1, c, num)
        + floodfill(board, r - 1, c, num);
    }
  
 /*
 ANALYSIS

 Run floodfill algorithm to find all the regions, if finding non zero region count the total of bales

 if it is greater than k, then need to "move" down the bales -- replace with 0

 move down algorithm:

 when getting the cells from the floodfill algorithm, order based on column and then get the maximum row
 and the minimum row and then can shift those number of cells down,

 mar, mir

 mir - 1

 need to replace all of the top with zero

 */
}


