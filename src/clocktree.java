// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class clocktree {

    private static LinkedList<Integer>[] adjacencyList;
    private static int[] nodeValues;
    private static int[] dfsNodeValues;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("clocktree.in"));
        int n = in.nextInt(); // number of rooms

        adjacencyList = new LinkedList[n];

        nodeValues = new int[n];

        for (int i = 0; i < n; i++) {
            nodeValues[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;

            adjacencyList[x].add(y);
            adjacencyList[y].add(x);
        }

        in.close();

        int result = 0;

        dfsNodeValues = new int[n];

        for (int i = 0; i < n; i++) {
            // start at each node

            System.arraycopy(nodeValues, 0, dfsNodeValues, 0, n);

            dfs(i, -1);

            if (dfsNodeValues[i] == 1 || dfsNodeValues[i] == 12) result++;
        }

        PrintWriter out = new PrintWriter(new File("clocktree.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int dfs(int i, int parent) {
        for (int node : adjacencyList[i]) {

            if (node != parent) {

                dfsNodeValues[i] += 12 - dfs(node, i);

                dfsNodeValues[i]  = (dfsNodeValues[i]-1)%12+1; // what exactly is this line doing
            }
        }

        if (parent == -1) return 0;

        dfsNodeValues[parent]= (1 + dfsNodeValues[parent]) % 12;
        return dfsNodeValues[i] % 12;
    }
}


