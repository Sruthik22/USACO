import java.util.*;
import java.io.*;

public class BoboniuBalls {

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

        int t = sc.nextInt();

        for (int tt = 0; tt < t; tt++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();

            if (count(r, g, b, w) <= 1) pw.println("Yes");
            else {
                if (r >= 1 && g >= 1 && b >= 1) {
                    if (count(r - 1, g - 1, b - 1, w + 3) <= 1) pw.println("Yes");
                    else pw.println("No");
                }

                else {
                    pw.println("No");
                }
            }
        }

        pw.close();
    }

    static int count(int a, int b, int c, int d) {
        int count = 0;
        if ((a & 1) == 1) count++;
        if ((b & 1) == 1) count++;
        if ((c & 1) == 1) count++;
        if ((d & 1) == 1) count++;
        return count;
    }
}


