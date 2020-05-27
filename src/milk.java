/*
ID: sruthi.2
LANG: JAVA
TASK: milk
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class milk {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("milk.in")));

        int n = nextInt();
        int m = nextInt();

        Farmer[] farmers = new Farmer[m];

        for (int i = 0; i < m; i++) {
            int price = nextInt();
            int amount = nextInt();

            Farmer f = new Farmer(price, amount);

            farmers[i] = f;
        }

        Arrays.sort(farmers);

        int result = 0;

        int curFarmerIndex = 0;

        while (n > 0) {

            Farmer curFarmer = farmers[curFarmerIndex];

            if (n >= curFarmer.milkProduction) {
                // take all of the milk
                result += curFarmer.price * curFarmer.milkProduction;
                curFarmerIndex++;
                n -= curFarmer.milkProduction;
            }

            else {
                result += curFarmer.price * n;
                n = 0;
            }
        }

        PrintWriter out = new PrintWriter(new File("milk.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Farmer implements Comparable<Farmer>{
        int price, milkProduction;

        Farmer(int price, int milkProduction) {
            this.price = price;
            this.milkProduction = milkProduction;
        }


        @Override
        public int compareTo(Farmer o) {
            return this.price - o.price;
        }
    }
}


