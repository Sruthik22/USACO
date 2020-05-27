// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class threesum {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("threesum.in")));
        PrintWriter out = new PrintWriter(new File("threesum.out"));

        int n = nextInt();
        int q = nextInt();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }

        long[][] ans = new long[n][n];

        int[] z = new int[2000001]; // the number of each number

        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                int ind = 1000000 - array[i] - array[j];
                if (ind >= 0 && ind <= 2000000) ans[i][j] = z[ind];
                // add the endpoint in now
                z[1000000+array[j]]++;
            }
            for (int j = i+1; j < n; j++) {
                z[1000000+array[j]]--;
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                ans[i][j] += ans[i][j-1]+ans[i+1][j]-ans[i+1][j-1];
            }
        }

        for (int i = 0; i < q; i++) {
            int index1 = nextInt()-1;
            int index2 = nextInt()-1;
            System.out.println("result is " + ans[index1][index2]);
            out.println(ans[index1][index2]);
        }
        out.close();
    }
}


