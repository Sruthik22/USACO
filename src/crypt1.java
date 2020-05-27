/*
ID: sruthi.2
LANG: JAVA
TASK: crypt1
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class crypt1 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static HashSet<Integer> digits;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("crypt1.in")));

        int n = nextInt();

        digits = new HashSet<>();

        for (int i = 0; i < n; i++) {
            digits.add(nextInt());
        }

        int result = 0;

        for (int i : digits) {
            for (int j : digits) {
                for (int k : digits) {
                    for (int l : digits) {
                        for (int a : digits) {
                            int partialSum1 = (100*i + 10*j + k) * a;
                            int partialSum2 = (100*i + 10*j + k) * l;

                            int product = (100*i + 10*j + k) * (10*l+a);

                            if (length(partialSum1) == 3 && length(partialSum2) == 3 &&
                            length(product) == 4 && works(partialSum1) && works(partialSum2)
                            && works(product)) result++;
                        }
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("crypt1.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int length(int num) {
        return String.valueOf(num).length();
    }

    static boolean works(int num) {
        while(num > 0) {
            int digit = num % 10;
            if (!digits.contains(digit)) return false;
            num /= 10;
        }
        return true;
    }
}


