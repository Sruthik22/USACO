// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PerfectKeyboard {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static HashSet<Integer>[] linkedLists;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            String pass = next();

            linkedLists = new HashSet[26];

            for (int j = 0; j < 26; j++) {
                linkedLists[j] = new HashSet<>();
            }

            for (int j = 0; j < pass.length()-1; j++) {
                int code1 = pass.charAt(j) - 'a';
                int code2 = pass.charAt(j+1) - 'a';

                linkedLists[code1].add(code2);
                linkedLists[code2].add(code1);
            }

            visited = new boolean[26];

            Stack<Integer> stack = new Stack<>();

            // we need to find an index that has either 0 or 1 connections

            int covered = 0;

            StringBuilder sb = new StringBuilder();

            boolean fail = false;

            while (covered < 26) {
                int index = findStart();

                if (index == -1) break;

                stack.add(index);

                while (!stack.isEmpty()) {
                    index = stack.pop();
                    visited[index] = true;
                    sb.append((char) ('a' + index));
                    covered++;

                    int numConnected = linkedLists[index].size();

                    if (numConnected > 2) {
                        fail = true;
                        break;
                    }

                    int count = 0;

                    for (int j : linkedLists[index]) {
                        if (!visited[j]) {
                            count++;
                            stack.add(j);
                        }
                    }

                    if (count > 1) {
                        fail = true;
                        break;
                    }
                }

                if (fail) break;
            }

            if (covered == 26) {
                out.println("YES");
                out.println(sb.toString());
            }

            else {
                out.println("NO");
            }
        }

        out.close();
    }

    static int findStart() {
        for (int i = 0; i < 26; i++) {
            if (!visited[i] && linkedLists[i].size() <= 1) return i;
        }

        return -1;
    }
}


