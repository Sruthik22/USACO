// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class teleport {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("teleport.in"));
        long n = in.nextLong();

        TreeMap<Long, Long> importantPoints = new TreeMap<>();

        int cur = 0;

        for (int i = 0; i < n; i++) {
            long a = in.nextLong();
            long b = in.nextLong();

            cur += Math.abs(b - a);

            long delta = Math.abs(b - a) - Math.abs(a);

            if (delta > 0) {
                if (importantPoints.containsKey(b-delta)) {
                    importantPoints.put(b-delta, importantPoints.get(b-delta) - 1);
                }

                if (importantPoints.containsKey(b)) {
                    importantPoints.put(b, importantPoints.get(b) + 2);
                }

                if (importantPoints.containsKey(b+delta)) {
                    importantPoints.put(b+delta, importantPoints.get(b+delta) - 1);
                }

                importantPoints.putIfAbsent(b-delta, -1L);
                importantPoints.putIfAbsent(b, 2L);
                importantPoints.putIfAbsent(b+delta, -1L);
            }
        }

        in.close();

        long result = cur;

        long lastx = importantPoints.firstKey();

        long deltaY = importantPoints.firstEntry().getValue();

        for (long i : importantPoints.keySet()) {

            if (i == importantPoints.firstKey()) continue;

            long xchange = i - lastx;
            cur += xchange * deltaY;

            result = Math.min(result, cur);

            deltaY += importantPoints.get(i);
            lastx = i;

        }

        PrintWriter out = new PrintWriter(new File("teleport.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


