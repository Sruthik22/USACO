// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class learning {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("learning.in"));
        int n = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();

        Cow[] cows = new Cow[n+2];

        for (int i = 0; i < n; i++) {

            Cow c = new Cow(in);

            cows[i] = c;
        }

        cows[n] = new Cow(false, Long.MAX_VALUE);
        cows[n+1] = new Cow (false, Long.MIN_VALUE);

        Arrays.sort(cows);

        in.close();

        long result = 0;

        for (int i = 0; i < cows.length - 1; i++) {

            Cow cow1 = cows[i];
            Cow cow2 = cows[i+1];

            long midValue = (cow1.weight + cow2.weight) / 2;

            if (cow1.spotted) {

                long s = Math.max(cow1.weight + 1, a);
                long e = Math.min(midValue, b);

                if  (e >= s) {
                    result += e - s + 1;
                }
            }

            if (cow2.spotted) {

                long s = Math.max(midValue + 1, a);
                long e = Math.min(cow2.weight, b);

                if (e >= s) {
                    result += e - s + 1;
                }

            }

            if (cow1.weight % 2  == cow2.weight % 2 && !cow1.spotted && cow2.spotted && a <= midValue && midValue <= b) {
                    result++;
            }
        }

        PrintWriter out = new PrintWriter(new File("learning.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {

        boolean spotted;
        long weight;

        public Cow (Scanner in) {
            this.spotted = in.next().charAt(0) == 'S';
            this.weight = in.nextLong();
        }

        public Cow (boolean spotted, Long weight) {
            this.spotted = spotted;
            this.weight = weight;
        }


        @Override
        public int compareTo(Cow o) {
            return (int) (this.weight - o.weight);
        }
    }
  
 /*
 ANALYSIS

 Classifier that agrees with nearest opinion and if 2 of equal distance or case true for spots

 sort the cow data


TESTING:
 1 4 10

 2.5, 6.5

 6

 LOGIC:

 Find the midpoints of all of the data, if 0.5, then doesn't matter

 if whole number then need to mark based on the surrounding numbers, spots or no spots rule

 Calculate based on intervals

 so if the cows start before a, then remove them, except for the one right before

 if the cows end after b, then remove them, except the one right after


 so the midpoint is 6.5 and then go until the next thing unless over b

 so the midpoint is 9 then go until b

 so the midpoint is 2.5

 starts at 3, just act like mid is 3

 the midpoint is 3.5, start from a

{ always go from a --> midpoint
 a --> next number

 always go from midpoint --> b
 number --> b }
 */
}


