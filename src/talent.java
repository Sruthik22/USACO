// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class talent {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("talent.in")));

        int n = nextInt();
        int w = nextInt();

        Cow[] cows = new Cow[n + 1];

        int totalTalent = 0;
        int totalWeight = 0;

        for (int i = 1; i <= n; i++) {
            cows[i] = new Cow(nextInt(), nextInt());
            totalTalent += cows[i].t;
            totalWeight += cows[i].w;
        }

        int[][] dp = new int[n + 1][totalTalent + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= totalTalent; j++) {
                dp[i][j] = -1;
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalTalent; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - cows[i].t >= 0 && dp[i-1][j - cows[i].t] != -1) {
                    if (dp[i][j] == -1) dp[i][j] = dp[i-1][j - cows[i].t] + cows[i].w;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j - cows[i].t] + cows[i].w);
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= totalTalent; i++) {
            if (dp[n][i] >= w) {
                int ratio = (int) (1000 * ((double) (i) / dp[n][i]));
                result = Math.max(result, ratio);
            }
        }

        PrintWriter out = new PrintWriter(new File("talent.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow {
        int w, t;

        Cow (int w, int t) {
            this.w = w;
            this.t = t;
        }
    }
}


