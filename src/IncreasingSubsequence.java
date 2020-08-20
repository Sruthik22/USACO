import java.util.*;
import java.io.*;

public class IncreasingSubsequence {

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Float.parseFloat(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class CPMath {
        static int add(int a, int b) {
            a += b;

            if (a >= mod) a -= mod;

            return a;
        }

        static int sub(int a, int b) {
            a -= b;
            if (a < 0) a += mod;
            return a;
        }

        static int multiply(int a, long b) {
            b = a * b;
            return (int) (b % mod);
        }

        static int divide(int a, int b) {
            return multiply(a, inverse(b));
        }

        static int inverse(int a) {
            return power(a, mod - 2);
        }

        static int power(int a, int b) {
            int r = 1;

            while (b > 0) {
                if (b % 2 == 1) {
                    r = multiply(r, a);
                }

                a = multiply(a, a);
                b /= 2;
            }

            return r;
        }
    }

    static InputReader sc;
    static PrintWriter pw;

    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] dp = new int[n];

        TreeSet<Pair> treeSet = new TreeSet<>(); // if something is greater and less than -- don't put in

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            if (treeSet.lower(new Pair(1, nums[i])) != null) {
                Pair p = treeSet.lower(new Pair(1, nums[i]));
                dp[i] = 1 + p.dpVal;
            }

            Pair p = new Pair(dp[i], nums[i]);

            treeSet.add(p);

            // we need to go through the treeset if there is any higher value, but with lower dp val, remove

            while (treeSet.higher(p) != null) {
                Pair tmp = treeSet.higher(p);
                if (tmp.dpVal <= p.dpVal) treeSet.remove(tmp);
                else break;
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }

        pw.println(result);
        pw.close();
    }

    static class Pair implements Comparable<Pair>{
        int dpVal, arrayVal;

        Pair(int dpVal, int arrayVal) {
            this.dpVal = dpVal;
            this.arrayVal = arrayVal;
        }

        @Override
        public int compareTo(Pair o) {
            return this.arrayVal - o.arrayVal;
        }
    }
}


