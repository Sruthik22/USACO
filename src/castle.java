/*
ID: sruthi.2
LANG: JAVA
TASK: castle
*/

import java.util.*;
import java.io.*;

public class castle {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int m;
    static int n;

    static int[][] grid;
    static int[][] castle;

    static HashMap<Integer, Integer> regionAreas;

    // for each of the partners need to hold the left most

    // make a hashmap with the int[] holding the leftmost point
    // for example, if 1 and 2 were connected -- key: 12 value: [1, 5]

    static Wall largestWall;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("castle.in")));

        m = nextInt();
        n = nextInt();

        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = nextInt();
                grid[i][j] = num;
            }
        }

        castle = new int[n][m];

        regionAreas = new HashMap<>();

        int regionId = 1;
        int largestArea = 0;

        for (int fr = 0; fr < n; fr++) {
            for (int fc = 0; fc < m; fc++) {
                if (castle[fr][fc] == 0) {
                    regionAreas.put(regionId, 0);
                    floodFill(fr, fc, regionId);
                    if (regionAreas.get(regionId) > largestArea) largestArea = regionAreas.get(regionId);
                    regionId++;
                }
            }
        }

        regionId = 1;

        for (int fr = 0; fr < n; fr++) {
            for (int fc = 0; fc < m; fc++) {
                if (castle[fr][fc] != 0) {
                    floodFill(fr, fc, regionId, 0);
                    regionId++;
                }
            }
        }

        int largestRoom = largestWall.sum;

        PrintWriter out = new PrintWriter(new File("castle.out"));
        System.out.println(regionId - 1);
        System.out.println(largestArea);
        System.out.println(largestRoom);
        System.out.println(largestWall);
        out.println(regionId - 1);
        out.println(largestArea);
        out.println(largestRoom);
        out.println(largestWall);

        out.close();
    }

    private static void floodFill(int r, int c, int id) {
        if (r < 0 || r >= n || c < 0 || c >= m) return;

        // no doing the same square over again
        if (castle[r][c] != 0) return;

        castle[r][c] = id;
        regionAreas.put(id, regionAreas.get(id)+1);

        String binString = Integer.toBinaryString(grid[r][c]);
        while (binString.length() < 4) {    //pad with 16 0's
            binString = "0" + binString;
        }

        char wallSouth = binString.charAt(0);

        char wallEast = binString.charAt(1);

        char wallNorth = binString.charAt(2);

        char wallWest = binString.charAt(3);

        if (wallSouth == '0') {
            floodFill(r+1, c, id); // south is 2
        }

        if (wallEast == '0') {
            floodFill(r, c+1, id); // east is 3
        }

        if (wallNorth == '0') {
            floodFill(r-1, c, id); // north is 0
        }

        if (wallWest == '0') {
            floodFill(r, c-1, id); // west is 1
        }
    }

    private static void floodFill(int r, int c, int id, int direction) {
        if (r < 0 || r >= n || c < 0 || c >= m) return;

        // no doing the same square over again
        if (castle[r][c] != 0 && castle[r][c] != id) {
            // we need to check if these relationship is the largest

            Wall newWall = new Wall(r, c, direction, regionAreas.get(id) + regionAreas.get(castle[r][c]));

            if (largestWall == null) {
                // then obviously replace

                largestWall = newWall;
            }

            if (largestWall.sum < newWall.sum) {
                largestWall = newWall;
            }

            if (largestWall.sum == newWall.sum) {
                if (largestWall.compareTo(newWall) < 0) {
                    largestWall = newWall;
                }
            }

            return;
        }

        if (castle[r][c] == 0) return;

        castle[r][c] = 0;

        floodFill(r+1, c, id, 2); // south is 2
        floodFill(r, c+1, id, 3); // east is 1
        floodFill(r-1, c, id, 0); // north is 0
        floodFill(r, c-1, id, 1); // west is 3
    }

    public static class Wall implements Comparable<Wall> {
        int row, col; // of the component that gave its direction

        int direction; // n, e -- 0, 1

        int sum;

        Wall (int row, int col, int direction, int sum) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            if (direction != 0 && direction != 1) this.direction -= 2;
            this.sum = sum;
        }

        @Override
        public String toString() {
            if (direction == 0) return (row+1) + " " + (col+1) + " N";
            else return (row + 1) + " " + (col) + " E";
        }

        @Override
        public int compareTo(Wall o) {
            if (this.col == o.col && this.row == o.row) {
                // need to compare direction
                if (this.direction == 0) return -1;
                else return 1;

                // need to make the north greater

            }

            if (this.col == o.col) {
                return this.row - o.row; // want the max
            }

            return o.col - this.col; // want the least so check and make sure that's what this does
        }
    }
}


