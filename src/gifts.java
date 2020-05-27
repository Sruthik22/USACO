// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class gifts {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("gifts.in"));
        int n = in.nextInt(); // number of gifts
        int b = in.nextInt(); // budget

        Gift[] gifts = new Gift[n];

        for (int i = 0; i < n; i++) {
            gifts[i] = new Gift(in);
        }

        in.close();

        Arrays.sort(gifts);

        int moneyLeft = b;
        int maxPrice = 0;
        int result = 0;

        for (Gift i: gifts) {
            if (moneyLeft - i.c >= 0) {
                moneyLeft -= i.c;
                result++;
                maxPrice = Math.max(i.p, maxPrice);
            }

            else break;
        }

        if (moneyLeft + maxPrice / 2 >= gifts[result].c) {
            result++;
        }

        else {
            for (int i = result; i < n; i++) {
                if (gifts[i].d + gifts[i].s <= moneyLeft) {
                    result++;
                    break;
                }
            }
        }


        PrintWriter out = new PrintWriter(new File("gifts.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Gift implements Comparable {

        int p, s, c, d;

        private Gift(Scanner in) {
            p = in.nextInt();
            s = in.nextInt();
            d = p/2;
            c = p+s;
        }

        @Override
        public int compareTo(Object o) {
            Gift comparingGift = (Gift) o;
            return this.c - comparingGift.c;
        }
    }
}


