// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class fenceplan {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static LinkedList<Integer>[] linkedLists;
    private static HashMap<Integer, ArrayList<Integer>> cowGroupDetails;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("fenceplan.in")));
        int n = nextInt(); // x, y of cow
        int m = nextInt(); // connection of cows

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(nextInt(), nextInt());
        }

        cowGroupDetails = new HashMap<>();

        linkedLists = new LinkedList[n]; // holding the ids of the cows

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int cow1 = nextInt() - 1;
            int cow2 = nextInt() - 1;

            linkedLists[cow1].add(cow2);
            linkedLists[cow2].add(cow1);
        }

        boolean[] visited = new boolean[n];

        int groupNum = 1;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            // actually dfs to find the entire connected component
            // make a hashmap with all of the cows included in the connected component as a value list
            dfs(visited, i, groupNum);

            groupNum++;
        }

        int result = Integer.MAX_VALUE;

        for (ArrayList<Integer> arrayList: cowGroupDetails.values()) {
            // need to find the extreme points in the arraylist

            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;

            for (int i : arrayList) {
                Cow c = cows[i];

                if (c.x > maxX) maxX = c.x;
                if (c.x < minX) minX = c.x;
                if (c.y > maxY) maxY = c.y;
                if (c.y < minY) minY = c.y;
            }

            int perimeter = 2 * (maxX - minX + maxY - minY);

            result = Math.min(result, perimeter);
        }

        PrintWriter out = new PrintWriter(new File("fenceplan.out"));
        System.out.println(result);
        out.println(result);
        out.close();

    }

    private static void dfs(boolean[] visited, int i, int groupNum) {
        if (visited[i]) return;
        visited[i] = true;
        cowGroupDetails.putIfAbsent(groupNum, new ArrayList<>());
        cowGroupDetails.get(groupNum).add(i);

        for (int j: linkedLists[i]) {
            dfs(visited, j, groupNum);
        }
    }

    public static class Cow {
        int x, y;
        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}