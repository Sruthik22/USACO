import java.util.*;
import java.io.*;

public class DivGame {

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

        long n = sc.nextLong();

        HashMap<Long, Integer> prime_factor = primeFactors(n);

        int result = 0;

        for (Map.Entry<Long, Integer> entry: prime_factor.entrySet()) {
            result += quad(entry.getValue());
        }

        pw.println(result);
        pw.close();
    }

    public static HashMap<Long, Integer> primeFactors(long n)
    {
        HashMap<Long, Integer> primeFactor = new HashMap<>();

        while (n % 2 == 0) {
            primeFactor.put(2L, primeFactor.getOrDefault(2L, 0) + 1);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                primeFactor.put((long) i, primeFactor.getOrDefault((long) i, 0) + 1);
                n /= i;
            }
        }

        if (n > 2)
            primeFactor.put(n, 1);
        return primeFactor;
    }

    public static int quad(int val) {
        double a = 1, b = 1, c = - 2 * val;
        double root1, root2;

        double determinant = b * b - 4 * a * c;

        // condition for real and different roots
        if(determinant > 0) {
            root1 = (-b + Math.sqrt(determinant)) / (2 * a);
            root2 = (-b - Math.sqrt(determinant)) / (2 * a);

            return (int) Math.floor(Math.max(root1, root2));
        }
        // Condition for real and equal roots
        else if(determinant == 0) {
            root1 = root2 = -b / (2 * a);

            return (int) Math.floor(root1);
        }

        return -1;
    }
}


