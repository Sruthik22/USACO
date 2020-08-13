// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class EditDistance {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            String s1 = in.next();
            String s2 = in.next();

            int[][] dp = new int[s1.length() + 1][s2.length() + 1];

            for (int j = 0; j <= s1.length(); j++) {
                dp[j][0] = j;
            }

            for (int j = 0; j <= s2.length(); j++) {
                dp[0][j] = j;
            }

            for (int j = 1; j <= s1.length(); j++) {
                for (int k = 1; k <= s2.length(); k++) {

                    dp[j][k] = Math.min(dp[j-1][k] + 1, dp[j][k - 1] + 1);

                    if (s1.charAt(j-1) == s2.charAt(k-1)) {
                        dp[j][k] = Math.min(dp[j][k], dp[j-1][k-1]);
                    }
                    else {
                        dp[j][k] = Math.min(dp[j][k], dp[j-1][k-1] + 1);
                    }
                }
            }

            out.println(dp[s1.length()][s2.length()]);
        }

        out.close();
    }
}


