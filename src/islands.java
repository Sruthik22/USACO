// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class islands {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("islands.in"));
        int n = in.nextInt();

        Region[] unsorted = new Region[n];
        Region[] sorted = new Region[n];

        for (int i = 0; i < n; i++) {
            unsorted[i] = new Region(in, i);
            sorted[i] = unsorted[i];
        }

        in.close();

        Arrays.sort(sorted);

        int result = 1;
        int max_result = 1;

        for (int i = 0; i < n; i++) {
            Region curRegion = sorted[i];

            if (curRegion.i != 0 &&  curRegion.i != n-1) {
                // neither one of the edge cases

                Region regionLeft = unsorted[curRegion.i-1];
                Region regionRight = unsorted[curRegion.i+1];

                if (regionLeft.height == curRegion.height && curRegion.height == regionRight.height) continue;

                if (regionRight.height == curRegion.height && curRegion.i <= n-3 && unsorted[curRegion.i+2].height == curRegion.height) continue;

                if (regionRight.height == curRegion.height && curRegion.i <= n-3) {
                     regionRight = unsorted[curRegion.i+2];
                }

                if (regionLeft.height < curRegion.height && regionRight.height < curRegion.height) {
                    result--;
                }

                else if (regionRight.height > curRegion.height && regionLeft.height > curRegion.height) {
                    result++;
                }
            }

            else if (curRegion.i == 0) {
                // it is the first one, so only the right region will be defined and the left value will be
                // considered to be lower
                Region regionRight = unsorted[curRegion.i+1];

                if (regionRight.height < curRegion.height) {
                    result--;
                }
            }

            else {
                Region regionLeft = unsorted[curRegion.i-1];

                if (regionLeft.height < curRegion.height) {
                    result--;
                }
            }

            max_result = Math.max(result, max_result);

        }

        result = max_result;

        PrintWriter out = new PrintWriter(new File("islands.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Region implements Comparable<Region> {

        int height, i;

        private Region(Scanner in, int i) {
            height = in.nextInt();
            this.i = i;
        }

        @Override
        public int compareTo(Region o) {
            return this.height - o.height;
        }
    }
  
 /*
 ANALYSIS


EXPLANATION OF THE PROBLEM:

Case 1:
 #######
        ########        <-- submerged
                ########
same number of islands and just became a bit smaller
+0

Case 2:
 #######        ########
        ########         <-- submerged
Originally was a single island, but now two separate islands when the middle becomes submerged
+1

Case 3:
        ########        <-- submerged
########        #######

Other two are already wet, and lost the top to lose another island
-1

Case 4:

                ########
        ########        <-- submerged
########
+0

Tied Regions (at the same height):

Make sure to not count results until all regions at a given height are handled
                         ########
        ########|########        <-- submerged
########
+0

If the values are the same then if the left is drowned make the right also drowned, and if the right left is dry
keep the same for the left

For special tiebreaking rules:

left-side as drowned, right-side as dry

Brute Force Solution:
Adding 1 unit of water every time, and then updating an array of which regions are islands or not islands.
Calculating the max number based on this.

O (n^2)

Alternative Solution:

store regions in original order and sorted height order

loop through the height order --> and then to count, make sure to look at the current one being flooded and its
2 neighbors (total 3)

O(n) * O(1) = O(n)

Basically a casework problem

 */
}


