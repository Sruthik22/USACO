// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class AzamonWebServices {

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
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            String s1 = next();
            String s2 = next();

            char[] s1_orig = s1.toCharArray();
            char[] s1_sorted = s1_orig.clone();
            Arrays.sort(s1_sorted);

            for (int j = 0; j < s1.length(); j++) {
                if (s1_orig[j] == s1_sorted[j]) continue;

                // otherwise must be less, just get the least value after this value and substitute

                for (int k = s1.length()-1; k > j; k--) {
                    if (s1_orig[k] == s1_sorted[j]) {
                        s1_orig[k] = s1_orig[j];
                        s1_orig[j] = s1_sorted[j];
                        break;
                    }
                }
                break;
            }

            s1 = String.valueOf(s1_orig);

            if (isSmaller(s1, s2)) {
                out.println(s1);
            }

            else {
                out.println("---");
            }
        }

        out.close();
    }

    static boolean isSmaller(String s1, String s2) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) - 'A' < s2.charAt(i) - 'A') return true;
            if (s1.charAt(i) - 'A' > s2.charAt(i) - 'A') return false;
        }

        return s1.length() < s2.length();
    }
}


