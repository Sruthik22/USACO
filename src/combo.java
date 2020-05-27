/*
ID: sruthi.2
LANG: JAVA
TASK: combo
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class combo {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static HashSet<Integer> combos;
    static int n;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("combo.in")));

        n = nextInt();

        int[] farmerJohnCombo = new int[3];

        for (int i = 0; i < 3; i++) {
            farmerJohnCombo[i] = nextInt();
        }

        int[] masterCombo = new int[3];

        for (int i = 0; i < 3; i++) {
            masterCombo[i] = nextInt();
        }

        int[] adds = {-2,-1,0,1,2};

        combos = new HashSet<>();

        for (int i : adds) {
            for (int j: adds) {
                for (int k : adds) {
                    // these are the numbers added to each digit
                    int[] combo = farmerJohnCombo.clone();
                    combo[0]+=i;
                    combo[1]+=j;
                    combo[2]+=k;

                    add(combo);
                }
            }
        }

        for (int i : adds) {
            for (int j: adds) {
                for (int k : adds) {
                    // these are the numbers added to each digit
                    int[] combo = masterCombo.clone();
                    combo[0]+=i;
                    combo[1]+=j;
                    combo[2]+=k;

                    add(combo);
                }
            }
        }

        int result = combos.size();

        PrintWriter out = new PrintWriter(new File("combo.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void add(int[] num) {
        // 0 actually means N
        // -1 actually means N - 1

        if (n == 1) {
            combos.add(1);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (num[i] < 1) num[i] = n + num[i];
            if (num[i] > n) num[i] -= n;
        }

        combos.add(num[0] * 101 * 101 + num[1] * 101 + num[2]);
    }
}


