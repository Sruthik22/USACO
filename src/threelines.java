// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/current/index.php?page=viewproblem2&cpid=131


// http://www.usaco.org/index.php?page=viewproblem2&cpid=360

import java.util.*;
import java.io.*;

public class threelines {

    private static HashMap<Integer, Set<Integer>> x;
    private static HashMap<Integer, Set<Integer>> y;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("3lines.in"));
        int n = in.nextInt();
        x = new HashMap<>();
        y = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int xCoordinate = in.nextInt();
            int yCoordinate = in.nextInt();

            Object o = x.get(xCoordinate);

            if (o != null) {
                Set<Integer> s = (HashSet) o;
                s.add(yCoordinate);
                x.put(xCoordinate, s);
            }

            else {
                Set<Integer> s = new HashSet<>();
                s.add(yCoordinate);
                x.put(xCoordinate, s);
            }

            Object obj = y.get(yCoordinate);

            if (obj != null) {
                Set<Integer> s = (HashSet) obj;
                s.add(xCoordinate);
                y.put(yCoordinate, s);
            }

            else {
                Set<Integer> s = new HashSet<>();
                s.add(xCoordinate);
                y.put(yCoordinate, s);
            }

        }

        in.close();

        int result = threeLines() ? 1 : 0;

        PrintWriter out = new PrintWriter(new File("3lines.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean threeLines() {
        if (y.size() <= 3 || x.size() <= 3) {
            return true;
        }

        for (Map.Entry<Integer,Set<Integer>> entry : x.entrySet()) {
            int key = entry.getKey();
            Set<Integer> value = entry.getValue();

            int valOrig = y.size();

            for (int i : value) {
                if (y.get(i).contains(key) && y.get(i).size() == 1) {
                    valOrig--;
                    if (valOrig <= 2) {
                        return true;
                    }
                }
            }
        }

        for (Map.Entry<Integer,Set<Integer>> entry : y.entrySet()) {
            int key = entry.getKey();
            Set<Integer> value = entry.getValue();

            int valOrig = x.size();

            for (int i : value) {
                if (x.get(i).contains(key) && x.get(i).size() == 1) {
                    valOrig--;
                    if (valOrig <= 2) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
  
 /*
 ANALYSIS

 To not have to loop through all of the possibilities of using all y values, we can just see the number of different
 y values and then check against 3. Set, Hash Set.

 Actually hard combinations:

 2 x 1
 1 x 2

 Brute Force Algorithm:

 we need to check if all of the cows are covered by all of the lines

 x2 for both vertical and horizontal

If there is a bunch of loops try to think of a datastructure that would help to cut down on the number of loops

TreeMap every line could be a key, and the every value would be a set of values

then you would just remove one of the lines and check if the number of rows/columns is greater than 2

2 Maps representing the x and y line

removing the points from the other lines



 */
}


