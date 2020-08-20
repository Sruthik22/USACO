// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class LongestIncreasingSubsequence {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = 1;

        for (int tt = 0; tt < t; tt++) {
            int n = nextInt();

            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = nextInt();
            }

            int[] dp = new int[n];
            int[] anotherOne = new int[n];

            for (int i = 0; i < n; i++) {
                dp[i] = 1;

                int maxVal = 0;
                int prevIndex = -1;

                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && dp[j] > maxVal) {
                        maxVal = dp[j];
                        prevIndex = j;
                    }
                }

                dp[i] = Math.max(dp[i], maxVal + 1);
                anotherOne[i] = prevIndex;
            }

            int result = 0;
            int maxIndex = -1;

            for (int i = 0; i < nums.length; i++) {
                if (dp[i] > result) {
                    result = dp[i];
                    maxIndex = i;
                }
            }

            StringBuilder sb = new StringBuilder();

            while (true) {
                sb.append(maxIndex);
                if (anotherOne[maxIndex] == -1) break;
                sb.append(" ");
                maxIndex = anotherOne[maxIndex];
            }

            out.println(result);
            //out.println(sb.reverse().toString());
        }

        out.close();
    }
}


