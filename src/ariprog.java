/*
ID: sruthi.2
LANG: JAVA
TASK: ariprog
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class ariprog {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("ariprog.in")));

        int n = nextInt();
        int m = nextInt();

        HashSet<Integer> bisquares = new HashSet<>();

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                bisquares.add(i * i + j * j);
            }
        }

        PrintWriter out = new PrintWriter(new File("ariprog.out"));

        ArrayList<Progression> progressionArrayList = new ArrayList<>();

        for (int i : bisquares) {
            for (int k = 1; k <= (2*m*m - i)/(n-1); k++) {
                // i is start
                // k is differences
                boolean flag = true;
                for (int j = 1; j < n; j++) {
                    if (!bisquares.contains(i + k * j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    progressionArrayList.add(new Progression(i, k));
                }
            }
        }

        Collections.sort(progressionArrayList);

        int start = -1;
        int diff = -1;

        for (Progression p : progressionArrayList) {
            int s = p.start;
            int d = p.diff;

            if (s != start || d != diff) {
                System.out.println(s + " " + d);
                out.print(s + " " + d + '\n');

                start = s;
                diff = d;
            }
        }

        if (progressionArrayList.isEmpty()) out.println("NONE");

        out.close();
    }

    static class Progression implements Comparable<Progression> {
        int start, diff;

        Progression(int start, int diff) {
            this.start = start;
            this.diff = diff;
        }


        @Override
        public int compareTo(Progression o) {
            if (diff == o.diff) return this.start - o.start;
            else return this.diff - o.diff;
        }
    }
}


