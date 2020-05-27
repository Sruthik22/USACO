// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class trapped {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("trapped.in")));

        int n = nextInt();
        int b = nextInt();

        Bale[] bales = new Bale[n];

        for (int i = 0; i < n; i++) {
            int height = nextInt();
            int position = nextInt();

            bales[i] = new Bale(position, height);
        }

        Arrays.sort(bales);

        int result = solve(bales, b, n);

        PrintWriter out = new PrintWriter(new File("trapped.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int solve (Bale[] bales, int bessiePos, int n) {

        // Find Bessie's relative position.
        int index = 0;
        while (index < n && bessiePos > bales[index].pos) index++;

        // Can't do it in this case.
        if (index == 0 || index == n) return -1;

        // Set up initial pointers.
        int low = index-1, high = index, res = Integer.MAX_VALUE;
        int tmpLeft = low-1, tmpRight = high+1;

        // Sweep in both directions.
        while (low >= 0 && high < n) {

            // We are stuck, answer is 0!
            int maxD = bales[high].pos - bales[low].pos;
            if (bales[high].size >= maxD && bales[low].size >= maxD) return 0;

                // One thing we could do is fortify high, update our result and increment high.
            else if (bales[high].size < maxD && bales[low].size >= maxD) {
                res = Math.min(res, maxD-bales[high].size);
                high++;
            }

            // Or here we could fortify low...
            else if (bales[high].size >= maxD && bales[low].size < maxD) {
                res = Math.min(res, maxD-bales[low].size);
                low--;
            }

            // Here both walls can come down...
            else {

                // See where our stopping point is going left.
                int tmpStop = Integer.MAX_VALUE;
                while (tmpLeft >= 0 && bales[tmpLeft].size < (bales[high].pos-bales[tmpLeft].pos)) tmpLeft--;

                // And right.
                while (tmpRight < n && bales[tmpRight].size < (bales[tmpRight].pos-bales[low].pos)) tmpRight++;

                // Can't trap bessie if we let her get this far.
                if (tmpLeft < 0 && tmpRight == n) {
                    if (res == Integer.MAX_VALUE) return -1;
                    return res;
                }

                // Here we fortify to the right.
                if (tmpLeft >= 0)
                    res = Math.min(res, bales[high].pos-bales[tmpLeft].pos-bales[high].size);

                // Here we fortify to the left.
                if (tmpRight < n)
                    res = Math.min(res, bales[tmpRight].pos-bales[low].pos-bales[low].size);

                // We can update both now.
                low--;
                high++;
            }
        }

        // Return accordingly when we get here.
        if (res == Integer.MAX_VALUE) return -1;
        return res;
    }

    static class Bale implements Comparable<Bale> {
        int pos, size;

        Bale(int position, int size) {
            this.pos = position;
            this.size = size;
        }

        @Override
        public int compareTo(Bale o) {
            return this.pos - o.pos;
        }
    }
}


