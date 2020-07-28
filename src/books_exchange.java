// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class books_exchange {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int q = nextInt();

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < q; i++) {
            int n = nextInt();

            LinkedList<Integer>[] linkedLists = new LinkedList[n];

            for (int j = 0; j < n; j++) {
                linkedLists[j] = new LinkedList<>();
            }

            for (int j = 0; j < n; j++) {
                int val = nextInt() - 1;

                linkedLists[j].add(val);
            }

            HashMap<Integer, Integer> hashMap = new HashMap<>();

            boolean[] visited = new boolean[n];

            for (int j = 0; j < n; j++) {
                if (visited[j]) continue;

                Stack<Integer> to_complete = new Stack<>();

                to_complete.add(j);
                visited[j] = true;

                ArrayList<Integer> list = new ArrayList<>();
                list.add(j);

                while (!to_complete.isEmpty()) {
                    int next = to_complete.pop();
                    for (int k: linkedLists[next]) {
                        if (!visited[k]) {
                            visited[k] = true;
                            to_complete.add(k);
                            list.add(k);
                        }
                    }
                }

                int size = list.size();

                for (int k: list) {
                    hashMap.put(k, size);
                }
            }

            for (int j = 0; j < n; j++) {
                out.print(hashMap.get(j));

                if (j != n-1) {
                    out.print(" ");
                }
            }

            out.println();
        }

        out.close();
    }
}


