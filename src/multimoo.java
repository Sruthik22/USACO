// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class multimoo {

    // Movement.
    final public static int[] DX = {-1,0,0,1};
    final public static int[] DY = {0,-1,1,0};

    // Store grid.
    public static int n;
    public static int[][] grid;
    public static int[][] regions;

    // To store condensed graph of regions.
    public static HashSet[] g_orig;
    public static ArrayList[] g;

    // Number of vertices and edges, respectively, in condensed graph.
    public static int id;
    public static int numE;

    // Size and number of each vertex in condensed graph.
    public static ArrayList<Integer> sizes;
    public static ArrayList<Integer> numbers;

    public static void main(String[] args) throws Exception {

        BufferedReader stdin = new BufferedReader(new FileReader("multimoo.in"));
        n = Integer.parseInt(stdin.readLine().trim());
        grid = new int[n][n];

        // Read in the grid.
        for (int i=0; i<n; i++) {
            StringTokenizer tok = new StringTokenizer(stdin.readLine());
            for (int j=0; j<n; j++)
                grid[i][j] = Integer.parseInt(tok.nextToken());
        }

        // Solve for one color, also marking all unique regions and storing region sizes.
        regions = new int[n][n];
        sizes = new ArrayList<Integer>();
        numbers = new ArrayList<Integer>();
        int oneRes = regBFS();

        // Our new graph has id vertices.
        g_orig = new HashSet[id];
        for (int i=0; i<id; i++) g_orig[i] = new HashSet<Integer>();

        // This is our new graph to search.
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                for (int k=0; k<DX.length; k++) {
                    if (inbounds(i+DX[k],j+DY[k]) && regions[i][j] != regions[i+DX[k]][j+DY[k]]) {
                        int r1 = regions[i][j];
                        int r2 = regions[i+DX[k]][j+DY[k]];
                        if (r1 < r2) 	g_orig[r1].add(r2);
                        else			g_orig[r2].add(r1);
                    }
                }
            }
        }

        // Reform our graph so I can id edges faster.
        numE = 0;
        g = new ArrayList[id];
        for (int i=0; i<id; i++) g[i] = new ArrayList<Long>();
        for (int i=0; i<id; i++) {
            for (Integer to: (HashSet<Integer>)g_orig[i]) {
                g[i].add( (1000000L*numE) + to);
                g[to].add( (1000000L*numE) + i);
                numE++;
            }
        }

        // Solve for two numbers.
        int twoRes = Math.max(oneRes, edgeBFS());

        // Write result.
        PrintWriter out = new PrintWriter(new FileWriter("multimoo.out"));
        out.println(oneRes);
        out.println(twoRes);
        out.close();
        stdin.close();
    }

    public static int edgeBFS() {

        int res = 1;

        // Set up an used array of edges.
        boolean[] used = new boolean[numE];

        // Go through each vertex.
        for (int i=0; i<id; i++) {
            int tmp = edgeBFS(i, used);
            res = Math.max(res, tmp);
        }

        return res;
    }

    // Runs a BFS from each vertex i.
    public static int edgeBFS(int i, boolean[] used) {

        int res = 0;

        // Now visit each possible vertex from i.
        for (Long code: (ArrayList<Long>)g[i]) {
            int to = (int)(code%1000000L);
            int eID = (int)(code/1000000L);
            if (used[eID]) continue;
            int tmp = go(i, to, eID, used);
            res = Math.max(res, tmp);
        }
        return res;
    }

    // Runs a BFS from edge going from i->j. I pass in the edgeID for quick marking of edges.
    public static int go(int i, int j, int edgeID, boolean[] used) {

        // All the boring stuff. We have to put both i and j in the queue.
        boolean[] usedV = new boolean[id];
        usedV[i] = true;
        usedV[j] = true;
        used[edgeID] = true;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(i);
        q.offer(j);
        int curSize = 0;

        // Run BFS here.
        while (q.size() > 0) {

            // Get next - this is part of our group.
            int curV = q.pollFirst();
            curSize += sizes.get(curV);

            // Get the next vertex.
            for (Long code: (ArrayList<Long>)g[curV]) {

                // Extract where we are going.
                int to = (int)(code%1000000L);
                if (usedV[to]) continue;

                // Extract edge id to see if we traversed this edge before.
                int eID = (int)(code/1000000L);
                if (used[eID]) continue;

                // This checks to see if this path is even valid.
                if (!numbers.get(to).equals (numbers.get(i)) && !numbers.get(to).equals(numbers.get(j))) continue;

                // If we make it here we add to the queue.
                q.offer(to);
                usedV[to] = true;
                used[eID] = true;
            }
        }

        return curSize;
    }

    // Wrapper for all of our regular BFSs.
    public static int regBFS() {

        int res = 1;
        id = 0;
        boolean[][] used = new boolean[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (!used[i][j]) {
                    numbers.add(grid[i][j]);
                    int tmp = bfs(i, j, used);
                    sizes.add(tmp);
                    res = Math.max(res, tmp);
                    id++;
                }
            }
        }

        return res;
    }

    public static int bfs(int x, int y, boolean[][] used) {

        // Set up BFS.
        int res = 0;
        int target = grid[x][y];
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(n*x+y);
        used[x][y] = true;

        // Start  it.
        while (q.size() > 0) {

            // Get next.
            int cur = q.poll();
            int cX = cur/n;
            int cY = cur%n;
            regions[cX][cY] = id;
            res++;

            // Go to neighbors.
            for (int i=0; i<DX.length; i++) {

                // This is my neighbor.
                int nX = cX + DX[i];
                int nY = cY + DY[i];

                // Should not go here.
                if (!inbounds(nX, nY) || used[nX][nY] || grid[nX][nY] != target) continue;

                // Add to queue and mark it.
                q.offer(n*nX+nY);
                used[nX][nY] = true;
            }
        }

        // Ta da!
        return res;
    }

    public static boolean inbounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}