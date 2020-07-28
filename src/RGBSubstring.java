// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class RGBSubstring {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int n;
    static int k;
    static char[] charList;
    static int[] initial_counts;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            n = nextInt();
            k = nextInt();

            String string = next();

            charList = string.toCharArray();

            // initial counts for RGB, GBR, BRG

            initial_counts = new int[3];
            initialization();

            int result = Integer.MAX_VALUE;

            int cur = Math.min(Math.min(initial_counts[0], initial_counts[1]), initial_counts[2]);
            result = Math.min(result, cur);

            for (int j = 1; j <= n - k; j++) {
                int indexToRemove = j-1;
                int indexToAdd = j+k-1;

                edit(indexToRemove, indexToAdd);

                cur = Math.min(Math.min(initial_counts[0], initial_counts[1]), initial_counts[2]);
                result = Math.min(result, cur);
            }

            out.println(result);
        }

        out.close();
    }

    static void remove(int indexToRemove) {
        char remove = charList[indexToRemove];

        int mod = indexToRemove % 3;

        if (remove == 'R') {
            if (mod == 0) {
                initial_counts[1]--;
                initial_counts[2]--;
            }

            else if (mod == 1) {
                initial_counts[0]--;
                initial_counts[1]--;
            }

            else {
                initial_counts[0]--;
                initial_counts[2]--;
            }
        }

        else if (remove == 'G') {
            if (mod == 0) {
                initial_counts[0]--;
                initial_counts[2]--;
            }

            else if (mod == 1) {
                initial_counts[1]--;
                initial_counts[2]--;
            }

            else {
                initial_counts[0]--;
                initial_counts[1]--;
            }
        }

        else {
            if (mod == 0) {
                initial_counts[0]--;
                initial_counts[1]--;
            }

            else if (mod == 1) {
                initial_counts[0]--;
                initial_counts[2]--;
            }

            else {
                initial_counts[1]--;
                initial_counts[2]--;
            }
        }
    }

    static void add(int indexToAdd) {
        char add = charList[indexToAdd];

        int mod = indexToAdd % 3;

        if (mod == 0) {
            if (add == 'R') {
                initial_counts[1]++;
                initial_counts[2]++;
            }

            else if (add == 'G') {
                initial_counts[0]++;
                initial_counts[2]++;
            }

            else {
                initial_counts[0]++;
                initial_counts[1]++;
            }
        }

        else if (mod == 1) {
            if (add == 'R') {
                initial_counts[0]++;
                initial_counts[1]++;
            }

            else if (add == 'G') {
                initial_counts[1]++;
                initial_counts[2]++;
            }

            else {
                initial_counts[0]++;
                initial_counts[2]++;
            }
        }

        else {
            if (add == 'R') {
                initial_counts[0]++;
                initial_counts[2]++;
            }

            else if (add == 'G') {
                initial_counts[0]++;
                initial_counts[1]++;
            }

            else {
                initial_counts[1]++;
                initial_counts[2]++;
            }
        }
    }

    static void edit(int indexToRemove, int indexToAdd) {
        remove(indexToRemove);
        add(indexToAdd);
    }

    static void initialization() {

        for (int j = 0; j < k; j++) {
            if (charList[j] == 'R') {
                if (j % 3 == 0) {
                    initial_counts[1]++;
                    initial_counts[2]++;
                }

                else if (j % 3 == 1){
                    initial_counts[0]++;
                    initial_counts[1]++;
                }

                else {
                    initial_counts[0]++;
                    initial_counts[2]++;
                }
            }

            else if (charList[j] == 'G') {
                if (j % 3 == 0) {
                    initial_counts[0]++;
                    initial_counts[2]++;
                }

                else if (j % 3 == 1){
                    initial_counts[1]++;
                    initial_counts[2]++;
                }

                else {
                    initial_counts[0]++;
                    initial_counts[1]++;
                }
            }

            else {
                if (j % 3 == 0) {
                    initial_counts[0]++;
                    initial_counts[1]++;
                }

                else if (j % 3 == 1){
                    initial_counts[0]++;
                    initial_counts[2]++;
                }

                else {
                    initial_counts[1]++;
                    initial_counts[2]++;
                }
            }
        }
    }
}


