// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class RemoveOneElement {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }

        int[] endHere = new int[n];
        endHere[0] = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i-1]) endHere[i] = endHere[i-1] + 1;
            else endHere[i] = 1;
        }

        int[] startHere = new int[n];
        startHere[n-1] = 1;

        for (int i = n-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) startHere[i] = 1 + startHere[i+1];
            else startHere[i] = 1;
        }

        int result = Math.max(endHere[0], endHere[n-1]);

        for (int i = 1; i < n-1; i++) {
            int norm = endHere[i];
            int removal = 0;

            if (nums[i-1] < nums[i+1]) {
                removal = endHere[i-1] + startHere[i+1];
            }

            result = Math.max(result, Math.max(norm, removal));
        }

        out.println(result);

        out.close();
    }
}