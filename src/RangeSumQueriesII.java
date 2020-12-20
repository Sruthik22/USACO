import java.util.*;
import java.io.*;

public class RangeSumQueriesII {

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
    static int[] curValues;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int q = sc.nextInt();

        SegTree segTree = new SegTree(0, n);

        curValues = new int[n];

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            segTree.update(i, value);
            curValues[i] = value;
        }

        for (int i = 0; i < q; i++) {
            int v = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (v == 1) {
                a--;
                segTree.update(a, b);
                curValues[a] = b;
            }

            if (v == 2) {
                a--;
                b--;
                pw.println(segTree.rangeQuery(a, b));
            }
        }

        pw.close();
    }

    static class SegTree {
        int leftmost, rightmost;
        long sum;
        SegTree lChild, rChild;
        public SegTree(int leftmost, int rightmost) {
            this.leftmost=leftmost;
            this.rightmost=rightmost;
            if (leftmost!=rightmost) {
                int mid=(leftmost+rightmost)/2;
                lChild=new SegTree(leftmost, mid);
                rChild=new SegTree(mid+1, rightmost);
            }
            sum = 0;
        }
        public void update(int index, long value) {
            if (index<leftmost||index>rightmost) return;
            this.sum += value - curValues[index];
            if (leftmost==rightmost) return;
            if (index<=lChild.rightmost) lChild.update(index, value);
            else rChild.update(index, value);
        }

        public long rangeQuery(int l, int r) {
            if (l<=leftmost && r>=rightmost) return this.sum;
            if (l>rightmost || r<leftmost) return 0;
            return lChild.rangeQuery(l, r) + rChild.rangeQuery(l, r);
        }

    }
}


