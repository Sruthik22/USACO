import java.util.*;
import java.io.*;

public class NoTimeForDragons {

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

        Dragon[] dragons = new Dragon[n];

        for (int i = 0; i < n; i++) {
            dragons[i] = new Dragon(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(dragons);

        long result = 0;
        long curVal = 0;

        for (int i = 0; i < n; i++) {
            if (curVal < dragons[i].ai) {
                result += dragons[i].ai - curVal;
                curVal = dragons[i].ai;
            }

            curVal -= dragons[i].bi;
        }

        pw.println(result);
        pw.close();
    }

    static class Dragon implements Comparable<Dragon>{
        int ai, bi;

        Dragon(int ai, int bi) {
            this.ai = ai;
            this.bi = bi;
        }

        @Override
        public int compareTo(Dragon o) {

            int remain1 = this.ai - this.bi;
            int remain2 = o.ai - o.bi;

            int curVal = this.ai + (remain1 < o.ai ? o.ai - remain1: 0);
            int oVal = o.ai + (remain2 < this.ai ? this.ai - remain2: 0);

            return curVal - oVal;
        }
    }
}