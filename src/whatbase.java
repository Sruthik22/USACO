// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class whatbase {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("whatbase.in"));
        int n = in.nextInt();

        PrintWriter out = new PrintWriter(new File("whatbase.out"));

        for (int i = 0; i < n; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();

            int max = Math.max(num1, num2); // this number is going to have a lower base
            int min = Math.min(num1, num2); // going to have a higher base

            int[] valuesLarger = new int[15000-10];
            int[] valuesSmaller = new int[15000-10];

            for (int j = 10; j < 15000; j++) {
                valuesSmaller[j-10] = convert(min, j);
                valuesLarger[j-10] = convert(max, j);
            }

            int pointerLarger = 0;
            int pointerSmaller = 0;

            while (pointerSmaller + 1 < valuesLarger.length) {
                pointerSmaller++;

                if (valuesSmaller[pointerSmaller] > valuesLarger[pointerLarger]) pointerLarger++;

                if (valuesSmaller[pointerSmaller] == valuesLarger[pointerLarger]) break;
            }

            // !!!!have to print in the correct order!!!

            if (min == num1) out.println("" + (pointerSmaller + 10) + " " + (pointerLarger + 10));
            else out.println("" + (pointerLarger + 10) + " " + (pointerSmaller + 10));
        }

        in.close();
        out.close();
    }

    private static int convert(int val, int base) {
        String s = String.valueOf(val);
        return (int) (Character.getNumericValue(s.charAt(0)) * Math.pow(base, 2) + Character.getNumericValue(s.charAt(1)) * base + Character.getNumericValue(s.charAt(2)));
    }
}


