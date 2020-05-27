/*
ID: sruthi.2
LANG: JAVA
TASK: sprime
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class sprime {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static ArrayList<Integer> superprimes;
    static int n;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("sprime.in")));
        n = nextInt();

        superprimes = new ArrayList<>();

        generate(0, 0);

        Collections.sort(superprimes);

        PrintWriter out = new PrintWriter(new File("sprime.out"));

        for (int i:superprimes) {
            System.out.println(i);
            out.println(i);
        }

        out.close();
    }

    public static void generate(int num, int length) {
        // base case
        if (length == n) {
            superprimes.add(num);
            return;
        }

        int newNum = num * 10;

        //recursive case
        for (int i = 0; i <= 9; i++) {
            // these are the numbers that could be added
            if (isPrime(newNum+i)) generate(newNum+i, length+1);
        }
    }

    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
}


