// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowjog {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cowjog.in"));
        int n = in.nextInt();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(in);
        }

        in.close();

        TreeSet<Cow> cowGroups = new TreeSet<>(
                new Comparator<Cow>() {
                    @Override
                    public int compare(Cow o1, Cow o2) {
                        return o1.pos - o2.pos;
                    }
                }
        );

        int result = 0;

        for (int i = n-1; i >= 0; i--) {
            Cow currentCow = cows[i];

            if (cowGroups.isEmpty()) {
                cowGroups.add(currentCow);
            }

            else {
                // compare with cowGroups
                Cow comparison = cowGroups.first();

                if (comparison.speed >= currentCow.speed) {
                    cowGroups.add(currentCow);
                }
            }
        }

        result = cowGroups.size();

        PrintWriter out = new PrintWriter(new File("cowjog.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static class Cow {
        int pos;
        int speed;

        public Cow(Scanner in) {
            this.pos = in.nextInt();
            this.speed = in.nextInt();
        }
    }
}


