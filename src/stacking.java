import java.util.*;
import java.io.*;

public class stacking {

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
        sc = new InputReader(new FileInputStream("stacking.in"));
        pw = new PrintWriter(new File("stacking.out"));

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] instructions = new int[n + 1];

        for (int i = 0; i < k; i++) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            instructions[a]++;
            instructions[b + 1]--;
        }

        ArrayList<Integer> arrayList = new ArrayList<>();

        int cur = 0;

        for (int i = 0; i < n; i++) {
            cur += instructions[i];
            arrayList.add(cur);
        }

        Collections.sort(arrayList);

        int result = arrayList.get(arrayList.size()/2);

        pw.println(result);
        pw.close();
    }
}