// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class GiftsFixing {

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

            Gift[] gifts = new Gift[n];

            int minA = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                gifts[i] = new Gift(nextInt(), 0);
                minA = Math.min(minA, gifts[i].a);
            }

            int minB = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                gifts[i].b = nextInt();
                minB = Math.min(minB, gifts[i].b);
            }

            long result = 0;

            for (int i = 0; i < n; i++) {
                Gift cur = gifts[i];
                long together = Math.min(cur.a - minA, cur.b - minB);
                result += together;
                result += cur.a - together - minA;
                result += cur.b - together - minB;
            }

            out.println(result);
        }

        out.close();
    }

    static class Gift {
        int a, b;

        Gift(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}


