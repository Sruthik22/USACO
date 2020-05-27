/*
ID: sruthi.2
LANG: JAVA
TASK: pprime
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class pprime {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("pprime.in")));

        int a = nextInt();
        int b = nextInt();

        PrintWriter out = new PrintWriter(new File("pprime.out"));

        List<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(b) + 1);
        List<Integer> pals = generatePal(0, 0, 3, primes);
        HashSet<Integer> ps = new HashSet<Integer>();
        ps.addAll(pals);
        pals = new ArrayList<Integer>();
        pals.addAll(ps);
        pals.sort(Comparator.naturalOrder());
        for (int i = 0; i < pals.size(); i++) {
            if (pals.get(i)>=a && pals.get(i)<=b) {
                out.println(pals.get(i));
            }
        }

        out.close();
    }

    public static List<Integer> generatePal(int digits, int N, int max, List<Integer> primes) {
        List<Integer> pals = new ArrayList<Integer>();
        String temp1 = String.valueOf(digits);
        StringBuilder temp2 = new StringBuilder(temp1);
        temp1+=temp2.reverse();
        if (isPrime(Integer.parseInt(temp1), primes)) {
            pals.add(Integer.parseInt(temp1));
        }
        if (N>1) {
            int temp3 = digits/10;
            temp1 = String.valueOf(digits);
            temp2 = new StringBuilder(String.valueOf(temp3));
            temp1+=temp2.reverse();
            if (isPrime(Integer.parseInt(temp1), primes)) {
                pals.add(Integer.parseInt(temp1));
            }
        } else if (isPrime(digits, primes)) {
            pals.add(digits);
        }
        if (N==0) {
            for (int d = 1; d <= 9; d+=2) {
                pals.addAll(generatePal(d, N+1, max, primes));
            }
        } else if (N<=max) {
            for (int d = 0; d <= 9; d++) {
                int temp = digits*10;
                temp+=d;
                pals.addAll(generatePal(temp, N+1, max, primes));
            }
        }
        return pals;
    }

    public static List<Integer> sieveOfEratosthenes(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static boolean isPrime(int number, List<Integer> primes) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 0; i < primes.size(); i++) {
            if (number % primes.get(i) == 0 && primes.get(i)>1 && primes.get(i) < sqrt) {
                return false;
            }
        }
        return true;
    }
}


