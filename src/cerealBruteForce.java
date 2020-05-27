// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cerealBruteForce {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cereal.in")));

        int n = nextInt();
        int m = nextInt();

        Cow[] cows = new Cow[n+1];

        for (int i = 1; i <= n; i++) {
            int c1 = nextInt();
            int c2 = nextInt();

            Cow c = new Cow(c1, c2);

            cows[i] = c;
        }

        int[] results = new int[n+1];

        for (int i = n; i > 0; i--) {
            HashSet<Integer> cereals = new HashSet<>();
            for (int j = i; j <= n; j++) {
                Cow c = cows[j];

                if (cereals.contains(c.c1)) {
                    cereals.add(c.c2);
                }

                cereals.add(c.c1);
            }

            results[i] = cereals.size();
        }

        PrintWriter out = new PrintWriter(new File("cereal.out"));

        for (int i = 1; i <= n; i++) {
            System.out.println(results[i]);
            out.println(results[i]);
        }

        out.close();
    }

    static class Cow {
        int c1, c2;

        Cow (int c1, int c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }
}


