// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class proximityv2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("proximity.in"));
        int n = in.nextInt();
        int k = in.nextInt() + 1;

        int[] cows = new int[n];

        for (int i = 0; i < n; i++) {
            cows[i] = in.nextInt();
        }

        in.close();

        int result = -1;

        int indexToRemove = 0;

        HashSet<Integer> s = new HashSet<>();

        for (int i = 0; i < k; i++) {

            if (s.contains(cows[i])) {
                result = Math.max(result, cows[i]);
            }

            else {
                s.add(cows[i]);
            }
        }

        for (int i = 0; i < n - k; i++) {
            s.remove(cows[indexToRemove]);

            if (s.contains(cows[k+i])) {
                result = Math.max(result, cows[k+i]);
            }

            else {
                s.add(cows[k + i]);
            }

            indexToRemove++;
        }

        PrintWriter out = new PrintWriter(new File("proximity.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

}


