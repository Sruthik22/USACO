// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowcode {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cowcode.in"));
        String s = in.next();
        long n = in.nextLong();

        in.close();

        char result = code(s, n-1);

        PrintWriter out = new PrintWriter(new File("cowcode.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static char code(String s, long index) {

        // print out the letter if the index is less than the length of the given word
        if (index < s.length()) {
            return s.charAt((int)index);
        }

        // needs to be subtracted amount that is 2 times for each set
        long length = s.length();
        while(2*length <= index) {
            length *= 2;
        }

        // if the index is the same amount we would be subtracting, this number cannot be track
        // this is the number that is being rotated, instead by going back one before, then we can track
        if (index == length) {
            return code(s, index - 1);
        }

        //usually, just skip all of the repeats contained in the length
        return code(s, index - length - 1);
    }
}


