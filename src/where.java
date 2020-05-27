import java.io.*;
import java.util.*;

public class where {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("where.in"));
        PrintWriter out = new PrintWriter(new File("where.out"));

        int n = in.nextInt();
        char[][] map = new char[n][];
        for(int i = 0; i < n; i++) {
            map[i] = in.next().toCharArray();
        }

        // areaWithCorrectRegions contains int[] denoting the bounds (rFrom, rTo, cFrom, cTo)
        // if PCLs that are not (yet) enclosed by any other PCL areas.
        ArrayList<int[]> areasWithCorrectRegions = new ArrayList<>();
        for(int rFrom = 0; rFrom < n; rFrom++) {
            for(int rTo = rFrom; rTo < n; rTo++) {
                for(int cFrom = 0; cFrom < n; cFrom++) {
                    for(int cTo = cFrom; cTo < n; cTo++) {
                        boolean hasCorrectRegions = hasCorrectRegions(map, rFrom, rTo, cFrom, cTo);
                        if(hasCorrectRegions) {

                            boolean isEnclosed = false;
                            Iterator<int[]> iter = areasWithCorrectRegions.iterator();
                            // note the the number of iterations of this loop is bounded
                            // by how many possible non-overlapping areas we can store
                            // which is n*n = 400
                            while(iter.hasNext()) {
                                int[] entry = iter.next();
                                // check whether each area in areasWithCorrectRegions is enclosed
                                // by current area. If so, remove it from areasWithCorrectRegions.
                                if(rFrom <= entry[0] && entry[1] <= rTo && cFrom <= entry[2] && entry[3] <= cTo) {
                                    iter.remove();
                                }
                                // check whether current area is being enclosed by others
                                // in areasWithCorrectRegions. If so, don't count this area
                                if(entry[0] <= rFrom && rTo <= entry[1] && entry[2] <= cFrom && cTo <= entry[3]) {
                                    isEnclosed = true;
                                    break;
                                }
                            }

                            if(!isEnclosed) {
                                areasWithCorrectRegions.add(new int[]{rFrom, rTo, cFrom, cTo});
                            }
                        }
                    }
                }
            }
        }

        int ans = areasWithCorrectRegions.size();

        System.out.println(ans);
        out.println(ans);
        in.close();
        out.close();
    }

    // returns whether the rectangular area defined by
    // map[rFrom]..map[rTo] * map[][cFrom]...map[][cTo] has 2+ disjoint regions of
    // same letter plus one single region of another letter
    static boolean hasCorrectRegions(char[][] map, int rFrom, int rTo, int cFrom, int cTo) {
        int rows = rTo - rFrom + 1;
        int cols = cTo - cFrom + 1;
        boolean[][] visited = new boolean[rows][cols];

        // letterCounts[i] counts how many time region with color 'A' + i appeared
        int[] letterCounts = new int[26];

        for(int r = rFrom; r <= rTo; r++) {
            for(int c = cFrom; c <= cTo; c++) {
                if(!visited[r-rFrom][c-cFrom]) {
                    char letter = map[r][c];
                    letterCounts[letter-'A']++;
                    flood(map, visited, r, c, rFrom, rTo, cFrom, cTo, letter);
                }
            }
        }

        return ok(letterCounts);
    }

    static void flood(char[][] map, boolean[][] visited, int r, int c, int rFrom, int rTo, int cFrom, int cTo, char color) {
        if(rFrom <= r && r <= rTo && cFrom <= c && c <= cTo &&
                !visited[r-rFrom][c-cFrom] && map[r][c] == color) {
            visited[r-rFrom][c-cFrom] = true;
            flood(map, visited, r-1, c, rFrom, rTo, cFrom, cTo, color);
            flood(map, visited, r+1, c, rFrom, rTo, cFrom, cTo, color);
            flood(map, visited, r, c-1, rFrom, rTo, cFrom, cTo, color);
            flood(map, visited, r, c+1, rFrom, rTo, cFrom, cTo, color);
        }
    }

    static boolean ok(int[] array) {
        int zeroCount = 0;
        int oneCount = 0;
        for(int x : array) {
            if(x == 0) {
                zeroCount++;
            }
            else if(x == 1) {
                oneCount++;
            }
        }
        return zeroCount == 24 && oneCount == 1;
    }
}
