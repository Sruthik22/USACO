// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class shuffle {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("shuffle.in"));
        int n = in.nextInt();
        int[] movements = new int[n];
        int[] numberOfEach = new int[n];

        for (int i = 0; i < n; i++) {
            movements[i] = in.nextInt() - 1;
            numberOfEach[movements[i]]++;
        }

        in.close();

        int result = n;

        PriorityQueue<Integer> obsoleteIndexes = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if (numberOfEach[i] == 0) {
                obsoleteIndexes.add(i);
                result--;
            }
        }

        while (obsoleteIndexes.size() > 0) {
            int index = obsoleteIndexes.poll();

            numberOfEach[movements[index]]--;

            if (numberOfEach[movements[index]] == 0) {
                obsoleteIndexes.add(movements[index]);
                result--;
            }
        }

        PrintWriter out = new PrintWriter(new File("shuffle.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}