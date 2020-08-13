import java.util.*;
import java.io.*;

public class BoboniuChatWithDu {

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

    static InputReader sc;
    static PrintWriter pw;


    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int d = sc.nextInt();
        int m = sc.nextInt();

        int[] moods = new int[n];

        for (int i = 0; i < n; i++) {
            moods[i] = sc.nextInt();
        }

        Arrays.sort(moods);

        int minIndex = 0;

        for (int i = 0; i < n; i++) {
            if (moods[i] <= m) {
                minIndex = i + 1;
            }
            else break;
        }

        long[] prefixBig = new long[n - minIndex];

        long previous = 0;

        for (int i = n - 1; i >= minIndex; i--) {
            prefixBig[n - i - 1] = previous + moods[i];
            previous = prefixBig[n - i - 1];
        }

        long[] prefixSmall = new long[minIndex + 1];

        for (int i = minIndex - 1; i >= 0; i--) {
            prefixSmall[minIndex - i] = moods[i] + prefixSmall[minIndex - i - 1];
        }

        long funFactor = prefixSmall[minIndex];

        for (int i = 0; i < n - minIndex; i++) {
            int numDays = (i) * (d + 1) + 1;

            if (numDays > n) break;

            int remainingDays = n - numDays;

            long total = prefixBig[i] + prefixSmall[Math.min(remainingDays, minIndex)];

            funFactor = Math.max(funFactor, total);
        }

        pw.println(funFactor);
        pw.close();
    }
}


