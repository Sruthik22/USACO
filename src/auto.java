// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class auto {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("auto.in"));
        int w = in.nextInt();
        int n = in.nextInt();

        TreeMap<String, Integer> dictionary = new TreeMap<>();

        for (int i = 1; i < w+1; i++) {
            dictionary.put(in.next(), i);
        }

        List<String> keySet = new ArrayList<>(dictionary.keySet());

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int index = in.nextInt();
            String searchableTerm = in.next();

            int dex = Collections.binarySearch(keySet, searchableTerm);

            if (dex < 0) {
                dex = -(dex + 1);
            }

            if (dex + index < dictionary.size() && keySet.get(dex+index - 1).startsWith(searchableTerm)) {
                result.append(dictionary.get(keySet.get(dex+index - 1)));
            }

            else {
                result.append("-1");
            }

            if (i != n-1) result.append('\n');
        }

        in.close();

        PrintWriter out = new PrintWriter(new File("auto.out"));
        System.out.println(result);
        out.println(result.toString());
        out.close();
    }
  
 /*
 ANALYSIS

 Sort all the words in alphabetical order

 But have a dictionary holding their original index as the value

 Search for the word by binary search, and then once found the index, add by the supplied integer

Once added make sure that the prefix of that string is the same as being searched -- otherwise -1

 */
}


