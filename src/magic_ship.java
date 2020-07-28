// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class magic_ship {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int x1, y1, x2, y2;

    static int s;

    static int[] windX;
    static int[] windY;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        x1 = nextInt();
        y1 = nextInt();
        x2 = nextInt();
        y2 = nextInt();

        s = nextInt();

        String wind_directions = next();

        windX = new int[s];
        windY = new int[s];

        int lastX = 0;
        int lastY = 0;

        for (int i = 0; i < s; i++) {
            char c = wind_directions.charAt(i);

            if (c == 'U') windY[i] = 1 + lastY;
            else if (c == 'D') windY[i] = lastY - 1;
            else if (c == 'L') windX[i] = lastX - 1;
            else windX[i] = lastX + 1;

            lastX = windX[i];
            lastY = windY[i];
        }

        long low = -1; // nothing is desired this point and lower
        long high = (long) 2E18; // at this point and higher everything is always true

        while (high - low > 1) {
            long mid = (low + high)/2;
            if (check(mid)) high = mid;
            else {
                low = mid;
            }
        }

        if(high > 5e17) high = -1;

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        out.println(high);
        out.close();
    }

    private static boolean check(long days) {

        long cur_x = x1;
        long cur_y = y1;

        long num_repeat = days / s;

        cur_x += windX[s-1] * num_repeat;
        cur_y += windY[s-1] * num_repeat;

        int remainder = (int) (days - s * num_repeat);

        if (remainder != 0) {
            cur_x += windX[remainder - 1];
            cur_y += windY[remainder - 1];
        }

        return (Math.abs(y2 - cur_y) + Math.abs(x2 - cur_x) <= days);
    }
}


