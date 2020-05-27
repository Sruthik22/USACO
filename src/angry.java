// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class angry {
    private static int n;
    private static int k;
    private static int[] xpos;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("angry.in"));
        n = in.nextInt();
        k = in.nextInt(); // num cows to shoot

        xpos = new int[n];

        for (int i = 0; i < n; i++) {
            xpos[i] = in.nextInt();
        }

        Arrays.sort(xpos);

        in.close();

        int lowerBound = 0;
        int upperBound = xpos[n-1] - xpos[0];

        while (lowerBound < upperBound) {
            int mid = (lowerBound + upperBound) / 2;
            if (simulate(mid)) {
                upperBound = mid;
            }

            else lowerBound = mid+1;
        }

        int result = lowerBound;
        PrintWriter out = new PrintWriter(new File("angry.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean simulate(int r) {

        int farthestX = 0;

        int cowsUsed = 0;

        for (int i = 0; i < n; i++) {
            if (xpos[i] > farthestX) {
                // then need to use another cow
                cowsUsed++;
                farthestX = 2 * r + xpos[i];

                if (cowsUsed > k) return false;
            }
        }

        return true;
    }
}


