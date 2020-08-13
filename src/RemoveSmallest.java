// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class RemoveSmallest {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int tt = nextInt();

        for (int t = 0; t < tt; t++) {
            int n = nextInt();
            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = nextInt();
            }

            Arrays.sort(nums);

            int last = nums[0];

            boolean works = true;

            for (int i = 1; i < n; i++) {
                if (nums[i] - last > 1) {
                    works = false;
                    break;
                }

                last = nums[i];
            }

            if (works) {
                out.println("YES");
            }

            else {
                out.println("NO");
            }
        }

        out.close();
    }
}


