// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SumInTheTree {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static LinkedList<Integer>[] linkedLists;
    static int[] vals;
    static long result;
    static boolean works = true;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        n = nextInt();

        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int next = nextInt() - 1;

            linkedLists[next].add(i+1);
        }

        vals = new int[n];

        for (int i = 0; i < n; i++) {
            vals[i] = nextInt();
        }

        dfs(0, 0);

        if (works)  out.println(result);
        else out.println(-1);

        out.close();
    }

    static void dfs(int sumSoFar, int index) {
        if (index >= n) return;

        if (vals[index] != -1) {
            if (vals[index] < sumSoFar) {
                works = false;
                return;
            }

            int diff = vals[index] - sumSoFar;

            result += diff;

            for (int i: linkedLists[index]) {
                dfs(vals[index], i);
            }
        }

        else {
            int min = Integer.MAX_VALUE;
            for (int i: linkedLists[index]) {
                min = Math.min(vals[i] - sumSoFar, min);
            }

            if (min == Integer.MAX_VALUE) min = 0;

            if (min < 0) {
                works = false;
                return;
            }

            result += min;

            for (int i: linkedLists[index]) {
                dfs(sumSoFar+min, i);
            }
        }
    }
}


