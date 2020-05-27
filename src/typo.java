// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class typo {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("typo.in"));
        String s = in.next();
        in.close();

        int result = 0;

        int[] num = new int[2];

        int prefix = 0;

        boolean startWrong = false;
        boolean endWrong = false;

        for (int i = 0; i < s.length(); i++) {

            if (i == 0 && s.charAt(i) != '(') {
                startWrong = true;
            }

            if (i == s.length()-1 && s.charAt(i) != ')') {
                endWrong = true;
            }

            if (s.charAt(i) == '(' && i != 0) num[0]++;
            else if (s.charAt(i) == ')' && i != s.length()-1) num[1]++;

            if (s.charAt(i) == '(') prefix++;
            else if (s.charAt(i) == ')') prefix--;
        }

        if (startWrong && endWrong) result = 0;
        else if (startWrong) {
            if (prefix == -2) result = 1;
        }

        else if (endWrong) {
            if (prefix == 2) result = 1;
        }

        else if (prefix == 2) {
            result = num[0];
        }

        else if (prefix == -2) {
            result = num[1];
        }

        PrintWriter out = new PrintWriter(new File("typo.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}

/*

need to switch ) to (, if depth becomes negative --> any of the ones before
need to switch ( to ), if depth at end is positive --> cannot make the depth negative

how to avoid making the depth negative:
all of them need two or more, so no possibility


*/


