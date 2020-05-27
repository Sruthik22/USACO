// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class photo {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("photo.in"));
        int n = in.nextInt();
        int k = in.nextInt();

        Pair[] pairs = new Pair[k];

        for (int i = 0; i < k; i++) {
            pairs[i] = new Pair(in.nextInt(), in.nextInt());
        }

        Arrays.sort(pairs);

        in.close();

        int result = 0;

        int startVal = 1;

        for (Pair pair: pairs) {
            if (pair.i1 < startVal) continue;

            result++;
            startVal = pair.i2;
        }

        result++;

        PrintWriter out = new PrintWriter(new File("photo.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static class Pair implements Comparable<Pair>{
        int i1;
        int i2;

        Pair(int num1, int num2) {
            i1 = Math.min(num1, num2);
            i2 = Math.max(num1, num2);
        }

        @Override
        public int compareTo(Pair o) {
            return this.i2 - o.i2;
        }
    }
}

/*
 unless one of a pair of cows moves, then will always be in the same place relatively
 so at least 3 out of the 5 will be in the correct place
 
 just choose the position of the max
*/


