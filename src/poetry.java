import java.util.*;
import java.io.*;

public class poetry {

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
        sc = new InputReader(new FileInputStream("poetry.in"));
        pw = new PrintWriter(new File("poetry.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        HashMap<Integer, ArrayList<Word>> wordsInRhyme = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int c = sc.nextInt();

            Word w = new Word(s, c);

            if (wordsInRhyme.containsKey(c)) wordsInRhyme.get(c).add(w);
            else {
                ArrayList<Word> arr = new ArrayList<>();
                arr.add(w);
                wordsInRhyme.put(c, arr);
            }
        }

        HashMap<Character, Integer> freq = new HashMap<>();

        for (int i = 0; i < m; i++) {
            char c = sc.next().charAt(0);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int[] dp = new int[5001];

        int[] dp_condensed = new int[k + 1];

        dp_condensed[0] = 1;

        for (int i = 1; i <= k; i++) {

            int sum = 0;

            for (Map.Entry<Integer, ArrayList<Word>> entry: wordsInRhyme.entrySet()) {
                int rhyme = entry.getKey();

                for (Word j: entry.getValue()) {
                    if (i - j.length >= 0) dp[rhyme] = CPMath.add(dp[rhyme], dp_condensed[i - j.length]);
                }

                sum = CPMath.add(sum, dp[rhyme]);
            }

            if (i != k) dp = new int[k + 1];

            dp_condensed[i] = sum;
        }

        int result = 1;

        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {

            int intermediate = 0;

            for (int rhyme: wordsInRhyme.keySet()) {
                intermediate = CPMath.add(intermediate, CPMath.power(dp[rhyme], entry.getValue()));
            }

            result = CPMath.multiply(result, intermediate);
        }

        pw.println(result);
        pw.close();
    }

    static class Word {
        int length, rhyme;

        Word(int length, int rhyme) {
            this.length = length;
            this.rhyme = rhyme;
        }
    }
}


