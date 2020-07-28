// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class BalancedTernaryString {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = in.nextInt();

        int[] countNums = new int[3];

        int[] nums = new int[n];

        String s = in.next();

        for (int i = 0; i < n; i++) {
            int next = Character.getNumericValue(s.charAt(i));

            countNums[next]++;

            nums[i] = next;
        }

        int correctNum = n/3;

        int[] passed = new int[3];

        for (int i = 0; i < n; i++) {
            int cur = nums[i];

            if (countNums[cur] <= correctNum) continue;

            // this is a case where we have more than necessary

            int lexSmallReplace = 0;

            for (int j = 0; j < 3; j++) {
                if (countNums[j] < correctNum) {
                    lexSmallReplace = j;
                    break;
                }
            }

            if (lexSmallReplace < cur) {
                nums[i] = lexSmallReplace;
                countNums[lexSmallReplace]++;
                countNums[cur]--;
            }

            else {
                if (passed[cur] == correctNum) {
                    nums[i] = lexSmallReplace;
                    countNums[lexSmallReplace]++;
                    countNums[cur]--;
                    passed[cur]--;
                }

                passed[cur]++;
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(nums[i]);
        }


        out.close();
    }
}


