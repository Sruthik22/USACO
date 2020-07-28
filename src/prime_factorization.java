// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class prime_factorization {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                    divisors.add(i);
                    n /= i;
            }
        }

        if (divisors.isEmpty() && n != 0 && n!= 1) {
            out.print(n);
        }

        for (int i = 0; i < divisors.size(); i++) {
            out.print(divisors.get(i));

            if (i != divisors.size()-1) {
                out.print("*");
            }
        }

        out.close();
    }
}


