// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class DistinctCharactersQueries {

    static StreamTokenizer in;
    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int M = (int) Math.pow(2, 17);
    static int[][] segmentTree = new int[2*M][27];
    static int[] result;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String s = next();

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            updateSegment(M - 1 + i, arr[i] - 'a', 26);
        }

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            int code = nextInt();

            if (code == 1) {
                int orig_pos = nextInt()-1;

                char orig_val = arr[orig_pos];
                char new_val = next().charAt(0);

                if (orig_val != new_val) {
                    updateSegment(M - 1 + orig_pos, new_val - 'a', orig_val - 'a');
                    arr[orig_pos] = new_val;
                }
            }

            else {
                int l = nextInt() - 1;
                int r = nextInt() - 1;

                result = new int[26];

                queryRange(0, 0, M-1, l, r);

                int res = 0;

                for (int j = 0; j < 26; j++) {
                    if (result[j] > 0) res++;
                }

                out.println(res);
            }
        }

        out.close();
    }

    static void updateSegment(int position, int newChar, int oldChar) {

        segmentTree[position][newChar]++;
        segmentTree[position][oldChar]--;

        if (position != 0)  updateSegment((position-1)/2, newChar, oldChar);
    }

    static void queryRange(int position, int nodeLowBound, int nodeHighBound, int l, int r) {
        if (nodeLowBound > nodeHighBound || nodeLowBound > r || nodeHighBound < l) return;
        if (l <= nodeLowBound && nodeHighBound <= r) {
            for (int i = 0; i < 26; i++) {
                result[i] += segmentTree[position][i];
            }
            return;
        }

        queryRange(position * 2 + 1, nodeLowBound, (nodeHighBound+nodeLowBound) / 2, l, r);
        queryRange(position * 2 + 2, (nodeHighBound+nodeLowBound) / 2 + 1, nodeHighBound, l, r);
    }
}