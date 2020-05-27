/*
ID: sruthi.2
LANG: JAVA
TASK: wormhole
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class wormhole {

    private static int n;
    private static Wormhole[] wormholes;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("wormhole.in"));
        n = in.nextInt();

        wormholes = new Wormhole[n];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            wormholes[i] = new Wormhole(x, y);
        }

        in.close();

        for (Wormhole w: wormholes) {
            w.findRight();
        }

        int result = tryPairings(0);
        PrintWriter out = new PrintWriter(new File("wormhole.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean simulate() {

        int breakCounter = 0;

        for (int x = 0; x < n; x++) {
            Wormhole currentWormhole = wormholes[x];

            for (int i = 0; i < n; i++) {

                currentWormhole = wormholes[currentWormhole.paired];

                if (currentWormhole.toRight == -1) {
                    breakCounter++;
                    break;
                }

                else {
                    currentWormhole = wormholes[currentWormhole.toRight];
                }
            }
        }

        return breakCounter != n;
    }

    private static int tryPairings(int a) {
        while (a < n && wormholes[a].paired != -1) a++;

        if (a >= n) {
            for (int i = 0; i < n; i++) {
                if (simulate()) return 1;
            }

            return 0;
        }

        int count = 0;

        for (int b = a+1; b < n; b++) {
            if (wormholes[b].paired != -1) continue;
            wormholes[a].paired = b;
            wormholes[b].paired = a;

            count += tryPairings(a+1);

            wormholes[a].paired = -1;
            wormholes[b].paired = -1;
        }

        return count;
    }

    private static class Wormhole {

        int x;
        int y;
        int paired = -1; // index of the one paired in array, or -1
        int toRight = -1; // closest to the right

        private Wormhole(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private void findRight() {
            for (int i = 0; i < n; i++) {
                Wormhole w = wormholes[i];
                if (w.y != this.y || w.x <= this.x) continue;
                if (toRight == -1 || w.x < wormholes[toRight].x) {
                    toRight = i;
                }
            }
        }
    }
  
 /*
 ANALYSIS

 They are in a cycle if there is a wormhole to the right of bessie

 Always pick the earliest possible to avoid duplicates
 
 */
}


