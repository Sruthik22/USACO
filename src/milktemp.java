// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class milktemp {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("milktemp.in"));
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();

        ProdChange[] changes = new ProdChange[2*n];

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            changes[2*i] = new ProdChange(a, y-x);
            changes[2*i + 1] = new ProdChange(b+1, z-y);
        }
        in.close();

        Arrays.sort(changes);

        int result = x * n;
        int prod = result; //running total

        for (int i = 0; i < 2 * n; i ++) {
            prod += changes[i].p;

            if (i == 2*n-1 || changes[i+1].t != changes[i].t) {
                result = Math.max(result, prod);
            }
        }


        PrintWriter out = new PrintWriter(new File("milktemp.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class ProdChange implements Comparable<ProdChange> {

        int t, p;
        // t - temperature when change occurs
        // p - production change at temperature


        private ProdChange(int t, int p) {
            this.t = t;
            this.p = p;
        }

        @Override
        public int compareTo(ProdChange o) {
            return this.t-o.t;
        }
    }
  
 /*

ANALYSIS

4 7 9 6 // 4 cows, 7 too cold, 9 just right, 6 too hot
5 8
3 4
13 20
7 10

7, 8, is the best range

But not all of the time is just where the most number of cows being just right

coordinate compression, identify important temperature

We can just loop through the next integer after the range because we are trying to maximise anyway --> still need
to reduce the amount of milk


 */
}


