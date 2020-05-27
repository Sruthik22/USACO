// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class herding {

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
    static int[] cows;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("herding.in")));

        n = nextInt();

        cows = new int[n];

        for (int i = 0; i < n; i++) {
            cows[i] = nextInt();
        }

        Arrays.sort(cows);

        int result = 0;

        int large = Math.max(cows[n-2]-cows[0], cows[n-1]-cows[1]);
        large -= n-2;

        // min case
        int min = solve_min();

        PrintWriter out = new PrintWriter(new File("herding.out"));

        System.out.println(min);
        System.out.println(large);
        out.println(min);
        out.println(large);
        out.close();
    }

    static int solve_min()
    {
        if (cows[n-2]-cows[0] == n-2 && cows[n-1]-cows[n-2]>2) return 2;
        if (cows[n-1]-cows[1] == n-2 && cows[1]-cows[0]>2) return 2;
        int i, j=0, best=0;
        for (i=0; i<n; i++) {
            while (j<n-1 && cows[j+1]-cows[i]<=n-1) j++;
            best = Math.max(best, j-i+1);
        }
        return n-best;
    }
}


