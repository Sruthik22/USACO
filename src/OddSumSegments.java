// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class OddSumSegments {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int q = nextInt();

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < q; i++) {
            int n = nextInt();
            int k = nextInt();

            int sum = 0;

            int[] ans = new int[k];

            ArrayList<Integer> odds = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int next = nextInt();

                if (next % 2 == 1) {
                    odds.add(j);
                }
            }

            if (odds.size() >= k && (odds.size() - k) % 2 == 0) {
                out.println("YES");

                for (int j = 0; j < k-1; j++) {
                    out.print(odds.get(j+1) + " ");
                }

                out.println(n);
            }

            else {
                out.println("NO");
            }
        }

        out.close();
    }
}


