// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class socdist2 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("socdist2.in")));

        int n = nextInt();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int a = nextInt();
            int b = nextInt();

            cows[i] = new Cow(a, b);
        }

        Arrays.sort(cows);

        int distance = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            Cow c = cows[i];
            Cow c2 = cows[i+1];

            if (c.infection != c2.infection) {
                distance = Math.min(distance, c2.x - c.x);
            }
        }

        distance--;

        int result = 0;
        int position = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            Cow c1 = cows[i];

            if (c1.infection == 1) {
                if (c1.x <= position) {
                    position = c1.x + distance;
                    continue;
                }
                result++;
                position = c1.x + distance;
            }
        }


        PrintWriter out = new PrintWriter(new File("socdist2.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int x, infection;

        Cow (int x, int infection) {
            this.x = x;
            this.infection = infection;
        }

        @Override
        public int compareTo(Cow o) {
            return this.x - o.x;
        }
    }
  
 /*
 ANALYSIS
 
 */
}


