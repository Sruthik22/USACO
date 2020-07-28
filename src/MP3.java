// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class MP3 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int I = nextInt();

        TreeMap<Integer, Integer> c = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int num = nextInt();

            if (c.containsKey(num)) {
                c.put(num, c.get(num) + 1);
            }

            else c.put(num, 1);
        }

        ArrayList<Integer> arr = new ArrayList<>();

        for (Map.Entry<Integer, Integer> vals: c.entrySet()) {
            arr.add(vals.getValue());
        }

        int K = 8 * I / n;

        int k = 1;

        for (int i = 0; i < K; i++) {
            k *= 2;

            if (k >= arr.size()) {
                out.println(0);
                out.close();
                return;
            }
        }

        // now since we can only have k distinct values, we have a sliding window of size k

        int sum_k = 0;

        for (int i = 0; i < k; i++) {
            sum_k += arr.get(i);
        }

        int result = sum_k;

        for (int i = k; i < arr.size(); i++) {
            int to_remove = arr.get(i - k);
            int to_add = arr.get(i);

            sum_k -= to_remove;
            sum_k += to_add;

            result = Math.max(result, sum_k);
        }

        out.println(n - result);

        out.close();
    }
}


