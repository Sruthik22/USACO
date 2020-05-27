// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moobuzz {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("moobuzz.in"));
        int n = in.nextInt();
        in.close();

        int num3s = 0;
        int num5s = 0;
        int num15s = 0;
        int result = n;

        boolean num3done = false;
        boolean num5done = false;

        while (!num3done || !num5done) {
            int a = result / 3;
            result += (a-num3s);
            num3done = a - num3s == 0;
            num3s = a;

            int b = result / 5;
            result += (b-num5s);
            num5done = b - num5s == 0;
            num5s = b;

            int c = result / 15;
            result -= (c-num15s);
            num15s = c;
        }


        PrintWriter out = new PrintWriter(new File("moobuzz.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


