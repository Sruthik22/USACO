/*
ID: sruthi.2
LANG: JAVA
TASK: palsquare
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class palsquare {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("palsquare.in")));

        PrintWriter out = new PrintWriter(new File("palsquare.out"));

        int b = nextInt();

        for (int n = 1; n < 300; n++) {
            int nsquared = n*n;

            String s = baseConversion(String.valueOf(nsquared), 10, b).toUpperCase();

            if (isPalindrome(s)) {
                out.print(baseConversion(String.valueOf(n), 10, b).toUpperCase());
                out.print(" ");
                out.print(s);
                out.println();
            }
        }
        out.close();
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
  
 /*
 ANALYSIS
 
 */
}


