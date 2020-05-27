/*
ID: sruthi.2
LANG: JAVA
TASK: frac1
*/

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class frac1 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("frac1.in")));

        int n = nextInt();

        // i is the denominator

        PrintWriter out = new PrintWriter(new File("frac1.out"));

        TreeSet<Fraction> fractions = new TreeSet<>();

        fractions.add(new Fraction(0, 1));
        fractions.add(new Fraction(1, 1));

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (bigIntegerRelativelyPrime(i, j)) fractions.add(new Fraction(j, i));
            }
        }

        for (Fraction f : fractions) {
            out.println(f.numerator + "/" + f.denominator);
        }

        out.close();
    }

    public static boolean bigIntegerRelativelyPrime(int a, int b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).equals(BigInteger.ONE);
    }

    static class Fraction implements Comparable<Fraction> {
        int numerator, denominator;
        double value;

        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;

            value = (double) numerator / denominator;
        }

        @Override
        public int compareTo(Fraction o) {
            if (this.value > o.value) return 1;
            else return -1;
        }
    }
  
 /*
 ANALYSIS

 Loop through the different denominators, and the numerator has to be all of the
 numbers that are relatively prime


 */
}


