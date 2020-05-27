// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class div7 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("div7.in"));
        int n = in.nextInt();

        int[] prefix7sum = new int[n];

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (i == 0) {
                prefix7sum[i] = a % 7;
            }

            else {
                prefix7sum[i] = (prefix7sum[i-1] + a) % 7;
            }
        }

        in.close();

        int[] startIndex = new int[] {-1, -1, -1, -1, -1, -1, -1};
        int[] endIndex = new int[] {-1, -1, -1, -1, -1, -1, -1};;

        for (int i = 0; i < n; i++) {
            int b = prefix7sum[i];
            if (startIndex[b] == -1) {
                startIndex[b] = i;
            }

            else {
                endIndex[b] = i;
            }
        }

        int result = 0;

        for (int i = 0; i < 7; i++) {
            result = Math.max(result, endIndex[i]-startIndex[i]);
        }


        PrintWriter out = new PrintWriter(new File("div7.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


