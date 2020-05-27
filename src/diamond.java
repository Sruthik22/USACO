// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class diamond {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("diamond.in"));
        int n = in.nextInt();
        int k = in.nextInt();

        int[] diamonds = new int[n];

        for (int i = 0; i < n; i++) {
            diamonds[i] = in.nextInt();
        }

        Arrays.sort(diamonds);

        in.close();

        int result = 0;

        int[] lowestIndex = new int[n];

        int lowI = 0;
        int highI = 0;

        while (highI < n) {

            if (diamonds[highI] - diamonds[lowI] <= k) {
                lowestIndex[lowI] = Math.max(lowestIndex[lowI], highI-lowI+1);
                highI++;
            }

            else
                lowI++;
        }

        for (int i=lowI+1; i<n; i++)
            lowestIndex[i] = n-i;

        int[] maxBack = new int[n];

        maxBack[n-1] = lowestIndex[n-1];

        for (int i = n-2; i >= 0; i--) {
            maxBack[i] = Math.max(maxBack[i+1], lowestIndex[i]);
        }

        for (int i = 0; i < n; i++) {
            if (lowestIndex[i] + i < n) {
                result = Math.max(result, lowestIndex[i]+maxBack[lowestIndex[i] + i]);
            }

            result = Math.max(result, lowestIndex[i]);
        }

        PrintWriter out = new PrintWriter(new File("diamond.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


