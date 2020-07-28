// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class AlmostAllDivisors {

    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        long t = nextLong();

        for (int i = 0; i < t; i++) {
            long n = nextLong();

            long[] divisors = new long[(int) n];

            long result = -1;

            for (int j = 0; j < n; j++) {
                divisors[j] = nextLong();
            }

            Arrays.sort(divisors);

            boolean works = true;

            for (int j = 0; j < n/2; j++) {
                long curRes = divisors[j] * divisors[(int) n - j - 1];

                if (result == -1) result = curRes;
                else {
                    if (result != curRes) {
                        works = false;
                        break;
                    }
                }
            }

            if (n % 2 == 1 && works) {
                if (divisors[(int)n/2]*divisors[(int)n/2] == result || result == -1) {
                    result = divisors[(int)n/2]*divisors[(int)n/2];
                }

                else works = false;
            }

            if (works) {
                int index = 0;
                for (long j = 2; j <= Math.sqrt(result); j++) {
                    if (result % j == 0) {
                        if (j != divisors[index]) {
                            works = false;
                            break;
                        }

                        index++;
                    }
                }
            }

            if (works) {
                out.println(result);
            }

            else {
                out.println(-1);
            }
        }

        out.close();
    }
}


