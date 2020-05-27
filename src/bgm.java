// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class bgm {

    final public static String KEY = "BESIGOM";

    final public static String[] WORDS = {"BESSIE", "GOES", "MOO"};

    public static long[][] freq;
    public static int[] lookup;

    public static void main(String[] args) throws Exception {

        // Make look up table.
        lookup = new int[26];
        Arrays.fill(lookup, -1);
        for (int i=0; i<KEY.length(); i++)
            lookup[KEY.charAt(i)-'A'] = i;

        Scanner stdin = new Scanner(new File("bgm.in"));
        int n = stdin.nextInt();

        // Store relevant mod 7 frequencies.
        freq = new long[7][7];
        for (int i=0; i<n; i++) {
            int letter = (int)(stdin.next().charAt(0)-'A');
            int num = (stdin.nextInt()%7+7)%7;
            freq[lookup[letter]][num]++;
        }

        int[] myMod = new int[7];
        long res = f(myMod, 0);

        // Solve and write out the result.
        FileWriter fout = new FileWriter(new File("bgm.out"));
        fout.write(res+"\n");
        fout.close();
    }

    public static long f(int[] mod, int k) {

        if (k == 7)
            return eval(mod);

        // Try each possibility for slot k from 0 to 6 and add up the results.
        long res = 0L;
        for (int i=0; i<7; i++) {
            mod[k] = i;
            res += f(mod, k+1);
        }
        return res;
    }

    //(B+E+S+S+I+E)(G+O+E+S)(M+O+O) "BESIGOM";
    // ([0]+2*[1]+2[2]+[3])([4]+[5]+[1]+[2])([6]+2*[5]])
    public static long eval(int[] mod) {

        boolean ok = false;
        for (int i=0; i<WORDS.length; i++) {
            int cur = 0;
            for (int j=0; j<WORDS[i].length(); j++)
                cur += mod[lookup[WORDS[i].charAt(j)-'A']];
            if (cur%7 == 0) {
                ok = true;
                break;
            }
        }

        if (!ok) return 0L;

        long res = 1;
        for (int i=0; i<7; i++)
            res *= freq[i][mod[i]];
        return res;
    }
}


