// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.text.AttributedCharacterIterator;
import java.util.*;
import java.io.*;

public class snowboots {

    private static int n;
    private static int b;
    private static int[] depthOfSnow;
    private static Boot[] boots;

    private static int min;

    private static boolean[][] beenthere;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("snowboots.in"));
        n = in.nextInt();
        b = in.nextInt();

        beenthere = new boolean[b][n];
        min = Integer.MAX_VALUE;

        depthOfSnow = new int[n];

        for (int i = 0; i < n; i++) {
            depthOfSnow[i] = in.nextInt();
        }

        boots = new Boot[b];

        for (int i = 0; i < b; i++) {
            boots[i] = new Boot(in.nextInt(), in.nextInt());
        }

        in.close();

        dfs(0, 0, 0);

        int result = min;
        PrintWriter out = new PrintWriter(new File("snowboots.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void dfs(int tile, int boot, int discard) {
        // no repetition case

        if (beenthere[boot][tile]) return;
        beenthere[boot][tile] = true;
        // end case

        if (tile == n-1) {
            min = Math.min(min, discard);
        }

        // forward with same boot

        for (int i = 1; i <= boots[boot].maxStepSize && tile + i < n; i++) {

            if (boots[boot].maxDepth >= depthOfSnow[tile+i]) dfs(tile + i, boot, discard);
        }

        // different boot

        for (int i = 1; i + boot < b; i++) {
            discard++;
            if (boots[boot+i].maxDepth >= depthOfSnow[tile]) dfs(tile, boot + i, discard);
        }
    }

    static class Boot {
        int maxDepth, maxStepSize;

        private Boot(int maxDepth, int maxStepSize) {
            this.maxDepth = maxDepth;
            this.maxStepSize = maxStepSize;
        }
    }
}


