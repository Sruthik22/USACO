import java.util.*;
import java.io.*;

public class RangeMinimumQueriesII {

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

        SegTree segTree = new SegTree(0, n);

        for (int i = 0; i < n; i++) {
            segTree.update(i, sc.nextInt());
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (a == 1) {
                b--;
                segTree.update(b, c);
            }

            if (a == 2) {
                b--;
                c--;
                pw.println(segTree.rangeQuery(b, c));
            }
        }

        pw.close();
    }

    static class SegTree {
        int leftmost, rightmost;
        long min;
        SegTree lChild, rChild;
        public SegTree(int leftmost, int rightmost) {
            this.leftmost=leftmost;
            this.rightmost=rightmost;
            if (leftmost!=rightmost) {
                int mid=(leftmost+rightmost)/2;
                lChild=new SegTree(leftmost, mid);
                rChild=new SegTree(mid+1, rightmost);
            }
            min=Long.MAX_VALUE;
        }
        public void update(int index, long value) {
            if (index<leftmost||index>rightmost) return;
            if (leftmost == rightmost) {
                this.min = value;
                return;
            }

            lChild.update(index, value);
            rChild.update(index, value);

            this.min = Math.min(lChild.min, rChild.min);
        }

        public long rangeQuery(int l, int r) {
            if (l<=leftmost && r>=rightmost) return min;
            if (l>rightmost || r<leftmost) return Long.MAX_VALUE;
            return Math.min(lChild.rangeQuery(l, r), rChild.rangeQuery(l, r));
        }

    }
}


