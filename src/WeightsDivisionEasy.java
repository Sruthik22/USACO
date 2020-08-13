// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class WeightsDivisionEasy {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static int[] counts;
    static boolean[] visited;
    static WeightedGraph.Graph graph;
    static PriorityQueue<Edge> priorityQueue;
    static long total;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int tt = nextInt();

        for (int t = 0; t < tt; t++) {
            int n = nextInt();
            long s = nextLong();
            graph = new WeightedGraph.Graph(n);
            for (int i = 0; i < n - 1; i++) {
                int node1 = nextInt() - 1;
                int node2 = nextInt() - 1;
                int weight = nextInt();
                graph.addEdge(node1, node2, weight);
                graph.addEdge(node2, node1, weight);
            }
            counts = new int[n];
            priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
            visited = new boolean[n];
            total = 0;
            dp_dfs(0);

            int moves = 0;

            while (total > s) {
                Edge e = priorityQueue.poll();
                total -= e.benefitIfTake();
                priorityQueue.add(new Edge(e.numNodes, e.weight/2));
                moves++;
            }

            out.println(moves);
        }

        out.close();
    }

    static void dp_dfs(int node) {
        visited[node] = true;

        if (graph.adjacencylist[node].size() == 1 && visited[graph.adjacencylist[node].get(0).destination]) {
            counts[node] = 1;
            return;
        }

        int sum = 0;

        for (WeightedGraph.Edge edge: graph.adjacencylist[node]) {
            if (!visited[edge.destination]) {
                dp_dfs(edge.destination);
                Edge e = new Edge(counts[edge.destination], edge.weight);
                priorityQueue.add(e);
                total += e.value();
                sum += counts[edge.destination];
            }
        }

        counts[node] = sum;
    }

    public static class WeightedGraph {
        static class Edge {
            int source;
            int destination;
            int weight;

            public Edge(int source, int destination, int weight) {
                this.source = source;
                this.destination = destination;
                this.weight = weight;
            }
        }

        static class Graph {
            int vertices;
            LinkedList<Edge> [] adjacencylist;

            Graph(int vertices) {
                this.vertices = vertices;
                adjacencylist = new LinkedList[vertices];
                for (int i = 0; i <vertices ; i++) {
                    adjacencylist[i] = new LinkedList<>();
                }
            }

            public void addEdge(int source, int destination, int weight) {
                Edge edge = new Edge(source, destination, weight);
                adjacencylist[source].addFirst(edge); //for directed graph
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        int numNodes, weight;

        Edge(int numNodes, int weight) {
            this.numNodes = numNodes;
            this.weight = weight;
        }

        long value() {
            return this.numNodes * (long) this.weight;
        }

        long benefitIfTake() {
            long diff = weight - weight / 2;
            return diff * this.numNodes;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(benefitIfTake(), o.benefitIfTake());
        }
    }
}


