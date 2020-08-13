// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class BinaryStringToSubsequences {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int tt = in.nextInt();

        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();

            TreeSet<Integer> ones = new TreeSet<>();
            TreeSet<Integer> zeroes = new TreeSet<>();

            String s = in.next();

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    zeroes.add(i);
                }

                else {
                    ones.add(i);
                }
            }

            int result = 0;
            int[] groups = new int[n];

            while (!ones.isEmpty() || !zeroes.isEmpty()) {
                result++;

                if (ones.isEmpty()) {
                    for (int i : zeroes) {
                        groups[i] = result;
                        result += 1;
                    }

                    result--;
                    break;
                }

                if (zeroes.isEmpty()) {
                    for (int i : ones) {
                        groups[i] = result;
                        result += 1;
                    }

                    result--;
                    break;
                }

                int min = Math.min(ones.first(), zeroes.first());
                groups[min] = result;

                if (ones.first() == min) {
                    ones.remove(min);
                    int lastIndex = min;
                    while (true) {
                        if (zeroes.ceiling(lastIndex) != null) {
                            lastIndex = zeroes.ceiling(lastIndex);
                            groups[lastIndex] = result;
                            zeroes.remove(lastIndex);
                            if (ones.ceiling(lastIndex) != null) {
                                lastIndex = ones.ceiling(lastIndex);
                                groups[lastIndex] = result;
                                ones.remove(lastIndex);
                            }
                            else break;
                        }

                        else break;
                    }
                }

                else {
                    zeroes.remove(min);
                    int lastIndex = min;
                    while (true) {
                        if (ones.ceiling(lastIndex) != null) {
                            lastIndex = ones.ceiling(lastIndex);
                            groups[lastIndex] = result;
                            ones.remove(lastIndex);
                            if (zeroes.ceiling(lastIndex) != null) {
                                lastIndex = zeroes.ceiling(lastIndex);
                                groups[lastIndex] = result;
                                zeroes.remove(lastIndex);
                            }
                            else break;
                        }

                        else break;
                    }
                }
            }

            out.println(result);

            for (int i = 0; i < n; i++) {
                out.print(groups[i] + " ");
            }
            out.println();
        }

        out.close();
    }
}


