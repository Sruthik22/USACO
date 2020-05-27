/*
ID: sruthi.2
LANG: JAVA
TASK: dualpal
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class dualpal {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("dualpal.in")));
        PrintWriter out = new PrintWriter(new File("dualpal.out"));

        int n = nextInt();
        int s = nextInt();

        int result = 0;
        int curNum = s+1;

        while (result < n) {
            if (doublePalindrome(String.valueOf(curNum))) {
                out.println(curNum);
                result++;
            }

            curNum++;
        }

        out.close();
    }

    public static boolean doublePalindrome(String number) {
        int numWorks = 0;
        for (int i = 2; i <= 10; i++) {
            if (isPalindrome(baseConversion(number, 10, i))) numWorks++;
            if (numWorks >= 2) return true;
        }

        return false;
    }

    public static String baseConversion(String number, int sBase, int dBase)
    {
        // Parse the number with source radix
        // and return in specified radix(base)
        return Integer.toString(Integer.parseInt(number, sBase), dBase);
    }

    public static boolean isPalindrome(String number) {
        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - i - 1)) return false;
        }

        return true;
    }
}


