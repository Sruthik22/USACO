// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class SubmarineRybinsk {

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

        int n = nextInt();

        int[] nums = new int[n];
        HashMap<Integer, Integer> lengths = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int next = nextInt();
            int length = Integer.toString(next).length();

            if (lengths.containsKey(length)) lengths.put(length, lengths.get(length) + 1);
            else lengths.put(length, 1);

            nums[i] = next;
        }

        long result = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            String valString = Integer.toString(val);
            int curLength = valString.length();

            for (Map.Entry<Integer, Integer> mapElement : lengths.entrySet()) {
                int count = mapElement.getValue();
                int len = mapElement.getKey();

                result += count * (contributeFirst(valString, curLength, len) + contributeSecond(valString, curLength, len)) % 998244353;
            }
        }

        out.println(result % 998244353);

        out.close();
    }

    static long contributeFirst(String value, int curLength, int otherLength) {
        StringBuilder sb = new StringBuilder();

        if (curLength >= otherLength) {
            for (int i = 0; i < curLength - otherLength + 1; i++) {
                sb.append(value.charAt(i));
            }

            int index = 1;

            for (int i = 0; i < 2 * otherLength - 1; i++) {
                if (i % 2 == 0) sb.append(0);
                else {
                    sb.append(value.charAt(curLength - otherLength + index));
                    index++;
                }
            }
        }

        else {

            int index = 0;

            for (int i = 0; i < 2 * curLength; i++) {
                if (i % 2 == 0) {
                    sb.append(value.charAt(index));
                    index++;
                }
                else sb.append(0);
            }
        }

        BigInteger number = new BigInteger(sb.toString());
        return number.mod(BigInteger.valueOf(998244353)).intValue();
    }
    static long contributeSecond(String value, int curLength, int otherLength) {
        StringBuilder sb = new StringBuilder();

        if (curLength > otherLength) {
            for (int i = 0; i < curLength - otherLength; i++) {
                sb.append(value.charAt(i));
            }

            int index = 0;

            for (int i = 0; i < 2 * otherLength; i++) {
                if (i % 2 == 0) sb.append(0);
                else {
                    sb.append(value.charAt(curLength - otherLength + index));
                    index++;
                }
            }
        }

        else {

            int index = 0;

            for (int i = 0; i < 2 * curLength - 1; i++) {
                if (i % 2 == 0) {
                    sb.append(value.charAt(index));
                    index++;
                }
                else sb.append(0);
            }
        }

        BigInteger number = new BigInteger(sb.toString());
        return number.mod(BigInteger.valueOf(998244353)).intValue();
    }
}


