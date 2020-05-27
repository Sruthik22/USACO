// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class censor {

    static long MOD = 1000000007L;
    static long p = 998244353L;

    static StreamTokenizer in;

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("censor.in")));

        String s = next();
        String substring = next();

        long target = 0;
        for (int i = 0; i < substring.length(); i++) {
            target = hash(target,substring.charAt(i));
        } // found the hash for the target string

        long multiplier = power(p,substring.length(),MOD);


        StringBuilder sb = new StringBuilder();

        StringBuilder lastSubstring = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            sb.append(c);
            lastSubstring.append(c);

            if (i < substring.length()) continue;

            if (lastSubstring.length() > substring.length()) {
                lastSubstring.delete(0, 1);
            }

            if (lastSubstring.toString().equals(substring)) {
                sb.replace(sb.length()-substring.length(), sb.length(), "");
                lastSubstring.delete(0, lastSubstring.length());
                lastSubstring.append(sb.substring(Math.max(0, sb.length()-substring.length()), sb.length()));
            }
        }


        String result = sb.toString();
        PrintWriter out = new PrintWriter(new File("censor.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    public static long hash(long prev, char c) {
        int num = c-'a';
        return (prev*p+num)%MOD;
    }

    public static long power(long x, long y, long m) {
        long ans = 1;
        x %= m;
        while (y > 0) {
            if(y % 2 == 1)
                ans = (ans * x) % m;
            y /= 2;
            x = (x * x) % m;
        }
        return ans;
    }
}


