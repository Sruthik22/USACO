// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class ballet {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("ballet.in"));
        int n = in.nextInt();

        int[][] curHoovesPos = {
                {0, 1}, //FL
                {1, 1}, //FR
                {0, 0}, //RL
                {1, 0}  //RR
        }; // (x, y)

        int[] extremes = {1, 1, 0, 0}; // Max X, Max Y, Min X, Min Y

        int direction = 0; // 0 - north, 1 - east, 2 - south, 3 - west

        boolean worked = true;

        for (int i = 0; i < n; i++) {
            String command = in.next();

            String foot = command.substring(0, 2);
            int findex = fIndexing(foot);

            char lastChar = command.charAt(2);

            if (lastChar == 'P') {
                direction = (direction + 1) % 4;

                for (int j = 0; j < 4; j++) {
                    if (j == findex) continue;

                    curHoovesPos[j] =
                            new int[] {
                                    curHoovesPos[findex][0] - (curHoovesPos[findex][1] - curHoovesPos[j][1]),
                                    curHoovesPos[findex][1] + (curHoovesPos[findex][0] - curHoovesPos[j][0])};
                }
            }

            else {
                if (lastChar == 'F') {
                    if (direction == 0) {
                        curHoovesPos[findex][1]++;
                    }

                    else if (direction == 1) {
                        curHoovesPos[findex][0]++;
                    }

                    else if (direction == 2) {
                        curHoovesPos[findex][1]--;
                    }

                    else {
                        curHoovesPos[findex][0]--;
                    }
                }

                else if (lastChar == 'B') {
                    if (direction == 0) {
                        curHoovesPos[findex][1]--;
                    }

                    else if (direction == 1) {
                        curHoovesPos[findex][0]--;
                    }

                    else if (direction == 2) {
                        curHoovesPos[findex][1]++;
                    }

                    else {
                        curHoovesPos[findex][0]++;
                    }
                }

                else if (lastChar == 'R') {
                    if (direction == 0) {
                        curHoovesPos[findex][0]++;
                    }

                    else if (direction == 1) {
                        curHoovesPos[findex][1]--;
                    }

                    else if (direction == 2) {
                        curHoovesPos[findex][0]--;
                    }

                    else {
                        curHoovesPos[findex][1]++;
                    }
                }

                else {
                    if (direction == 0) {
                        curHoovesPos[findex][0]--;
                    }

                    else if (direction == 1) {
                        curHoovesPos[findex][1]++;
                    }

                    else if (direction == 2) {
                        curHoovesPos[findex][0]++;
                    }

                    else {
                        curHoovesPos[findex][1]--;
                    }
                }

                if (sameSquare(findex, curHoovesPos)) {
                    worked = false;
                    break;
                }
            }

            extremes[0] = Math.max(extremes[0], Math.max(curHoovesPos[0][0], Math.max(curHoovesPos[1][0], Math.max(curHoovesPos[2][0], curHoovesPos[3][0]))));
            extremes[1] = Math.max(extremes[1], Math.max(curHoovesPos[0][1], Math.max(curHoovesPos[1][1], Math.max(curHoovesPos[2][1], curHoovesPos[3][1]))));
            extremes[2] = Math.min(extremes[2], Math.min(curHoovesPos[0][0], Math.min(curHoovesPos[1][0], Math.min(curHoovesPos[2][0], curHoovesPos[3][0]))));
            extremes[3] = Math.min(extremes[3], Math.min(curHoovesPos[0][1], Math.min(curHoovesPos[1][1], Math.min(curHoovesPos[2][1], curHoovesPos[3][1]))));
        }

        in.close();

        int result;

        if (worked) {
            result = (extremes[0] - extremes[2] + 1) * (extremes[1] - extremes[3] + 1);
        }

        else {
            result = -1;
        }

        PrintWriter out = new PrintWriter(new File("ballet.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int fIndexing(String foot) {
        int findex;

        if (foot.equals("FL")) {
            findex = 0;
        }

        else if (foot.equals("FR")) {
            findex = 1;
        }

        else if (foot.equals("RL")) {
            findex = 2;
        }

        else {
            findex = 3;
        }

        return findex;
    }

    private static boolean sameSquare(int fIndex, int[][] curHoovesPos) {
        for (int i = 0; i < 4; i++) {
            if (i == fIndex) continue;

            if (curHoovesPos[i] == curHoovesPos[fIndex]) return true;
        }

        return false;
    }
  
 /*
 ANALYSIS

 Create a 2d array 4 x 2 where it is a 2d array for each hoof (x, y)

 FL FR RL RR

 90 clockwise

 (x, y) --> (y, -x)

 Around a point (a, b) (pivot point)
 ((y - b) + a, - (x - a) + b)


 */
}


