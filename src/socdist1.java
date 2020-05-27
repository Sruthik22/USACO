// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class socdist1 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("socdist1.in"));

        int n = in.nextInt();

        String s = in.next();

        ArrayList<Integer> gaps = new ArrayList<>();

        int last0 = -1;

        for (int i = 0; i < n; i++) {
            if (last0 == -1) {
                if (s.charAt(i) == '0') last0 = i;
            }

            else {
                if (s.charAt(i) == '1') {
                    gaps.add(i - last0);
                    last0 = -1;
                }
            }
        }

        if (s.charAt(n-1) == '0') {
            gaps.add(n - last0);
        }

        if (gaps.size() >= 2) {
            gaps.add(0, gaps.get(0)+1);
            gaps.add(gaps.size()-1, gaps.get(gaps.size()-1)+1);
        }

        else {
            gaps.add(0, gaps.get(0)+1);
        }

        Collections.sort(gaps);

        int result = Integer.MIN_VALUE;

        if (gaps.size() >= 2) {
            result = (int) Math.ceil((double) gaps.get(gaps.size() - 2)/2);
        }

        int gapSize = gaps.get(gaps.size()-1);

        result = Math.max(result, (int) (Math.floor((double) (gapSize - 2) / 3) + 1));

        PrintWriter out = new PrintWriter(new File("socdist1.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


