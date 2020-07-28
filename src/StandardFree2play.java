// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class StandardFree2play {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            int h = nextInt();
            int n = nextInt();

            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = nextInt();
            }

            int result = 0;
            int indexArr = 1;

            while (h > 1) {
                if (indexArr == n) break;
                if (arr[indexArr] == h-1) {
                    if (h <= 2) break;

                    if (indexArr+1 == n) {
                        result++;
                        break;
                    }

                    if (arr[indexArr+1] == h-2) {
                        h -= 2;
                        indexArr += 2;
                    }

                    else {
                        result++;
                        h -= 2;
                        indexArr++;
                    }
                }

                else {
                    h = arr[indexArr] + 1;
                }
            }

            out.println(result);
        }

        out.close();
    }
}


