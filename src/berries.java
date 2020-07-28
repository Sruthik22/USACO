// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class berries {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static Berry[] berries;

    static int mod = 1;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("berries.in")));

        int n = nextInt();
        int k = nextInt();

        berries = new Berry[n];

        int max_berries = 0;

        for (int i = 0; i < n; i++) {
            berries[i] = new Berry(nextInt());

            max_berries = Math.max(max_berries, berries[i].val);
        }

        int result = 0;

        for (int i = 1; i < max_berries; i++) {

            int num_baskets = number_of_baskets(i);

            if (num_baskets >= k) result = i * (k/2);

            else {
                if (num_baskets >= k/2) {
                    int more = k - num_baskets;
                    int have = k/2 - more;

                    mod = i;

                    Arrays.sort(berries);

                    int left_add = 0;

                    for (int j = 0; j < Math.min(more, berries.length); j++) {
                        left_add += berries[j].val % i;
                    }

                    int cur = have * i + left_add;

                    result = Math.max(result, cur);
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("berries.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static class Berry implements Comparable<Berry> {
        int val;

        Berry(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Berry o) {
            return - this.val % mod + o.val % mod;
        }
    }

    static int number_of_baskets(int berries_per) {
        int total = 0;
        for (Berry i : berries) {
            total += i.val / berries_per;
        }

        return total;
    }
}


