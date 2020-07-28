// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class WalkingRobot {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int b = nextInt();
        int max_a = nextInt();

        int a = max_a;

        int result = 0;

        int[] segments = new int[n];

        for (int i = 0; i < n; i++) {
            int seg_val = nextInt();
            segments[i] = seg_val;
        }

        for (int i = 0; i < n; i++) {
            int seg_val = segments[i];

            if (seg_val == 0) {
                if (a > 0) {
                    a--;
                }

                else if (b > 0) {
                    b--;
                }

                else {
                    break;
                }
            }

            else {
                if (a == max_a) {
                    a--;
                }

                else if (b > 0) {
                    b--;
                    a++;
                    a = Math.min(a, max_a);
                }

                else if (a > 0) {
                    a--;
                }

                else {
                    break;
                }
            }

            result++;
        }

        out.println(result);
        out.close();
    }
}


