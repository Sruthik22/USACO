// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SportMafia {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();
        int k = nextInt();

        int result = (int) (Math.round(n + 1.5 - Math.sqrt(2 * (n + k) + 2.75)));
        PrintWriter out = new PrintWriter(new File("SportMafia.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


