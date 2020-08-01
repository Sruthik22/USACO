// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Playlist {

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

        int n = nextInt();

        int[] k = new int[n];

        for (int i = 0; i < n; i++) {
            k[i] = nextInt();
        }

        int lp = 0;
        int rp = 0;
        HashSet<Integer> songs = new HashSet<>();

        int result = 0;

        while (lp < n) {
            while (rp < n && !songs.contains(k[rp])) {
                songs.add(k[rp]);
                rp++;
            }

            result = Math.max(result, rp - lp);
            songs.remove(k[lp]);
            lp++;
        }

        out.println(result);

        out.close();
    }
}


