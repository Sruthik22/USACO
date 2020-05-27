// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class revegetate {

    static HashMap<Integer, Set<Integer>> same;
    static HashMap<Integer, Set<Integer>> different;

    private static int[] L;

    private static boolean impossible;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("revegetate.in"));
        int n = in.nextInt(); // # of pastures
        int m = in.nextInt(); // # of lines

        same = new HashMap<>();
        different = new HashMap<>();

        L = new int[n + 1];

        for (int i = 0; i < m; i++) {
            char c = in.next().charAt(0);
            int f1 = in.nextInt();
            int f2 = in.nextInt();

            different.putIfAbsent(f1, new HashSet<>());
            different.putIfAbsent(f2, new HashSet<>());
            same.putIfAbsent(f1, new HashSet<>());
            same.putIfAbsent(f2, new HashSet<>());

            if (c == 'S') {

                same.get(f1).add(f2);
                same.get(f2).add(f1);
            }

            else {

                different.get(f1).add(f2);
                different.get(f2).add(f1);
            }
        }

        in.close();
        int result = 0;

        for (int i = 1; i <= n; i++) {
            if (L[i] == 0) {
                visit(i, 1);
                result++;
            }
        }

        PrintWriter out = new PrintWriter(new File("revegetate.out"));

        if (impossible) out.println("0");
        else {
            out.print("1");
            for (int i = 0; i < result; i++) {
                out.print("0");
            }
        }

        out.close();
    }

    private static void visit(int x, int v)
    {
        L[x] = v;
        if (same.containsKey(x)) {
            for (int n : same.get(x)) {
                if (L[n] == 3-v) impossible = true;
                if (L[n] == 0) visit(n, v);
            }
        }

        if (different.containsKey(x)) {
            for (int n : different.get(x)) {
                if (L[n] == v) impossible = true;
                if (L[n] == 0) visit(n, 3-v);
            }
        }
    }
  
 /*
 ANALYSIS

 There are two different grass seed

 grass type -1 means that both grasses are allowed
 1 is used when field is same as -1
 2 is used when field is different as -1, or 1

 2^(the number of -1s) --> binary

 Instead of hash map, and 1 and -1

 -----------

 create three different arrays,

 same where all values in the array are the same
 different, where all values in the are different from the same

 unknown array, where arrays are nested into same and different parts, and placed into the other arrays, if other
 members are part of the main array

recursive depth first search
    checks all of the nodes around them and labels them
    if sees the S and D on a single node inconsistencies - impossible

-------------

Instead of thinking of the problem in terms of arrays

think of nodes and edges connecting the nodes determining if they are the same seed or not

GRAPH THEORY
 */
}


