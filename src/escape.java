// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class escape {

    private static int n;
    private static int[] cowWeights;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("escape.in"));
        n = in.nextInt();
        cowWeights = new int[n];
        for (int i = 0; i < n; i++) {
            cowWeights[i] = in.nextInt();
        }
        in.close();

        int result = maxCountMaybeWith(0, 0);

        PrintWriter out = new PrintWriter(new File("escape.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int maxCountMaybeWith(int weightSoFar, int mbCow) {
        // be a recursive function that either adds a cow or doesn't add the cow

        if (mbCow >= n) return 0;

        int withoutMB = maxCountMaybeWith(weightSoFar, mbCow + 1);

        if (causeCarries(weightSoFar, cowWeights[mbCow])) {
            return withoutMB;
        }

        weightSoFar += cowWeights[mbCow];

        int countWithCur = 1 + maxCountMaybeWith(weightSoFar, mbCow+1);

        return Math.max(countWithCur, withoutMB);

    }

    private static boolean causeCarries(int a, int b) {
        while (a > 0 && b > 0) {
            if (a % 10 + b % 10 < 10) {
                a /= 10;
                b /= 10;
            } else return true;
        }

        return false;
    }
  
 /*
 ANALYSIS
 Try each cow in and out of the combinations

 recursion
 */
}


