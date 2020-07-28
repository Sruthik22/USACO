// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class maximum_median {

    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static long k;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        n = nextInt();
        k = nextLong();

        arr = new long[n];

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < n; i++) {
            long a = nextLong();

            arr[i] = a;
        }

        Arrays.sort(arr);
        out.println(search());

        out.close();
    }

    // binary searches for the correct answer
    static long search(){
        long pos = 0; long max = (long)2E9;
        for(long a = max; a >= 1; a /= 2){
            while(check(pos+a)) pos += a;
        }
        return pos;
    }

    private static boolean check(long val) {
        long total = 0;
        for (int i = n/2; i < n; i++) {
            total += Math.max(0, val - arr[i]);
        }

        return total <= k;
    }
}


