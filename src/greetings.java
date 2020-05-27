// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class greetings {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("greetings.in"));
        int b = in.nextInt();
        int e = in.nextInt();

        int[] bMoves = new int[b];
        int[] bDir = new int[b];
        for (int i = 0; i < b; i++) {
            bMoves[i] = in.nextInt();

            if (in.next().charAt(0) == 'L') bDir[i] = -1;
            else bDir[i] = 1;
        }

        int[] eMoves = new int[e];
        int[] eDir = new int[e];

        for (int i = 0; i < e; i++) {
            eMoves[i] = in.nextInt();

            if (in.next().charAt(0) == 'L') eDir[i] = -1;
            else eDir[i] = 1;
        }

        in.close();

        int result = 0;

        int eIndex = 0;
        int bIndex = 0;

        int ePos = 0;
        int bPos = 0;

        while (bIndex<b || eIndex<e) {

            int minTime = 0;

            int prevE = ePos;
            int prevB = bPos;

            if (bIndex >= b) {
                // out of range
                // epos won't change, just doing bessie

                minTime = eMoves[eIndex];

                if (eDir[eIndex] < 0) ePos -= minTime;
                else ePos += minTime;
            }

            else if (eIndex >= e) {
                // out of range
                minTime = bMoves[bIndex];

                if (bDir[bIndex] < 0) bPos -= minTime;
                else bPos += minTime;
            }

            else {
                minTime = Math.min(eMoves[eIndex], bMoves[bIndex]);

                if (eDir[eIndex] < 0) ePos -= minTime;
                else ePos += minTime;

                if (bDir[bIndex] < 0) bPos -= minTime;
                else bPos += minTime;
            }




            if (prevE > prevB && bPos >= ePos) result++;
            if (prevB > prevE && ePos >= bPos) result++;

            if (eIndex<e) eMoves[eIndex]-=minTime;
            if (bIndex<b) bMoves[bIndex]-=minTime;

            if (eIndex<e&&eMoves[eIndex] == 0) eIndex++;
            if (bIndex<b&&bMoves[bIndex] == 0) bIndex++;
        }


        PrintWriter out = new PrintWriter(new File("greetings.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


