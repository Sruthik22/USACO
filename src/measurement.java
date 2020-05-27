// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class measurement {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("measurement.in"));
        int n = in.nextInt();
        int g = in.nextInt();

        Data[] data = new Data[n];

        TreeMap<Integer, Integer> cows = new TreeMap<>(); // milk production -- set of ids of cows
        HashMap<Integer, Integer> cowsProducts = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int day = in.nextInt();
            int intID = in.nextInt() - 1;
            int change = in.nextInt();

            data[i] = new Data(day, intID, change);
            cowsProducts.put(intID, g);
        }

        cows.put(g, n);

        Arrays.sort(data);

        in.close();

        int result = 0;

        int max = g;

        // the tree map doesn't need to store which cows are in which value
        // just needs to store the amount of cows at each
        // the array anyway tells you what value each cow has

        for (int i = 0; i < n; i++) {
            int prevAmount = cowsProducts.get(data[i].intID);
            int curAmount = prevAmount + data[i].change;
            cowsProducts.put(data[i].intID, curAmount);

            int numOld = cows.get(prevAmount);

            if (numOld == 1) cows.remove(prevAmount);
            else {
                cows.put(prevAmount, numOld-1);
            }

            if (cows.containsKey(curAmount)) cows.put(curAmount, cows.get(curAmount)+1);
            else cows.put(curAmount, 1);

            if (prevAmount < max && curAmount >= max) result++;
            if (prevAmount == max && numOld > 1 && curAmount > max) result++;

            int newTop = cows.lastKey();

            if (prevAmount == max && curAmount < newTop) result++;
            if (prevAmount == max && curAmount == newTop && cows.get(newTop) > 1) result++;

            max = newTop;

        }

        PrintWriter out = new PrintWriter(new File("measurement.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static class Data implements Comparable<Data> {
        int day, intID, change;

        Data(int day, int intID, int change) {
            this.day = day;
            this.intID = intID;
            this.change = change;
        }

        @Override
        public int compareTo(Data o) {
            return day - o.day;
        }
    }
}


