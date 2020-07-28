// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PrefixFlipEasy {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();

            char[] bin1 = in.next().toCharArray();
            char[] bin2 = in.next().toCharArray();

            ArrayList<Integer> moves = new ArrayList<>();

            for (int j = n-1; j >= 1; j--) {
                if (bin1[j] == bin2[j]) continue;

                if (bin1[0] != bin1[j]) {
                    moves.add(0);
                    bin1[0] = bin1[j];
                }

                // now need to flip and rotate

                moves.add(j);
                bin1 = flip(bin1, j+1);
                bin1 = rotate(bin1, j+1);
            }

            if (bin1[0] != bin2[0]) moves.add(0);

            out.print(moves.size() + " ");

            for (int j = 0; j < moves.size(); j++) {
                out.print(moves.get(j) + 1);

                if (j != moves.size()-1) {
                    out.print(" ");
                }
            }
            out.println();
        }

        out.close();
    }

    static char[] flip(char[] bin, int j) {
        for (int i = 0; i < j; i++) {
            if (bin[i] == '1') {
                bin[i] = '0';
            }

            else {
                bin[i] = '1';
            }
        }

        return bin;
    }

    static char[] rotate(char[] bin, int j) {
        char[] copy = bin.clone();

        for (int i = 0; i < j; i++) {
            bin[i] = copy[j - i - 1];
        }

        return bin;
    }
}


