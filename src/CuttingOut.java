// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CuttingOut {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static Berry[] berries;

    static int k;
    static int mod = 1;
    static ArrayList<Berry> maxArray;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        k = nextInt();

        HashMap<Integer, Integer> vals = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int next = nextInt();

            if (vals.containsKey(next)) vals.put(next, vals.get(next) + 1);
            else vals.put(next, 1);
        }

        berries = new Berry[vals.size()];

        int num = 0;
        int max_berries = 0;

        for (Map.Entry<Integer, Integer> entry : vals.entrySet()) {
            int nums = entry.getValue();
            int key = entry.getKey();
            berries[num] = new Berry(nums, key);
            max_berries = Math.max(max_berries, nums);
            num++;
        }

        maxArray = new ArrayList<>();

        int low = 0; // always true
        int high = n; // never true

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (number_of_baskets(mid)) low = mid;
            else high = mid;
        }

        if (!number_of_baskets(high)) number_of_baskets(low);

        for (int i = 0; i < k; i++) {
            out.print(maxArray.get(i));

            if (i != k-1) out.print(" ");
        }

        out.close();
    }

    private static class Berry implements Comparable<Berry> {
        int val;
        int key;

        Berry(int val, int key) {
            this.val = val;
            this.key = key;
        }

        @Override
        public String toString() {
            return "" + key;
        }

        @Override
        public int compareTo(Berry o) {
            return - this.val % mod + o.val % mod;
        }
    }

    static boolean number_of_baskets(int berries_per) {
        ArrayList<Berry> curBerries = new ArrayList<>();
        int total = 0;
        for (Berry i : berries) {
            total += i.val / berries_per;
            if (i.val / berries_per > 0) {
                // we need to add the value the number of times
                for (int j = 0; j < i.val / berries_per; j++) {
                    if (curBerries.size() == k) break;
                    curBerries.add(i);
                }
            }

            if (total >= k) {
                maxArray = (ArrayList<Berry>) curBerries.clone();
                return true;
            }
        }

        return false;
    }
}


