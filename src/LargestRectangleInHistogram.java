// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class LargestRectangleInHistogram {

    public static void main(String[] args) throws Exception {
        System.out.println(largestRectangleArea(new int[] {3, 6, 5, 7, 4, 8, 1, 0}));
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // indexes of the elements in the stack

        int max = 0;

        for (int i = 0; i < heights.length + 1; i++) {

            int val = 0;

            if (i != heights.length) {
                val = heights[i];
            }

            while (!stack.isEmpty()) {
                int last = heights[stack.peek()];

                if (val >= last) {
                    break;
                }

                else {


                    int index = stack.pop();
                    if (stack.size() == 0) {
                        max = Math.max(max, last * (i));
                    }

                    else {
                        int prev = stack.peek();
                        max = Math.max(max, last * (i - prev - 1));
                    }
                }
            }

            stack.push(i);
        }

        return max;
    }
}