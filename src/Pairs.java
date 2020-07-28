// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Pairs {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static ArrayList<Pair> pairs;
    static HashMap<Integer, Integer> nums;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int m = nextInt();

        int secondSelected = -1;
        boolean another = false;
        int time = 0;

        boolean works = true;

        pairs = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            pairs.add(new Pair(nextInt(), nextInt()));
        }

        for (int j = 0; j < 2; j++) {

            int firstSelected = pairs.get(0).pair_nums[j];

            for (int i = 0; i < m; i++) {
                if (pairs.get(i).contains(firstSelected) || pairs.get(i).contains(secondSelected)) {
                    works = true;
                    continue;
                }

                if (secondSelected == -1) {
                    secondSelected = pairs.get(i).pair_nums[0];
                    time = i;
                }

                else {
                    if (another) {
                        // tried both, doesn't work

                        secondSelected = -1;
                        another = false;
                        works = false;
                        break;
                    }

                    else {
                        i = time;
                        secondSelected = pairs.get(i).pair_nums[1];
                        another = true;
                    }
                }
            }

            if (works) break;
        }

        if (!works) {
            out.println("NO");
        }

        else {
            out.println("YES");
        }

        out.close();
    }

    static class Pair {
        int[] pair_nums = new int[2];

        Pair(int x, int y) {
            pair_nums[0] = x;
            pair_nums[1] = y;
        }

        private boolean contains(int index) {
            return this.pair_nums[0] == index || this.pair_nums[1] == index;
        }
    }
}


