// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class bovineshuffle {

    private static LinkedList<Integer>[] linkedList;

    static int found;
    static int[] cycleDetection;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("shuffle.in"));
        int n = in.nextInt();

        linkedList = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedList[i] = new LinkedList<>();
        }

        for (int i = 0; i<n; i++) {
            linkedList[i].add(in.nextInt()-1);
        }

        in.close();

        int result = 0;

        cycleDetection = new int[n];

        for (int i = 0; i < n; i++) {
            found = -1;

            if (cycleDetection[i] == 0) dfs(i);
        }

        for (int i = 0; i < n; i++) {
            if (cycleDetection[i] == 2) result++;
        }

        PrintWriter out = new PrintWriter(new File("shuffle.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void dfs(int j) {

        if (cycleDetection[j] == 1) {
            found = j;
            return;
        }

        if (cycleDetection[j] == 2) {
            found = -1;
            return;
        }

        cycleDetection[j] = 1;

        for (int i  : linkedList[j]) {
            dfs(i);
        }

        if (j != found && cycleDetection[j] != 2 && found != -1) {
            cycleDetection[j] = 2;
            return;
        }

        if (found == j && cycleDetection[j] != 2) {
            // the cycle detection is done, now make found -1 so that no more

            cycleDetection[j] = 2;
            found = -1;
        }
    }
}


