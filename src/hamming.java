/*
ID: sruthi.2
LANG: JAVA
TASK: hamming
*/

import java.util.*;
import java.io.*;

public class hamming {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int b;
    static int d;

    static ArrayList<Integer> codes;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("hamming.in")));
        n = nextInt();
        b = nextInt();
        d = nextInt();

        codes = new ArrayList<>();

        codes.add(0);

        for (int i = 1; i < Math.pow(2, b); i++) {
            if (works(i)) codes.add(i);
        }

        PrintWriter out = new PrintWriter(new File("hamming.out"));

        for (int i = 0; i < n; i++) {
            int num = codes.get(i);
            if (i != 0 && i % 10 == 0) {
                out.println();
            }
            out.print(num);
            System.out.print(num);

            if (i % 10 == 9) continue;

            if (i != n-1) {
                out.print(" ");
                System.out.print(" ");
            }
        }

        out.println();
        out.close();
    }

    public static boolean works (int num) {
        for (int i : codes) {
            if (hamming(num, i) < d) return false;
        }

        return true;
    }

    public static int hamming(int num1, int num2) {
        String aBin = Integer.toBinaryString(num1);
        String bBin = Integer.toBinaryString(num2);

        while (aBin.length() < b) {
            aBin = "0" + aBin;
        }

        while (bBin.length() < b) {
            bBin = "0" + bBin;
        }

        int result = 0;

        for (int i = 0; i < b; i++) {
            if (aBin.charAt(i) != bBin.charAt(i)) result++;
        }

        return result;
    }
}


