// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class LongestKGoodSegment {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        int[] ks = new int[n];

        for (int i = 0; i < n; i++) {
            ks[i] = nextInt();
        }

        int lp = 0;
        int rp = 0;
        HashMap<Integer, Integer> songs = new HashMap<>();

        int result = 0;
        int longLP = 0;
        int longRP = 0;

        while (lp < n) {
            while (rp < n && (songs.containsKey(ks[rp]) || songs.size() < k)) {
                if (songs.containsKey(ks[rp])) {
                    songs.put(ks[rp], songs.get(ks[rp]) + 1);
                }
                else {
                    songs.put(ks[rp], 1);
                }
                rp++;
            }

            if (rp - lp > result) {
                result = rp-lp;
                longLP = lp;
                longRP = rp;
            }

            if (songs.get(ks[lp]) == 1) songs.remove(ks[lp]);
            else songs.put(ks[lp], songs.get(ks[lp]) - 1);
            lp++;
        }

        out.println((longLP+1) + " " + (longRP));

        out.close();
    }
}


