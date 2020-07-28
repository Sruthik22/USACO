// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Sum {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();
        int x = nextInt();

        int[] nums = new int[n];
        int[] orig_order = new int[n];

        for (int i = 0; i < n; i++) {
            int val = nextInt();
            nums[i] = val;
            orig_order[i] = val;
        }

        Arrays.sort(nums);

        int pointer1 = 0;
        int pointer2 = n-1;

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        while (nums[pointer1] + nums[pointer2] != x && (pointer2 - pointer1 > 1)) {
            int sum = nums[pointer1] + nums[pointer2];

            if (sum > x && pointer2 != pointer1+1) pointer2--;
            else if (sum < x && pointer2 != pointer1+1) pointer1++;
        }

        if (nums[pointer1] + nums[pointer2] == x) {
            int val1 = nums[pointer1];
            int val2 = nums[pointer2];

            int pointer1_corrected = -1;
            int pointer_2_corrected = -1;

            for (int i = 0; i < n; i++) {
                if (orig_order[i] == val1 && pointer1_corrected == -1) pointer1_corrected = i + 1;
                if (orig_order[i] == val2 && pointer1_corrected != i+1) pointer_2_corrected = i + 1;
            }

            if (pointer_2_corrected == -1) out.println("IMPOSSIBLE");
            else out.print(pointer1_corrected + " " + pointer_2_corrected);
        }

        else {
            out.println("IMPOSSIBLE");
        }

        out.close();
    }
}


