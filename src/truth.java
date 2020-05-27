// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class truth {

    static HashMap<Integer, Set<Integer>> same;
    static HashMap<Integer, Set<Integer>> different;

    private static int[] L;

    private static boolean impossible;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("truth.in"));

        int n = in.nextInt(); // # of cows
        int m = in.nextInt(); // # of statements

        same = new HashMap<>();
        different = new HashMap<>();



        int result = 0;


        for (int i = 0; i < m; i++) {
            int f1 = in.nextInt();
            int f2 = in.nextInt();
            char c = in.next().charAt(0);

            different.putIfAbsent(f1, new HashSet<>());
            different.putIfAbsent(f2, new HashSet<>());
            same.putIfAbsent(f1, new HashSet<>());
            same.putIfAbsent(f2, new HashSet<>());

            L = new int[n+1];

            if (c == 'T') {

                same.get(f1).add(f2);
                same.get(f2).add(f1);

            } else {

                different.get(f1).add(f2);
                different.get(f2).add(f1);
            }

            /*
            Every new iteration, we already know all of the previous things work, don't need to do again
            just test the new statement*/

            for (int a = 1; a <= n; a++) {
                if (L[a] == 0) {
                    visit(a, 1);
                    if (impossible) break;
                }
            }
            if (impossible) break;
            result++;
        }

        in.close();

        PrintWriter out = new PrintWriter(new File("truth.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void visit(int x, int v) {
        L[x] = v;
        if (same.containsKey(x)) {
            for (int n : same.get(x)) {
                if (L[n] == 3 - v) impossible = true;
                if (L[n] == 0) visit(n, v);
            }
        }

        if (different.containsKey(x)) {
            for (int n : different.get(x)) {
                if (L[n] == v) impossible = true;
                if (L[n] == 0) visit(n, 3 - v);
            }
        }
    }
}


