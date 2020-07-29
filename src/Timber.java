// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Timber {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("Timber.in")));
        PrintWriter out = new PrintWriter(new File("Timber.out"));

        int t = nextInt();

        for (int j = 0; j < t; j++) {
            int n = nextInt();

            Tree[] trees = new Tree[n];

            for (int i = 0; i < n; i++) {
                int p = nextInt();
                int h = nextInt();

                trees[i] = new Tree(p, h);
            }

            Arrays.sort(trees);

            HashMap<Integer, Integer> rights = new HashMap<>();

            //right
            for (int i = 0; i < n; i++) {
                Tree tree = trees[i];

                rights.putIfAbsent(tree.p, 0);

                if (rights.containsKey(tree.p + tree.h)) {
                    int cur = rights.get(tree.p + tree.h);

                    rights.put(tree.p + tree.h, Math.max(cur, rights.get(tree.p) + tree.h));
                }

                rights.putIfAbsent(tree.p + tree.h, rights.get(tree.p) + tree.h);
            }

            HashMap<Integer, Integer> left = new HashMap<>();

            //left
            for (int i = n-1; i >= 0; i--) {
                Tree tree = trees[i];

                left.putIfAbsent(tree.p, 0);

                if (left.containsKey(tree.p - tree.h)) {
                    int cur = left.get(tree.p - tree.h);

                    left.put(tree.p - tree.h, Math.max(cur, left.get(tree.p) + tree.h));
                }

                left.putIfAbsent(tree.p - tree.h, left.get(tree.p) + tree.h);
            }

            int result = 0;

            for (Map.Entry<Integer, Integer> entry: rights.entrySet()) {
                int p = entry.getKey();
                int max = entry.getValue();

                if (left.containsKey(p)) {
                    result = Math.max(result, max + left.get(p));
                }

                else {
                    result = Math.max(result, max);
                }
            }

            for (Map.Entry<Integer, Integer> entry: left.entrySet()) {
                int max = entry.getValue();

                result = Math.max(result, max);
            }

            out.print("Case #" + (j+1) + ": ");
            out.println(result);
        }

        out.close();
    }

    static class Tree implements Comparable<Tree> {
        int p, h;

        Tree(int p, int h) {
            this.p = p;
            this.h = h;
        }

        @Override
        public int compareTo(Tree o) {
            return this.p - o.p;
        }
    }
}


