// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moosick {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("moosick.in"));
        in.close();

        int result = 0;
        PrintWriter out = new PrintWriter(new File("moosick.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
  
 /*
 ANALYSIS

 sliding window

 need the differences between each number, because the differences persist after transposition
 then check if the the difference arrays are the same but in different orders

    (can sort the list) -- canonical form
 */
}


