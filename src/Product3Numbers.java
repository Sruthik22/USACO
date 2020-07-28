// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Product3Numbers {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int t = nextInt();

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            List<Integer> primes = primeFactors(n);

            HashMap<Integer, Integer> primeFactorized = new HashMap<>();

            for (int p : primes) {
                if (primeFactorized.containsKey(p)) {
                    primeFactorized.put(p, primeFactorized.get(p) + 1);
                }

                else primeFactorized.put(p, 1);
            }

            List<Integer> aList = new ArrayList<Integer>(primeFactorized.keySet());

            if (primeFactorized.keySet().size() >= 3) {
                out.println("YES");
                out.println(aList.get(0) + " " + aList.get(1) + " " + n / aList.get(0) / aList.get(1));
            }

            else if (primeFactorized.keySet().size() == 2) {
                int val1 = aList.get(0);
                int val1Times = primeFactorized.get(val1);
                int val2 = aList.get(1);
                int val2Times = primeFactorized.get(val2);

                if ((val1Times >= 2 && val2Times >= 2) || (val1Times >= 3) || (val2Times >= 3)) {
                    out.println("YES");
                    out.println(val1 + " " + val2 + " " + n / val1 / val2);
                }

                else {
                    out.println("NO");
                }
            }

            else {
                if (aList.isEmpty()) {
                    out.println("NO");
                }

                int num = aList.get(0);

                if (primeFactorized.get(num) >= 6) {
                    out.println("YES");
                    out.println(num + " " + num * num + " " + (int) (Math.pow(num, primeFactorized.get(num) - 3)));
                }

                else {
                    out.println("NO");
                }
            }
        }

        out.close();
    }

    public static List<Integer> primeFactors(int numbers) {
        int n = numbers;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }
}


