// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class bcount {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("bcount.in"));
        int n = in.nextInt();
        int q = in.nextInt();

        int[] cowID = new int[n];

        for (int i = 0; i < n; i++) {
            cowID[i] = in.nextInt();
        }

        int[] holsteins = new int[n]; // 1
        int[] guernseys = new int[n]; // 2
        int[] jerseys = new int[n]; // 3

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (cowID[i] == 1) {
                    holsteins[0] = 1;
                    guernseys[0] = 0;
                    jerseys[0] = 0;
                }

                else if (cowID[i] == 2) {
                    holsteins[0] = 0;
                    guernseys[0] = 1;
                    jerseys[0] = 0;
                }

                else {
                    holsteins[0] = 0;
                    guernseys[0] = 0;
                    jerseys[0] = 1;
                }

            }

            else {
                holsteins[i] = holsteins[i-1];
                guernseys[i] = guernseys[i-1];
                jerseys[i] = jerseys[i-1];

                if (cowID[i] == 1) {
                    holsteins[i]++;
                }

                else if (cowID[i] == 2) {
                    guernseys[i]++;
                }

                else {
                    jerseys[i]++;
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("bcount.out"));

        for (int i = 0; i < q; i++) {
            int s = in.nextInt() - 1;
            int e = in.nextInt() - 1;

            int h = holsteins[e] - holsteins[s];
            int g = guernseys[e] - guernseys[s];
            int j = jerseys[e] - jerseys[s];

            if (cowID[s] == 1) h++;
            if (cowID[s] == 2) g++;
            if (cowID[s] == 3) j++;

            System.out.println("" + h + " " + g + " " + j);
            out.println("" + h + " " + g + " " + j);


        }

        in.close();


        out.close();
    }
}


