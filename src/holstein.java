/*
ID: sruthi.2
LANG: JAVA
TASK: holstein
*/

import java.util.*;
import java.io.*;

public class holstein {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int v;
    static int[] vitaminRequirements;
    static int g;
    static int[][] feedVitamins;

    static int min = Integer.MAX_VALUE;
    static int[] scoops;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("holstein.in")));

        v = nextInt();

        vitaminRequirements = new int[v];

        int total = 0;

        for (int i = 0; i < v; i++) {
            vitaminRequirements[i] = nextInt();
            total += vitaminRequirements[i];
        }

        g = nextInt();

        feedVitamins = new int[g][v];

        for (int i = 0; i < g; i++) {
            for (int j = 0; j < v; j++) {
                feedVitamins[i][j] = nextInt();
            }
        }

        scoops = new int[g];

        scoopRecurse(0, 0, vitaminRequirements, total, scoops);

        int result = min;
        PrintWriter out = new PrintWriter(new File("holstein.out"));
        System.out.print(result + " ");
        out.print(result + " ");

        int used = 0;

        for (int i = 0; i < g; i++) {
            if (scoops[i] == 1) {
                used++;
                out.print(i+1);
                System.out.print(i+1);

                if (used != min) {
                    out.print(" ");
                    System.out.print(" ");
                }
            }
        }

        out.println();

        out.close();
    }

    public static void arrayFix(int[] testing) {
        for (int i = 0; i < g; i++) {
            if (testing[i] == 1 && scoops[i] == 0) {
                scoops = testing.clone();
                break;
            }

            if (scoops[i] == 1 && testing[i] == 0) break;
        }
    }

    public static void scoopRecurse(int scoopToEvaluate, int numScoops, int[] vitaminsLeft, int totalLeft, int[] scoopsPrev) {

        // base case
        if (totalLeft <= 0) {
            if (min > numScoops) {
                // then need to replace the min and the scoops used
                min = numScoops;
                scoops = scoopsPrev;
            }

            if (min == numScoops) {
                // need to find the smaller scoop values
                arrayFix(scoopsPrev);
            }

            return;
        }

        if (scoopToEvaluate == g) {
            // this doesn't work
            return;
        }

        //recursive case

        // use this current scoop

        int[] newVitaminsLeft = new int[v];
        int newLeft = totalLeft;
        int[] newScoops = scoopsPrev.clone();
        newScoops[scoopToEvaluate] = 1;

        for (int i = 0; i < v; i++) {
            int toRemove = feedVitamins[scoopToEvaluate][i];
            int have = vitaminsLeft[i];

            int actualRemove = Math.max(have - toRemove, 0);

            newVitaminsLeft[i] = actualRemove;
            newLeft -= have - actualRemove;
        }

        scoopRecurse(scoopToEvaluate+1, numScoops+1, newVitaminsLeft, newLeft, newScoops);

        // don't use this current scoop
        scoopRecurse(scoopToEvaluate+1, numScoops, vitaminsLeft, totalLeft, scoopsPrev);
    }
}


