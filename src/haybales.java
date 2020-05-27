// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class haybales {

    public static void main(String[] args) throws Exception {
        BufferedReader stdin = new BufferedReader(new FileReader("haybales.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        int n = Integer.parseInt(tok.nextToken());
        int q = Integer.parseInt(tok.nextToken());

        int[] locations = new int[n];

        tok = new StringTokenizer(stdin.readLine());
        for (int i = 0; i < n; i++) {
            int loc = Integer.parseInt(tok.nextToken());
            locations[i] = loc;
        }

        Arrays.sort(locations);

        PrintWriter out = new PrintWriter(new File("haybales.out"));

        for (int i = 0; i < q; i++) {
            tok = new StringTokenizer(stdin.readLine());
            int sIndex = Integer.parseInt(tok.nextToken());
            int eIndex = Integer.parseInt(tok.nextToken());

            int sBinSearch = Arrays.binarySearch(locations, sIndex);

            if (sBinSearch < 0) {
                sBinSearch = -(sBinSearch+1);
            }

            int eBinSearch = Arrays.binarySearch(locations, eIndex);

            if (eBinSearch < 0) {
                eBinSearch = -(eBinSearch+1) - 1;
            }

            System.out.println(eBinSearch - sBinSearch + 1);
            out.println(eBinSearch - sBinSearch + 1);
        }

        out.close();
        stdin.close();
    }
}


