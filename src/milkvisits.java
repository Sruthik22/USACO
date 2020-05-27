// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class milkvisits {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("milkvisits.in"));
        int n = in.nextInt();
        int m = in.nextInt();

        Graph g = new Graph(n);

        String s = in.next();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            g.addNode(i, c);
        }

        for (int i = 0; i < n-1; i++) {
            int num1 = in.nextInt() - 1;
            int num2 = in.nextInt() - 1;

            g.addEdge(num1, num2);
        }

        g.connectedComponents();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int index1 = in.nextInt()-1;
            int index2 = in.nextInt()-1;

            char c = in.next().charAt(0);

            if (g.connectedRegions[index1] == g.connectedRegions[index2]) {
                if (g.nodes[index1] == c) result.append("1");
                else result.append("0");
            }

            else {
                result.append("1");
            }
        }

        in.close();

        PrintWriter out = new PrintWriter(new File("milkvisits.out"));
        System.out.println(result.toString());
        out.println(result.toString());
        out.close();
    }

    private static class Graph {
        int V;
        LinkedList<Integer>[] adjListArray;
        char[] nodes;
        int[] connectedRegions;


        // constructor
        Graph(int V) {
            this.V = V;
            adjListArray = new LinkedList[V];

            for(int i = 0; i < V ; i++){
                adjListArray[i] = new LinkedList<Integer>();
            }

            nodes = new char[V];

            connectedRegions = new int[V];
        }

        void addNode(int index, char c) {
            nodes[index] = c;
        }

        void addEdge( int src, int dest) {
            adjListArray[src].add(dest);
            adjListArray[dest].add(src);
        }

        void DFSUtil(int v, boolean[] visited, int i) {
            visited[v] = true;
            connectedRegions[v] = i;

            for (int x : adjListArray[v]) {
                if(!visited[x] && nodes[x] == nodes[v]) DFSUtil(x,visited, i);
            }

        }
        void connectedComponents() {

            int i = 0;

            boolean[] visited = new boolean[V];
            for(int v = 0; v < V; ++v) {
                if(!visited[v]) {
                    DFSUtil(v,visited, i);

                    i++;
                }
            }
        }
    }
}


