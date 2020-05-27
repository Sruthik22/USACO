/*
ID: sruthi.2
LANG: JAVA
TASK: skidesign
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class skidesign {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("skidesign.in"));

        int number_of_hills = in.nextInt();

        int[] heights_of_hills = new int[number_of_hills];

        for (int i = 0; i < number_of_hills; i++) {
            heights_of_hills[i] = in.nextInt();
        }

        in.close();

        int result = Integer.MAX_VALUE;

        for (int i = 1; i < 84; i++) {

            int max_of_range = i + 17;
            int temp = 0;

            for (int x = 0; x < number_of_hills; x++) {

                int current_value = heights_of_hills[x];


                if (current_value >= i && current_value <= max_of_range) {
                    continue;
                }

                else if (current_value < i) {
                    temp+= Math.pow(i - current_value,2);
                }

                else {
                    // The value is larger than the upper bound
                    temp+= Math.pow(current_value - max_of_range, 2);
                }
            }

            result = Math.min(result, temp);
        }

        PrintWriter out = new PrintWriter(new File("skidesign.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


