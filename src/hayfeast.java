// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class hayfeast {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static ArrayDeque<Haybale> arrayDeque;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("hayfeast.in")));

        int n = nextInt();
        long m = nextLong();

        Haybale[] haybales = new Haybale[n];

        for (int i = 0; i < n; i++) {
            haybales[i] = new Haybale(nextLong(), nextLong(), i);
        }

        long result = Long.MAX_VALUE;
        int lp = 0;
        int rp = 0;

        long curSpiciness = 0;

        arrayDeque = new ArrayDeque<>();

        while (lp < n) {
            while (rp < n && curSpiciness < m) {
                curSpiciness += haybales[rp].flavor;
                add(haybales[rp]);
                rp++;
            }

            if (curSpiciness >= m) {
                result = Math.min(result, arrayDeque.getFirst().spiciness);
            }

            else break;

            if (arrayDeque.getFirst().index <= lp) arrayDeque.removeFirst();
            curSpiciness -= haybales[lp].flavor;
            lp++;
        }

        PrintWriter out = new PrintWriter(new File("hayfeast.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void add(Haybale haybale) {
        while (!arrayDeque.isEmpty()) {
            if (haybale.spiciness < arrayDeque.getLast().spiciness) break;
            arrayDeque.removeLast();
        }

        arrayDeque.add(haybale);
    }

    static class Haybale {
        long flavor, spiciness;
        int index;

        Haybale(long flavor, long spiciness, int index) {
            this.flavor = flavor;
            this.spiciness = spiciness;
            this.index = index;
        }
    }
}


