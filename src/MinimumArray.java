// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class MinimumArray {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        TreeMap<Integer, Integer> numValues = new TreeMap<>();

        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt() % n;
            if (a[i] == 0) a[i] = n;
        }

        for (int i = 0; i < n; i++) {
            b[i] = nextInt() % n;
            if (numValues.containsKey(b[i])) numValues.put(b[i], numValues.get(b[i]) + 1);
            else numValues.put(b[i], 1);
        }

        for (int i = 0; i < n; i++) {
            int to_add = n - a[i];

            if (numValues.ceilingKey(to_add) != null) {
                int key_ciel = numValues.ceilingKey(to_add);

                out.print((a[i] + key_ciel) % n);
                int num = numValues.get(key_ciel) - 1;
                if (num == 0) numValues.remove(key_ciel);
                else numValues.put(key_ciel, num);
            }

            else {
                int key_floor = numValues.ceilingKey(0);

                out.print((a[i] + key_floor) % n);
                int num = numValues.get(key_floor) - 1;
                if (num == 0) numValues.remove(key_floor);
                else numValues.put(key_floor, num);
            }

            if (i != n-1) {
                out.print(" ");
            }
        }

        out.close();
    }
}


