/*
ID: sruthi.2
LANG: JAVA
TASK: beads
*/

import java.util.*;
import java.io.*;

public class beads {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("beads.in")));

        int n = nextInt();
        String beads = next();

        int result = 0;

        for (int i = 0; i < n; i++) {
            char c = beads.charAt(i); // problem is if c is a w, then we need to set c as the first letter that is non w
            int rightIndex = i;

            int totalBeads = 0;

            boolean[] visited = new boolean[n];

            while ((beads.charAt(rightIndex) == c || beads.charAt(rightIndex) == 'w') && !visited[rightIndex]) {
                // if the bead is the same
                visited[rightIndex] = true;
                rightIndex++;
                rightIndex%=n;
                totalBeads++;

                if (c == 'w') {
                    if (beads.charAt(rightIndex) != 'w') c = beads.charAt(rightIndex);
                }
            }

            int leftIndex = i - 1;
            if (leftIndex == -1) {
                leftIndex = n-1;
            }

            c = beads.charAt(leftIndex);

            while ((beads.charAt(leftIndex) == c || beads.charAt(leftIndex) == 'w') && !visited[leftIndex]) {
                // if the bead is the same
                leftIndex--;
                if (leftIndex == -1) {
                    leftIndex = n-1;
                }
                totalBeads++;

                if (c == 'w') {
                    if (beads.charAt(leftIndex) != 'w') c = beads.charAt(leftIndex);
                }
            }

            result = Math.max(result, totalBeads);
        }

        PrintWriter out = new PrintWriter(new File("beads.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS
 O(n^2) works fine
 */
}


