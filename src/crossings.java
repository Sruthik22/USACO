// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.awt.event.ComponentListener;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class crossings {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("crossings.in"));
        int n = in.nextInt();

        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            int a1 = in.nextInt();
            int b1 = in.nextInt();

            lines[i] = new Line (i, a1, b1);
        }

        Arrays.sort(lines);

        in.close();

        int result = 0;

        int[] maxl = new int[n];
        maxl[0] = lines[0].p2;
        for(int i=1; i<n; i++)
            maxl[i] = Math.max(maxl[i-1], lines[i].p2);

        int[] minr = new int[n];
        minr[n-1] = lines[n-1].p2;
        for(int i=n-2; i>=0; i--)
            minr[i] = Math.min(minr[i+1], lines[i].p2);

        for(int i=0; i<n; i++) {
            boolean ok = true;
            if(i!=0 && maxl[i-1] > lines[i].p2) ok = false;
            if(i!=n-1 && minr[i+1] < lines[i].p2) ok = false;
            if(ok) result++;
        }

        PrintWriter out = new PrintWriter(new File("crossings.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Line implements Comparable<Line> {
        int id, p1, p2;

        Line (int id, int p1, int p2) {
            this.id = id;
            this.p1 = p1;
            this.p2 = p2;
        }


        @Override
        public int compareTo(Line o) {
            return this.p1 - o.p1;
        }
    }
}


