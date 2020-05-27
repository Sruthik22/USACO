// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class shufflecycle {

    static int[] cycleCheck;
    static int[] forwardTo;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("shuffle.in"));
        int n = in.nextInt();

        forwardTo = new int[n];

        for (int i = 0; i < n; i++) {
            forwardTo[i] = in.nextInt() - 1;
        }

        in.close();

        cycleCheck = new int[n];
        // 0 haven't checked yet
        // 1 have visited
        // 2 part of a cycle

        for (int i = 0; i < n; i++) {
            if (cycleCheck[i] == 0) check(i);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (cycleCheck[i] == 2) result++;
        }


        PrintWriter out = new PrintWriter(new File("shuffle.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void check(int i) {
        HashSet<Integer> beenTo = new HashSet<>();

        // check if the value that we are going through has already been part of the array, or already been to every

        while (cycleCheck[i] == 0) {
            cycleCheck[i] = 1;
            beenTo.add(i); // adding whatever have gone to, so to help detect cycle later
            i = forwardTo[i];
        }

        // now finished marking what we have visited, need to mark the cycles
        if (beenTo.contains(i)) {

            int lookingFor = i;

            do {
                cycleCheck[i] = 2;
                i = forwardTo[i];
            } while (i != lookingFor);
        }
    }
}


