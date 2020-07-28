// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class sorted_adjacent_differences {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();
        for (int i = 0; i < t; i++) {
            int n = nextInt();

            int[] nums = new int[n];

            for (int j = 0; j < n; j++) {
                nums[j] = nextInt();
            }

            Arrays.sort(nums);

            int start = (int) Math.ceil((double) (n+1)/2)-1;

            int[] ans = new int[n];

            ans[0] = nums[start];

            for (int j = 0; j < n-1; j++) {

                int index = 0;

                if (j % 2 == 0) {
                    int what_step = j / 2;

                    index = start - (what_step + 1);
                }

                else {
                    int what_step = (j - 1) / 2;

                    index = start + (what_step + 1);
                }

                ans[j+1] = nums[index];
            }

            for (int j = 0; j < n; j++) {
                int answer = ans[j];
                out.print(answer);
                if (j != n-1) {
                    out.print(" ");
                }
            }

            out.println();
        }

        out.close();
    }
}


