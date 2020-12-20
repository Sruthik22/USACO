import java.util.*;
import java.io.*;

public class MaximumBuildingI {

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
        int m = sc.nextInt();

        int[] row = new int[m];

        int result = 0;

        for (int i = 0; i < n; i++) {

            String stringRow = sc.next();

            for (int j = 0; j < m; j++) {
                if (stringRow.charAt(j) == '.') row[j]++;
                else row[j] = 0;
            }

            result = Math.max(result, largestRectangleArea(row));
        }

        pw.println(result);
        pw.close();
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();

        int max = 0;

        for (int i = 0; i < heights.length + 1; i++) {

            int val = 0;

            if (i != heights.length) {
                val = heights[i];
            }

            while (!stack.isEmpty()) {
                int last = heights[stack.peek()];

                if (val >= last) {
                    break;
                }

                else {
                    int index = stack.pop();
                    if (stack.size() == 0) {
                        max = Math.max(max, last * (i));
                    }

                    else {
                        int prev = stack.peek();
                        max = Math.max(max, last * (i - prev - 1));
                    }
                }
            }

            stack.push(i);
        }

        return max;
    }
}