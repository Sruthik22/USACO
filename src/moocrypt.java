// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moocrypt {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("moocrypt.in"));
        int n = in.nextInt();
        int m = in.nextInt();

        char[][] encryption = new char[n][m];

        for (int i = 0; i < n; i++) {
            encryption[i] = in.next().toCharArray();
            in.nextLine();
        }

        in.close();

        HashMap<String, Integer> numberOfOccurances = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cur = encryption[i][j];

                // diagonal south east

                if (j < m-2 && i < n-2) {
                    if (encryption[i+1][j+1] == encryption[i+2][j+2] && encryption[i+2][j+2] != cur) {
                        String key = "" + cur + encryption[i+2][j+2];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                // diagonal north west

                if (j > 1 && i > 1) {
                    if (encryption[i-1][j-1] == encryption[i-2][j-2] && encryption[i-2][j-2] != cur) {
                        String key = "" + cur + encryption[i-2][j-2];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                // diagonal north east

                if (i > 1 && j < m-2) {
                    if (encryption[i-1][j+1] == encryption[i-2][j+2] && encryption[i-2][j+2] != cur) {
                        String key = "" + cur + encryption[i-2][j+2];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                // diagonal south west

                if (i < n-2 && j > 1) {
                    if (encryption[i+1][j-1] == encryption[i+2][j-2] && encryption[i+2][j-2] != cur) {
                        String key = "" + cur + encryption[i+2][j-2];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                // right and left

                if (j < m-2) {
                    if (encryption[i][j+1] == encryption[i][j+2] && encryption[i][j+1] != cur) {
                        String key = "" + cur + encryption[i][j+2];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                if (j > 1) {
                    if (encryption[i][j-1] == encryption[i][j-2] && encryption[i][j-1] != cur) {
                        String key = "" + cur + encryption[i][j-1];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                // up and down

                if (i < n-2) {
                    if (encryption[i+1][j] == encryption[i+2][j] && encryption[i+1][j] != cur) {
                        String key = "" + cur + encryption[i+2][j];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }

                if (i > 1) {
                    if (encryption[i-1][j] == encryption[i-2][j] && encryption[i-1][j] != cur) {
                        String key = "" + cur + encryption[i-2][j];
                        numberOfOccurances.putIfAbsent(key, 0);
                        numberOfOccurances.put(key, numberOfOccurances.get(key)+1);
                    }
                }
            }
        }

        /*case checking here (MOO rules)*/

        int result = 0;

        for (Map. Entry<String, Integer> entry : numberOfOccurances. entrySet()) {
            if (entry.getKey().charAt(0) != 'M' && entry.getKey().charAt(1) != 'O') {
                result = Math.max(result, entry.getValue());
            }
        }

        PrintWriter out = new PrintWriter(new File("moocrypt.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS

 Figure out the total number of MOO patterns, where letters map to two different letters in the particular order
 that can not be M, O for the single and double repetition respectively

 hold in a hashmap and figure out the max number of occurences

 4 6
TAMHGI
MMQVWM
QMMQSM
HBQUMQ



 */
}


