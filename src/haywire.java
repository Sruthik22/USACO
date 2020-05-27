// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class haywire {

    private static int n;
    private static int[][] nbr;
    //row#1: which cows friends
    //col#1: which friend is it

    private static int best = Integer.MAX_VALUE;

    static int[] cow_pos; // the indexes of each of the cows


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("haywire.in"));
        n = in.nextInt();
        nbr = new int[n+1][3];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                nbr[i][j] = in.nextInt();
            }
        }

        in.close();

        cow_pos = new int[n+1];

        solve(0,0,0,0);

        PrintWriter out = new PrintWriter(new File("haywire.out"));
        System.out.println(best);
        out.println(best);
        out.close();
    }

    static void solve(int cows_so_far, int cost_so_far, int pending_links, int pending_link_cost)
    {
        if (cows_so_far==n) {
            best = Math.min(best, cost_so_far);
            return;
        }

        /* Prune search if no hope of beating the best solution found so far... */
        if (cost_so_far + pending_link_cost >= best) return;

        cows_so_far++;

        for (int i=1; i<=n; i++)
            if (cow_pos[i] == 0) {

                cow_pos[i] = cows_so_far;

                int added_cost = 0, new_pending_links = 3;
                for (int j=0; j<3; j++)
                    if (cow_pos[nbr[i][j]] != 0) {
                        added_cost += cow_pos[i] - cow_pos[nbr[i][j]];
                        new_pending_links -= 2;
                    }

                solve(cows_so_far,
                        cost_so_far + added_cost,
                        pending_links + new_pending_links,
                        pending_link_cost + (pending_links + new_pending_links) - added_cost);

                cow_pos[i] = 0;
            }

    }


  
 /*
 ANALYSIS

 6
 625
 134
 426
 532
 461
 153

 Lined up

brute force, every single combination

place cow 1 as first
and continue to n

trying all possibilities is last space, and every space previously

12!

improved algorithm:
the cows to add need to finish a wire

try all first cows (12)

3 after the first

12 * 3 * 4 * 5 * 6 * 7

 */
}


