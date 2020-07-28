// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class BalancedTeam {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();

        Integer[] skills = new Integer[n];

        for (int i = 0; i < n; i++) {
            skills[i] = nextInt();
        }

        Arrays.sort(skills);

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int result = 0;
        int rightPoint = 0;

        for (int leftPoint = 0; leftPoint < n; leftPoint++) {
            rightPoint = Math.max(rightPoint, leftPoint);
            while (rightPoint < n && skills[rightPoint] - skills[leftPoint] <= 5) {
                rightPoint++;

                result = Math.max(result, rightPoint - leftPoint);
            }
        }

        out.println(result);

        out.close();
    }
}


