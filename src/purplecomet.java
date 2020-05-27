// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class purplecomet {

    static HashSet<String> result;

    public static void main(String[] args) throws Exception {
        result = new HashSet<>();

        printPermutn("DDDNNNNPPP", "");

        int ans = 0;

        for (String s : result) {

            int patientLast = s.indexOf('P');
            int numNurse = 0;
            int numDoctor = 0;

            boolean flag = false;

            for (int i = patientLast+1; i < s.length(); i++) {
                if (s.charAt(i) == 'P') {
                    if (numNurse < 1 || numDoctor < 1) {
                        flag = true;
                        break;
                    }
                    numNurse=0;
                    numDoctor=0;
                }
                if (s.charAt(i) == 'N') numNurse++;
                if (s.charAt(i) == 'D') numDoctor++;
            }

            if (!flag) ans++;
        }

        System.out.println(ans);

    }

    // Function to print all the permutations of str
    static void printPermutn(String str, String ans)
    {

        // If string is empty
        if (str.length() == 0) {
            result.add(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            // Recurvise call
            printPermutn(ros, ans + ch);
        }
    }
}


