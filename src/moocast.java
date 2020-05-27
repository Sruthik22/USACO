// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moocast {

    private static int maxBroadcast;
    private static int curBroadcast;
    private static Node[] nodes;
    private static double[][] nodeDist;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("moocast.in"));
        int n = in.nextInt();

        nodes = new Node[n];
        nodeDist = new double[n][n];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int p = in.nextInt();

            nodes[i] = new Node(x, y, p);
        }

        in.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nodeDist[i][j] = Math.hypot(nodes[i].x-nodes[j].x, nodes[i].y-nodes[j].y);
            }
        }

        Graph g = new Graph(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g.addEdge(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            maxBroadcast = Math.max(maxBroadcast, g.DFS(i));
            curBroadcast = 0;
        }

        int result = maxBroadcast;
        PrintWriter out = new PrintWriter(new File("moocast.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Node {
        int x, y, p;

        Node(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }

    static class Graph
    {
        private int V;   // No. of vertices

        // Array  of lists for Adjacency List Representation
        private LinkedList[] adj;

        // Constructor
        Graph(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }

        //Function to add an edge into the graph
        void addEdge(int v, int w)
        {
            adj[v].add(w);  // Add w to v's list.
        }

        void DFSUtil(int v,boolean visited[])
        {
            visited[v] = true;
            curBroadcast++;

            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n] && nodeDist[v][n] <= nodes[v].p)
                    DFSUtil(n, visited);
            }
        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        private int DFS(int v)
        {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean visited[] = new boolean[V];

            // Call the recursive helper function to print DFS traversal
            DFSUtil(v, visited);

            return curBroadcast;
        }
    }
}


