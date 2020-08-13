// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class teamwork {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("teamwork.in")));

        int n = nextInt();
        int k = nextInt();

        int[] skills = new int[n];

        for (int i = 0; i < n; i++) {
            skills[i] = nextInt();
        }

        int[] dp = new int[n];
        dp[0] = skills[0];

        for (int i = 1; i < n; i++) {
            int max = skills[i];
            for (int j = i; j >= 0 && i + 1 - j <= k; j--) {
                max = Math.max(skills[j], max);

                if (j == 0) dp[i] = Math.max(dp[i], max * (i + 1 - j));
                else dp[i] = Math.max(dp[i], max * (i + 1 - j) + dp[j-1]);
            }
        }

        int result = dp[n-1];

        PrintWriter out = new PrintWriter(new File("teamwork.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


