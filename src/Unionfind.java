import java.util.*;
import java.io.*;

public class Unionfind {

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
        int q = sc.nextInt();

        initialize(n);

        for (int i = 0; i < q; i++) {
            int t = sc.nextInt();
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (t == 0) {
                union(u, v);
            }

            else {
                // are they connected?
                if (find(u) == find(v)) pw.println(1);
                else pw.println(0);
            }
        }


        pw.close();
    }

    static int[] parent; //stores the root of each set

    static void initialize(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i; //initially, the root of each set is the node itself
        }
    }

    static int find(int x){ //finds the root of the set of x
        if(x == parent[x]){ //if x is the parent of itself, it is the root
            return x;
        }
        else{
            return parent[x] = find(parent[x]); //otherwise, recurse to the parent of x
        }
    }
    static void union(int a, int b) { //merges the sets of a and b
        int c = find(a); //find the root of a
        int d = find(b); //find the root of b
        if (c != d) {
            parent[d] = c; //merge the sets by setting the parent of d to c
        }
    }
}


