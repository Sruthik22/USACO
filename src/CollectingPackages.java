// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class CollectingPackages {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int t = nextInt();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < t; i++) {
            int n = nextInt();
            Package[] packages = new Package[n];
            for (int j = 0; j < n; j++) {
                packages[j] = new Package(nextInt(), nextInt());
            }

            String result = directionYX(packages);

            out.println(result);
        }

        out.close();
    }

    static String directionYX(Package[] packages) {
        Arrays.sort(packages, new Comparator<Package>(){
            public int compare(Package one, Package two) {
                if (one.y == two.y) return one.x - two.x;
                else return one.y - two.y;
            }
        });

        int prevPosX = 0;
        int prevPosY = 0;

        StringBuilder sb = new StringBuilder();

        for (Package p: packages) {
            if (p.x >= prevPosX && p.y >= prevPosY) {
                for (int i = prevPosX; i < p.x; i++) {
                    sb.append('R');
                }

                for (int i = prevPosY; i < p.y; i++) {
                    sb.append('U');
                }

                prevPosX = p.x;
                prevPosY = p.y;
            }

            else {
                return "NO";
            }
        }

        return "YES\n" + sb.toString();
    }

    static class Package {
        int x, y;

        Package(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


