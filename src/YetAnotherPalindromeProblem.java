// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class YetAnotherPalindromeProblem {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            HashMap<Integer, Integer> rememberPos = new HashMap<>();

            boolean works = false;

            int[] numbers = new int[n];

            for (int j = 0; j < n; j++) {
                numbers[j] = nextInt();
            }

            for (int j = 0; j < n; j++) {
                int next = numbers[j];

                if (rememberPos.containsKey(next)) {
                    if (rememberPos.get(next) != j-1) {
                        works = true;
                        break;
                    }
                }

                else rememberPos.put(next, j);
            }

            if (works) {
                out.println("YES");
            }

            else {
                out.println("NO");
            }
        }

        out.close();
    }
}


