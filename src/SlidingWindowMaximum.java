// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3)));
    }

    static ArrayDeque<Value> arrayDeque;

    public static int[] maxSlidingWindow(int[] nums, int k) {

        arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            clear(i, nums);
        }

        int[] ans = new int[nums.length - k + 1];

        ans[0] = arrayDeque.getFirst().value;

        for (int i = k; i < nums.length; i++) {
            if (arrayDeque.getFirst().index == i-k) arrayDeque.removeFirst();

            clear(i, nums);

            ans[i-k+1] = arrayDeque.getFirst().value;
        }

        return ans;
    }

    public static void clear(int index, int[] nums) {
        while (!arrayDeque.isEmpty()) {
            if (nums[index] < arrayDeque.getLast().value) break;
            arrayDeque.removeLast();
        }

        arrayDeque.addLast(new Value(index, nums[index]));
    }

    static class Value {
        int index, value;

        Value(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}


