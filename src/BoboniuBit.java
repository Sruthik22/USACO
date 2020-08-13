import java.util.*;
import java.io.*;

public class BoboniuBit {

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
        int m = sc.nextInt();

        int[] as = new int[n];

        for (int i = 0; i < n; i++) {
            as[i] = sc.nextInt();
        }

        int[] bs = new int[m];

        for (int i = 0; i < m; i++) {
            bs[i] = sc.nextInt();
        }

        int result = 0;

        for (int i = 0; i < 512; i++) {

            boolean complete_flag = false;

            for (int j = 0; j < n; j++) {
                int curA = as[j];
                boolean flag = false;
                for (int k = 0; k < m; k++) {
                    int curB = bs[k];
                    if (((curA & curB) | i) == i) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    complete_flag = true;
                    break;
                }
            }

            if (complete_flag) continue;

            result = i;
            break;
        }

        pw.println(result);
        pw.close();
    }
}


