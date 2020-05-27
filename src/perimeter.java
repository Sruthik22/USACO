// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class perimeter {

    private static boolean[][] alreadyVisited;
    private static int n;
    private static int[][] grid;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("perimeter.in"));
        n = in.nextInt();

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            char[] c = s.toCharArray();

            for (int j = 0; j < n; j++) {
                if (c[j] == '#') {
                    grid[i][j] = 0;
                }

                if (c[j] == '.') {
                    grid[i][j] = -1;
                }
            }
        }

        in.close();

        String result = "";
        int groupNumber = 1;

        alreadyVisited = new boolean[n][n];

        ArrayList<Integer> areas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!alreadyVisited[i][j] && grid[i][j] == 0) {
                    // then start the floodfill to label as the current groupNumber
                    int area = floodfill(i, j, groupNumber);
                    groupNumber++;
                    areas.add(area);
                }
            }
        }

        ArrayList<Integer> perimeters = new ArrayList<>(Collections.nCopies(groupNumber - 1, 0));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // finding the perimeters of the regions
                int x = grid[i][j];
                if (x != -1 && x != 0) {
                    int num = perimeters.get(x - 1);

                    if (i == 0) {
                        num++;
                    }

                    if (j == 0) {
                        num++;
                    }

                    if (i == n - 1) {
                        num++;
                    }

                    if (j == n - 1) {
                        num++;
                    }

                    if (i + 1 < n && grid[i+1][j] == -1) {
                        num++;
                    }

                    if (i - 1 >= 0 && grid[i-1][j] == -1) {
                        num++;
                    }

                    if (j + 1 < n && grid[i][j+1] == -1) {
                        num++;
                    }

                    if (j - 1 >= 0 && grid[i][j-1] == -1) {
                        num++;
                    }

                    perimeters.set(x - 1, num);
                }
            }
        }

        Blob[] blobs = new Blob[groupNumber-1];

        for (int i = 0; i < groupNumber - 1; i++) {
            blobs[i] = new Blob(areas.get(i), perimeters.get(i));
        }

        Arrays.sort(blobs);

        result = blobs[groupNumber-2].area + " " + blobs[groupNumber - 2].perimeter;

        PrintWriter out = new PrintWriter(new File("perimeter.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int floodfill (int r, int c, int groupNumber) {
        if (r < 0 || r >= n || c < 0 || c >= n) return 0;

        if (grid[r][c] != 0) return 0;

        if (alreadyVisited[r][c]) return 0;

        alreadyVisited[r][c] = true;
        grid[r][c] = groupNumber;

        return 1 + floodfill(r, c + 1, groupNumber) +
        floodfill(r, c - 1, groupNumber) +
        floodfill(r + 1, c, groupNumber) +
        floodfill(r - 1, c, groupNumber);
    }

    private static class Blob implements Comparable<Blob>{
        int area, perimeter;
        private Blob (int area, int perimeter) {
            this.area = area;
            this.perimeter = perimeter;
        }

        @Override
        public int compareTo(Blob o) {
            if (this.area == o.area) {
                return - (this.perimeter - o.perimeter);
            }
            return this.area - o.area;
        }
    }
  
 /*
 ANALYSIS

Assign the different regions two different ids

to find the area, just count the number on characters in that region

to find the perimeter, it is the number of surrounding cells from the region
Just loop through the surrounding cells and if they are bordering a cell from the
region add them to the perimeter

If it is an edge piece then it can be automatically added to the perimeter

 */
}


