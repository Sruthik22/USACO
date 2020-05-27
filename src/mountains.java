// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class mountains {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("mountains.in"));
        int n = in.nextInt();

        Mountain[] mountains = new Mountain[n];

        for (int i = 0; i < n; i++) {
            mountains[i] = new Mountain(in);
        }

        Arrays.sort(mountains);

        in.close();

        int result = 0;

        int maxX = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (mountains[i].x + mountains[i].y > maxX) {
                maxX = mountains[i].x + mountains[i].y;
                result++;
            }
        }



        PrintWriter out = new PrintWriter(new File("mountains.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static class Mountain implements Comparable<Mountain>{
        int x, y;
        private Mountain(Scanner in) {
            this.x = in.nextInt();
            this.y = in.nextInt();
        }

        @Override
        public int compareTo(Mountain o) {
            if ((this.x - this.y) == (o.x - o.y)) {
                if ((this.x + this.y) < (o.x + o.y)) {
                    return 1;
                }

                return -1;
            }
            return (this.x - this.y) - (o.x - o.y);
        }
    }
  
 /*
 ANALYSIS

 if a point is in another triangles area, then it can not be seen
 each triangle is determined by:
 y = x + (x intercept)
 y = -x + (y intercept)

 4, 6
 equations are:
 y >= 0
 y intercept is 4, 6
 6 - 4 = 2
 so 0, 2
 y <= x + 2
 y intercept is 4, 6
 6 + 4 = 10
 y <= -x + 10

Solution Key Points:

1. The solution needs a way to compare between all of the peaks, rather than doing an equation based on the peak,
which changes and can be sorted in too many different ways

Sort based on beginning of where the base hits the axis and have a variable that holds the so far farthest right on the axis. If the next

mountains start is less than the farthest right then there is a possibility that the peak is within the mountain boundaries
check if the peak is within based on equation on the right, if the point is on the left side then within

x - y is the point where it hits the x axis on the left

x-y >= other x - other y
x + y <= other x + other y

meaning the two mountains are on the same point

occlusions are only counted from 2 compared to the one before

so if the start x is the same check the end x, the larger x should go in the front so that the
second is counted as an occlusions

just had to remember if the maximum x is greater than the currents addition then the peak is within

 */
}


