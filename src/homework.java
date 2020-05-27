// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class homework {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("homework.in"));
        int n = in.nextInt();
        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            scores[i] = in.nextInt();
        }

        in.close();


        int prefixSum = scores[n-1];

        int min = scores[n-1];
        double highAverage = 0;

        ArrayList<Integer> ks = new ArrayList<>();

        PrintWriter out = new PrintWriter(new File("homework.out"));


        for (int i = n-2; i > 0; i--) {
            prefixSum += scores[i];

            min = Math.min(min, scores[i]);

            int curTotal = prefixSum - min;

            int curNumbs = n - i - 1;

            double curAverage = curTotal / ((double) (curNumbs));

            if (curAverage > highAverage) {
                ks.clear();
                ks.add(i);
                highAverage = curAverage;
            }

            else if (curAverage == highAverage) {
                ks.add(i);
            }
        }

        for (int i = ks.size() - 1; i >= 0; i--) {
            System.out.println(ks.get(i));
            out.println(ks.get(i));
        }

        out.close();
    }
}


