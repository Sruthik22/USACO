// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class berries {

    static int mod;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("berries.in"));
        int n = in.nextInt(); // num of berry trees
        int k = in.nextInt(); // num of baskets

        /* the key to the problem: all of the top k/2 should be the same value (think about it, to have the most berries left over, why want any extra in the top k/2"
        * but how to pick the value for the top k/2? There are only 1000 different values! Just loop through all values  */

        ArrayList<Integer> berries = new ArrayList<>();

        int max = 0;

        for (int i = 0; i < n; i++) {
            berries.add(in.nextInt());
            max = Math.max(max, berries.get(i));
        }

        in.close();

        int result = 0;

        for (int b = 1; b <= max; b++) {
            int total = 0; // how many full b's there are

            for (int i = 0; i < n; i++) {
                total += berries.get(i) / b;
            }

            if (total < k/2) {
                break;
            }

            if (total >= k) {
                result = Math.max(result, b * (k/2));
                continue;
            }

            int soFar = (total - k/2)*b;

            mod = b;

            // need to sort the array based on the mods
            berries.sort(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return -(o1 % mod - o2 % mod);
                }
            });

            for (int i = 0; i < n && i < k-total; i++) {
                soFar+=berries.get(i)%b;
            }

            result = Math.max(result, soFar);

        }

        PrintWriter out = new PrintWriter(new File("berries.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int mod(int o, int b) {
        return o%b;
    }




}


