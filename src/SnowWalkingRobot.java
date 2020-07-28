// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SnowWalkingRobot {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            String s = next();

            int left = 0;
            int right = 0;
            int up = 0;
            int down = 0;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);

                if (c == 'L') left++;
                else if (c == 'R') right++;
                else if (c == 'U') up++;
                else if (c == 'D') down++;
            }

            int side = Math.min(left, right);
            int top = Math.min(up, down);

            if (side == 0) {
                top = Math.min(top, 1);
            }

            if (top == 0) {
                side = Math.min(side, 1);
            }

            out.println( 2 * (side + top));

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < top; j++) {
                sb.append('U');
            }

            for (int j = 0; j < side; j++) {
                sb.append('R');
            }

            for (int j = 0; j < top; j++) {
                sb.append('D');
            }

            for (int j = 0; j < side; j++) {
                sb.append('L');
            }

            out.println(sb.toString());
        }

        out.close();
    }
}


