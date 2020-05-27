// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class rental {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("rental.in")));

        int n = nextInt();
        int m = nextInt();
        int r = nextInt();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            Cow c = new Cow(nextInt());
            cows[i] = c;
        }

        Arrays.sort(cows);

        Shop[] shops = new Shop[m];

        for (int i = 0; i < m; i++) {
            Shop shop = new Shop(nextInt(), nextInt());

            shops[i] = shop;
        }

        Arrays.sort(shops);

        Integer[] rentals = new Integer[r];

        for (int i = 0; i < r; i++) {
            rentals[i] = nextInt();
        }

        Arrays.sort(rentals, Collections.reverseOrder());

        long[] rentalPrice = new long[r+1];

        for (int i = 0; i < r+1; i++) {
            if (i == 0) {
                rentalPrice[i] = 0;
            }
            else {
                rentalPrice[i] = rentals[i-1] + rentalPrice[i-1];
            }
        }

        long result = 0;

        int shopIndex = 0;

        long gallonsPrice = 0;

        for (int i = -1; i < n; i++) {

            // i + 1 is how much selling milk
            // n - i - 1 is how much renting

            if (i == -1) {
                // this is the case where all are being rented

                int index = Math.min(n - i - 1, r);
                result = Math.max(result, rentalPrice[index]);
            }

            else {
                Cow c = cows[i];

                int gallonsToSell = c.milkProduction;

                // calculate the milk selling price
                while (gallonsToSell > 0 && shopIndex < m) {
                    // need to sell all of it
                    if (shops[shopIndex].gallons >= gallonsToSell) {
                        gallonsPrice+=gallonsToSell*shops[shopIndex].price;
                        shops[shopIndex].gallons -= gallonsToSell;
                        gallonsToSell = 0;
                    }

                    else {
                        gallonsPrice+=shops[shopIndex].gallons*shops[shopIndex].price;
                        gallonsToSell -= shops[shopIndex].gallons;
                        shopIndex++;
                    }
                }

                int index = Math.min(n - i - 1, r);
                result = Math.max(result, gallonsPrice+rentalPrice[index]);
            }
        }

        PrintWriter out = new PrintWriter(new File("rental.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int milkProduction;

        Cow (int milkProduction) {
            this.milkProduction = milkProduction;
        }

        @Override
        public int compareTo(Cow o) {
            return o.milkProduction - this.milkProduction;
        }
    }

    static class Shop implements Comparable<Shop> {
        int gallons, price;

        Shop (int gallons, int price) {
            this.gallons = gallons;
            this.price = price;
        }


        @Override
        public int compareTo(Shop o) {
            return o.price - this.price;
        }
    }
}


