/* This template code suggested by KT BYTE Computer Science Academy
for use in reading and writing files for USACO problems.

https://content.ktbyte.com/problem.java

http://www.usaco.org/index.php?page=viewproblem2&cpid=104
*/

import java.util.*;
import java.io.*;

public class stacking {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("stacking.in"));

        int n = in.nextInt(); // # of stacks
        int k = in.nextInt(); // # of instructions

        TreeMap <Integer, Integer> relCoats = new TreeMap<>();

        for (int i = 0; i < k; i++) {
            int a = in.nextInt();
            int b = in.nextInt() + 1;

            Integer aVal = relCoats.get(a);

            if (aVal == null) aVal = 0;

            relCoats.put(a, aVal + 1);

            Integer bVal = relCoats.get(b);

            if (bVal == null) bVal = 0;

            relCoats.put(b, bVal - 1);
        }
        in.close();

        int coats = 0;
        int x = 1;
        int index = 0;

        int[] stacks = new int[n];

        for (int newX : relCoats.keySet()) {
            int dist = newX-x;

            for (int i = 0; i < dist; i++) {
                stacks[index] = coats;
                index++;
            }

            x = newX;

            coats += relCoats.get(x);
        }

        Arrays.sort(stacks);

        int result = stacks[(stacks.length - 1) / 2];

        PrintWriter out = new PrintWriter(new File("stacking.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


