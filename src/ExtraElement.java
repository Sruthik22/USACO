// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class ExtraElement {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        Index[] elems = new Index[n];

        for (int i = 0; i < n; i++) {
            elems[i] = new Index(nextInt(), i);
        }

        Arrays.sort(elems);

        boolean works = true;

        if (elems.length > 2) {
            int diff = elems[2].val - elems[1].val;
            int indexToRemove = elems[0].index;

            for (int i = 2; i < n; i++) {
                if (elems[i].val - elems[i-1].val == diff) continue;

                works = false;
                break;
            }

            if (works) {
                out.println(indexToRemove+1);
                out.close();
                return;
            }

            works = true;

            diff = elems[2].val - elems[0].val;
            indexToRemove = elems[1].index;

            for (int i = 3; i < n; i++) {
                if (elems[i].val - elems[i-1].val == diff) continue;

                works = false;
                break;
            }

            if (works) {
                out.println(indexToRemove+1);
                out.close();
                return;
            }
        }

        works = true;

        int diff = elems[1].val - elems[0].val;

        int indexToRemove = -1;

        for (int i = 1; i < n; i++) {
            if (indexToRemove != -1 && indexToRemove == elems[i-1].index) {
                if (elems[i].val - elems[i-2].val == diff) continue;
            }

            if (elems[i].val - elems[i-1].val == diff) continue;

            if (i != n-1) {
                if (!(elems[i+1].val - elems[i-1].val == diff && indexToRemove == -1)) {
                    works = false;
                    break;
                }
            }

            indexToRemove = elems[i].index;
        }

        if (works) {
            out.println(Math.max(indexToRemove+1, 1));
            out.close();
            return;
        }

        else {
            out.println(-1);
        }

        out.close();
    }

    static class Index implements Comparable<Index> {
        int val, index;

        Index(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Index o) {
            return this.val - o.val;
        }
    }
}


