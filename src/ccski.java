// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class ccski {

    final public static int[] DX = {-1,0,0,1};
    final public static int[] DY = {0,-1,1,0};

    public static int r;
    public static int c;
    public static int[][] elevations;
    public static int[][] waypts;
    public static int firstWay;

    public static void main(String[] args) throws Exception {

        // Read in data.
        BufferedReader stdin = new BufferedReader(new FileReader("ccski.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        r = Integer.parseInt(tok.nextToken());
        c = Integer.parseInt(tok.nextToken());
        elevations = new int[r][c];
        waypts = new int[r][c];
        firstWay = -1;
        for (int i=0; i<r; i++) {
            tok = new StringTokenizer(stdin.readLine());
            for (int j=0; j<c; j++)
                elevations[i][j] = Integer.parseInt(tok.nextToken());
        }
        for (int i=0; i<r; i++) {
            tok = new StringTokenizer(stdin.readLine());
            for (int j=0; j<c; j++) {
                waypts[i][j] = Integer.parseInt(tok.nextToken());
                if (firstWay == -1) firstWay = i*c + j;
            }
        }

        // Do binary search.
        int low = 0, high = 1000000000;
        while (low < high-1) {
            int mid = (low+high)/2;
            if (!valid(mid))
                low = mid+1;
            else
                high = mid;
        }

        if (!valid(low)) low++;

        // Write result.
        PrintWriter out = new PrintWriter(new FileWriter("ccski.out"));
        out.println(low);
        out.close();
        stdin.close();
    }

    public static boolean valid(int x) {

        // Set up queue.
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.offer(firstWay);
        boolean[] used = new boolean[r*c];
        used[firstWay] = true;

        // Run BFS.
        while (q.size() > 0) {
            int next = q.poll();
            int nextr = next/c;
            int nextc = next%c;

            // Go each direction only if the difference of elevation is <= x.
            for (int i=0; i<DX.length; i++) {
                int code = (nextr+DX[i])*c + nextc+DY[i];
                if (inbounds(nextr+DX[i], nextc+DY[i]) && !used[code] && Math.abs(elevations[nextr][nextc] - elevations[nextr+DX[i]][nextc+DY[i]]) <= x) {
                    q.offer(code);
                    used[code] = true;
                }
            }
        }

        // Return false if a waypoint isn't hit.
        for (int i=0; i<r; i++)
            for (int j=0; j<c; j++)
                if (waypts[i][j] == 1 && !used[i*c+j])
                    return false;

        // We made it.
        return true;
    }

    public static boolean inbounds(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}