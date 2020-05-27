// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class paint {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("paint.in"));
        int n = in.nextInt();
        TreeMap <Integer, Integer> relCoats = new TreeMap<>();

        // TreeMap is a sorted key value pair structure
        // key: location on the fence
        // value: relative number of coats

        int x = 0;

        for (int i = 0; i < n; i++) {
            int steps = in.nextInt();

            if (in.next().charAt(0) == 'L') {
                steps*=-1;
            }

            int a = Math.min(x, x+steps);
            int b = Math.max(x, x+steps);

            Integer aVal = relCoats.get(a);

            if (aVal == null) aVal = 0;

            relCoats.put(a, aVal + 1);

            Integer bVal = relCoats.get(b);

            if (bVal == null) bVal = 0;

            relCoats.put(b, bVal - 1);

            x+= steps;
        }
        in.close();

        int result = 0;
        int coats = 0;
        x = -1000000000;

        for (int newX : relCoats.keySet()) {
             int dist = newX-x;
             if (coats >= 2) result += dist;
             x = newX;
             coats += relCoats.get(x);
        }

        PrintWriter out = new PrintWriter(new File("paint.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


