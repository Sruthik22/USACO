// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class decorate {

    static HashMap<Integer, Set<Integer>> different;

    private static int[] L;

    private static boolean impossible;

    private static int[] colorCount;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("decorate.in"));
        int n = in.nextInt(); // # of pastures
        int m = in.nextInt(); // # of lines

        different = new HashMap<>();

        L = new int[n + 1];

        colorCount = new int[3];

        for (int i = 0; i < m; i++) {
            int f1 = in.nextInt();
            int f2 = in.nextInt();

            different.putIfAbsent(f1, new HashSet<>());
            different.putIfAbsent(f2, new HashSet<>());

            different.get(f1).add(f2);
            different.get(f2).add(f1);
        }

        in.close();

        int result = 0;

        for (int i = 1; i <= n; i++) {
            if (L[i] == 0) {
                colorCount[1] = 0;
                colorCount[2] = 0;
                visit(i, 1);

                result += Math.max(colorCount[1], colorCount[2]);
            }
        }

        PrintWriter out = new PrintWriter(new File("decorate.out"));

        if (impossible) result = -1;

        out.println(result);
        out.close();
    }

    private static void visit(int x, int v)
    {
        L[x] = v;

        colorCount[v]++;

        if (!different.containsKey(x)) return;

        for (int n : different.get(x)) {
            if (L[n] == v) impossible = true;
            if (L[n] == 0) visit(n, 3-v);
        }
    }
}


