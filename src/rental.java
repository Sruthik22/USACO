import java.util.*;
import java.io.*;

public class rental {

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
        sc = new InputReader(new FileInputStream("rental.in"));
        pw = new PrintWriter(new File("rental.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(sc.nextInt());
        }

        Arrays.sort(cows);

        Farm[] farms = new Farm[m];
        for (int i = 0; i < m; i++) {
            farms[i] = new Farm(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(farms);

        Neighbors[] neighbors = new Neighbors[r];
        for (int i = 0; i < r; i++) {
            neighbors[i] = new Neighbors(sc.nextInt());
        }

        Arrays.sort(neighbors);

        long[] prefix_neighbors = new long[r + 1];

        for (int i = 1; i <= r; i++) {
            prefix_neighbors[i] = prefix_neighbors[i - 1] + neighbors[i - 1].price;
        }

        long result = prefix_neighbors[Math.min(r, n)];
        int cur_farm_index = 0;
        long money_from_farm = 0;

        for (int i = 0; i < n; i++) {
            Cow cur = cows[i];

            for (int j = cur_farm_index; j < m; j++) {
                Farm cur_farm = farms[j];

                if (cur_farm.q < cur.produce) {
                    money_from_farm += (long) (cur_farm.q) * cur_farm.p;
                    cur.produce -= cur_farm.q;
                    cur_farm_index++;
                }

                else {
                    money_from_farm += (long) (cur.produce) * cur_farm.p;
                    farms[j].q -= cur.produce;
                    break;
                }
            }

            int cows_left = n - 1 - i;
            long renter_total = prefix_neighbors[Math.min(r, cows_left)];
            result = Math.max(result, money_from_farm + renter_total);
        }

        pw.println(result);

        pw.close();
    }

    static class Cow implements Comparable<Cow> {
        int produce;

        Cow(int produce) {
            this.produce = produce;
        }

        @Override
        public int compareTo(Cow o) {
            return -(this.produce - o.produce);
        }
    }

    static class Farm implements Comparable<Farm> {
        int q, p;

        Farm(int q, int p) {
            this.q = q;
            this.p = p;
        }

        @Override
        public int compareTo(Farm o) {
            if (this.p == o.p) {
                return -(this.q - o.q);
            }

            return -(this.p - o.p);
        }
    }

    static class Neighbors implements Comparable<Neighbors> {
        int price;

        Neighbors(int price) {
            this.price = price;
        }

        @Override
        public int compareTo(Neighbors o) {
            return -(this.price - o.price);
        }
    }
}