// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SegmentIntersections {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int j = 0; j < t; j++) {
            int n = nextInt();
            int k = nextInt();

            long l1 = nextInt();
            long r1 = nextInt();

            long l2 = nextInt();
            long r2 = nextInt();

            if (r2 < l1 || r1 < l2) {
                // not intersecting

                long initialCost = 0;
                long efficiency = 0;

                if (r2 < l1) {
                    initialCost += l1 - r2;
                }

                else {
                    initialCost += l2 - r1;
                }

                efficiency += Math.max(r1, r2) - Math.min(l1, l2);

                long more = k - (efficiency);

                if (initialCost + efficiency >= 2 * efficiency) {
                    long maxCost = 2 * more + efficiency + initialCost;
                    out.println(maxCost);
                }

                else {
                    long maxDist = efficiency * (n-1);

                    if (maxDist <= more) {
                        long totalCost = (initialCost + efficiency) * n + (more - maxDist) * 2;
                        out.println(totalCost);
                    }

                    else {
                        // we have enough

                        long full = more / efficiency;
                        long left = more % efficiency;

                        full++;

                        long totalCost = 0;

                        if (left * 2 <= initialCost + left) {
                            totalCost += full * (initialCost + efficiency);
                            totalCost += left * 2;

                            out.println(totalCost);
                        }

                        else {
                            totalCost += full * (initialCost + efficiency);
                            totalCost += initialCost + left;

                            out.println(totalCost);
                        }
                    }
                }
            }

            else {
                // inside each other

                long already = Math.max(r1, r2) - Math.max(l1, l2);
                already *= n;

                long step_pos = Math.abs(l1 - l2) + Math.abs(r2 - r1); // most efficient
                step_pos *= n; // total possible at most efficient

                if (already >= k) {
                    out.println(0);
                    continue;
                }

                k -= already;

                if (step_pos >= k) {
                    out.println(k);
                }

                else {
                    long remaining = k - step_pos;
                    step_pos += 2 * remaining;
                    out.println(step_pos);
                }
            }
        }

        out.close();
    }
}


