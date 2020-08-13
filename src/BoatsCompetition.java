// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class BoatsCompetition {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int tt = nextInt();

        for (int t = 0; t < tt; t++) {
            int n = nextInt();
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = nextInt();
            }

            int result = 0;

            for (int i = 1; i <= 100; i++) {

                HashMap<Integer, Integer> hashMap = new HashMap<>();
                int curVal = 0;

                for (int j = 0; j < n; j++) {
                    if (hashMap.containsKey(i - weights[j]) &&
                            hashMap.get(i - weights[j]) > 0) {
                        curVal++;
                        hashMap.put(i - weights[j], hashMap.get(i - weights[j]) - 1);
                    }

                    else {
                        hashMap.put(weights[j], hashMap.getOrDefault(weights[j], 0) + 1);
                    }
                }

                result = Math.max(result, curVal);
            }

            out.println(result);
        }

        out.close();
    }
}


