// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class LongestCommonSubsequence {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static String text1;
    static String text2;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        text1 = next();
        text2 = next();

        out.println(longestCommonSubsequence());
        out.close();
    }

    public static String longestCommonSubsequence() {
        int[][] max = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    max[i][j] = 1 + max[i-1][j-1];
                }

                else {
                    max[i][j] = Math.max(max[i-1][j], max[i][j-1]);
                }
            }
        }

        int indexText1 = text1.length();
        int indexText2 = text2.length();

        StringBuilder sb = new StringBuilder();

        while (indexText1 >= 1 && indexText2 >= 1) {
            if (areEqual(indexText1, indexText2)) {
                sb.append(text1.charAt(indexText1 - 1));
                indexText1--;
                indexText2--;
            }

            else {
                if (max[indexText1 - 1][indexText2] > max[indexText1][indexText2 - 1]) {
                    indexText1--;
                }

                else {
                    indexText2--;
                }
            }
        }

        return sb.reverse().toString();
    }

    static boolean areEqual(int index1, int index2) {
        return text1.charAt(index1 - 1) == text2.charAt(index2 - 1);
    }
}


