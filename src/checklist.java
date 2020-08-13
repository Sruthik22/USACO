// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.awt.im.spi.InputMethod;
import java.util.*;
import java.io.*;

public class checklist {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("checklist.in")));

        int hs = nextInt();
        int gs = nextInt();

        Cow[] holsteins = new Cow[hs];

        for (int i = 0; i < hs; i++) {
            holsteins[i] = new Cow(nextInt(), nextInt());
        }

        Cow[] guernseys = new Cow[gs];

        for (int i = 0; i < gs; i++) {
            guernseys[i] = new Cow(nextInt(), nextInt());
        }

        long[][][] dp = new long[hs + 1][gs + 1][2];

        for (int i = 0; i <= hs; i++) {
            for (int j = 0; j <= gs; j++) {
                Arrays.fill(dp[i][j], 1L << 60);
            }
        }

        dp[1][0][0] = 0;

        for (int i = 0; i <= hs; i++) {
            for (int j = 0; j <= gs; j++) {
                for (int k = 0; k < 2; k++) {
                    if (k == 0 && i == 0) continue;
                    if (k == 1 && j == 0) continue;

                    Cow cur;
                    if (k == 0) cur = holsteins[i - 1];
                    else cur = guernseys[j - 1];

                    if (i < hs) {
                        dp[i + 1][j][0] = Math.min(dp[i + 1][j][0], dp[i][j][k] + cur.distance(holsteins[i]));
                    }

                    if (j < gs) {
                        dp[i][j + 1][1] = Math.min(dp[i][j + 1][1], dp[i][j][k] + cur.distance(guernseys[j]));
                    }
                }
            }
        }

        long result = dp[hs][gs][0];
        PrintWriter out = new PrintWriter(new File("checklist.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow {
        int x, y;

        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distance(Cow other) {
            return Math.abs(other.x - x) * Math.abs(other.x - x) + Math.abs(other.y - y) * Math.abs(other.y - y);
        }
    }
}


